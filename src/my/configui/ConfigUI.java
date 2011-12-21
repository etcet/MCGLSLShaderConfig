/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfigUI.java
 *
 * Created on Dec 20, 2011, 3:40:05 PM
 */
package my.configui;

import java.io.*;

/**
 *
 * @author chris
 */
public class ConfigUI extends javax.swing.JFrame {
  
    public void save() {
        boolean dof = jCheckBox1.isSelected();
        if (dof) {
            uncomment("final.fsh", "USE_DOF");
        } else {
            comment("final.fsh", "USE_DOF");
        }
        
        boolean hq_blur = jCheckBox5.isSelected();
        if (hq_blur) {
            uncomment("final.fsh", "USE_HIGH_QUALITY_BLUR");
        } else {
            comment("final.fsh", "USE_HIGH_QUALITY_BLUR");
        }
        
        boolean god_rays = jCheckBox2.isSelected();
        if (god_rays) {
            uncomment("final.fsh", "GODRAYS");
        } else {
            comment("final.fsh", "GODRAYS");
        }
        
        boolean bloom = jCheckBox3.isSelected();
        float bloom_amount = Float.parseFloat(jTextField1.getText());
        int bloom_range = Integer.parseInt(jTextField2.getText());
        if (bloom) {
            uncomment("final.fsh", "BLOOM");
            setValue("final.fsh", "BLOOM_AMOUNT", Float.toString(bloom_amount));
            setValue("final.fsh", "BLOOM_RANGE", Integer.toString(bloom_range));
        } else {
            comment("final.fsh", "BLOOM");
        }
    
        boolean celshading = jCheckBox4.isSelected();
        float cel_threshold = Float.parseFloat(jTextField3.getText());
        float cel_thickness = Float.parseFloat(jTextField4.getText());
        if (celshading) {
            uncomment("final.fsh", "CEL_SHADING");
            setValue("final.fsh", "CEL_SHADING_THRESHOLD", Float.toString(cel_threshold));
            setValue("final.fsh", "CEL_SHADING_THICKNESS", Float.toString(cel_thickness));
            
        } else {
            comment("final.fsh", "CEL_SHADING");
        }
        
        boolean cross_process = jCheckBox9.isSelected();
        if (cross_process) {
            uncomment("final.fsh", "CROSSPROCESS");
        } else {
            comment("final.fsh", "CROSSPROCESS");
        }
        
        boolean world_curve = jCheckBox10.isSelected();
        float world_radius = Float.parseFloat(jTextField5.getText());
        float world_radius_squared = Float.parseFloat(jTextField6.getText());
        if (world_curve) {
            uncomment("gbuffers_terrain.vsh", "CURVY_WORLD");
            uncomment("gbuffers_textured.vsh", "CURVY_WORLD");
            uncomment("gbuffers_textured_lit.vsh", "CURVY_WORLD");
            uncomment("gbuffers_water.vsh", "CURVY_WORLD");
            setValue("gbuffers_terrain.vsh", "CURVY_WORLD_RADIUS", Float.toString(world_radius));
            setValue("gbuffers_terrain.vsh", "CURVY_WORLD_RADIUS_SQUARED", Float.toString(world_radius_squared));
            setValue("gbuffers_textured.vsh", "CURVY_WORLD_RADIUS", Float.toString(world_radius));
            setValue("gbuffers_textured.vsh", "CURVY_WORLD_RADIUS_SQUARED", Float.toString(world_radius_squared));
            setValue("gbuffers_textured_lit.vsh", "CURVY_WORLD_RADIUS", Float.toString(world_radius));
            setValue("gbuffers_textured_lit.vsh", "CURVY_WORLD_RADIUS_SQUARED", Float.toString(world_radius_squared));
            setValue("gbuffers_water.vsh", "CURVY_WORLD_RADIUS", Float.toString(world_radius));
            setValue("gbuffers_water.vsh", "CURVY_WORLD_RADIUS_SQUARED", Float.toString(world_radius_squared));
        } else {
            comment("gbuffers_terrain.vsh", "CURVY_WORLD");
            comment("gbuffers_textured.vsh", "CURVY_WORLD");
            comment("gbuffers_textured_lit.vsh", "CURVY_WORLD");
            comment("gbuffers_water.vsh", "CURVY_WORLD");
        }
        
        boolean bump_mapping = jCheckBox11.isSelected();
        if (bump_mapping) {
            uncomment("composite.fsh", "BUMP_MAPPING");
            uncomment("composite.vsh", "BUMP_MAPPING");
            uncomment("gbuffers_basic.vsh", "BUMP_MAPPING");
            uncomment("gbuffers_hand.vsh", "BUMP_MAPPING");
            uncomment("gbuffers_terrain.fsh", "BUMP_MAPPING");
            uncomment("gbuffers_terrain.vsh", "BUMP_MAPPING");
            uncomment("gbuffers_textured.fsh", "BUMP_MAPPING");
            uncomment("gbuffers_textured.vsh", "BUMP_MAPPING");
            uncomment("gbuffers_textured_lit.fsh", "BUMP_MAPPING");
            uncomment("gbuffers_textured_lit.vsh", "BUMP_MAPPING");
            uncomment("gbuffers_weather.fsh", "BUMP_MAPPING");
            uncomment("gbuffers_weather.vsh", "BUMP_MAPPING");
        } else {
            comment("composite.fsh", "BUMP_MAPPING");
            comment("composite.vsh", "BUMP_MAPPING");
            comment("gbuffers_basic.vsh", "BUMP_MAPPING");
            comment("gbuffers_hand.vsh", "BUMP_MAPPING");
            comment("gbuffers_terrain.fsh", "BUMP_MAPPING");
            comment("gbuffers_terrain.vsh", "BUMP_MAPPING");
            comment("gbuffers_textured.fsh", "BUMP_MAPPING");
            comment("gbuffers_textured.vsh", "BUMP_MAPPING");
            comment("gbuffers_textured_lit.fsh", "BUMP_MAPPING");
            comment("gbuffers_textured_lit.vsh", "BUMP_MAPPING");
            comment("gbuffers_weather.fsh", "BUMP_MAPPING");
            comment("gbuffers_weather.vsh", "BUMP_MAPPING");
        }
        boolean bump16 = jRadioButton1.isSelected();
        if (bump16) {
            uncomment("gbuffers_terrain.fsh", "BUMP_16");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_16");
        }
        boolean bump32 = jRadioButton2.isSelected();
        if (bump32) {
            uncomment("gbuffers_terrain.fsh", "BUMP_32");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_32");
        }
        boolean bump64 = jRadioButton3.isSelected();
        if (bump64) {
            uncomment("gbuffers_terrain.fsh", "BUMP_64");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_64");
        }
        boolean bump128 = jRadioButton4.isSelected();
        if (bump128) {
            uncomment("gbuffers_terrain.fsh", "BUMP_128");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_128");
        }
        boolean bump256 = jRadioButton5.isSelected();
        if (bump256) {
            uncomment("gbuffers_terrain.fsh", "BUMP_256");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_256");
        }
        
        boolean waving_grass = jCheckBox6.isSelected();
        if (waving_grass) {
            uncomment("gbuffers_terrain.vsh", "WAVY_GRASS");
        } else {
            comment("gbuffers_terrain.vsh", "WAVY_GRASS");
        }
        boolean waving_wheat = jCheckBox7.isSelected();
        if (waving_wheat) {
            uncomment("gbuffers_terrain.vsh", "WAVY_WHEAT");
        } else {
            comment("gbuffers_terrain.vsh", "WAVY_WHEAT");
        }
        boolean waving_leaves = jCheckBox8.isSelected();
        if (waving_leaves) {
            uncomment("gbuffers_terrain.vsh", "WAVY_LEAVES");
        } else {
            comment("gbuffers_terrain.vsh", "WAVY_LEAVES");
        }
        
    }
    
    //copypasta from http://www.javapractices.com/topic/TopicAction.do?Id=42
    static public void setContents(File aFile, String aContents)
            throws FileNotFoundException, IOException {
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) {
            throw new FileNotFoundException("File does not exist: " + aFile);
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }

        Writer output = new BufferedWriter(new FileWriter(aFile));
        try {
            output.write(aContents);
        } finally {
            output.close();
        }
    }

    private void setValue(String filename, String define, String value) {
        String match_start = "#define " + define;
        filename = "shaders/" + filename;
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();

        try {

            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    if (line.startsWith(match_start)) {
                        String[] line_contents = line.split(" ");
                        line = line_contents[0] + " " + line_contents[1] + " " + value;
                    }
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(contents.toString());
        try {
            setContents(file, contents.toString());
        } catch (Exception e) {
        }
    }

    private void uncomment(String filename, String define) {
        String match_start = "//#define " + define;
        filename = "shaders/" + filename;
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();

        try {

            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    if (line.startsWith(match_start)) {
                        line = line.substring(2);
                    }
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(contents.toString());
        try {
            setContents(file, contents.toString());
        } catch (Exception e) {
        }
    }

    private void comment(String filename, String define) {
        String match_start = "#define " + define;
        filename = "shaders/" + filename;
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();

        try {

            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    if (line.startsWith(match_start)) {
                        line = "//" + line;
                    }
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(contents.toString());
        try {
            setContents(file, contents.toString());
        }
        catch (Exception e) {
        }
    }
    /** Creates new form ConfigUI */
    public ConfigUI() {
        initComponents();
        class FloatVerifier extends javax.swing.InputVerifier {
            public boolean verify(javax.swing.JComponent input) {   // can use with any JComponent
                javax.swing.JTextField tf = (javax.swing.JTextField) input;   // cast to TextField to allow getText() on it
                try {
                    Float.parseFloat(tf.getText());
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }
        class IntVerifier extends javax.swing.InputVerifier {
            public boolean verify(javax.swing.JComponent input) {   // can use with any JComponent
                javax.swing.JTextField tf = (javax.swing.JTextField) input;   // cast to TextField to allow getText() on it
                try {
                    Integer.parseInt(tf.getText());
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }
        jTextField1.setInputVerifier(new FloatVerifier());
        jTextField2.setInputVerifier(new IntVerifier());
        jTextField3.setInputVerifier(new FloatVerifier());
        jTextField4.setInputVerifier(new FloatVerifier());
        jTextField5.setInputVerifier(new FloatVerifier());
        jTextField6.setInputVerifier(new FloatVerifier());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minecraft GLSL Shaders Mod Configuration");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Minecraft GLSL Shaders Mod Configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), java.awt.Color.black)); // NOI18N
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Depth of Field");
        jCheckBox1.setToolTipText("Focus the camera at whatever you're looking at.");

        jCheckBox5.setText("High Quality Blur");
        jCheckBox5.setToolTipText("Better blurring, at the cost of frames per second.");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox11.setText("Bump Mapping");
        jCheckBox11.setToolTipText("Make textures bumpy, if you have a supported texture pack");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("16x16");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("32x32");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("64x64");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("128x128");

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("256x256");

        jCheckBox2.setText("God Rays (might not work)");
        jCheckBox2.setToolTipText("See light shafts from the sun (currently broken)");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Bloom");
        jCheckBox3.setToolTipText("Make stuff glow");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Bloom Amount:");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("9.5");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Bloom Range:");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("4");
        jTextField2.setToolTipText("Increasing this will hurt performance but make things prettier");

        jCheckBox4.setSelected(true);
        jCheckBox4.setText("Cel Shading");
        jCheckBox4.setToolTipText("Draw an outline around things, like a comic book");

        jLabel3.setText("Threshold:");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("0.4");

        jLabel4.setText("Thickness:");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0.0040");

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("Waving Grass");
        jCheckBox6.setToolTipText("Grass appears to wave in the wind");

        jCheckBox7.setSelected(true);
        jCheckBox7.setText("Wheat");
        jCheckBox7.setToolTipText("Wheat appears to wave in the wind");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jCheckBox8.setSelected(true);
        jCheckBox8.setText("Leaves");
        jCheckBox8.setToolTipText("Leaves appears to wave in the wind (a little bit buggy)");

        jCheckBox9.setText("Cross Processing");
        jCheckBox9.setToolTipText("Change the colors");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jCheckBox10.setText("World Curvature");
        jCheckBox10.setToolTipText("Bend the world so it feels like a little planet (Animal Crossing mode)");

        jLabel6.setText("World Radius:");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("30.0");

        jLabel7.setText("World Radius Squared:");

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("10000.0");

        jLabel5.setText("(float)");

        jLabel8.setText("(integer)");

        jLabel9.setText("(float)");

        jLabel10.setText("(float)");

        jLabel11.setText("(float)");

        jLabel12.setText("(float)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox8))
                    .addComponent(jCheckBox10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)))
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jCheckBox5)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addGap(13, 13, 13)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(7, 7, 7)
                .addComponent(jCheckBox11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    ConfigUI.this.save();
}//GEN-LAST:event_jButton1ActionPerformed

private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox2ActionPerformed

private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox3ActionPerformed

private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox5ActionPerformed

private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox7ActionPerformed

private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox9ActionPerformed

private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jRadioButton1ActionPerformed

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    System.exit(0);
}//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfigUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConfigUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

}
