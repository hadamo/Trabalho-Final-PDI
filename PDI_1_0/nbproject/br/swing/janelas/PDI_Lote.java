/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.janelas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import implementacoes.Greenness;
import implementacoes.LipDetection;
import infraBasica_conflito.ManipulaArquivo;
import interfaces.ImageInterface;
import swing.JMainFrame;
import pdi.PDIKmeansGray;

/**
 * NESTE PROGRAMA, A LINHA DO HORIZONTE É DECTEDADA COM AS FORMULAS WHITENESS
 * PORÉM, AS IMAGENS SÃO SALVAS NO CAMINHO ESCOLHIDO SEM SER SEPARADAS POR
 * FORMULAS
 *
 *
 */
/**
 * @author Flavia Mattos
 */
public class PDI_Lote extends javax.swing.JFrame {

    private static JMainFrame mainFrame2;
    ImageInterface canvas, canvas1, canvas2, canvas3, canvas4, canvas5, canvas6, canvas7, canvas8, canvas9, canvas0;
    String pastaSalvar = "";
    String Nome;

    /**
     * Creates new form ProcessaWhiteness
     *
     * @param mainFrame
     */
    public PDI_Lote(JMainFrame mainFrame) {
        mainFrame2 = mainFrame;
        initComponents();
    }

    private PDI_Lote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CaminhoImagemOriginal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ImagemOriginal = new javax.swing.JButton();
        CaminhoSaidaImagem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SaidaImagem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tecnica01 = new javax.swing.JCheckBox();
        Processar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setResizable(false);

        CaminhoImagemOriginal.setEditable(false);
        CaminhoImagemOriginal.setText("C:\\Users\\hadam\\Google Drive\\Ufes\\2020\\pdi\\Trabalho Final\\DatasetRostos");
        CaminhoImagemOriginal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaminhoImagemOriginalActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Processamento de Imagens em Lote");

        jLabel2.setText("Escolha a Pasta para Processamento");

        ImagemOriginal.setText("Escolher a Pasta");
        ImagemOriginal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImagemOriginalActionPerformed(evt);
            }
        });

        CaminhoSaidaImagem.setEditable(false);
        CaminhoSaidaImagem.setText("C:\\Users\\hadam\\Google Drive\\Ufes\\2020\\pdi\\Trabalho Final\\Saida");;
        CaminhoSaidaImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaminhoSaidaImagemActionPerformed(evt);
            }
        });

        jLabel3.setText("Escolha a Pasta de Saida");

        SaidaImagem.setText("Escolher a Pasta");
        SaidaImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaidaImagemActionPerformed(evt);
            }
        });

        jLabel5.setText("Qual (s):");

        tecnica01.setText("Detectar Labios");
        tecnica01.setSelected(true);
        tecnica01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tecnica01ActionPerformed(evt);
            }
        });

        Processar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Processar.setText("Processar");
        Processar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcessarActionPerformed(evt);
            }
        });

        jLabel6.setVisible(false);
        jLabel6.setText("Processado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(CaminhoImagemOriginal)
                            .addComponent(CaminhoSaidaImagem))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ImagemOriginal)
                            .addComponent(SaidaImagem))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(tecnica01)))
                        .addGap(80, 210, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(Processar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CaminhoImagemOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImagemOriginal))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CaminhoSaidaImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaidaImagem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tecnica01)
                    .addComponent(jLabel5))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Processar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(261, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CaminhoSaidaImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaminhoSaidaImagemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaminhoSaidaImagemActionPerformed

    private void CaminhoImagemOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaminhoImagemOriginalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaminhoImagemOriginalActionPerformed

    private void tecnica01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tecnica01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tecnica01ActionPerformed

    private void ProcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcessarActionPerformed
        // PROCESSAMENTO         
        jLabel6.setVisible(false);

        try {
            //VERIFICA SE O CAMINHO SELECIONADO DE IMAGEM E SAIDA EXISTEM
            jLabel6.setVisible(false);
            if (!CaminhoImagemOriginal.getText().isEmpty() && !CaminhoSaidaImagem.getText().isEmpty()) {
                File fileOriginal = new File(CaminhoImagemOriginal.getText());
                File[] pathsOriginal;
                //NOME DAS IMAGENS SELECIONADAS PARA PROCESSAMENTO
                pastaSalvar = CaminhoSaidaImagem.getText();
                pathsOriginal = fileOriginal.listFiles();
                //FILE SEPARATOR: CORRESPONDENTE A SEPARA��O DE PASTA DO SISTEMA OPERACIONAL UTILIZADO
                //EXEMPLO: WINDOWS (\\) , LINUX E MAC (//)
                pastaSalvar += File.separator;

                if (!Paths.get(pastaSalvar).toFile().exists()) {
                    (new File(pastaSalvar)).mkdir();
                }
                //EXEMPLO DE T�CNICA QUE PRECISA DE UM PARAMETRO DE ENTRADA....
                if (tecnica01.isSelected()) {
                    for (File pathtecnica01 : pathsOriginal) {
                        String imagevariavelK = pathtecnica01.getName();
                        //DIVISAO DO NOME DA IMAGEM SEM SUA EXTENS�O PARA COMPAR��O FUTURA
                        imagevariavelK = imagevariavelK.substring(0, (imagevariavelK.length() - 4));
                        BufferedImage imgvariavelK = ImageIO.read(pathtecnica01);
                        //Cria imagem resultante
                        BufferedImage resvariavelK, outvariavelK = new BufferedImage(imgvariavelK.getWidth(), imgvariavelK.getHeight(), imgvariavelK.getType());
                        //CRIA OBJETO DA CLASSE 
                        //Neste exemplo, utiliza-se a t�cnica que est� na classe Greeness
                        // Greenness WA = new Greenness();
                        LipDetection LP = new LipDetection();
                        //IMAGEM RESULTANTE DA FORMULA
                        // ----- APLICACAO DA TECNICA -------
                        // resvariavelK = WA.GreennKG(imgvariavelK);
                        resvariavelK = LP.LipDetector(imgvariavelK);
                        //Nome que vai no nome do arquivo para identificar t�cnica.
                        Nome = "_LipMap";
                        //SAIDA CONTENDO CAMINHO DA IMAGEM + NOME DA IMAGEM
                        String aSaida = pastaSalvar + imagevariavelK + Nome + ".png";
                        File outputFile = new File(aSaida);
                        //SALVA A IMAGEM
                        ImageIO.write(resvariavelK, "png", outputFile);
                    }
                }                
            } else {
                JOptionPane.showMessageDialog(mainFrame2, "Falha");
            }

            jLabel6.setVisible(true);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_ProcessarActionPerformed

    private void SaidaImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaidaImagemActionPerformed
        // TODO add your handling code here:
        ManipulaArquivo ma = new ManipulaArquivo();
        String caminho = ma.selecionarDiretorio("Selecione a Pasta para Processamento");
        CaminhoSaidaImagem.setText(caminho);
    }//GEN-LAST:event_SaidaImagemActionPerformed

    private void ImagemOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImagemOriginalActionPerformed
        // TODO add your handling code here:
        ManipulaArquivo ma = new ManipulaArquivo();
        String caminho = ma.selecionarDiretorio("Selecione a Pasta para Processamento");
        CaminhoImagemOriginal.setText(caminho);
    }//GEN-LAST:event_ImagemOriginalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PDI_Lote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PDI_Lote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CaminhoImagemOriginal;
    private javax.swing.JTextField CaminhoSaidaImagem;
    private javax.swing.JButton ImagemOriginal;
    private javax.swing.JButton Processar;
    private javax.swing.JButton SaidaImagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JCheckBox tecnica01;
    // End of variables declaration//GEN-END:variables
}
