/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.mathematics;

import java.util.Arrays;

/**
 *
 * @author stellarspot
 */
public class MDVector extends AMDVector{


    private int dim;
    private double[] elems;

    public MDVector(int dim){
        this.dim = dim;
        elems = new double[dim];
    }

    public MDVector(double... elems){
        this.dim = elems.length;
        this.elems = elems;
    }
    
    public MDVector(MDVector vector){
        dim = vector.dim;
        elems = Arrays.copyOf(vector.elems, dim);
    }

    public int getDim() {
        return dim;
    }

    public double getElem(int n) {
        return elems[n];
    }


}
