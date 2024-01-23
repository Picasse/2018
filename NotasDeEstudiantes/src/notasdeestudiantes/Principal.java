
package notasdeestudiantes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Principal {
    public int fi1a, co1umna,q = 0, tipo = 0;
    public float promedio, aprobado = 0,reprobado = 0, conta = 0;
    public float nota[] = new float[50];
    public float cel[][] = new float[50][50];
    public String nom[] = new String[50];   
    public String resul[] = new String[50];
    public String nomhist, nombre; 
            
    public void ver_tabla1(JTable jTable1){
     DefaultTableModel d = new DefaultTableModel// Esta linea permite crear un nuevo objeto de tabla
        (
                new Object[0][0], // Esta linea permite crear la cantidad de celdas que va a tener la tabla y que va a contener cada una
                new Object[0]// Permite ponerle nombre a cada columna
        ){};        
        jTable1.setModel(d);// Esta linea permite que los datos de la tabla creada anteriormente se vean en la tabla de la interfaz
        jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
        
        d.addColumn("Nombres");// Adiciona una columna con titulo de nombre
        for (int i = 1; i < co1umna+1; i++) {
            d.addColumn("Nota "+i);// Adiciona una columna con su respectivo titulo
        }
        d.setRowCount(fi1a);// Adiciona la cantidad de filas de la tabla
        jTable1.setModel(d);// Genera la grafica anteriormente creada
    }
    public void ver_tabla2(JTable jTable2){
     DefaultTableModel d = new DefaultTableModel// Esta linea permite crear un nuevo objeto de tabla
        (
                new Object[fi1a][3], // Esta linea permite crear la cantidad de celdas que va a tener la tabla y que va a contener cada una
                new Object[]{"Nombre","Promedio","Resultado"}// Permite ponerle nombre a cada columna
        ){};
        
        jTable2.setModel(d);// Esta linea permite que los datos de la tabla creada anteriormente se vean en la tabla de la interfaz
        
        jTable2.setPreferredScrollableViewportSize(jTable2.getPreferredSize());
    }
    public String mortalidad(){
        aprobado = (aprobado/fi1a)*100;// Se halla el porcentaje de los que aprobaron
        reprobado = 100-aprobado;// Se halla el porcentaje de los que reprobaron
        String estadisticas;// Se inicializa una variable tipo String
        estadisticas = "Aprobo: "+aprobado+"%, Reprobo: "+reprobado+"%";// Genera el texto de resultado
        return estadisticas;// Regresa el texto guardado dentro de la varible estadisticas
    }
    ArrayList<ArchivoCsv> notas = new ArrayList();// Se crea un objeto en el cual se van a guardar las lineas ingresadas
    public void cargarCsv() throws IOException{
                
            FileReader f = new FileReader("Notas.csv");// Esta linea selecciona el documento del cual se van a sacar los datos
            BufferedReader br = new BufferedReader(f);// Esta linea permite crear una memoria temporal
            boolean k = true;// Esta linea crea una variable que posteriormente funciona para saber en que momento se acaban los datos del documento
            int p = 0;// Inicializa la variable p el cual es un contador 
            fi1a = 0;// Inicializa la variable f1 
            co1umna = 4;// Esta linea le da valor a la variable c1
            while(k){
                String tx = br.readLine();// Esta linea lee todo el texto que se encuentra en la linea
                if (tx != null) {
                        String valores [];// Se crea un vector de tipo string
                        valores = tx.split(",");// Se guardan los valores dentro de cada posicion del vector antiriormente creado
                        nom[p] = valores[0];// Le davalor a la variable nomb en una posicion determinada 
                        cel[p][1] = Float.parseFloat(valores[1]);// Le da valor a la variable cel en una posicion determinada
                        cel[p][2] = Float.parseFloat(valores[2]);// Le da valor a la variable cel en una posicion determinada
                        cel[p][3] = Float.parseFloat(valores[3]);// Le da valor a la variable cel en una posicion determinada
                        cel[p][4] = Float.parseFloat(valores[4]);// Le da valor a la variable cel en una posicion determinada
                        p++;// Aumenta en 1 la variable p 
                        fi1a++;// Aumenta en 1 la variable f1
                }
                else
                    k = false;// Permite que el proceso de leer las lineas termine ya que no cumple la condicion del while
            }
    }
    public void ver_tabla(JTable jTable1){
     DefaultTableModel d = new DefaultTableModel// Esta linea permite crear un nuevo objeto de tabla
        (
                new Object[0][0], // Esta linea permite crear la cantidad de celdas que va a tener la tabla y que va a contener cada una
                new Object[0]// Permite ponerle nombre a cada columna
        ){};        
        jTable1.setModel(d);// Esta linea permite que los datos de la tabla creada anteriormente se vean en la tabla de la interfaz
        jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
        
        d.addColumn("Nombres");// Adiciona una columna con titulo de nombre
        for (int i = 1; i < 5; i++) {
            d.addColumn("Nota "+i);// Adiciona una columna con su respectivo titulo
        }
        d.setRowCount(fi1a);// Adiciona la cantidad de filas de la tabla
        jTable1.setModel(d);// Genera la grafica anteriormente creada
    }
}  