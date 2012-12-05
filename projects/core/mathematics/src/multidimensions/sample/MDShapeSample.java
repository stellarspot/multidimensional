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

        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }
        
        
        public IMDUniverse getUniverse(int dim, double radius, int M) {
            return getUniverse(dim, new MDCross(dim, radius));
        }
    },
    CUBE {

        public String getTitle() {
            return "Cube";
        }
        
        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            return getUniverse(dim, new MDCube(dim, radius));
        }
    },
    SPHERE {

        public String getTitle() {
            return "Sphere";
        }
        
        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            return getUniverse(dim, new MDSphere(dim, radius, M));
        }
    };
    private static final int d = 100;
    private static final int[] DIMENSIONS = {2, 3, 4, 5, 6, 7, 8};

    static IMDUniverse getUniverse(int dim, IMDShape shape) {

        return new MDUniverse(shape);

    }
}
