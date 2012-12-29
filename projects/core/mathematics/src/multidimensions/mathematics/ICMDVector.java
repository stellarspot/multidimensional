/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author user
 */
public interface ICMDVector extends IMDBaseVector {

    void setElem(int n, double value);

    IMDVector getIMDVector();
}
