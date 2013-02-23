/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.opengl.camera;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import multidimensions.sample.MDSampleSet;
import multidimensions.sample.MDShapeSample;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDOpenGLSamples {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;


    private static boolean paused = false;
    private static int DELAY = 25;

    public static void main(String[] args) {

        int dim = 4;
        int M = 24;
        MDCameraOpenGL camera = new MDCameraOpenGL();


        final MDSampleSet sampleSet = new MDSampleSet(camera, MDShapeSample.values());
        sampleSet.setSampleIndex(4);
        sampleSet.setM(M);
        int[] dims = sampleSet.getCurrentDimensions();
        for (int i = 0; i < dims.length; i++) {
            if (dims[i] == dim) {
                sampleSet.setDimensionIndex(i);
            }
        }


        Frame frame = new Frame("OpenGL Sample");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(camera.getCanvas());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);

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
}
