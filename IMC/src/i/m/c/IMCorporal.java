package i.m.c;
import java.util.Scanner;//Me permite leer

public class IMCorporal {
  
    public static void main(String[] args)
    {
        Scanner Sc = new Scanner(System. in);//Objeto dela clase Scanner que permite leer variables
        Usuario Us1;
        Us1 = new Usuario("Santiago","masculino",3,5,2);
        for(int i=0;i<=20;i++)
        {
        System.out.println("Digite su nombre");
        Us1.Setnombre(Sc.next());
        System.out.println("Digite su genero");
        Us1.Setgenero(Sc.next());
        System.out.println("Digite su edad");  
        Us1.Setedad (Sc.nextInt());// Metodo de la clase scanner que permite leer enteros 
        System.out.println("Digite su peso");
        Us1.Setpeso(Sc.nextInt());
        System.out.println("Digite su estatura");
        Us1.Setestatura(Sc.nextFloat());//Metodo de la clase scanner que permite leer enteros
        System.out.println("\n\n\n I.M.C es: ");
        System.out.print(Us1.imc(Us1.getpeso(), Us1.getestatura()));
        Us1.tipoper();
        System.out.print(Us1.nota);
        Us1.cuidado();
        System.out.println("n");
        System.out.println(Us1.salir);

       i=i+1;
  
       }
     } 
  }
    




