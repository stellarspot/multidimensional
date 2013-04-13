/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.shape;

import multidimension.datatype.ICMDObservableList;

/**
 *
 * @author stellarspot
 */
public interface IMDUniverse {

    IMDShape getShape();
    void setShape(IMDShape shape);

    ICMDObservableList<IMDCamera> getCameras();

    void evaluate();
}
