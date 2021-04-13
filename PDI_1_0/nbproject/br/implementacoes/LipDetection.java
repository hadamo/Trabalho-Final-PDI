package implementacoes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import swing.janelas.PDI_Lote;

/**
 * Implementação de detecção de lábios em imagens
 * 
 * @author Hádamo da Silva Egito 2014204411
 */
public class LipDetection {

    public LipDetection() {
    }

    public BufferedImage LipDetector(BufferedImage img) {
        BufferedImage workingImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        int numPixels = imgHeight * imgWidth;

        float minCr = 0, minCb = 0, maxCr = 0, maxCb = 0;

        float normalizeMin = 0;
        float normalizeMax = 255;

        float[][] Y = new float[imgWidth][imgHeight];
        float[][] Cb = new float[imgWidth][imgHeight];
        float[][] Cr = new float[imgWidth][imgHeight];

        float[][] Cr2 = new float[imgWidth][imgHeight];
        float[][] Crcb = new float[imgWidth][imgHeight];

        float[][] corFinal = new float[imgWidth][imgHeight];

        float somatorioCr2 = 0;
        float somatorioRazaoCrCb = 0;
        float n;

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                Color color = new Color(img.getRGB(i, j));
                float[] convertedColor = this.RGBtoYCrCb(color);

                float cb = convertedColor[1];
                float cr = convertedColor[2];

                if (i == 0 && j == 0) {
                    minCr = cr;
                    minCb = cb;

                    maxCr = cr;
                    maxCb = cb;
                } else {
                    if (cr > maxCr)
                        maxCr = cr;
                    if (cb > maxCb)
                        maxCb = cb;
                    if (cr < minCr)
                        minCr = cr;
                    if (cb < minCb)
                        minCb = cb;
                }

                Y[i][j] = convertedColor[0];
                Cb[i][j] = cb;
                Cr[i][j] = cr;
            }
        }

        // NORMALIZAR

        float minCr2 = 0, minCrCb = 0, maxCr2 = 0, maxCrCb = 0;
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                float cb = Cb[i][j];
                float cr = Cr[i][j];

                cb = Normalize(cb, maxCb, minCb, 0, 255);
                cr = Normalize(cr, maxCr, minCr, 0, 255);

                float cr2 = cr * cr;
                float crcb = cr / (cb + 1);

                Cb[i][j] = cb;
                Cr[i][j] = cr;

                Crcb[i][j] = crcb;
                Cr2[i][j] = cr2;

                if (i == 0 && j == 0) {
                    minCr2 = cr2;
                    minCrCb = crcb;

                    maxCr2 = cr2;
                    maxCrCb = crcb;
                } else {
                    if (cr > maxCr2)
                        maxCr2 = cr2;
                    if (cb > maxCrCb)
                        maxCrCb = crcb;
                    if (cr < minCr2)
                        minCr2 = cr2;
                    if (cb < minCrCb)
                        minCrCb = crcb;
                }
                // somatorioCr2 += cr * cr;
                // somatorioRazaoCrCb += cr / cb;
            }
        }

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                float crcb = Crcb[i][j];
                float cr2 = Cr2[i][j];

                crcb = Normalize(crcb, maxCrCb, minCrCb, 0, 255);
                cr2 = Normalize(cr2, maxCr2, minCr2, 0, 255);
                Crcb[i][j] = crcb;
                Cr2[i][j] = cr2;

                somatorioCr2 += cr2;
                somatorioRazaoCrCb += crcb;

            }
        }

        n = (float) 0.95 * (somatorioCr2 / somatorioRazaoCrCb);

        float[][] cores = new float[imgWidth][imgHeight];
        float maxCor = 0, minCor = 0;

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {

                float cr2 = Cr2[i][j];
                float crcb = Crcb[i][j];

                float cr2ncrcb = (cr2 - n * (crcb));
                float cor = cr2 * cr2ncrcb * cr2ncrcb;

                if (i == 0 && j == 0) {
                    minCor = cor;
                    maxCor = cor;
                } else {
                    if (cor > maxCor)
                        maxCor = cor;
                    if (cor < minCor)
                        minCor = cor;
                }

                cores[i][j] = cor;

            }
        }

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                int cor = (int) Normalize(cores[i][j] , maxCor, minCor, 0, 255);
                Color novo = new Color(cor, cor, cor);
                workingImage.setRGB(i, j, novo.getRGB());
            }
        }

        return workingImage;
    }

    public float Normalize(float value, float max, float min, float intervalMin, float inertervalMax) {
        return ((value - min) / (max - min)) * (inertervalMax - intervalMin) + intervalMin;
    }

    public float[] RGBtoYCrCb(Color pixel) {
        float r = pixel.getRed();
        float g = pixel.getGreen();
        float b = pixel.getBlue();

        float[] yCbCr = new float[3];
        float y, cb, cr;

        y = (float) ((0.229 * r) + (0.587 * g) + (0.144 * b));
        cb = (float) ((0.168 * r) - (0.3313 * g) + (0.5 * b)) + 128;
        cr = (float) ((0.5 * r) - (0.4187 * g) - (0.0813 * b)) + 128;

        yCbCr[0] = y;
        yCbCr[1] = cb;
        yCbCr[2] = cr;

        return yCbCr;
    }
}
