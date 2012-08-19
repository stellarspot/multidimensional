/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.sample;

import javax.swing.SwingUtilities;
import multidimensions.java2d.camera.MDCameraJava2D;
import multidimensions.java2d.camera.MDFrameJava2D;
import multidimensions.mathematics.IMDVector;
import multidimensions.mathematics.MDVector;
import multidimensions.shape.*;

/**
 *
 * @author stellarspot
 */
public class MDSampleCross {

    public static void main(String[] args) throws Exception {


        double d = 100;
        IMDVector v1 = new MDVector(d, 0);
        IMDVector v2 = new MDVector(0, d);


        IMDShapeSegment segment = new MDShapeSegment(v1, v2);

        IMDShape shape = new MDShape();
        shape.getSegments().addLast(segment);


        IMDUniverse universe = new MDUniverse(shape);



        //MDFrameJava2D frame = new MDFrameJava2D(universe);

        MDFrameJava2D.invokeOnEDT(universe);
        
        universe.evaluate();

        //universe

    }
}
