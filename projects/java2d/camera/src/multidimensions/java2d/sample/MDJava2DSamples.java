/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.sample;

import multidimensions.sample.MDShapeSample;
import multidimensions.java2d.camera.MDFrameJava2D;

/**
 *
 * @author stellarspot
 */
public class MDJava2DSamples {

    public static void main(String[] args) throws Exception {
        int dim = 4;
        MDFrameJava2D.invokeOnEDT(MDShapeSample.values());
        //MDFrameJava2D.invokeOnEDT(MDShapeSamples.CROSS.getUniverse(dim));
        //MDFrameJava2D.invokeOnEDT(MDShapeSample.CUBE.getUniverse(dim));
        //MDFrameJava2D.invokeOnEDT(MDShapeSample.SPHERE.getUniverse(3));
    }
}

