/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.mathematics;

import java.util.Arrays;

/**
 *
 * @author stellarspot
 */
public final class MDVector extends AMDVector {

    private final int dim;
    private final double[] elems;

    public MDVector(int dim) {
        this.dim = dim;
        elems = new double[dim];
    }

    public MDVector(double... elems) {
        this.dim = elems.length;
        this.elems = elems;
    }

    public MDVector(MDVector vector) {
        dim = vector.dim;
        elems = Arrays.copyOf(vector.elems, dim);
    }

    public MDVector(IMDBaseVector vector) {
        dim = vector.getDim();
        elems = new double[dim];
        for (int i = 0; i < dim; i++) {
            elems[i] = vector.getElem(i);
        }
    }

    public int getDim() {
        return dim;
    }

    public double getElem(int n) {
        return elems[n];
    }

    @Override
    public ICMDVector getCVector() {
        return new CMDVector(elems.clone());
    }

}
