/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.jogl.samples;

import javax.swing.SwingUtilities;
import multidimension.java2d.camera.MDFrameJava2D;
import multidimension.jogl.camera.MDCameraJOGL;
import multidimension.sample.MDShapeSample;

/**
 *
 * @author stellarspot
 */
public class MDOpenGLSamples {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new MDFrameJava2D(new MDCameraJOGL(), MDShapeSample.values());
            }
        });
    }
}
