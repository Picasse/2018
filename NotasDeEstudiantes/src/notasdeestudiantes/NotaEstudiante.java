
package notasdeestudiantes;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class NotaEstudiante extends javax.swing.JFrame {

    
    public NotaEstudiante() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NOTAS DE LOS ESTUDIANTES");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N
        jPanel1.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Número de estudiantes");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Número de notas");

        jButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton1.setText("Generar Tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton2.setText("Historico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton3.setText("Promedio y gráfica de aprobación ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Gráfica");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Porcentaje comparativo del nivel de los alumnos");

        jButton4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton4.setText("Subir Archivo Csv");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField4)
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(204, 204, 204))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addComponent(jButton3)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    Principal cont = new Principal();
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cont.fi1a = (int) Float.parseFloat(jTextField2.getText());// Esta línea se usa para transformar el texto en un dato de tipo int
        cont.co1umna = (int) Float.parseFloat(jTextField3.getText());// Esta línea se usa para transformar el texto en un dato de tipo int
        cont.ver_tabla1(jTable1);// Esta linea genera la tabla para ingresar las notas con la cantidad de estudiantes y notas que uno desee que uno desee
        cont.tipo = 0;// Ayuda a que el programa realice las demas operaciones luego de ingresar los datos en la tabla
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (cont.tipo == 0) {
           for (int i = 0; i < cont.fi1a; i++) {
                cont.nom[i] = (String) jTable1.getValueAt(i, 0);// Guarda el nombre contenido en la casilla dentro del vector
                for (int j = 1; j < cont.co1umna+1; j++) {
                    String celda= (String) jTable1.getValueAt(i, j);// Esta linea permite guardar en una variable el texto de determinada casilla de la tabla 1
                    cont.cel[i][j] = Float.parseFloat(celda);// Esta linea permite guardar en un arreglo bidimensional el texto introducido en la tabla 1
                }
            }
        }
        cont.nomhist = jTextField1.getText();// Guarda en la variable el nombre ingresado
        for (int i = 0; i < cont.fi1a; i++) {
            if (cont.nom[i].equals(cont.nomhist)) 
            {
                for (int j = 1; j < cont.co1umna+1; j++) 
                {
                    cont.nota[j] = cont.cel[i][j];// Se guarda en el vector las notas que se van a graficar
                }
                cont.q = i;// Guarda en la varibable el numero de la fila en el cual el nombre del estudiante es igual al ingresado en el jTextField1
            }
        }
        String historico;
            if (cont.nom[cont.q].equals(cont.nomhist)){
                historico = "Historico de "+jTextField1.getText();// Se guarda en la variable el nombre de la persona de la cual se va a realizar la grafica

                XYSeries series = new XYSeries("Notas");// Se crea el objeto que se va a graficar en una grafica simple XY  
                for (int i = 1; i < cont.co1umna+1; i++) {
                    series.add(i, cont.nota[i]);// Se adicionan los datos que se van a graficar
                }
                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(series);
                
                JFreeChart chart = ChartFactory.createXYLineChart(// Se genera la grafica
                historico, // Titulo
                "Numero de nota", // Label del eje x
                "Calificacion", // Label del eje y
                dataset, // Datos que se van a graficar
                PlotOrientation.VERTICAL, // Orientacion de la grafica
                true, 
                true, 
                false 
                );
                ChartPanel pane = new ChartPanel(chart);// Se crea un nuevo panel
                add(pane);// Se adiciona el panel
                pane.setBounds(425, 120, 400, 260); // Se genera la grafica en el punto y con las dimensiones deseadas
            }
            else{
                JOptionPane.showMessageDialog(null, "¡Error!\n El nombre ingresado no es valido \n Por favor ingrese otro nombre ");
// Permite ver un mensaje de alerta en caso de que el nombre ingresado no coincida con ninguno de los que se tienen en la tabla 1
            }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (cont.tipo == 0) {
           for (int i = 0; i < cont.fi1a; i++) {
                cont.nom[i] = (String) jTable1.getValueAt(i, 0);// Guarda el nombre contenido en la casilla dentro del vector
                for (int j = 1; j < cont.co1umna+1; j++) {
                    String celda= (String) jTable1.getValueAt(i, j);// Esta linea permite guardar en una variable el texto de determinada casilla de la tabla 1
                    cont.cel[i][j] = Float.parseFloat(celda);// Esta linea permite guardar en un arreglo bidimensional el texto introducido en la tabla 1
                }
            }
        }
        
        cont.ver_tabla2(jTable2);// Esta linea genera la tabla que muestra las notas finales

        int i, j;
        cont.aprobado = 0;
        cont.reprobado = 0;
        for ( i = 0; i < cont.fi1a; i++) {
            for ( j = 1; j <= cont.co1umna+1; j++) {
                cont.conta += cont.cel[i][j];// Se realiza la suma de todas las notas
            }
            cont.promedio = cont.conta/cont.co1umna;// Se realiza la division para hallar el promedio
            if (cont.promedio < 3.0) {
                cont.resul[i] = "Reprobado";// Guarda en el vector que el estudiante reprovo
                cont.reprobado++;// Es un contador que aumenta la cantidad de personas que reprovaron
            }
            else{
                cont.resul[i] = "Aprobado";// Guarda en el vector que el estudiante aprovo
                cont.aprobado++;// Es un contador que aumenta la cantidad de personas que aprovaron
            }
            jTable2.setValueAt(cont.nom[i], i, 0);// Permite cambiar los datos contenidos en la casilla indicada
            jTable2.setValueAt(cont.promedio, i, 1);// Permite cambiar los datos contenidos en la casilla indicada
            jTable2.setValueAt(cont.resul[i], i, 2);// Permite cambiar los datos contenidos en la casilla indicada
            cont.conta = 0;
        }
        jTextField4.setText(cont.mortalidad());// Ingresa al cuadro de texto el resultado luego de hallar las estadisticas de mortalidad
        
        // Crea una grafica simple de torta
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Aprobados: "+cont.aprobado+"%", cont.aprobado);
        pieDataset.setValue("Reprobados: "+cont.reprobado+"%", cont.reprobado);

        JFreeChart chart = ChartFactory.createPieChart
        ("Estadisticas de mortalidad", // Titulo
        pieDataset,// Datos que se van a graficar
        true, 
        true, 
        false 
        );
        ChartPanel pane = new ChartPanel(chart);// Se crea un nuevo panel
        add(pane);// Se adiciona el panel
        pane.setBounds(425, 120, 400, 260); // Se genera la grafica en el punto y con las dimensiones deseadas
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            cont.cargarCsv();// Carga los datos del documento dentro de las variables
            cont.ver_tabla(jTable1);// Genera la grafica para posteriormente ingresar los datos
        } catch (IOException ex) {
            System.out.println("Error!!!");
        }
        for (int j = 0; j < cont.fi1a; j++) {
            for (int i = 1; i < 5; i++) {
            jTable1.setValueAt(cont.nom[j], j, 0);// Se adiciona el nombre de la persona en la posicion deseada 
            jTable1.setValueAt(cont.cel[j][i], j, i);// Se adiciona la nota en la posicion deseada
            }
        }
        cont.tipo = 1;// Ayuda a que el programa realice las demas operaciones luego de ingresar los datos en la tabla
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(NotaEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotaEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
