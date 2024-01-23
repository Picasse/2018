
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GuiBotones extends javax.swing.JFrame {
    int acumulador1 = 0;
    int acumulador2 = 0;
    int acumulador3 = 0;
    int acumulador4 = 0;
    int acumulador5 = 0;
    int acumulador6 = 0;
    int acumulador7 = 0;
    int acumulador8 = 0;
    int velocidad;
    int popo;
    
    
    Timer T1 =  new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion1();}});
    Timer T2 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion2();}});
    Timer T3 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion3();}});
    Timer T4 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion4();}});
    Timer T5 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion5();}});
    Timer T6 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion6();}});
    Timer T7 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion7();}});
    Timer T8 = new Timer(100, new ActionListener(){public void actionPerformed(ActionEvent e){funcion8();}});

    
    
    
    public void funcion1()
{
    popo = 1;
    //Mostrar el tiempo
    acumulador1++;
    jLabel1.setText(String.valueOf(acumulador1));
    jButton1.setBackground(Color.yellow);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador1==velocidad){
    T1.stop();
    acumulador2 = 0;
    T2.start();
    }
}
    public void funcion2(){
    popo = 2;
    acumulador2++;
    jLabel1.setText(String.valueOf(acumulador2));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.yellow);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador2==velocidad){
    T2.stop();
    acumulador3 = 0;
    T3.start();
    }
    }
    
    public void funcion3(){
    popo = 3;
    acumulador3++;
    jLabel1.setText(String.valueOf(acumulador3));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.yellow);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador3==velocidad){
    T3.stop();
    acumulador4 = 0;
    T4.start();
    }
    }
    
    public void funcion4(){
    popo = 4;
    acumulador4++;
    jLabel1.setText(String.valueOf(acumulador4));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.yellow);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador4==velocidad){
    T4.stop();
    acumulador5 = 0;
    T5.start();
    }
    }
    public void funcion5(){
    popo = 5;
    acumulador5++;
    jLabel1.setText(String.valueOf(acumulador5));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.yellow);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador5==velocidad){
    T5.stop();
    acumulador6 = 0;
    T6.start();
    }
    }
    public void funcion6(){
    popo = 6;
    acumulador6++;
    jLabel1.setText(String.valueOf(acumulador6));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.yellow);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador6==velocidad){
    T6.stop();
    acumulador7 = 0;
    T7.start();
    }
    }
    public void funcion7(){
    popo = 7;
    acumulador7++;
    jLabel1.setText(String.valueOf(acumulador7));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.yellow);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    if(acumulador7==velocidad){
    T7.stop();
    acumulador8 = 0;
    T8.start();
    }
    }
    public void funcion8(){
    popo = 8;
    acumulador8++;
    jLabel1.setText(String.valueOf(acumulador8));
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.yellow);
    jButton9.setBackground(Color.white);
    if(acumulador8==velocidad){
    T8.stop();
    acumulador1=0;
    T1.start();
    }
    }
    
    
    
    
    public GuiBotones() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Empezar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Pausar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(24, 24, 24));
        jLabel1.setFont(new java.awt.Font("Charlemagne Std", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText(" ");

        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(10);
        jSlider1.setMinimum(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jButton11.setText("Resetear");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton2))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton6)))
                                    .addComponent(jButton8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(jButton4)
                                    .addComponent(jButton3)))
                            .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)
                        .addGap(196, 196, 196))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton8)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(23, 23, 23)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    T1.start();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (popo == 1){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if (popo == 2){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if (popo == 3){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    if (popo == 4){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    if (popo == 5){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    if (popo == 6){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    if (popo == 7){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    if (popo == 8){
    T1.stop();
    T2.stop();
    T3.stop();
    T4.stop();
    T5.stop();
    T6.stop();
    T7.stop();
    T8.stop();
    jLabel1.setText("Ganó");
    }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
    velocidad = jSlider1.getValue();
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        acumulador1 = 0;
        acumulador2 = 0;
        acumulador3 = 0;
        acumulador4 = 0;
        acumulador5 = 0;
        acumulador6 = 0;
        acumulador7 = 0;
        acumulador8 = 0;
     
        T1.stop();
        T2.stop();
        T3.stop();
        T4.stop();
        T5.stop();
        T6.stop();
        T7.stop();
        T8.stop();
        
        jLabel1.setText("");
        
    jButton1.setBackground(Color.white);
    jButton2.setBackground(Color.white);
    jButton3.setBackground(Color.white);
    jButton4.setBackground(Color.white);
    jButton5.setBackground(Color.white);
    jButton6.setBackground(Color.white);
    jButton7.setBackground(Color.white);
    jButton8.setBackground(Color.white);
    jButton9.setBackground(Color.white);
    }//GEN-LAST:event_jButton11ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiBotones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiBotones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiBotones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiBotones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiBotones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
