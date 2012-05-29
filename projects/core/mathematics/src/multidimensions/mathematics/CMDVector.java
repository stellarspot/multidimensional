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
public class CMDVector extends ACMDVector{


    private int dim;
    private double[] elems;

    public CMDVector(int dim){
        this.dim = dim;
        elems = new double[dim];
    }
    
    public CMDVector(double... elems){
        this.dim = elems.length;
        this.elems = elems;
    }

    public CMDVector(CMDVector vector){
        dim = vector.dim;
        elems = Arrays.copyOf(vector.elems, dim);
    }

    public int getDim() {
        return dim;
    }

    public double getElem(int n) {
        return elems[n];
    }

    public void setElem(int n, double value) {
        elems[n] = value;
    }


    


}
