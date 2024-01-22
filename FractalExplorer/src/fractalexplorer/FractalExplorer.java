
package fractalexplorer;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

public class FractalExplorer extends JFrame {
    
    static final int WIDTH = 1600;
    static final int HEIGHT = 900;
    
    Canvas canvas;
    BufferedImage fractalImage;
    
    static final int MAX_ITER = 500;//No subir mas de 200 peligra pc
 
    static final double DEFAULT_ZOOM = 100.0;
    static final double DEFAULT_TOP_LEFT_X = -3.0;
    static final double DEFAULT_TOP_LEFT_Y = +3.0;
    
    double zoomFactor = DEFAULT_ZOOM;
    double topLeftX = DEFAULT_TOP_LEFT_X;
    double topLeftY = DEFAULT_TOP_LEFT_Y;
    
    public FractalExplorer(){ //constructor
    setInitialGUIProperties();
    addCanvas();
    canvas.addKeyStrokeEvents();
    updateFractal();
    }
    
    private double getXPos(double x){
    return x/zoomFactor + topLeftX;
    }
    
    private double getYPos(double y){
    return y/zoomFactor - topLeftY;
    }
    
    public void updateFractal(){
    for (int x = 0; x < WIDTH; x++){
        for (int y = 0; y < HEIGHT; y++){
            
            double c_r = getXPos(x);
            double c_i = getYPos(y);
            
            int iterCount = computeIterations(c_r,c_i);
            
            int pixelColor = makeColor(iterCount);
            fractalImage.setRGB(x, y, pixelColor);
            
            }
        }
    canvas.repaint();
    }
    
    private int makeColor(int iterCount){//The most beautiful method
    int color = 0b011011100001100101101000;
    int mask = 0b000000000000010101110111;
    int shiftMag = iterCount/13;
    
    if(iterCount == MAX_ITER)
        return Color.BLACK.getRGB();
        return color | (mask << shiftMag);
    }//Makecolor
    
    private int computeIterations(double c_r, double c_i){

        double z_r = 0.0;
        double z_i = 0.0;
        int iterCount = 0;
        
        while( z_r*z_r + z_i*z_i <= 4.0){
        double z_r_tmp = z_r;
        
        z_r = z_r*z_r - z_i*z_i + c_r;
        z_i = 2*z_i*z_r_tmp + c_i;
        //Point was inside the Mandelbrot Set  
        if (iterCount >= MAX_ITER){
            return MAX_ITER;
            }
            iterCount++;
        }
        //Comple point was outside Mandelbrot Set
        return iterCount++;
    }
    
    private void moveUp(){
    double curHeight = HEIGHT/zoomFactor;
    topLeftY+=curHeight/6;
    }
    
    private void moveDown(){
    double curHeight = HEIGHT/zoomFactor;
    topLeftY-=curHeight/6;
    }
    
    private void moveLeft(){
    double curWidht = WIDTH/zoomFactor;
    topLeftX-=curWidht/6;
    }
    
    private void moveRight(){
    double curWidht = WIDTH/zoomFactor;
    topLeftX+=curWidht/6;
    }
    
    private void addCanvas(){
    canvas = new Canvas();
    fractalImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    canvas.setVisible(true);
    this.add(canvas, BorderLayout.CENTER);
    }
    
    public void setInitialGUIProperties(){
    this.setTitle("Fractal Explorer");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WIDTH,HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    }
    
    public static void main(String[] args) {//main
    new FractalExplorer();
    }
    
    private void adjustZoom(double newX,double newY, double newZoomFactor){
    topLeftX+=newX/zoomFactor;
    topLeftY-=newY/zoomFactor;
    
    zoomFactor = newZoomFactor;
    
    topLeftX-=(WIDTH/2)/zoomFactor;
    topLeftY+=(HEIGHT/2)/zoomFactor;
    
    updateFractal();
    }
    
    private class Canvas extends JPanel implements MouseListener{
    
    public Canvas(){
    addMouseListener(this);
    }    
        
    @Override public Dimension getPreferredSize(){
    return new Dimension(WIDTH, HEIGHT);
    }
    
    @Override public void paintComponent(Graphics drawingObj){
    drawingObj.drawImage(fractalImage,0,0,null);
    }//PaintComponent
    @Override public void mousePressed(MouseEvent mouse){
    double x = (double) mouse.getX();
    double y = (double) mouse.getY();
    
    switch( mouse.getButton()){
        //left
        case MouseEvent.BUTTON1:
            adjustZoom(x, y, zoomFactor*2);
            break;
        //right
        case MouseEvent.BUTTON3:
            adjustZoom(x, y, zoomFactor/2);
            break;
    }
    }
    
    public void addKeyStrokeEvents(){
    KeyStroke wKey = KeyStroke.getKeyStroke(KeyEvent.VK_W,0);
    KeyStroke aKey = KeyStroke.getKeyStroke(KeyEvent.VK_A,0);
    KeyStroke sKey = KeyStroke.getKeyStroke(KeyEvent.VK_S,0);
    KeyStroke dKey = KeyStroke.getKeyStroke(KeyEvent.VK_D,0);
    
    Action wPressed = new AbstractAction(){
        @Override public void actionPerformed(ActionEvent e){
        moveUp();
        }
      };
    Action aPressed = new AbstractAction(){
        @Override public void actionPerformed(ActionEvent e){
        moveLeft();
        }
      };
    Action sPressed = new AbstractAction(){
        @Override public void actionPerformed(ActionEvent e){
        moveDown();
        }
      };
    Action dPressed = new AbstractAction(){
        @Override public void actionPerformed(ActionEvent e){
        moveRight();
        }
      };
    
    /*this.getImputMap().put( wKey, "w_key" );
    this.getImputMap().put( aKey, "a_key" );
    this.getImputMap().put( sKey, "s_key" );
    this.getImputMap().put( dKey, "d_key" );*/
    
    this.getActionMap().put("w_key", wPressed);
    this.getActionMap().put("a_key", aPressed);
    this.getActionMap().put("s_key", sPressed);
    this.getActionMap().put("d_key", dPressed);
    }
    
    @Override public void mouseReleased(MouseEvent mouse){}
    @Override public void mouseClicked(MouseEvent mouse){}
    @Override public void mouseEntered(MouseEvent mouse){}
    @Override public void mouseExited(MouseEvent mouse){}
   }//canvas
}//FractalExplorer
