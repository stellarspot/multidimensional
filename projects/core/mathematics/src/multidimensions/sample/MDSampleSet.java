/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

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

    public MDSampleSet(int maxDimension, IMDCamera camera, IMDSample... samples) {
        this.maxDimension = maxDimension;
        this.samples = samples;
        universes = new IMDUniverse[samples.length][maxDimension];
        this.camera = camera;
        init();
    }

    public int getMaxDimension() {
        return maxDimension;
    }

    public IMDUniverse getUniverse() {


        IMDUniverse universe = universes[sampleIndex][dimension];
        if(universe == null){
            init();
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

    private void init() {
        //universe = samples[sampleIndex].getUniverse(dimension, radius, M);
        if (universes[sampleIndex][dimension] == null) {
            universes[sampleIndex][dimension] = samples[sampleIndex].getUniverse(dimension, radius, M);
            universes[sampleIndex][dimension].getCameras().addLast(camera);

        }

    }
}
