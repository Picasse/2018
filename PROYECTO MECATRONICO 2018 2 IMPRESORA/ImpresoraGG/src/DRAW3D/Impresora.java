/*
 * Ejemplo1.java
 *
 * Created on 29 de septiembre de 2008, 09:30 AM
 */
package DRAW3D;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GraphicsConfiguration;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;
import panamahitek.Arduino.PanamaHitek_Arduino;


public class Impresora extends javax.swing.JApplet {
    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    private SimpleUniverse simpleU=null;
    private TransformGroup obj1,  obj2, obj3;
    
public static void delaySegundo(){try{Thread.sleep(1000);}catch(InterruptedException e){}}//esperar 1 segundo

    /** Initializes the applet Ejemplo1 */
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
                    Canvas3D canvas3D = new Canvas3D(config);

                    jPanel2.add("Center", canvas3D);

                    BranchGroup scene = createSceneGraph();
                    scene.compile();

                    simpleU = new SimpleUniverse(canvas3D);
                    simpleU.getViewingPlatform().setNominalViewingTransform();
                    simpleU.addBranchGraph(scene);

                    try {
                        arduino.arduinoTX("COM4", 9600);
                    } catch (Exception ex) {
                        Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (InterruptedException ex) {
        } catch (InvocationTargetException ex) {
        }
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        //Inicializar objetos
        obj1 = new TransformGroup();
        obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        obj2 = new TransformGroup();
        obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        obj3 = new TransformGroup();
        obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        obj1.addChild(loadGeometryWRL("pieza1.wrl"));
        objRoot.addChild(obj1);
       
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj1.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0f, .0f, 1.8f);
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj1.setTransform(transformin4);
        
        obj2.addChild(loadGeometryWRL("base.wrl"));
        obj1.addChild(obj2);
        
        Transform3D transformin44 = new Transform3D();
        Transform3D transformin144 = new Transform3D();
        obj2.getTransform(transformin44);
        Vector3f vector44 = new Vector3f(0.02f, -0.075f, 0.01f);
        transformin144.setTranslation(vector44);
        transformin44.mul(transformin144);
        obj2.setTransform(transformin44);
        
        
        obj3.addChild(loadGeometryWRL("servo.wrl"));
        obj1.addChild(obj3);
        
        Transform3D a = new Transform3D();
        obj3.getTransform(a);//guarda
        Transform3D inc3 = new Transform3D();
        inc3.rotY(Math.PI/2);//guarda en 2
        a.mul(inc3);//multiplica
        obj3.setTransform(a);
        
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(-0.001f, 0.0021f, -0.037f);
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5);
        

        
        
        //luces
        objRoot.addChild(luces());

        //fondo
        objRoot.addChild(fondo());

        //piso
        objRoot.addChild(piso());
        
        return objRoot;

    }

    public BranchGroup loadGeometryWRL(String geometryURL) {
        BranchGroup objLoad = new BranchGroup();

        VrmlLoader wrl = new VrmlLoader();
        try {
            objLoad = wrl.load(geometryURL).getSceneGroup();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ParsingErrorException ex) {
            ex.printStackTrace();
        } catch (IncorrectFormatException ex) {
            ex.printStackTrace();
        }
        return objLoad;
    }

    private TransformGroup fondo() {
        TransformGroup objRoot = new TransformGroup();
        Background font = new Background(new Color3f(0.5f, 0.6f, 0.9f));
        font.setApplicationBounds(new BoundingSphere(new Point3d(), 100.0));
        objRoot.addChild(font);
        return objRoot;
        
        
    }

    private TransformGroup luces() {
        TransformGroup objRoot = new TransformGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 5), 100.0);
        Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f light1Direction = new Vector3f(0.0f, -1.0f, -1f);

        DirectionalLight luz1 = new DirectionalLight(lightColor, light1Direction);
        luz1.setInfluencingBounds(bounds);
        objRoot.addChild(luz1);

        AmbientLight luz2 = new AmbientLight(lightColor);
        luz2.setInfluencingBounds(bounds);
        objRoot.addChild(luz2);
        
        return objRoot;
        
    }

    private TransformGroup piso() {
        TransformGroup sueloTransf = new TransformGroup();

        int tamano = 100;
        Point3f[] vertices = new Point3f[tamano * tamano];

        float inicio = -20.0f;
        float x = inicio;
        float z = inicio;

        float salto = 1.0f;

        int[] indices = new int[(tamano - 1) * (tamano - 1) * 4];
        int n = 0;

        Color3f blanco = new Color3f(0.1f, 0.1f, 0.3f);
        Color3f negro = new Color3f(0.5f, 0.0f, 0.0f);
        Color3f[] colors = {blanco, negro};

        int[] colorindices = new int[indices.length];

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                vertices[i * tamano + j] = new Point3f(x, -1.0f, z);
                z += salto;
                if (i < (tamano - 1) && j < (tamano - 1)) {
                    int cindex = (i % 2 + j) % 2;
                    colorindices[n] = cindex;
                    indices[n++] = i * tamano + j;
                    colorindices[n] = cindex;
                    indices[n++] = i * tamano +
                            (j + 1);
                    colorindices[n] = cindex;
                    indices[n++] = (i + 1) *
                            tamano + (j + 1);
                    colorindices[n] = cindex;
                    indices[n++] = (i + 1) *
                            tamano + j;
                }
            }
            z = inicio;
            x += salto;
        }

        IndexedQuadArray geom = new IndexedQuadArray(vertices.length,
                GeometryArray.COORDINATES |
                GeometryArray.COLOR_3,
                indices.length);
        geom.setCoordinates(0, vertices);
        geom.setCoordinateIndices(0, indices);
        geom.setColors(0, colors);
        geom.setColorIndices(0, colorindices);

        Shape3D suelo = new Shape3D(geom);
        sueloTransf.addChild(suelo);

        return sueloTransf;
        
        
        
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        jPanel3.setBackground(new java.awt.Color(255, 204, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IMPRESORA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 255))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Camara"));

        jLabel6.setText("Mover Camara");

        jButton18.setText("-X");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton21.setText("+X");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton23.setText("-Y");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton25.setText("+Y");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton33.setText("-Z");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setText("+Z");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jLabel7.setText("Rotar Camara");

        jButton35.setText("-X");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setText("+X");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setText("-Y");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setText("+Y");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setText("-Z");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setText("+Z");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setText("Derecha");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.setText("Izquierda");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setText("Arriba");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setText("Abajo");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton42)
                            .addComponent(jButton43))
                        .addGap(31, 31, 31)
                        .addComponent(jButton44))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton36))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton23)
                            .addComponent(jButton33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton38))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton41)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jButton39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton40)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton21)
                    .addComponent(jButton35)
                    .addComponent(jButton36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23)
                    .addComponent(jButton25)
                    .addComponent(jButton37)
                    .addComponent(jButton38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton34)
                    .addComponent(jButton33)
                    .addComponent(jButton39)
                    .addComponent(jButton40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton41)
                    .addComponent(jButton42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton44)
                    .addComponent(jButton43)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Carro 1 (Esfero)");

        jButton45.setText("← (F AA )");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.setText("→ (G DD)");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jLabel4.setText("Solo mover");

        jButton8.setText("← (A)");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("→ (D)");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel5.setText("Mover Dibujando");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton46)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9)))))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton45)
                    .addComponent(jButton46)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Carro 2 (Plataforma Movil)");

        jButton47.setText("↑ (Y WW)");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setText("↓ (H SS)");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton10.setText("↑  (W)");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("↓ (S)");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel9.setText("Solo Mover");

        jLabel10.setText("Mover Dibujando");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10)
                            .addComponent(jButton11)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel10)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton47)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton48)
                    .addComponent(jButton11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("Vistas");

        jButton51.setText("Frontal (U)");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setText("Superior (I)");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setText("Isometrico (O)");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton53)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton51)
                    .addComponent(jButton52)
                    .addComponent(jButton53))
                .addContainerGap())
        );

        jButton54.setText("Reset Camara (P)");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Trayectorias"));

        jButton7.setText("Cuadrado (J)");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton12.setText("Escalera(K)");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton12))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Movimiento por Teclado"));

        jCheckBox1.setText("Activar Teclas");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jCheckBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBox1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jButton54)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        //Obtener TransformGroup camara
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(-0.1, 0, 0)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0.1, 0, 0)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

        //Obtener TransformGroup camara
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, -0.1, 0)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

        //Obtener TransformGroup camara
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0.1, 0)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -0.1));//cambie -0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed

        //Obtener TransformGroup camara
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, 0.1)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotX(-Math.PI / 270); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotX(Math.PI / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(-Math.PI / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotZ(-Math.PI / 64); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotZ(Math.PI / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(-Math.PI / 60.);

        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(-0.127, 0.0, 0.0));

        //Multiplicar posicion actual por incremento
        actual.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI / 60.);

        //Multiplicar posicion actual por incremento
        actual.mul(inc);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(0.127, 0.0, 0.0));

        //Multiplicar posicion actual por incremento
        actual.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotX(-Math.PI / 60.);

        //Multiplicar posicion actual por incremento
        actual.mul(inc);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(0.0, 0.127, 0.0));

        //Multiplicar posicion actual por incremento
        actual.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotX(Math.PI / 60.);

        //Multiplicar posicion actual por incremento
        actual.mul(inc);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(0.0, -0.127, 0.0));

        //Multiplicar posicion actual por incremento
        actual.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
//F

        try {arduino.sendData("F");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (-0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
        
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
//G
        try {arduino.sendData("G");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
        
        
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
//Y

        try {arduino.sendData("Y");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
//H

        try {arduino.sendData("H");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3D
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -0.62));

        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        Transform3D actual1 = new Transform3D();
        universo.getTransform(actual1);

        //Crear un incremento
        Transform3D inc1 = new Transform3D();
        inc1.set(new Vector3d(-1.0, 0, 0));
        //Multiplicar posicion actual por incremento
        actual1.mul(inc1);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual1);

        //Obtener posicion de la camara con un transform3d
        Transform3D actual2 = new Transform3D();
        universo.getTransform(actual2);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.rotY(-Math.PI / 2.);

        //Multiplicar posicion actual por incremento
        actual2.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual2);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
         simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -0.62));

        //Multiplicar posicion actual por incremento
        actual.mul(inc);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        Transform3D actual1 = new Transform3D();
        universo.getTransform(actual1);

        //Crear un incremento
        Transform3D inc1 = new Transform3D();
        inc1.set(new Vector3d(0, 0.7, 0));

        //Multiplicar posicion actual por incremento
        actual1.mul(inc1);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual1);

        //Obtener posicion de la camara con un transform3d
        Transform3D actual2 = new Transform3D();
        universo.getTransform(actual2);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.rotX(-Math.PI / 2.);

        //Multiplicar posicion actual por incremento
        actual2.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual2);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
               simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);
        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI *30 / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
        
        Transform3D inc1 = new Transform3D();
        inc1.set(new Vector3d(0, 0, 0.1)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc1);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
        
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(0.4, 0, 0)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc2);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
                simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jCheckBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox1KeyPressed
if (evt.getKeyCode() == evt.VK_U) {
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3D
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -0.62));

        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        Transform3D actual1 = new Transform3D();
        universo.getTransform(actual1);

        //Crear un incremento
        Transform3D inc1 = new Transform3D();
        inc1.set(new Vector3d(-1.0, 0, 0));
        //Multiplicar posicion actual por incremento
        actual1.mul(inc1);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual1);

        //Obtener posicion de la camara con un transform3d
        Transform3D actual2 = new Transform3D();
        universo.getTransform(actual2);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.rotY(-Math.PI / 2.);

        //Multiplicar posicion actual por incremento
        actual2.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual2);
        }

if (evt.getKeyCode() == evt.VK_I) {
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -0.62));

        //Multiplicar posicion actual por incremento
        actual.mul(inc);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        Transform3D actual1 = new Transform3D();
        universo.getTransform(actual1);

        //Crear un incremento
        Transform3D inc1 = new Transform3D();
        inc1.set(new Vector3d(0, 0.7, 0));

        //Multiplicar posicion actual por incremento
        actual1.mul(inc1);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual1);

        //Obtener posicion de la camara con un transform3d
        Transform3D actual2 = new Transform3D();
        universo.getTransform(actual2);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.rotX(-Math.PI / 2.);

        //Multiplicar posicion actual por incremento
        actual2.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual2);
}

if (evt.getKeyCode() == evt.VK_O) {
            simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);
        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI *30 / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
        
        Transform3D inc1 = new Transform3D();
        inc1.set(new Vector3d(0, 0, 0.1)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc1);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
        
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(0.4, 0, 0)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc2);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
}
        
if (evt.getKeyCode() == evt.VK_W) {

        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }        
}
if (evt.getKeyCode() == evt.VK_Y) {

        try {arduino.sendData("Y");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
}
if (evt.getKeyCode() == evt.VK_S) {

        try {arduino.sendData("S");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
}
if (evt.getKeyCode() == evt.VK_H) {

        try {arduino.sendData("H");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
}
if (evt.getKeyCode() == evt.VK_A) {

        try {arduino.sendData("A");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (-0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
}
if (evt.getKeyCode() == evt.VK_F) {

        try {arduino.sendData("F");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (-0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
}
if (evt.getKeyCode() == evt.VK_D) {

        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        } 
}
if (evt.getKeyCode() == evt.VK_G) {

        try {arduino.sendData("G");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
}
        if (evt.getKeyCode() == evt.VK_P) {
        simpleU.getViewingPlatform().setNominalViewingTransform();
        }
        
        if (evt.getKeyCode() == evt.VK_J) {
        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }    
        
        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        
        try {arduino.sendData("A");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (-0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
        
        try {arduino.sendData("S");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }  
        }
        
        if (evt.getKeyCode() == evt.VK_K) {
    
        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        } 
        
        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        } 
        
        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        
        
        
        }
        
    }//GEN-LAST:event_jCheckBox1KeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
//A
        try {arduino.sendData("A");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (-0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
//D

        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
//W

        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
//S
        try {arduino.sendData("S");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }    
        
        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        
        try {arduino.sendData("A");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (-0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        }
        
        try {arduino.sendData("S");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
    
        
        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        } 
        
        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        try {arduino.sendData("D");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin5 = new Transform3D();
        Transform3D transformin15 = new Transform3D();
        obj3.getTransform(transformin5);
        Vector3f vector5 = new Vector3f(0f, 0.0f, (0.0055f/5000000));
        transformin15.setTranslation(vector5);
        transformin5.mul(transformin15);
        obj3.setTransform(transformin5); 
        } 
        
        try {arduino.sendData("W");} catch (Exception ex) {Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);}
        delaySegundo();
        for(int i=0; i<5000000;i++){
        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj2.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(0.0f, 0.0f, (-0.01f/5000000));
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj2.setTransform(transformin4);
        }
        
        
        
    }//GEN-LAST:event_jButton12ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
