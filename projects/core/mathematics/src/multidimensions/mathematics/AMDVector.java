/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author Женя
 */
public abstract class AMDVector implements IMDVector {

    public double scalar(IMDVector v) {
        double s = 0;
        for(int i=0; i< getDim(); i++){
            s+= getElem(i) * v.getElem(i);
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
        ICMDVector vector = getCVector();
        vector.cAdd(v);
        return vector;
    }

    public IMDVector sub(IMDVector v) {
        ICMDVector vector = getCVector();
        vector.cSub(v);
        return vector;
    }

    public IMDVector mul(double a) {
        ICMDVector vector = getCVector();
        vector.cMul(a);
        return vector;
    }

    public ICMDVector getCVector() {
        return getCVector(getDim());
    }

    private ICMDVector getCVector(int dim) {
        CMDVector vector = new CMDVector(dim);
        for (int n = 0; n < dim; n++) {
            vector.setElem(n, getElem(n));
        }
        return vector;
    }

    @Override
    public String toString() {
        String res = "[";
        for(int i=0; i< getDim(); i++){
            res += getElem(i) +" ";
        }
        res +="]";
        
        return res;
    }

    
}
