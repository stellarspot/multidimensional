/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.mathematics;

/**
 *
 * @author stellarspot
 */
public class CMDVector extends AMDCVector implements ICMDVector{

    int dim;
    double[] elems;

    public CMDVector(int dim){
        this.dim = dim;
        elems = new double[dim];
    }

    public CMDVector(double... elems){
        this.dim = elems.length;
        this.elems = elems;
    }

    @Override
    public int getDim() {
        return dim;
    }
    @Override
    public double getElem(int n) {
        return elems[n];
    }
    @Override
    public void setElem(int n, double value) {
        elems[n] = value;
    }

}
