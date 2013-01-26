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

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private static int DELAY = 25;
    private volatile MDSampleSet sampleSet;
    private volatile boolean paused = false;

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

        final JLabel status = new JLabel();

        final JComboBox dimensions = new JComboBox();
        for (int i : sampleSet.getCurrentDimensions()) {
            dimensions.addItem(i);

        }

        dimensions.setSelectedIndex(currentDimensionIndex);
        sampleSet.setDimensionIndex(currentDimensionIndex);
        dimensions.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //System.out.println("selected: " + dimensions.getSelectedIndex());
                int selectedIndex = dimensions.getSelectedIndex();

                sampleSet.setDimensionIndex(selectedIndex);
                status.setText("dimension: " + sampleSet.getCurrentDimension());
                camera.canvas.requestFocus();
                paused = false;
            }
        });

        DefaultListModel categoriesModel = new DefaultListModel();

        for (int i = 0; i < samples.length; i++) {
            categoriesModel.addElement(samples[i]);
        }


        final JList sampleList = new JList(categoriesModel);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.setSelectedIndex(sampleSet.getSampleIndex());
        sampleList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = sampleList.getSelectedIndex();
                sampleSet.setSampleIndex(selectedIndex);
                sampleSet.setDimensionIndex(dimensions.getSelectedIndex());
                status.setText("sample: " + samples[selectedIndex]);
                camera.canvas.requestFocus();
                paused = false;
            }
        });


        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());

        listPanel.add(dimensions, BorderLayout.NORTH);
        listPanel.add(sampleList, BorderLayout.CENTER);

        mainPanel.add(listPanel, BorderLayout.WEST);
        mainPanel.add(camera.canvas, BorderLayout.CENTER);
        mainPanel.add(status, BorderLayout.SOUTH);


        camera.canvas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("process key: '" + e.getKeyChar() + "'");
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    paused = !paused;
                    status.setText(paused ? "Sample paused" : "Sample run");
                }
            }
        });

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                camera.canvas.requestFocus();
            }
        }).start();

        return mainPanel;
    }

    void animate() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (!paused) {
                        sampleSet.evaluate();
                    }
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ex) {
                    }

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
