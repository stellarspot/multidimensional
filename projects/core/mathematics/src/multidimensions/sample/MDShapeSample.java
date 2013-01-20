/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

import multidimensions.shape.IMDShape;
import multidimensions.shape.IMDShapeElem;
import multidimensions.shape.IMDUniverse;
import multidimensions.shape.MDCrossElem;
import multidimensions.shape.MDGridElem;
import multidimensions.shape.MDShape;
import multidimensions.shape.MDSphereElem;
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
            return getUniverse(dim, new MDCrossElem(dim, radius));
        }
    },
    GRID {
        public String getTitle() {
            return "GRID";
        }

        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            //return getUniverse(dim, new MDGridElem(dim, radius, M));
            return getUniverse(dim, new MDGridElem(dim, radius, 4));
        }
    },
    //    CUBE {
    //
    //        public String getTitle() {
    //            return "Cube";
    //        }
    //
    //        @Override
    //        public int[] getDimensions() {
    //            return DIMENSIONS;
    //        }
    //
    //        public IMDUniverse getUniverse(int dim, double radius, int M) {
    //            return getUniverse(dim, new MDCube(dim, radius));
    //        }
    //    },
    SPHERE {
        public String getTitle() {
            return "Sphere";
        }

        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            return getUniverse(dim, new MDSphereElem(dim, radius, M));
        }
    };
    private static final int d = 100;
    private static final int[] DIMENSIONS = {2, 3, 4, 5, 6, 7, 8};

    static IMDUniverse getUniverse(int dim, IMDShapeElem shape) {
        return getUniverse(dim, new MDShape(shape));
    }

    static IMDUniverse getUniverse(int dim, IMDShape shape) {
        return new MDUniverse(shape);
    }
}
