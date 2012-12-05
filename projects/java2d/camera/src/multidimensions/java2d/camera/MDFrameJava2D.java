/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import multidimensions.sample.*;

/**
 *
 * @author stellarspot
 */
public class MDFrameJava2D extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static int DELAY = 25;
    private volatile MDSampleSet sampleSet;

    public MDFrameJava2D(MDShapeSample[] samples) {
        setTitle("Java2D Samples Frame");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        getContentPane().add(getSamplesPanel(samples));
        setVisible(true);

        animate();
    }


    JPanel getSamplesPanel(final MDShapeSample[] samples) {

        //int N = 8;
        //final double radius = 200;
        //final int M = 10;
        int currentDimensionIndex = 1;

        final MDCameraJava2D camera = new MDCameraJava2D();
        sampleSet = new MDSampleSet(camera, samples);

        final JComboBox dimensions = new JComboBox();
        for(int i : sampleSet.getCurrentDimensions()){
            dimensions.addItem(i);

        }

        dimensions.setSelectedIndex(currentDimensionIndex);
        sampleSet.setDimensionIndex(currentDimensionIndex);
        dimensions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("selected: " + dimensions.getSelectedIndex());
                sampleSet.setDimensionIndex(dimensions.getSelectedIndex());
            }
        });

        DefaultListModel categoriesModel = new DefaultListModel();

        for (int i = 0; i < samples.length; i++) {
            categoriesModel.addElement(samples[i]);
        }


        final JList sampleList = new JList(categoriesModel);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.setSelectedIndex(0);
        sampleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                sampleSet.setSampleIndex(sampleList.getSelectedIndex());
            }
        });


        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(dimensions, BorderLayout.NORTH);
        listPanel.add(sampleList, BorderLayout.CENTER);
        mainPanel.add(listPanel, BorderLayout.WEST);
        mainPanel.add(camera.canvas, BorderLayout.CENTER);
        return mainPanel;
    }

    void animate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ex) {
                    }
                    sampleSet.evaluate();
                }
            }
        }).start();
    }


    public static void invokeOnEDT(final MDShapeSample[] samples) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new MDFrameJava2D(samples);
            }
        });
    }
}
