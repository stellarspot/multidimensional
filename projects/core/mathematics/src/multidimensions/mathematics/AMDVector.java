/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author stellarspot
 */
public abstract class AMDVector implements IMDVector {

    public double scalar(IMDVector v) {
        double s = 0;
        for (int i = 0; i < getDim(); i++) {
            s += getElem(i) * v.getElem(i);
        }
        return s;
    }

    public double length() {
        return Math.sqrt(scalar(this));
    }

    public boolean equals(IMDVector v, double delta) {
        return sub(v).length() <= delta;
    }

    public IMDVector add(IMDVector v) {
        double[] elems = getElems();

        for (int i = 0; i < getDim(); i++) {
            elems[i] += v.getElem(i);
        }
        return new MDVector(elems);
    }

    public IMDVector sub(IMDVector v) {
        double[] elems = getElems();

        for (int i = 0; i < getDim(); i++) {
            elems[i] -= v.getElem(i);
        }
        return new MDVector(elems);
    }

    public IMDVector mul(double a) {
        double[] elems = getElems();

        for (int i = 0; i < getDim(); i++) {
            elems[i] *= a;
        }
        return new MDVector(elems);
    }

    protected double[] getElems() {
        double[] elems = new double[getDim()];
        for (int i = 0; i < getDim(); i++) {
            elems[i] = getElem(i);
        }

        return elems;
    }

//    public ICMDVector getCVector() {
//        return getCVector(getDim());
//    }
//
//    private ICMDVector getCVector(int dim) {
//        CMDVector vector = new CMDVector(dim);
//        for (int n = 0; n < dim; n++) {
//            vector.setElem(n, getElem(n));
//        }
//        return vector;
//    }
    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < getDim(); i++) {
            res += getElem(i) + " ";
        }
        res += "]";

        return res;
    }
}
