/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

import multidimensions.mathematics.MDAxesRotation;
import multidimensions.shape.IMDAnimation;
import multidimensions.shape.IMDUniverse;
import multidimensions.shape.MDCube;
import multidimensions.shape.MDUniverse;

/**
 *
 * @author stellarspot
 */
public enum MDShapeSamples implements IMDSample {

    CUBE {
        public String getTitle() {
            return "MD Cube";
        }

        public IMDUniverse getUniverse(int dim) {
            double d = 150;
            int N = dim;

            MDCube cube = new MDCube(N, d);

            final MDAxesRotation[] rotations = MDAxesRotation.getRotations(N);
            cube.getTransforms().addLast(rotations);

            IMDAnimation animation = new IMDAnimation() {
                @Override
                public void animate() {
                    double deltaAngle = 0.7 * 2 * Math.PI / 360;
                    for (int i = 0; i < rotations.length; i++) {
                        rotations[i].addAngle(deltaAngle);
                    }
                }
            };

            cube.getAnimations().addLast(animation);
            return new MDUniverse(cube);

        }
    }
}
