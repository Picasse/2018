package dibujo3d;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Node;
import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author rvirtual
 */
public class Brazo extends javax.swing.JApplet {

    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();

    private SimpleUniverse simpleU = null;
    private TransformGroup obj2, obj3, obj4;
    float LimSup = 0;
    float Limbase = 0;
    float LimbaseJS = 0;
    float Reset1;
    float dato = 0.0f;
    int n;

    /**
     * Initializes the applet Brazo
     */
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
                        arduino.arduinoTX("COM5", 9600);
                    } catch (Exception ex) {
                        Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (InterruptedException ex) {
        } catch (InvocationTargetException ex) {
        }
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        setFocusable(true);
        //Inicializar objetos
        
        obj2 = new TransformGroup();
        obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        obj3 = new TransformGroup();
        obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        obj4 = new TransformGroup();
        obj4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        //obj.addChild(loadGeometryWRL("base.wrl"));

        obj2.addChild(loadGeometryWRL("1.wrl"));

        obj3.addChild(loadGeometryWRL("2.wrl"));

        obj4.addChild(loadGeometryWRL("3.wrl"));

        objRoot.addChild(obj2);
        
        

        obj2.addChild(obj3);
        
        
        
        

        Transform3D transformin = new Transform3D();
        Transform3D transformin1 = new Transform3D();
        obj3.getTransform(transformin);
        Vector3f vector = new Vector3f(.0f, .16f, .0f);
        transformin1.setTranslation(vector);
        transformin.mul(transformin1);
        obj3.setTransform(transformin);

        obj3.addChild(obj4);
      

        Transform3D transformin4 = new Transform3D();
        Transform3D transformin14 = new Transform3D();
        obj4.getTransform(transformin4);
        Vector3f vector4 = new Vector3f(-.1f, .0f, .0f);
        transformin14.setTranslation(vector4);
        transformin4.mul(transformin14);
        obj4.setTransform(transformin4);

        //obj1.addChild(obj2);
        //obj1.addChild(obj2);
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
        } catch (ParsingErrorException ex) {
        } catch (IncorrectFormatException ex) {
        }
        return objLoad;
    }

    private TransformGroup fondo() {
        TransformGroup objRoot = new TransformGroup();
        Background font = new Background(new Color3f(0.5f, 0.6f, 0.9f));
        font.setApplicationBounds(new BoundingSphere(new Point3d(), 100.0));
        objRoot.addChild(font);
        return objRoot;
        /*TransformGroup objRoot = new TransformGroup();
        TextureLoader cargadorCielo = new TextureLoader("fondo.jgp", this);               
        Background back = new Background();
        back.setImage(cargadorCielo.getImage());
        back.setImageScaleMode(Background.SCALE_FIT_ALL);
        back.setApplicationBounds(limites);
        objRoot.addChild(back);
        return objRoot;*/
    }

    private TransformGroup luces() {
        TransformGroup objRoot = new TransformGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 5), 100.0);
        Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f light1Direction = new Vector3f(0.0f, 0.0f, 0.4f);

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
        int q = 0;

        Color3f blanco = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f negro = new Color3f(0.502f, 0.502f, 0.502f);
        Color3f[] colors = {blanco, negro};

        int[] colorindices = new int[indices.length];

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                vertices[i * tamano + j] = new Point3f(x, -1.0f, z);
                z += salto;
                if (i < (tamano - 1) && j < (tamano - 1)) {
                    int cindex = (i % 2 + j) % 2;
                    colorindices[q] = cindex;
                    indices[q++] = i * tamano + j;
                    colorindices[q] = cindex;
                    indices[q++] = i * tamano
                            + (j + 1);
                    colorindices[q] = cindex;
                    indices[q++] = (i + 1)
                            * tamano + (j + 1);
                    colorindices[q] = cindex;
                    indices[q++] = (i + 1)
                            * tamano + j;
                }
            }
            z = inicio;
            x += salto;
        }

        IndexedQuadArray geom = new IndexedQuadArray(vertices.length, GeometryArray.COORDINATES | GeometryArray.COLOR_3, indices.length);
        geom.setCoordinates(0, vertices);
        geom.setCoordinateIndices(0, indices);
        geom.setColors(0, colors);
        geom.setColorIndices(0, colorindices);

        Shape3D suelo = new Shape3D(geom);
        sueloTransf.addChild(suelo);

        return sueloTransf;

    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(40, 40, 40));
        jPanel1.setForeground(new java.awt.Color(90, 150, 60));
        jPanel1.setToolTipText("");

        jPanel7.setBackground(new java.awt.Color(240, 20, 40));

        jLabel3.setFont(new java.awt.Font("Tekton Pro Cond", 0, 18)); // NOI18N
        jLabel3.setText("Rotar Cámara");

        jButton25.setText("-Y");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("+Z");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText("-Z");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton24.setText("+Y");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton21.setText("+X");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton23.setText("-X");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton4.setText("Rot. Izquierda (Z)");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Rot. Abajo (C)");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton26)
                                    .addComponent(jButton21)
                                    .addComponent(jButton24))))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 16, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25)
                    .addComponent(jButton24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(1, 1, 1)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Giro");

        jButton13.setText("<-A");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("D->");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel7.setText("Brazo");

        jButton16.setText("^W");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton22.setText("Sv");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel8.setText("Mano");

        jButton28.setText("^Q");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText("Ev");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(240, 240, 40));
        jCheckBox1.setText("Manejo por teclado");
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

        jPanel3.setBackground(new java.awt.Color(240, 20, 40));

        jButton1.setText("L. Derecha (B)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Superior (N)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton9.setText("Isométrico (M)");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tekton Pro Cond", 0, 18)); // NOI18N
        jLabel1.setText("Vistas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(162, 162, 162))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton9)
                    .addComponent(jButton1))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Trajan Pro 3", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(110, 50, 100));
        jLabel2.setText("Movimientos");

        jPanel5.setBackground(new java.awt.Color(240, 20, 40));

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel4.setText("Mover Cámara");

        jButton18.setText("-X");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton17.setText("+X");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton20.setText("-Y");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton19.setText("+Y");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton8.setText("-Z");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setText("+Z");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton3.setText("Rot. Derecha (X)");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Rot. Arriba (V)");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton17)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(240, 20, 40));

        jButton10.setText("Mov. 1 (J)");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Mov. 2 (K)");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tekton Pro Cond", 0, 18)); // NOI18N
        jLabel5.setText("Movimientos autónomos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addGap(85, 85, 85))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel7)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(196, 196, 196))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jButton16)
                    .addComponent(jButton22)
                    .addComponent(jButton28)
                    .addComponent(jButton29))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotZ(Math.PI / 64); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotZ(-Math.PI / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotX(-Math.PI / 128); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
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
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

//Obtener TransformGroup camarafor
        dato -= 10.0f;
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI / 270); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        try {

            arduino.sendData(String.valueOf(dato));

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
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
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //Obtener TransformGroup camara
        TransformGroup universo
                = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, 0.1));//cambie -0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        //Obtener TransformGroup camara
        TransformGroup universo
                = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -0.1)); // cambie 0.1 por 0.5
        //Multiplicar posicion actual por incremento
        actual.mul(inc);
        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

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
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed

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
    }//GEN-LAST:event_jButton19ActionPerformed

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

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

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
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
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

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Transform3D actual = new Transform3D();
        obj2.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI *10/ 180);
        actual.mul(inc);
        obj2.setTransform(actual);        // TODO add your handling code here:

        try {
            arduino.sendData("D");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        Transform3D actual = new Transform3D();
        obj3.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotZ(Math.PI*10 / 180);
        actual.mul(inc);
        obj3.setTransform(actual);        // TODO add your handling code here:

        try {

            arduino.sendData("S");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        Transform3D actual = new Transform3D();
        obj4.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotZ(Math.PI*10 / 180);
        actual.mul(inc);
        obj4.setTransform(actual);        // TODO add your handling code here:

        try {
            arduino.sendData("E");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:        
        Transform3D actual = new Transform3D();
        obj2.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotY(-Math.PI *10/ 180);
        actual.mul(inc);
        obj2.setTransform(actual);

        try {
            arduino.sendData("A");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        Transform3D actual = new Transform3D();
        obj3.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotZ(-Math.PI*10 / 180);
        actual.mul(inc);
        obj3.setTransform(actual);        // TODO add your handling code here:

        try {
            // dato = KeyListener();
            arduino.sendData("W");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed

        Transform3D actual = new Transform3D();
        obj4.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotZ(-Math.PI *10/ 180);
        actual.mul(inc);
        obj4.setTransform(actual);

        try {
            arduino.sendData("Q");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jCheckBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox1KeyPressed
        if (evt.getKeyCode() == evt.VK_W) {

            Transform3D actual = new Transform3D();
            obj3.getTransform(actual);
            Transform3D inc = new Transform3D();
            inc.rotZ(-Math.PI / 36);
            actual.mul(inc);
            obj3.setTransform(actual);

            try {
                // dato = KeyListener();
                arduino.sendData("W");

            } catch (Exception ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == evt.VK_S) {

            Transform3D actual = new Transform3D();
            obj3.getTransform(actual);
            Transform3D inc = new Transform3D();
            inc.rotZ(Math.PI / 36);
            actual.mul(inc);
            obj3.setTransform(actual);

            try {
                // dato = KeyListener();
                arduino.sendData("S");

            } catch (Exception ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == evt.VK_A) {
            Transform3D actual = new Transform3D();
            obj2.getTransform(actual);
            Transform3D inc = new Transform3D();
            inc.rotY(-Math.PI / 36);
            actual.mul(inc);
            obj2.setTransform(actual);
            try {
                // dato = KeyListener();
                arduino.sendData("A");
            } catch (Exception ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == evt.VK_D) {
            Transform3D actual = new Transform3D();
            obj2.getTransform(actual);
            Transform3D inc = new Transform3D();
            inc.rotY(Math.PI / 36);
            actual.mul(inc);
            obj2.setTransform(actual);
            try {
                // dato = KeyListener();
                arduino.sendData("D");
            } catch (Exception ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == evt.VK_Q) {
            Transform3D actual = new Transform3D();
            obj4.getTransform(actual);
            Transform3D inc = new Transform3D();
            inc.rotZ(-Math.PI / 36);
            actual.mul(inc);
            obj4.setTransform(actual);
            try {
                // dato = KeyListener();
                arduino.sendData("Q");
            } catch (Exception ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == evt.VK_E) {
            Transform3D actual = new Transform3D();
            obj4.getTransform(actual);
            Transform3D inc = new Transform3D();
            inc.rotZ(Math.PI / 36);
            actual.mul(inc);
            obj4.setTransform(actual);
            try {
                // dato = KeyListener();
                arduino.sendData("E");
            } catch (Exception ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == evt.VK_Z) {
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

        } else if (evt.getKeyCode() == evt.VK_X) {
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

        } else if (evt.getKeyCode() == evt.VK_C) {
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

        } else if (evt.getKeyCode() == evt.VK_V) {
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

        } else if (evt.getKeyCode() == evt.VK_B) {
            simpleU.getViewingPlatform().setNominalViewingTransform();

            //Obtener TransformGroup camara
            TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

            //Obtener posicion de la camara con un transform3D
            Transform3D actual = new Transform3D();
            universo.getTransform(actual);

            //Crear un incremento
            Transform3D inc = new Transform3D();
            inc.set(new Vector3d(0, 0, -2.42));

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

        } else if (evt.getKeyCode() == evt.VK_N) {
            simpleU.getViewingPlatform().setNominalViewingTransform();

            //Obtener TransformGroup camara
            TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

            //Obtener posicion de la camara con un transform3d
            Transform3D actual = new Transform3D();
            universo.getTransform(actual);

            //Crear un incremento
            Transform3D inc = new Transform3D();
            inc.set(new Vector3d(0, 0, -2.42));

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

        } else if (evt.getKeyCode() == evt.VK_M) {
            simpleU.getViewingPlatform().setNominalViewingTransform();

            //Obtener TransformGroup camara
            TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

            //Obtener posicion de la camara con un transform3d
            Transform3D actual = new Transform3D();
            universo.getTransform(actual);

            //Crear un incremento
            Transform3D inc = new Transform3D();
            inc.rotY(Math.PI / 7.5);

            //Multiplicar posicion actual por incremento
            actual.mul(inc);

            //Escribir resultado de la nueva posicion
            universo.setTransform(actual);

            //Crear un incremento
            Transform3D inc2 = new Transform3D();
            inc2.set(new Vector3d(1.016, 0.0, 0.0));

            //Multiplicar posicion actual por incremento
            actual.mul(inc2);

            //Escribir resultado de la nueva posicion
            universo.setTransform(actual);

            //Obtener posicion de la camara con un transform3d
            Transform3D actual1 = new Transform3D();
            universo.getTransform(actual1);

            //Crear un incremento
            Transform3D inc3 = new Transform3D();
            inc3.rotX(-Math.PI / 30);

            //Multiplicar posicion actual por incremento
            actual1.mul(inc3);

            //Escribir resultado de la nueva posicion
            universo.setTransform(actual1);

            //Crear un incremento
            Transform3D inc4 = new Transform3D();
            inc4.set(new Vector3d(0.0, 0.254, 0.0));

            //Multiplicar posicion actual por incremento
            actual1.mul(inc4);

            //Escribir resultado de la nueva posicion
            universo.setTransform(actual1);

            //Obtener posicion de la camara con un transform3d
            Transform3D actual3 = new Transform3D();
            universo.getTransform(actual3);

            //Crear un incremento
            Transform3D inc5 = new Transform3D();
            inc5.set(new Vector3d(0, 0, -1.1));

            //Multiplicar posicion actual por incremento
            actual3.mul(inc5);

            //Escribir resultado de la nueva posicion
            universo.setTransform(actual3);

        }
    }//GEN-LAST:event_jCheckBox1KeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3D
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -2.42));

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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0, -2.42));

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

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //Obtener TransformGroup camara
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();

        //Obtener posicion de la camara con un transform3d
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);

        //Crear un incremento
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI / 7.5);

        //Multiplicar posicion actual por incremento
        actual.mul(inc);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        //Crear un incremento
        Transform3D inc2 = new Transform3D();
        inc2.set(new Vector3d(1.016, 0.0, 0.0));

        //Multiplicar posicion actual por incremento
        actual.mul(inc2);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual);

        //Obtener posicion de la camara con un transform3d
        Transform3D actual1 = new Transform3D();
        universo.getTransform(actual1);

        //Crear un incremento
        Transform3D inc3 = new Transform3D();
        inc3.rotX(-Math.PI / 30);

        //Multiplicar posicion actual por incremento
        actual1.mul(inc3);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual1);

        //Crear un incremento
        Transform3D inc4 = new Transform3D();
        inc4.set(new Vector3d(0.0, 0.254, 0.0));

        //Multiplicar posicion actual por incremento
        actual1.mul(inc4);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual1);

        //Obtener posicion de la camara con un transform3d
        Transform3D actual3 = new Transform3D();
        universo.getTransform(actual3);

        //Crear un incremento
        Transform3D inc5 = new Transform3D();
        inc5.set(new Vector3d(0, 0, -1.1));

        //Multiplicar posicion actual por incremento
        actual3.mul(inc5);

        //Escribir resultado de la nueva posicion
        universo.setTransform(actual3);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            arduino.sendData("J");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            arduino.sendData("K");

        } catch (Exception ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton11ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables

    private Node LoadGeometryWRL(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
