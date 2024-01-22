
package java3d;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class JAVA3D {

    SimpleUniverse mundo = new SimpleUniverse();//creacion del universo
    BranchGroup grupo = new BranchGroup();// creacion del grupo
    ColorCube cubol = new ColorCube(0.1);// figura a graficar
    ColorCube cubo2 = new ColorCube(0.1);
    TransformGroup TG = new TransformGroup();//para el grupo de transformaciones
    Transform3D T3D = new Transform3D();//modificar el objeto
     TransformGroup TG2 = new TransformGroup();//para el grupo de transformaciones
    Transform3D T3D2 = new Transform3D();//modificar el objeto
    
    public JAVA3D(){// creacion del constructor que inicializa la escena 
        mundo.getViewingPlatform().setNominalViewingTransform();// distancia predeterminada
        T3D.setTranslation(new Vector3d(0.5f,0.1f,0.9f));       
        TG.setTransform(T3D);
        TG.addChild(cubol);
        grupo.addChild(TG);//relacion padre e hijo grupo padre de cubo      
        
        T3D2.rotX(Math.PI/2);
        TG2.setTransform(T3D2);
        TG2.addChild(cubo2);
        grupo.addChild(TG2);
        
        mundo.addBranchGraph(grupo);//relacion entre el mundo y el subgrafo de escena   
       
    }
    
    public static void main(String[] args) {
        new JAVA3D();
    }
}
