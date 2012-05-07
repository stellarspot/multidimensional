/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author stellarspot
 */
public interface ICMDVector extends IMDVector {

    void setElem(int index, double value);

    void cAdd(IMDVector v);
    void cSub(IMDVector v);
    void cMul(double a);
}
