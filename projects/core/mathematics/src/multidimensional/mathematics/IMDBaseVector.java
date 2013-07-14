/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.mathematics;

/**
 *
 * @author stellarspot
 */
public interface IMDBaseVector {

    int getDim();
    double getElem(int n);

    double length();
    double scalar(IMDBaseVector v);

    double[] getElemsCopy();
    //ICMDVector getCVector();

    boolean equals(IMDBaseVector v, double delta);
}
