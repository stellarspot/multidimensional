/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.sample;

import multidimensions.java2d.camera.MDFrameJava2D;
import multidimensions.mathematics.IMDVector;
import multidimensions.mathematics.MDAxesRotation;
import multidimensions.mathematics.MDVector;
import multidimensions.shape.*;

/**
 *
 * @author stellarspot
 */
public class MDSampleCross {

    public static void main(String[] args) throws Exception {


        double d = 100;

        IMDShapeSegment segment1 = new MDShapeSegment(new MDVector(d, 0), new MDVector(-d, 0));
        IMDShapeSegment segment2 = new MDShapeSegment(new MDVector(0, d), new MDVector(0, -d));

        IMDShape shape = new MDShape();
        shape.getSegments().addLast(segment1);
        shape.getSegments().addLast(segment2);

        final MDAxesRotation rotation = new MDAxesRotation(0, 1);
        shape.getTransforms().addLast(rotation);
        
        IMDAnimation animation = new IMDAnimation() {

            @Override
            public void animate() {
                //System.out.println("Animate");
                //double N = 360;
                double deltaAngle = 5 * 2 * Math.PI / 360;
                rotation.addAngle(deltaAngle);
            }
        };
        shape.getAnimations().addLast(animation);
        
        IMDUniverse universe = new MDUniverse(shape);



        //MDFrameJava2D frame = new MDFrameJava2D(universe);

        MDFrameJava2D.invokeOnEDT(universe);

        universe.evaluate();

        //universe

    }
}
