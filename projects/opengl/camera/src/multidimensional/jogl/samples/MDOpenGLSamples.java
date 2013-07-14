/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.jogl.samples;

import javax.swing.SwingUtilities;
import multidimensional.java2d.camera.MDFrameJava2D;
import multidimensional.jogl.camera.MDCameraJOGL;
import multidimensional.sample.MDPlotterSample;
import multidimensional.sample.MDShapeSample;

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
                //new MDFrameJava2D(new MDCameraJOGL(), MDPlotterSample.values());
            }
        });
    }
}
