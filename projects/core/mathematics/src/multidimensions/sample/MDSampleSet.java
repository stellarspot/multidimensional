/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

import multidimensions.mathematics.MDAxesRotation;
import multidimensions.shape.IMDAnimation;
import multidimensions.shape.IMDUniverse;
import multidimensions.shape.camera.IMDCamera;

/**
 *
 * @author stellarspot
 */
public class MDSampleSet {

    private int maxDimension;
    private int dimension = 2;
    private IMDSample[] samples;
    // sample, dimension
    private IMDUniverse[][] universes;
    private int sampleIndex = 0;
    private double radius = 100;
    private int M = 4;
    private IMDCamera camera;
    private double angle;
    private double deltaAngle = 0.5 * Math.PI / 90;
    // dimension, rotations
    private MDAxesRotation[][] rotations;

    public MDSampleSet(int maxDimension, IMDCamera camera, IMDSample... samples) {
        this.maxDimension = maxDimension;
        this.samples = samples;
        universes = new IMDUniverse[samples.length][maxDimension];
        this.camera = camera;

        rotations = new MDAxesRotation[maxDimension][];

        for (int dim = 0; dim < maxDimension; dim++) {
            rotations[dim] = MDAxesRotation.getRotations(dim);
        }

        initUniverse();
    }

    public int getMaxDimension() {
        return maxDimension;
    }

    public IMDUniverse getUniverse() {


        IMDUniverse universe = universes[sampleIndex][dimension];
        if (universe == null) {
            initUniverse();
            universe = universes[sampleIndex][dimension];
        }
        return universe;


    }

    public void setSampleIndex(int sampleIndex) {
        this.sampleIndex = sampleIndex;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

//    public void setCamera(IMDCamera camera) {
//        this.camera = camera;
//    }
    public void reset() {
    }

    private void initUniverse() {
        IMDUniverse universe = universes[sampleIndex][dimension];

        if (universe == null) {
            universe = samples[sampleIndex].getUniverse(dimension, radius, M);
            universe.getCameras().addLast(camera);

            universe.getShape().getTransforms().addLast(rotations[dimension]);

            final MDAxesRotation[] r = rotations[dimension];

            IMDAnimation animation = new IMDAnimation() {
                @Override
                public void animate() {
                    double deltaAngle = 0.7 * 2 * Math.PI / 360;
                    for (int i = 0; i < r.length; i++) {
                        r[i].addAngle(deltaAngle);
                    }
                }
            };

            universe.getShape().getAnimations().addLast(animation);
            universes[sampleIndex][dimension] = universe;
        }
    }
}
