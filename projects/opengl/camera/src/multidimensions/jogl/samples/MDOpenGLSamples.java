/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.jogl.samples;

import javax.swing.SwingUtilities;
import multidimensions.java2d.camera.MDFrameJava2D;
import multidimensions.jogl.camera.MDCameraJOGL;
import multidimensions.sample.MDShapeSample;

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
