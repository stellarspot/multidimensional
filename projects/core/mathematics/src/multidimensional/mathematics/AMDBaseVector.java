/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.mathematics;

/**
 *
 * @author user
 */
public abstract class AMDBaseVector implements IMDBaseVector{

    private double sqr(double x){
        return x * x;
    }

    public double scalar(IMDBaseVector v) {
        double s = 0;
        for (int i = 0; i < getDim(); i++) {
            s += getElem(i) * v.getElem(i);
        }
        return s;
    }

    public double length() {
        return Math.sqrt(scalar(this));
    }

    public boolean equals(IMDBaseVector v, double delta) {

        double deltaSqr = 0;
        for (int i = 0; i < getDim(); i++) {
            deltaSqr += sqr(getElem(i) - v.getElem(i));
        }

        return deltaSqr <= sqr(delta);
    }

    public double[] getElemsCopy() {
        double[] elems = new double[getDim()];
        for (int i = 0; i < getDim(); i++) {
            elems[i] = getElem(i);
        }

        return elems;
    }

    @Override
    public String toString() {
        String res = "[ ";

        for (int i = 0; i < getDim(); i++) {
            res += getElem(i) + " ";
        }

        res += "]";

        return res;
    }



}
