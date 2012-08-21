/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author stellarspot
 */
public interface IMDVector {

    int getDim();
    double getElem(int n);

    double length();
    double scalar(IMDVector v);

    IMDVector add(IMDVector v);
    IMDVector sub(IMDVector v);
    IMDVector mul(double a);

    
    double[] getElems();
    //ICMDVector getCVector();

    boolean equals(IMDVector v, double delta);
}
