package i.m.c;

public final class Usuario {
    private String nombre;
    private String genero;
    private int edad;
    private float estatura;
    private float peso;
    public String nota;
    public String salir;
    public Usuario (String nom,String gen,int ed,float es,float pe)
    {
        Setnombre(nom);
        Setgenero(gen);
        Setedad(ed);
        Setestatura(es);
        Setpeso(pe);        
    }
public void Setnombre(String nomb){nombre=nomb;}
public String getnombre(){return nombre;}
public void Setgenero(String genr){genero=genr;}
public String getgenero(){return genero;}
public void Setedad(int años){edad= años;}
public int getedad(){return edad;}
public void Setestatura(float est){estatura=est;}
public float getestatura(){return estatura;}
public void Setpeso(float pes){peso= pes;}
public float getpeso(){return peso;}
public float imc(float a,float b){return (a/(b*b));}

public void tipoper()
{
    if(edad<=12)
        nota= "\n Niño";
        else
        {
            if(edad>=13 && edad<=18)
            {
               nota= "\n Adolescente";
            }
            else
            {
                if ( edad>=19 && edad<=60)
                {
                     nota= "\n Adulto";
                }
                else
                {
                    if(edad>60)
                    nota= "\n Adulto mayor"; 
                }
                }
            }      
}

public void cuidado()
{
  float  c = imc(peso,estatura);
     if( c>25 )
     {
            salir= "\t\n Su IMC es muy alto, no es el adecuado, por favor dirijase a un especialista para tratar el caso \n";
     }
        else 
        {
                    if(c<18)
            {
                salir= "\t\n Su IMC es muy bajo, no es el adecuado, por favor dirijase a un especialista para tratar el caso \n";
            }
            else
                    {
                        salir= "\t\n Su IMC es adecuado, no necesita consultar a un especialista \n";
                    }
        }
    }
}

