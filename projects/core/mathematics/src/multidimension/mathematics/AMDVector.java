/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.mathematics;

/**
 *
 * @author stellarspot
 */
public abstract class AMDVector extends AMDBaseVector implements IMDVector {


    public IMDVector add(IMDVector v) {
        double[] elems = getElemsCopy();

        for (int i = 0; i < getDim(); i++) {
            elems[i] += v.getElem(i);
        }
        return new MDVector(elems);
    }

    public IMDVector sub(IMDVector v) {
        double[] elems = getElemsCopy();

        for (int i = 0; i < getDim(); i++) {
            elems[i] -= v.getElem(i);
        }
        return new MDVector(elems);
    }

    public IMDVector mul(double a) {
        double[] elems = getElemsCopy();

        for (int i = 0; i < getDim(); i++) {
            elems[i] *= a;
        }
        return new MDVector(elems);
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
