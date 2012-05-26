/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author stellarspot
 */
public abstract class ACMDVector extends AMDVector implements ICMDVector {

    public void cAdd(IMDVector v) {
        for (int n = 0; n < getDim(); n++) {
            setElem(n, getElem(n) + v.getElem(n));
        }
    }

    public void cSub(IMDVector v) {
        for (int n = 0; n < getDim(); n++) {
            setElem(n, getElem(n) - v.getElem(n));
        }
    }

    public void cMul(double a) {
        for (int n = 0; n < getDim(); n++) {
            setElem(n, a * getElem(n));
        }
    }

}
