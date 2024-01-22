
package EYEs;

import java.awt.Color;
import java.awt.Graphics;


public class eggs extends javax.swing.JPanel {

    
    public eggs() {
        initComponents();
    }
    
int X = 0; 
int Y = 0;

    public void paintComponent(Graphics S){
    super.paintComponent(S);
    
    S.setColor(Color.white);
    S.fillOval(100, 100, 80, 120);
    S.setColor(Color.BLACK);
    S.drawOval(100, 100, 80, 120);
    
    S.setColor(Color.white);
    S.fillOval(180, 100, 80, 120);
    S.setColor(Color.BLACK);
    S.drawOval(180, 100, 80, 120);
    
    S.setColor(Color.black);
    S.fillOval(130+X,150+Y, 20, 20);
    
    S.setColor(Color.black);
    S.fillOval(210+X,150+Y, 20, 20);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
