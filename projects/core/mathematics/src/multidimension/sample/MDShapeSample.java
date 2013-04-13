/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.sample;

import multidimension.mathematics.IMDBaseVector;
import multidimension.mathematics.IMDTransform;
import multidimension.mathematics.IMDVector;
import multidimension.mathematics.MDVector;
import multidimension.shape.IMDShape;
import multidimension.shape.IMDShapeElem;
import multidimension.shape.IMDUniverse;
import multidimension.shape.MDCrossElem;
import multidimension.shape.MDGridElem;
import multidimension.shape.MDPlotterElem;
import multidimension.shape.MDShape;
import multidimension.shape.MDSphereElem;
import multidimension.shape.MDTetrahedronElem;
import multidimension.shape.MDUniverse;

/**
 *
 * @author stellarspot
 */
public enum MDShapeSample implements IMDShapeSample {

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
    CUBE {
        public String getTitle() {
            return "Cube";
        }

        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            return getUniverse(dim, new MDGridElem(dim, radius, 1));
        }
    },
    GRID {
        public String getTitle() {
            return "Grid";
        }

        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            //return getUniverse(dim, new MDGridElem(dim, radius, M));
            int[] grid = new int[dim];
            for (int i = 0; i < dim; i++) {
                grid[i] = i + 1;
            }
            return getUniverse(dim, new MDGridElem(dim, radius, grid));
        }
    },
    TETRAHEDRON {
        public String getTitle() {
            return "Tetrahedron";
        }

        @Override
        public int[] getDimensions() {
            return DIMENSIONS;
        }

        public IMDUniverse getUniverse(int dim, double radius, int M) {
            return getUniverse(dim, new MDTetrahedronElem(dim, radius));
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
            return getUniverse(dim, new MDSphereElem(dim, radius, M));
        }
    },
    SPIRAL {
        public String getTitle() {
            return "Spiral";
        }

        @Override
        public int[] getDimensions() {
            return new int[]{3};
        }

        public IMDUniverse getUniverse(int dim, final double radius, int M) {

            M = 500;
            final double d = 2 * radius;
            final double w = 8 * 2 * Math.PI / d;

            IMDTransform transform = new IMDTransform() {
                final double r = radius / 3;

                @Override
                public IMDVector transform(IMDBaseVector vector) {
                    double s = vector.getElem(0);
                    double x = r * Math.cos(w * s);
                    double y = r * Math.sin(w * s);
                    return new MDVector(x, y, s);

                }
            };

            MDPlotterElem plotter = new MDPlotterElem(transform, new MDGridElem(1, d, M));
            return getUniverse(dim, plotter);
        }
    },
    HYPERBOLOID {
        public String getTitle() {
            return "Hyperboloid";
        }

        @Override
        public int[] getDimensions() {
            return new int[]{3};
        }

        public IMDUniverse getUniverse(int dim, final double radius, int M) {

            M = 50;
            final double d = radius / 100;

            IMDTransform transform = new IMDTransform() {
                final double a = radius / 3;
                final double c = radius / 3;

                @Override
                public IMDVector transform(IMDBaseVector vector) {
                    double angle = vector.getElem(0);
                    double u = vector.getElem(1);
                    double x = a * Math.cosh(u) * Math.cos(angle);
                    double y = a * Math.cosh(u) * Math.sin(angle);
                    double z = c * Math.sinh(u);

                    return new MDVector(x, y, z);
                }
            };

            MDPlotterElem plotter = new MDPlotterElem(transform, new MDGridElem(2, d, M));
            return getUniverse(dim, plotter);
        }
    },
    XY {
        public String getTitle() {
            return "XY";
        }

        @Override
        public int[] getDimensions() {
            return new int[]{3};
        }

        public IMDUniverse getUniverse(int dim, final double radius, int M) {

            M = 100;
            final double s = radius;
            final double d = radius / s;
            

            IMDTransform transform = new IMDTransform() {
                @Override
                public IMDVector transform(IMDBaseVector vector) {
                    double x = vector.getElem(0);
                    double y = vector.getElem(1);
                    double r2 = x * x + y * y;

                    double z = (x * x - y * y) / (r2 * r2);

                    return new MDVector(2 * s * x, 2 * s * y, z);
                }
            };

            MDPlotterElem plotter = new MDPlotterElem(transform, new MDGridElem(2, d, M));
            return getUniverse(dim, plotter);
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

    @Override
    public String toString() {
        return getTitle();
    }
}
