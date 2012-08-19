/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.ICMDObservableList;

/**
 *
 * @author stellarspot
 */
public interface IMDShape {

    ICMDObservableList<IMDShapeSegment> getSegments();

    ICMDObservableList<IMDShape> getShapes();
}
