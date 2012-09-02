/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

import multidimensions.mathematics.MDAxesRotation;
import multidimensions.shape.IMDAnimation;
import multidimensions.shape.IMDShape;
import multidimensions.shape.IMDUniverse;
import multidimensions.shape.MDCross;
import multidimensions.shape.MDCube;
import multidimensions.shape.MDSphere;
import multidimensions.shape.MDUniverse;

/**
 *
 * @author stellarspot
 */
public enum MDShapeSample implements IMDSample {

    CROSS {
        public String getTitle() {
            return "Cross";
        }

        public IMDUniverse getUniverse(int dim) {
            return getUniverse(dim, new MDCross(dim, d));
        }
    },
    CUBE {
        public String getTitle() {
            return "Cube";
        }

        public IMDUniverse getUniverse(int dim) {
            return getUniverse(dim, new MDCube(dim, d));
        }
    },
    SPHERE {
        public String getTitle() {
            return "Sphere";
        }

        public IMDUniverse getUniverse(int dim) {
            return getUniverse(dim, new MDSphere(dim, 2 * d, 10));
        }
    };
    static final int d = 100;

    static IMDUniverse getUniverse(int dim, IMDShape shape) {

        final MDAxesRotation[] rotations = MDAxesRotation.getRotations(dim);
        shape.getTransforms().addLast(rotations);

        IMDAnimation animation = new IMDAnimation() {
            @Override
            public void animate() {
                double deltaAngle = 0.7 * 2 * Math.PI / 360;
                for (int i = 0; i < rotations.length; i++) {
                    rotations[i].addAngle(deltaAngle);
                }
            }
        };

        shape.getAnimations().addLast(animation);
        return new MDUniverse(shape);

    }

}
