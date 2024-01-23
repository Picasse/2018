
package r;



public class Resistencia {
    //public int vector[] = {0,0,0} ;
    public double vector []=new double [5];
    public String resultado[] = new String[1];
    private double band1;
    private double band2;
    private double band3;
    private String tolerancia;
    private String total;

   
    
    public void Setband1(double b1){band1=b1;}

    public void Setband2(double b2){band2=b2;}
    
    public void Setband3(double b3){band3=b3;}
    
    public void SetTolerancia(String t){tolerancia=t;}
    
    public String getTolerancia(){return tolerancia;}
    
    public String getTotal(){return total;}
    
    public String[] getResultado(){return resultado;}
    
    
    public void SetVector(){
    vector[0]=band1;
    vector[1]=band2;
    vector[2]=band3;
    vector[3]=0;
    vector[4]=0;
    double k = (vector[0]+vector[1])*(vector[2]);
    String w = Double.toString(k);
    total = w;
    }

    public static void main(String[] args) {}
    
}
