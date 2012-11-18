/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import multidimensions.sample.MDSampleSet;
import multidimensions.sample.MDShapeSample;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDFrameJava2D extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    private static int DELAY = 25;
    private volatile IMDUniverse universe;

    public MDFrameJava2D(IMDUniverse universe) {
        setTitle("Java2D Frame");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        getContentPane().add(getUniversePanel(universe));
        setVisible(true);

        animate();
    }

    public MDFrameJava2D(MDShapeSample[] samples) {
        setTitle("Java2D Samples Frame");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        getContentPane().add(getSamplesPanel(samples));
        setVisible(true);

        animate();
    }

    JPanel getUniversePanel(IMDUniverse universe) {
        this.universe = universe;
        MDCameraJava2D camera = new MDCameraJava2D();
        universe.getCameras().addLast(camera);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(camera.canvas, BorderLayout.CENTER);
        return panel;
    }

    JPanel getSamplesPanel(final MDShapeSample[] samples) {

        int N = 8;
        final double radius = 200;
        final int M = 10;
        int currentDimension = 3;

        final MDCameraJava2D camera = new MDCameraJava2D();
        final MDSampleSet sampleSet = new MDSampleSet(N, camera, samples);

        final JComboBox dimensions = new JComboBox();
        for (int i = 0; i < N; i++) {
            dimensions.addItem(i + 1);
        }

        dimensions.setSelectedIndex(currentDimension);
        sampleSet.setDimension(currentDimension);
        dimensions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sampleSet.setDimension(dimensions.getSelectedIndex());
                universe = sampleSet.getUniverse();
            }
        });

        DefaultListModel<MDShapeSample> categoriesModel = new DefaultListModel<MDShapeSample>();

        for (int i = 0; i < samples.length; i++) {
            categoriesModel.addElement(samples[i]);
        }

        universe = sampleSet.getUniverse();


        final JList sampleList = new JList(categoriesModel);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.setSelectedIndex(0);
        sampleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                sampleSet.setSampleIndex(sampleList.getSelectedIndex());
                universe = sampleSet.getUniverse();
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
                    universe.evaluate();
                }
            }
        }).start();
    }

    public static void invokeOnEDT(final IMDUniverse universe) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new MDFrameJava2D(universe);
            }
        });
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
