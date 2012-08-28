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
import multidimensions.sample.MDShapeSample;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDFrameJava2D extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    //MDCameraJava2D camera;
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

        final MDCameraJava2D camera = new MDCameraJava2D();
        //universe.getCameras().addLast(camera);

        final JComboBox dimensions = new JComboBox();
        int N = 8;
        for (int i = 0; i < N; i++) {
            dimensions.addItem(i + 1);
        }
        dimensions.setSelectedIndex(3);

        final IMDUniverse[] universes = new IMDUniverse[samples.length];
        DefaultListModel<MDShapeSample> categoriesModel = new DefaultListModel<MDShapeSample>();

        for (int i = 0; i < samples.length; i++) {
            universes[i] = samples[i].getUniverse(4);
            universes[i].getCameras().addLast(camera);
            categoriesModel.addElement(samples[i]);
        }

        universe = universes[0];

        final JList categories = new JList(categoriesModel);
        categories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categories.setSelectedIndex(0);
        categories.addListSelectionListener(new ListSelectionListener() {
            int selectedIndex = categories.getSelectedIndex();

            public void valueChanged(ListSelectionEvent e) {
//                System.out.println("");
//                System.out.println("value is adjusted: " + e.getValueIsAdjusting());
//                System.out.println("value first: " + e.getFirstIndex());
//                System.out.println("value last: " + e.getLastIndex());
                int index = categories.getSelectedIndex();
                if (selectedIndex != index) {
                    universe = universes[index];
                    selectedIndex = index;
                }

            }
        });

        dimensions.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = categories.getSelectedIndex();
                universes[index] = samples[index].getUniverse(dimensions.getSelectedIndex() + 1);
                universe = universes[index];
                universe.getCameras().addLast(camera);
                System.out.println("dim: " + dimensions.getSelectedIndex());
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(dimensions, BorderLayout.NORTH);
        listPanel.add(categories, BorderLayout.CENTER);
        mainPanel.add(listPanel, BorderLayout.WEST);
        mainPanel.add(camera.canvas, BorderLayout.CENTER);
        //animate();
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
