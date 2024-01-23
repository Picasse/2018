
package notasdeestudiantes;

public class ArchivoCsv {
private String nombre;
private float nota1;
private float nota2;
private float nota3;
private float nota4;
private float nota5;

    public ArchivoCsv() {
    }

    public ArchivoCsv(String nombre, float nota1, float nota2, float nota3, float nota4, float nota5) {
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.nota5 = nota5;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getNota1() {
        return nota1;
    }
    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }
    
    public float getNota2() {
        return nota2;
    }
    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }
    
    public float getNota3() {
        return nota3;
    }
    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float getNota4() {
        return nota4;
    }
    public void setNota4(float nota4) {
        this.nota4 = nota4;
    }    
}
