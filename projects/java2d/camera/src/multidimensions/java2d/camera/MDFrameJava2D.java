/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import multidimensions.mathematics.MDAxesRotation;
import multidimensions.sample.*;
import multidimensions.shape.IMDAnimation;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDFrameJava2D extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private static final int DELAY = 25;
    private static final double DELTA_ANGLE = 0.5 * 2 * Math.PI / 360;
    private static final double PLUS_ANGLE = DELTA_ANGLE / 15;
    private double deltaAngle = DELTA_ANGLE;
    private int dimension;
    private int dimensionIndex = 1;
    private double radius = 300;
    private int M = 16;
    private int sampleIndex = 2;
    private double angle;
    private IMDSwingCamera camera;
    private IMDShapeSample[] samples;
    private volatile boolean paused = false;
    private volatile IMDUniverse universe;

    public MDFrameJava2D(MDShapeSample[] samples) {
        this(new MDCameraJava2D(), samples);
    }

    public MDFrameJava2D(IMDSwingCamera camrea, MDShapeSample[] samples) {
        setTitle("MD Samples");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.camera = camrea;
        this.samples = samples;

        getContentPane().add(getSamplesPanel());
        setVisible(true);

        updateSample();

        animate();
    }

    void updateSample() {
        IMDShapeSample sample = samples[sampleIndex];
//        System.out.println("dimensions: " + sample.getDimensions().length);
//        if (sample.getDimensions().length <= sampleIndex) {
//            dimensionIndex = sample.getDimensions().length - 1;
//        }
        int dimension = sample.getDimensions()[dimensionIndex];
        universe = sample.getUniverse(dimension, radius, M);
        universe.getCameras().addTail(camera);

        final MDAxesRotation[] rotations = MDAxesRotation.getRotations(dimension);
        universe.getShape().getTransforms().addTail(rotations);

        IMDAnimation animation = new IMDAnimation() {
            @Override
            public void animate() {
                angle += deltaAngle;
                for (int i = 0; i < rotations.length; i++) {
                    rotations[i].setAngle(angle);
                }
            }
        };

        universe.getShape().getAnimations().addTail(animation);


        paused = false;
        camera.getComponent().requestFocusInWindow();
    }

    JPanel getSamplesPanel() {

        final JLabel status = new JLabel();
        IMDShapeSample sample = samples[sampleIndex];

        final JComboBox dimensions = new JComboBox();
        upateDimensions(sample, dimensions);

        dimensions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = dimensions.getSelectedIndex();
                if (dimensionIndex != selectedIndex && dimensions.isEnabled() && 0 <= selectedIndex) {
                    dimensionIndex = selectedIndex;
                    updateSample();
                }
            }
        });

        DefaultListModel categoriesModel = new DefaultListModel();

        for (int i = 0; i < samples.length; i++) {
            categoriesModel.addElement(samples[i].toString());
        }


        final JList sampleList = new JList(categoriesModel);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.setSelectedIndex(sampleIndex);
        sampleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = sampleList.getSelectedIndex();
                if (sampleIndex != selectedIndex) {
                    sampleIndex = selectedIndex;
                    // update dimensions
                    upateDimensions(samples[sampleIndex], dimensions);
                    updateSample();
                }
            }
        });


        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());

        listPanel.add(dimensions, BorderLayout.NORTH);
        listPanel.add(sampleList, BorderLayout.CENTER);

        mainPanel.add(listPanel, BorderLayout.WEST);
        mainPanel.add(camera.getComponent(), BorderLayout.CENTER);
        mainPanel.add(status, BorderLayout.SOUTH);


        camera.getComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("process key: '" + e.getKeyChar() + "'" + e.getKeyCode());

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        paused = !paused;
                        break;
                    case KeyEvent.VK_EQUALS:
                        deltaAngle += PLUS_ANGLE;
                        //System.out.println("delta angle: " + deltaAngle);
                        break;
                    case KeyEvent.VK_MINUS:
                        deltaAngle -= PLUS_ANGLE;
                        //System.out.println("delta angle: " + deltaAngle);
                        break;
                }
            }
        });

        return mainPanel;
    }

    void upateDimensions(IMDShapeSample sample, JComboBox combobox) {
        int[] dimensions = sample.getDimensions();

        for (int i = 0; i < dimensions.length; i++) {
            if (dimensions[i] == dimension) {
                dimensionIndex = i;
                break;
            }
        }

        combobox.removeAllItems();
        combobox.setEnabled(false);
        for (int i : dimensions) {
            combobox.addItem(i);
        }

//        if (dimensions.length <= dimensionIndex) {
//            dimensionIndex = dimensions.length - 1;
//        }
        dimension = dimensions[dimensionIndex];
        combobox.setSelectedIndex(dimensionIndex);
        combobox.setEnabled(1 < dimensions.length);
    }

    void animate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!paused) {
                        universe.evaluate();
                    }
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ex) {
                    }

                }
            }
        }).start();
    }
}
