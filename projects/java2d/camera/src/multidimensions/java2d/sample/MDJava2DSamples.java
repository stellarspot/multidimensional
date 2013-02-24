/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.sample;

import javax.swing.SwingUtilities;
import multidimensions.java2d.camera.MDCameraJava2D;
import multidimensions.java2d.camera.MDFrameJava2D;
import multidimensions.sample.MDShapeSample;

/**
 *
 * @author stellarspot
 */
public class MDJava2DSamples {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new MDFrameJava2D(new MDCameraJava2D(), MDShapeSample.values());
            }
        });
    }
}
