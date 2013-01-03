/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

import multidimensions.mathematics.IMDTransform;
import multidimensions.mathematics.MDAxesRotation;
import multidimensions.shape.IMDAnimation;
import multidimensions.shape.IMDCamera;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDSampleSet {

    private static double RADIUS = 350;
    private static int M = 18;
    final double DELTA_ANGLE = 0.5 * 2 * Math.PI / 360;
    //private int maxDimension;
    //private int dimensionIndex = 2;
    //private IMDSample[] samples;
    // sample, dimension
    //private IMDUniverse[][] universes;
    private int sampleIndex = 0;
    private IMDCamera camera;
    private double angle;
    // dimension, rotations
    private MDAxesRotation[][] rotations;
    private SetSample[] setSamples;

    public MDSampleSet(IMDCamera camera, IMDSample... samples) {
        //this.samples = samples;
        this.camera = camera;

        setSamples = new SetSample[samples.length];
        for (int i = 0; i < samples.length; i++) {
            setSamples[i] = new SetSample(samples[i]);
        }

    }

    public void setSampleIndex(int sampleIndex) {
        this.sampleIndex = sampleIndex;
    }

//    public double getRadius() {
//        return radius;
//    }
//
//    public void setRadius(double radius) {
//        this.radius = radius;
//    }
    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getDimensionIndex() {
        return getSetSample().dimensionIndex;
    }

    public int getCurrentDimension() {
        return getSetSample().getDimension();
    }

    public int[] getCurrentDimensions() {
        return getSetSample().sample.getDimensions();
    }

    //public int getCurrentDimensionIndex
    public void setDimensionIndex(int index) {
        getSetSample().setDimensionIndex(index);
    }

    public void reset() {
    }

    public void evaluate() {
        getSetSample().getUniverse().evaluate();
    }

//    private void initUniverse() {
//        int[] dimensions = samples[sampleIndex].getDimensions();
//
//        IMDUniverse universe = universes[sampleIndex][dimensionIndex];
//
//        if (universe == null) {
//            universe = samples[sampleIndex].getUniverse(dimensionIndex, radius, M);
//            universe.getCameras().addLast(camera);
//
//            universe.getShape().getTransforms().addLast(rotations[dimensionIndex]);
//
//            System.out.println("dimensions: " + dimensions.length);
//            //final MDAxesRotation[] r = rotations[dimensions.length];
//            final MDAxesRotation[] r = MDAxesRotation.getRotations(M);
//
//            IMDAnimation animation = new IMDAnimation() {
//
//                @Override
//                public void animate() {
//                    final double deltaAngle = 3.0 * 2 * Math.PI / 360;
//                    angle += deltaAngle;
//                    for (int i = 0; i < r.length; i++) {
//                        r[i].setAngle(angle);
//                    }
//                }
//            };
//
//            universe.getShape().getAnimations().addLast(animation);
//            universes[sampleIndex][dimensionIndex] = universe;
//        }
//    }
    private SetSample getSetSample() {
        return setSamples[sampleIndex];
    }

    class SetSample {

        int dimensionIndex = 2;
        IMDSample sample;
        IMDUniverse[] universes;
        IMDTransform[][] rotations;

        public SetSample(IMDSample sample) {
            this.sample = sample;
            int dimensions = sample.getDimensions().length;

            universes = new IMDUniverse[dimensions];
            rotations = new IMDTransform[dimensions][];
        }

        void setDimensionIndex(int dimensionIndex) {
            //System.out.println("set dimension index: " + dimensionIndex);
            int[] dimensions = sample.getDimensions();
            if (0 <= dimensionIndex && dimensionIndex < dimensions.length) {
                this.dimensionIndex = dimensionIndex;
            } else {
                this.dimensionIndex = 0;
            }
        }

        IMDUniverse getUniverse() {
            init();
            return universes[dimensionIndex];
        }

        int getDimension() {
            return sample.getDimensions()[dimensionIndex];
        }

        void init() {
            if (universes[dimensionIndex] == null) {
                int dimension = sample.getDimensions()[dimensionIndex];
                //System.out.println("Dimension: " + dimension);
                IMDUniverse universe = sample.getUniverse(dimension, RADIUS, M);
                universe.getCameras().addTail(camera);



                //System.out.println("dimensions: " + dimensions.length);
                //final MDAxesRotation[] r = rotations[dimensions.length];
                final MDAxesRotation[] r = MDAxesRotation.getRotations(dimension);
                rotations[dimensionIndex] = r;
                universe.getShape().getTransforms().addTail(r);


                IMDAnimation animation = new IMDAnimation() {

                    @Override
                    public void animate() {
                        angle += DELTA_ANGLE;
                        for (int i = 0; i < r.length; i++) {
                            r[i].setAngle(angle);
                        }
                    }
                };

                universe.getShape().getAnimations().addTail(animation);
                universes[dimensionIndex] = universe;

            }
        }
    }
}
