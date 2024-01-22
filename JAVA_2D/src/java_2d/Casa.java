
package java_2d;

import java.awt.Color;
import java.awt.Graphics;

public class Casa extends javax.swing.JPanel {

    public Casa() {
        initComponents();
    }

 public void paintComponent(Graphics g)
{
    Color cafe = new Color(100,202,85);
    
super.paintComponent(g);
g.setColor(Color.red);
g.fillRect(2,2,100,100);
g.setColor(Color.blue);
g.fillRect(15,15,100,100);


g.setColor(cafe);
g.fillRect(2,2,100,100);
g.setColor(Color.yellow);
g.fillRect(2,15,100,100);
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
