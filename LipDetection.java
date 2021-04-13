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

        float minCor = -1;
        float maxCor = 0;

        float[][] Y = new float[imgWidth][imgHeight];
        float[][] Cb = new float[imgWidth][imgHeight];
        float[][] Cr = new float[imgWidth][imgHeight];
        float[][] corFinal = new float[imgWidth][imgHeight];

        // int[][] isLipColor = new int[ imgWidth ][ imgHeight ];

        float somatorioCr2 = 0;
        float somatorioRazaoCrCb = 0;
        float n;

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                Color color = new Color(img.getRGB(i, j));
                float[] convertedColor = this.RGBtoYCrCb(color);

                float cb = convertedColor[1];
                float cr = convertedColor[2];

                Y[i][j] = convertedColor[0];

                Cb[i][j] = cb;
                Cr[i][j] = cr;

                somatorioCr2 += cr * cr;
                somatorioRazaoCrCb += cr / cb;

                
            }
        }

        n = (float) 0.95 * (somatorioCr2 / somatorioRazaoCrCb);


        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {

                float cr = Cr[i][j];
                float cr2 = cr * cr;
                float cb = Cb[i][j];

                float cr2ncrcb = (cr2 - n * (cr / cb));
                float cor = cr2 * cr2ncrcb * cr2ncrcb;
                corFinal[i][j] = cor;
                minCor = GetMin(minCor, cor);
                maxCor = GetMax(maxCor, cor);

                // Color novo = new Color(cor, cor, cor);

                // workingImage.setRGB(i, j, novo.getRGB());
            }
        }

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                float cor = corFinal[i][j];
                int corNormalizada = (int) ( (cor - minCor)/(maxCor - minCor) ) * (255 - 0) + 0;
                Color novo = new Color(corNormalizada, corNormalizada, corNormalizada);
                workingImage.setRGB(i, j, novo.getRGB());
            }
        }


        return workingImage;
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

    public float GetMin(float min, float newValue) {
        if (min < 0 || min > newValue) {
            return newValue;
        } 
        return min;
    }

    public float GetMax(float max, float newValue) {
        if (max > newValue) {
            return max;
        } 
        return newValue;
    }
}
