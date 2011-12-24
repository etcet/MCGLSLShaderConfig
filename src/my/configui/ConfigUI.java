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
        boolean dof = SEL_DOF.isSelected();
        if (dof) {
            uncomment("final.fsh", "USE_DOF");
        } else {
            comment("final.fsh", "USE_DOF");
        }
        
        boolean hq_blur = SEL_HQ_BLUR.isSelected();
        if (hq_blur) {
            uncomment("final.fsh", "USE_HIGH_QUALITY_BLUR");
        } else {
            comment("final.fsh", "USE_HIGH_QUALITY_BLUR");
        }
        
        boolean god_rays = SEL_GODRAYS.isSelected();
        float godrays_exposure = Float.parseFloat(TEXT_GOD_EXP.getText());
        int godrays_samples = Integer.parseInt(TEXT_GOD_SAMPLES.getText());
        float godrays_decay = Float.parseFloat(TEXT_GOD_DECAY.getText());
        float godrays_density = Float.parseFloat(TEXT_GOD_DENS.getText());
        if (god_rays) {
            uncomment("final.fsh", "GODRAYS");
            setValue("final.fsh", "GODRAYS_EXPOSURE", Float.toString(godrays_exposure));
            setValue("final.fsh", "GODRAYS_SAMPLES", Integer.toString(godrays_samples));
            setValue("final.fsh", "GODRAYS_DECAY", Float.toString(godrays_decay));
            setValue("final.fsh", "GODRAYS_DENSITY", Float.toString(godrays_density));
        } else {
            comment("final.fsh", "GODRAYS");
        }
        
        boolean bloom = SEL_BLOOM.isSelected();
        float bloom_amount = Float.parseFloat(TEXT_BLOOM_AMT.getText());
        int bloom_range = Integer.parseInt(TEXT_BLOOM_RANGE.getText());
        if (bloom) {
            uncomment("final.fsh", "BLOOM");
            setValue("final.fsh", "BLOOM_AMOUNT", Float.toString(bloom_amount));
            setValue("final.fsh", "BLOOM_RANGE", Integer.toString(bloom_range));
        } else {
            comment("final.fsh", "BLOOM");
        }
    
        boolean celshading = SEL_CEL.isSelected();
        float cel_threshold = Float.parseFloat(TEXT_CEL_THRES.getText());
        float cel_thickness = Float.parseFloat(TEXT_CEL_THICK.getText());
        if (celshading) {
            uncomment("final.fsh", "CEL_SHADING");
            setValue("final.fsh", "CEL_SHADING_THRESHOLD", Float.toString(cel_threshold));
            setValue("final.fsh", "CEL_SHADING_THICKNESS", Float.toString(cel_thickness));
            
        } else {
            comment("final.fsh", "CEL_SHADING");
        }
        
        boolean cross_process = SEL_COLOR.isSelected();
        String cross_process_r = TEXT_COLOR_R.getText();
        String cross_process_g = TEXT_COLOR_G.getText();
        String cross_process_b = TEXT_COLOR_B.getText();
        if (cross_process) {
            uncomment("final.fsh", "CROSSPROCESS");
            setValue("final.fsh", "CROSSPROCESS_R", cross_process_r);
            setValue("final.fsh", "CROSSPROCESS_G", cross_process_g);
            setValue("final.fsh", "CROSSPROCESS_B", cross_process_b);
        } else {
            comment("final.fsh", "CROSSPROCESS");
        }
        
        boolean world_curve = SEL_CURVE.isSelected();
        float world_radius = Float.parseFloat(TEXT_WORLD_R.getText());
        float world_radius_squared = Float.parseFloat(TEXT_WORLD_R2.getText());
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
        
        boolean bump_mapping = SEL_BUMP.isSelected();
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
        boolean bump16 = RADIO_16.isSelected();
        if (bump16) {
            uncomment("gbuffers_terrain.fsh", "BUMP_16");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_16");
        }
        boolean bump32 = RADIO_32.isSelected();
        if (bump32) {
            uncomment("gbuffers_terrain.fsh", "BUMP_32");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_32");
        }
        boolean bump64 = RADIO_64.isSelected();
        if (bump64) {
            uncomment("gbuffers_terrain.fsh", "BUMP_64");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_64");
        }
        boolean bump128 = RADIO_128.isSelected();
        if (bump128) {
            uncomment("gbuffers_terrain.fsh", "BUMP_128");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_128");
        }
        boolean bump256 = RADIO_256.isSelected();
        if (bump256) {
            uncomment("gbuffers_terrain.fsh", "BUMP_256");
        } else {
            comment("gbuffers_terrain.fsh", "BUMP_256");
        }
        
        boolean waving_grass = SEL_GRASS.isSelected();
        if (waving_grass) {
            uncomment("gbuffers_terrain.vsh", "WAVY_GRASS");
        } else {
            comment("gbuffers_terrain.vsh", "WAVY_GRASS");
        }
        boolean waving_wheat = SEL_WHEAT.isSelected();
        if (waving_wheat) {
            uncomment("gbuffers_terrain.vsh", "WAVY_WHEAT");
        } else {
            comment("gbuffers_terrain.vsh", "WAVY_WHEAT");
        }
        boolean waving_leaves = SEL_LEAVES.isSelected();
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
        //System.out.println(contents.toString());
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
    
    private boolean isUncommented(String filename, String define) {
        String match_start = "#define " + define;
        filename = "shaders/" + filename;
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();
        Boolean found = false;
        
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    if (line.startsWith(match_start)) {
                        found = true;
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
        return found;
    }
    
    private String getValue(String filename, String define) {
        String match_start = "#define " + define;
        String or_match = "//" + match_start;
        filename = "shaders/" + filename;
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();
        String value = "";

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    if (line.startsWith(match_start) || line.startsWith(or_match)) {
                        String[] line_contents = line.split(" ");
                        for (int i = 2 ; i < line_contents.length; i++) {
                            value += line_contents[i];
                        }
                        return value;
                    }
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //System.out.println(contents.toString());
        try {
            setContents(file, contents.toString());
        } catch (Exception e) {
        }
        return value;
    }
    
    private void readValuesFromShaders() {
        if (isUncommented("final.fsh", "USE_DOF")) {
            SEL_DOF.doClick();
        }
        SEL_HQ_BLUR.setSelected(isUncommented("final.fsh", "USE_HIGH_QUALITY_BLUR"));

        if (isUncommented("final.fsh", "GODRAYS")) {
            SEL_GODRAYS.doClick();
        }
        TEXT_GOD_EXP.setText(getValue("final.fsh", "GODRAYS_DENSITY"));
        TEXT_GOD_SAMPLES.setText(getValue("final.fsh", "GODRAYS_SAMPLES"));
        TEXT_GOD_DECAY.setText(getValue("final.fsh", "GODRAYS_DECAY"));
        TEXT_GOD_DENS.setText(getValue("final.fsh", "GODRAYS_DENSITY"));

        if (isUncommented("final.fsh", "BLOOM")) {
            SEL_BLOOM.doClick();
        }
        TEXT_BLOOM_AMT.setText(getValue("final.fsh", "BLOOM_AMOUNT"));
        TEXT_BLOOM_RANGE.setText(getValue("final.fsh", "BLOOM_RANGE"));


        if (isUncommented("final.fsh", "CEL_SHADING")) {
            SEL_CEL.doClick();
        }
        TEXT_CEL_THRES.setText(getValue("final.fsh", "CEL_SHADING_THRESHOLD"));
        TEXT_CEL_THICK.setText(getValue("final.fsh", "CEL_SHADING_THICKNESS"));

        SEL_GRASS.setSelected(isUncommented("gbuffers_terrain.vsh", "WAVY_GRASS"));
        SEL_WHEAT.setSelected(isUncommented("gbuffers_terrain.vsh", "WAVY_WHEAT"));
        SEL_LEAVES.setSelected(isUncommented("gbuffers_terrain.vsh", "WAVY_LEAVES"));


        if (isUncommented("final.fsh", "CROSSPROCESS")) {
            SEL_COLOR.doClick();
        }
        TEXT_COLOR_R.setText(getValue("final.fsh", "CROSSPROCESS_R"));
        TEXT_COLOR_G.setText(getValue("final.fsh", "CROSSPROCESS_G"));
        TEXT_COLOR_B.setText(getValue("final.fsh", "CROSSPROCESS_B"));


        if (isUncommented("gbuffers_terrain.vsh", "CURVY_WORLD")) {
            SEL_CURVE.doClick();
        }
        TEXT_WORLD_R.setText(getValue("gbuffers_terrain.vsh", "CURVY_WORLD_RADIUS"));
        TEXT_WORLD_R2.setText(getValue("gbuffers_terrain.vsh", "CURVY_WORLD_RADIUS_SQUARED"));


        if (isUncommented("composite.fsh", "BUMP_MAPPING")) {
            SEL_BUMP.doClick();
        }
        RADIO_16.setSelected(isUncommented("gbuffers_terrain.fsh", "BUMP_16"));
        RADIO_32.setSelected(isUncommented("gbuffers_terrain.fsh", "BUMP_32"));
        RADIO_64.setSelected(isUncommented("gbuffers_terrain.fsh", "BUMP_64"));
        RADIO_128.setSelected(isUncommented("gbuffers_terrain.fsh", "BUMP_128"));
        RADIO_256.setSelected(isUncommented("gbuffers_terrain.fsh", "BUMP_256"));
    }
            
    /** Creates new form ConfigUI */
    public ConfigUI() {
        initComponents();
        readValuesFromShaders();
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
        TEXT_BLOOM_AMT.setInputVerifier(new FloatVerifier());
        TEXT_BLOOM_RANGE.setInputVerifier(new IntVerifier());
        TEXT_CEL_THRES.setInputVerifier(new FloatVerifier());
        TEXT_CEL_THICK.setInputVerifier(new FloatVerifier());
        TEXT_WORLD_R.setInputVerifier(new FloatVerifier());
        TEXT_WORLD_R2.setInputVerifier(new FloatVerifier());
        TEXT_GOD_EXP.setInputVerifier(new FloatVerifier());
        TEXT_GOD_SAMPLES.setInputVerifier(new IntVerifier());
        TEXT_GOD_DECAY.setInputVerifier(new FloatVerifier());
        TEXT_GOD_DENS.setInputVerifier(new FloatVerifier());
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
        SEL_DOF = new javax.swing.JCheckBox();
        SEL_HQ_BLUR = new javax.swing.JCheckBox();
        RADIO_16 = new javax.swing.JRadioButton();
        RADIO_32 = new javax.swing.JRadioButton();
        RADIO_64 = new javax.swing.JRadioButton();
        RADIO_128 = new javax.swing.JRadioButton();
        RADIO_256 = new javax.swing.JRadioButton();
        SEL_GODRAYS = new javax.swing.JCheckBox();
        SEL_BLOOM = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        TEXT_BLOOM_AMT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TEXT_BLOOM_RANGE = new javax.swing.JTextField();
        SEL_CEL = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        TEXT_CEL_THRES = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TEXT_CEL_THICK = new javax.swing.JTextField();
        SEL_GRASS = new javax.swing.JCheckBox();
        SEL_WHEAT = new javax.swing.JCheckBox();
        SEL_LEAVES = new javax.swing.JCheckBox();
        SEL_COLOR = new javax.swing.JCheckBox();
        SEL_CURVE = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        TEXT_WORLD_R = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TEXT_WORLD_R2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TEXT_GOD_EXP = new javax.swing.JTextField();
        TEXT_GOD_SAMPLES = new javax.swing.JTextField();
        TEXT_GOD_DECAY = new javax.swing.JTextField();
        TEXT_GOD_DENS = new javax.swing.JTextField();
        SEL_BUMP = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TEXT_COLOR_R = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        TEXT_COLOR_G = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        TEXT_COLOR_B = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        BUT_SAVE = new javax.swing.JButton();
        BUT_EXIT = new javax.swing.JButton();
        BUT_RESET = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minecraft GLSL Shaders Mod Configuration");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 14));

        SEL_DOF.setText("Depth of Field");
        SEL_DOF.setToolTipText("Focus the camera at whatever you're looking at.");
        SEL_DOF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_DOFActionPerformed(evt);
            }
        });

        SEL_HQ_BLUR.setText("High Quality Blur");
        SEL_HQ_BLUR.setToolTipText("Better blurring, at the cost of frames per second.");
        SEL_HQ_BLUR.setEnabled(false);
        SEL_HQ_BLUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_HQ_BLURActionPerformed(evt);
            }
        });

        buttonGroup1.add(RADIO_16);
        RADIO_16.setText("16x16");
        RADIO_16.setEnabled(false);
        RADIO_16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RADIO_16ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RADIO_32);
        RADIO_32.setText("32x32");
        RADIO_32.setEnabled(false);

        buttonGroup1.add(RADIO_64);
        RADIO_64.setText("64x64");
        RADIO_64.setEnabled(false);

        buttonGroup1.add(RADIO_128);
        RADIO_128.setSelected(true);
        RADIO_128.setText("128x128");
        RADIO_128.setEnabled(false);

        buttonGroup1.add(RADIO_256);
        RADIO_256.setText("256x256");
        RADIO_256.setEnabled(false);

        SEL_GODRAYS.setText("God Rays");
        SEL_GODRAYS.setToolTipText("See light shafts from the sun (turn down bloom amount to less than 5 to see)");
        SEL_GODRAYS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_GODRAYSActionPerformed(evt);
            }
        });

        SEL_BLOOM.setText("Bloom");
        SEL_BLOOM.setToolTipText("Make stuff glow");
        SEL_BLOOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_BLOOMActionPerformed(evt);
            }
        });

        jLabel1.setText("Bloom Amount:");

        TEXT_BLOOM_AMT.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_BLOOM_AMT.setText("9.5");
        TEXT_BLOOM_AMT.setEnabled(false);
        TEXT_BLOOM_AMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TEXT_BLOOM_AMTActionPerformed(evt);
            }
        });

        jLabel2.setText("Bloom Range:");

        TEXT_BLOOM_RANGE.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_BLOOM_RANGE.setText("4");
        TEXT_BLOOM_RANGE.setToolTipText("Increasing this will hurt performance but make things prettier");
        TEXT_BLOOM_RANGE.setEnabled(false);

        SEL_CEL.setText("Cel Shading");
        SEL_CEL.setToolTipText("Draw an outline around things, like a comic book");
        SEL_CEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_CELActionPerformed(evt);
            }
        });

        jLabel3.setText("Threshold:");

        TEXT_CEL_THRES.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_CEL_THRES.setText("0.4");
        TEXT_CEL_THRES.setEnabled(false);

        jLabel4.setText("Thickness:");

        TEXT_CEL_THICK.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_CEL_THICK.setText("0.0040");
        TEXT_CEL_THICK.setEnabled(false);

        SEL_GRASS.setText("Waving Grass");
        SEL_GRASS.setToolTipText("Grass appears to wave in the wind");

        SEL_WHEAT.setText("Wheat");
        SEL_WHEAT.setToolTipText("Wheat appears to wave in the wind");
        SEL_WHEAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_WHEATActionPerformed(evt);
            }
        });

        SEL_LEAVES.setText("Leaves");
        SEL_LEAVES.setToolTipText("Leaves appears to wave in the wind (a little bit buggy)");

        SEL_COLOR.setText("Colorize");
        SEL_COLOR.setToolTipText("Change the colors");
        SEL_COLOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_COLORActionPerformed(evt);
            }
        });

        SEL_CURVE.setSelected(false);
        SEL_CURVE.setText("World Curvature");
        SEL_CURVE.setToolTipText("Bend the world so it feels like a little planet (Animal Crossing mode)");
        SEL_CURVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_CURVEActionPerformed(evt);
            }
        });

        jLabel6.setText("World Radius:");

        TEXT_WORLD_R.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_WORLD_R.setText("30.0");
        TEXT_WORLD_R.setEnabled(false);

        jLabel7.setText("World Radius Squared:");

        TEXT_WORLD_R2.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_WORLD_R2.setText("10000.0");
        TEXT_WORLD_R2.setEnabled(false);

        jLabel5.setText("(float)");

        jLabel8.setText("(integer)");

        jLabel9.setText("(float)");

        jLabel10.setText("(float)");

        jLabel11.setText("(float)");

        jLabel12.setText("(float)");

        TEXT_GOD_EXP.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_GOD_EXP.setText("0.2");
        TEXT_GOD_EXP.setEnabled(false);

        TEXT_GOD_SAMPLES.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_GOD_SAMPLES.setText("32");
        TEXT_GOD_SAMPLES.setEnabled(false);

        TEXT_GOD_DECAY.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_GOD_DECAY.setText("0.95");
        TEXT_GOD_DECAY.setEnabled(false);

        TEXT_GOD_DENS.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TEXT_GOD_DENS.setText("0.5");
        TEXT_GOD_DENS.setEnabled(false);

        SEL_BUMP.setText("Bump Mapping");
        SEL_BUMP.setToolTipText("Make textures bumpy, if you have a supported texture pack");
        SEL_BUMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEL_BUMPActionPerformed(evt);
            }
        });

        jLabel13.setText("Exposure:");

        jLabel14.setText("Samples:");

        jLabel15.setText("Decay:");

        jLabel16.setText("Density:");

        jLabel17.setText("(float)");

        jLabel18.setText("(float)");

        jLabel19.setText("(float)");

        jLabel20.setText("(integer)");

        jLabel21.setText("Red:");

        TEXT_COLOR_R.setText("color.r * 1.3 + 0.01");
        TEXT_COLOR_R.setEnabled(false);

        jLabel22.setText("Green:");

        TEXT_COLOR_G.setText("color.g * 1.2");
        TEXT_COLOR_G.setEnabled(false);

        jLabel23.setText("Blue:");

        TEXT_COLOR_B.setText("color.b * 0.75 + 0.10");
        TEXT_COLOR_B.setEnabled(false);

        jLabel24.setText("Compatible textures can be found on the wiki:");

        jTextField14.setEditable(false);
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField14.setText("http://daxnitro.wikia.com/wiki/Texture_Packs_(Shaders2)");

        jLabel25.setText("You must use MCPatcher to enable HD Textures.");

        jLabel26.setText("Animal Crossing");

        jLabel27.setText("10");

        jLabel28.setText("300");

        jLabel29.setText("..Extreme");

        jLabel30.setText("20");

        jLabel31.setText("200");

        jLabel32.setText("..Bigger");

        jLabel33.setText("10");

        jLabel34.setText("1000");

        jLabel35.setText("Planetoid");

        jLabel36.setText("40");

        jLabel37.setText("42000");

        jLabel38.setText("-400");

        jLabel39.setText("10");

        jLabel40.setText("Inception");

        jLabel41.setText("You can alter the colors using mathematics.");

        jLabel42.setText("You can use any GLSL function.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SEL_CURVE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TEXT_WORLD_R2)
                                    .addComponent(TEXT_WORLD_R, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))))
                        .addGap(119, 119, 119)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel38)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(TEXT_COLOR_B))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TEXT_COLOR_G)
                                    .addComponent(TEXT_COLOR_R, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel41)))
                    .addComponent(SEL_COLOR, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(SEL_HQ_BLUR))
                            .addComponent(SEL_DOF))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TEXT_GOD_DENS)
                                    .addComponent(TEXT_GOD_DECAY)
                                    .addComponent(TEXT_GOD_EXP)
                                    .addComponent(TEXT_GOD_SAMPLES, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel17)))
                            .addComponent(SEL_GODRAYS))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SEL_BLOOM)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TEXT_BLOOM_RANGE)
                                            .addComponent(TEXT_BLOOM_AMT, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SEL_CEL)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TEXT_CEL_THICK))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TEXT_CEL_THRES, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SEL_GRASS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEL_WHEAT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEL_LEAVES))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(RADIO_16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RADIO_32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RADIO_64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RADIO_128)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RADIO_256))
                            .addComponent(SEL_BUMP))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SEL_DOF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SEL_HQ_BLUR))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SEL_BLOOM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(TEXT_BLOOM_AMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8)
                                    .addComponent(TEXT_BLOOM_RANGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SEL_GODRAYS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(TEXT_GOD_EXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(TEXT_GOD_SAMPLES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SEL_CEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(TEXT_CEL_THRES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(TEXT_CEL_THICK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(TEXT_GOD_DECAY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(TEXT_GOD_DENS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(SEL_GRASS)
                            .addComponent(SEL_WHEAT)
                            .addComponent(SEL_LEAVES))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SEL_COLOR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TEXT_COLOR_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(TEXT_COLOR_G, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(TEXT_COLOR_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEL_CURVE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35)
                    .addComponent(jLabel40))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TEXT_WORLD_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TEXT_WORLD_R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31)
                            .addComponent(jLabel34)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)))
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(jLabel33)
                        .addComponent(jLabel36)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEL_BUMP)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RADIO_16)
                    .addComponent(RADIO_32)
                    .addComponent(RADIO_64)
                    .addComponent(RADIO_128)
                    .addComponent(RADIO_256)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BUT_SAVE.setText("Save");
        BUT_SAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUT_SAVEActionPerformed(evt);
            }
        });

        BUT_EXIT.setText("Exit");
        BUT_EXIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUT_EXITActionPerformed(evt);
            }
        });

        BUT_RESET.setText("Reset Defaults");
        BUT_RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUT_RESETActionPerformed(evt);
            }
        });

        jLabel43.setText("Remember to overwrite the files in the shader folder in your minecraft.jar (using 7zip) with the shader files generated by this tool.");

        jLabel44.setText("This tool does not install the shader files! You have to do that yourself.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BUT_SAVE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BUT_RESET)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 593, Short.MAX_VALUE)
                        .addComponent(BUT_EXIT))
                    .addComponent(jLabel44)
                    .addComponent(jLabel43))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BUT_SAVE)
                    .addComponent(BUT_RESET)
                    .addComponent(BUT_EXIT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



private void BUT_SAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUT_SAVEActionPerformed
    ConfigUI.this.save();
}//GEN-LAST:event_BUT_SAVEActionPerformed

private void SEL_GODRAYSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_GODRAYSActionPerformed
    TEXT_GOD_EXP.setEnabled(SEL_GODRAYS.isSelected());
    TEXT_GOD_SAMPLES.setEnabled(SEL_GODRAYS.isSelected());
    TEXT_GOD_DECAY.setEnabled(SEL_GODRAYS.isSelected());
    TEXT_GOD_DENS.setEnabled(SEL_GODRAYS.isSelected());
}//GEN-LAST:event_SEL_GODRAYSActionPerformed

private void SEL_BLOOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_BLOOMActionPerformed
// TODO add your handling code here:
    TEXT_BLOOM_AMT.setEnabled(SEL_BLOOM.isSelected());
    TEXT_BLOOM_RANGE.setEnabled(SEL_BLOOM.isSelected());
}//GEN-LAST:event_SEL_BLOOMActionPerformed

private void SEL_HQ_BLURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_HQ_BLURActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_SEL_HQ_BLURActionPerformed

private void SEL_WHEATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_WHEATActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_SEL_WHEATActionPerformed

private void SEL_COLORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_COLORActionPerformed
    TEXT_COLOR_R.setEnabled(SEL_COLOR.isSelected());
    TEXT_COLOR_G.setEnabled(SEL_COLOR.isSelected());
    TEXT_COLOR_B.setEnabled(SEL_COLOR.isSelected());
}//GEN-LAST:event_SEL_COLORActionPerformed

private void RADIO_16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RADIO_16ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_RADIO_16ActionPerformed

private void TEXT_BLOOM_AMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TEXT_BLOOM_AMTActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_TEXT_BLOOM_AMTActionPerformed

private void BUT_EXITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUT_EXITActionPerformed
    System.exit(0);
}//GEN-LAST:event_BUT_EXITActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void BUT_RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUT_RESETActionPerformed
        if (!SEL_DOF.isSelected()) {
            SEL_DOF.doClick();
        }

        if (SEL_GODRAYS.isSelected()) {
            SEL_GODRAYS.doClick();
        }
        TEXT_GOD_EXP.setText("0.2");
        TEXT_GOD_SAMPLES.setText("32");
        TEXT_GOD_DECAY.setText("0.95");
        TEXT_GOD_DENS.setText("0.5");

        if (!SEL_BLOOM.isSelected()) {
            SEL_BLOOM.doClick();
        }
        TEXT_BLOOM_AMT.setText("9.5");
        TEXT_BLOOM_RANGE.setText("4");

        if (!SEL_CEL.isSelected()) {
            SEL_CEL.doClick();
        }
        TEXT_CEL_THRES.setText("0.4");
        TEXT_CEL_THICK.setText("0.0040");

        SEL_GRASS.setSelected(true);
        SEL_WHEAT.setSelected(true);
        SEL_LEAVES.setSelected(true);

        if (SEL_COLOR.isSelected()) {
            SEL_COLOR.doClick();
        }
        TEXT_COLOR_R.setText("color.r * 1.3 + 0.01");
        TEXT_COLOR_G.setText("color.g * 1.2");
        TEXT_COLOR_B.setText("color.b * 0.75 + 0.10");

        if (SEL_CURVE.isSelected()) {
            SEL_CURVE.doClick();
        }
        TEXT_WORLD_R.setText("30.0");
        TEXT_WORLD_R2.setText("10000.0");

        if (SEL_BUMP.isSelected()) {
            SEL_BUMP.doClick();
        }
        RADIO_128.setSelected(true);
    }//GEN-LAST:event_BUT_RESETActionPerformed

    private void SEL_CURVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_CURVEActionPerformed
        TEXT_WORLD_R.setEnabled(SEL_CURVE.isSelected());
        TEXT_WORLD_R2.setEnabled(SEL_CURVE.isSelected());
    }//GEN-LAST:event_SEL_CURVEActionPerformed

    private void SEL_CELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_CELActionPerformed
        TEXT_CEL_THRES.setEnabled(SEL_CEL.isSelected());
        TEXT_CEL_THICK.setEnabled(SEL_CEL.isSelected());
    }//GEN-LAST:event_SEL_CELActionPerformed

    private void SEL_BUMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_BUMPActionPerformed
        RADIO_16.setEnabled(SEL_BUMP.isSelected());
        RADIO_32.setEnabled(SEL_BUMP.isSelected());
        RADIO_64.setEnabled(SEL_BUMP.isSelected());
        RADIO_128.setEnabled(SEL_BUMP.isSelected());
        RADIO_256.setEnabled(SEL_BUMP.isSelected());
    }//GEN-LAST:event_SEL_BUMPActionPerformed

    private void SEL_DOFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEL_DOFActionPerformed
        SEL_HQ_BLUR.setEnabled(SEL_DOF.isSelected());
    }//GEN-LAST:event_SEL_DOFActionPerformed

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
    private javax.swing.JButton BUT_EXIT;
    private javax.swing.JButton BUT_RESET;
    private javax.swing.JButton BUT_SAVE;
    private javax.swing.JRadioButton RADIO_128;
    private javax.swing.JRadioButton RADIO_16;
    private javax.swing.JRadioButton RADIO_256;
    private javax.swing.JRadioButton RADIO_32;
    private javax.swing.JRadioButton RADIO_64;
    private javax.swing.JCheckBox SEL_BLOOM;
    private javax.swing.JCheckBox SEL_BUMP;
    private javax.swing.JCheckBox SEL_CEL;
    private javax.swing.JCheckBox SEL_COLOR;
    private javax.swing.JCheckBox SEL_CURVE;
    private javax.swing.JCheckBox SEL_DOF;
    private javax.swing.JCheckBox SEL_GODRAYS;
    private javax.swing.JCheckBox SEL_GRASS;
    private javax.swing.JCheckBox SEL_HQ_BLUR;
    private javax.swing.JCheckBox SEL_LEAVES;
    private javax.swing.JCheckBox SEL_WHEAT;
    private javax.swing.JTextField TEXT_BLOOM_AMT;
    private javax.swing.JTextField TEXT_BLOOM_RANGE;
    private javax.swing.JTextField TEXT_CEL_THICK;
    private javax.swing.JTextField TEXT_CEL_THRES;
    private javax.swing.JTextField TEXT_COLOR_B;
    private javax.swing.JTextField TEXT_COLOR_G;
    private javax.swing.JTextField TEXT_COLOR_R;
    private javax.swing.JTextField TEXT_GOD_DECAY;
    private javax.swing.JTextField TEXT_GOD_DENS;
    private javax.swing.JTextField TEXT_GOD_EXP;
    private javax.swing.JTextField TEXT_GOD_SAMPLES;
    private javax.swing.JTextField TEXT_WORLD_R;
    private javax.swing.JTextField TEXT_WORLD_R2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField14;
    // End of variables declaration//GEN-END:variables

}
