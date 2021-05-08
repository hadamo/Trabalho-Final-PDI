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

    /**
     * Método para detectar pixels de lábios em imagens de rosto.
     * @author Hádamo Egito
     * @param img : BufferedImage para detecção
     * @return workingImage: BufferedImage onde pixels de cor mais próximo da cor 
     *         branca são os com maior probabilidade de serem pixels de lábios
     */
    public BufferedImage LipDetector(BufferedImage img) {
        //Criação de imagem para armazenar resultados e de propriedades da imagem
        BufferedImage workingImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();

        //Variaveis para guardar valores de canais de cores YCbCr
        float[][] Y = new float[imgWidth][imgHeight];
        float[][] Cb = new float[imgWidth][imgHeight];
        float[][] Cr = new float[imgWidth][imgHeight];

        //Variaveis para os canais Cr² e Cr/Cb
        float[][] Cr2 = new float[imgWidth][imgHeight];
        float[][] Crcb = new float[imgWidth][imgHeight];

        //Variaveis para as partes da fórmula isLipcolor
        float somatorioCr2 = 0;
        float somatorioRazaoCrCb = 0;
        float n;

        //Conversão para YCbCr 
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                //Conversão para YCbCr
                Color color = new Color(img.getRGB(i, j));
                float[] convertedColor = this.RGBtoYCrCb(color);

                //Armazenando resultados de conversão
                Y[i][j] = convertedColor[0];
                Cb[i][j] = convertedColor[1];
                Cr[i][j] = convertedColor[2];
            }
        }

        //Cálculo de  Cr² e Cr/Cb e de seus mínimos e máximos para normalização
        float minCr2 = 0, minCrCb = 0, maxCr2 = 0, maxCrCb = 0;
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                float cb = Cb[i][j];
                float cr = Cr[i][j];

                // cb = Normalize(cb, maxCb, minCb, 0, 255);
                // cr = Normalize(cr, maxCr, minCr, 0, 255);

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
                    if (cr2 > maxCr2)
                        maxCr2 = cr2;
                    if (crcb > maxCrCb)
                        maxCrCb = crcb;
                    if (cr2 < minCr2)
                        minCr2 = cr2;
                    if (crcb < minCrCb)
                        minCrCb = crcb;
                }
            }
        }

        //calculo de somatório de valores normalizados
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

        //Cálculo da componente n da fórmula de detecção
        n = (float) 0.95 * (somatorioCr2 / somatorioRazaoCrCb);

        //Variáveis para guardar valores máximos e mínimos das cores 
        //encontradas na detecção para cada pixel para normalizar
        float[][] cores = new float[imgWidth][imgHeight];
        float maxCor = 0, minCor = 0;

        //Cálculo de cor resultante da detecção e de mínimos e máximos para normalizar o resultado
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {

                float cr2 = Cr2[i][j];
                float crcb = Crcb[i][j];

                //Cálculo dos componentes da função de detecção
                // de Cr² * (Cr² - n * Cr/Cb)²
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

                // Salvando a cor com resultado da detecção para normalização
                cores[i][j] = cor;
            }
        }

        //Normalizar cores e conversão para rgb em níveis de cinza
        //onde quanto mais próximo de 255 mais provável que o pixel seja de lábio
        //e quanto mais próximo de 0 mais provável que não seja.
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                int cor = (int) Normalize(cores[i][j] , maxCor, minCor, 0, 255);
                Color novo = new Color(cor, cor, cor);
                //salva a cor no pixel da imagem resultante
                workingImage.setRGB(i, j, novo.getRGB());
            }
        }

        return workingImage;
    }

    /**
     * Método para normalizar valor através do intervalo 
     * de mínimos e máximos originais e de mínimos e máximos desejados
     * @param value
     * @param max
     * @param min
     * @param intervalMin
     * @param inertervalMax
     * @return valor normalizado : float
     */
    public float Normalize(float value, float max, float min, float intervalMin, float inertervalMax) {
        return ((value - min) / (max - min)) * (inertervalMax - intervalMin) + intervalMin;
    }

    /**
     * Método para transformar cor de pixel rgb para ycbcr
     * @param pixel : Color pixel com cor em RGB
     * @return yCbCr : float[3] vetor de float com os 3 canais YCbCr
     */
    public float[] RGBtoYCrCb(Color pixel) {
        //Recuperar os 3 canais de cor do pixel RGB
        float r = pixel.getRed();
        float g = pixel.getGreen();
        float b = pixel.getBlue();

        float[] yCbCr = new float[3];
        float y, cb, cr;

        //Conversão para os três canais Y CB CR
        y = (float) ((0.229 * r) + (0.587 * g) + (0.144 * b));
        cb = (float) ((0.168 * r) - (0.3313 * g) + (0.5 * b)) + 128;
        cr = (float) ((0.5 * r) - (0.4187 * g) - (0.0813 * b)) + 128;

        yCbCr[0] = y;
        yCbCr[1] = cb;
        yCbCr[2] = cr;

        return yCbCr;
    }
}
