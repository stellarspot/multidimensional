/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.ICMDObservableList;
import multidimensions.mathematics.IMDTransform;

/**
 *
 * @author stellarspot
 */
public interface IMDShape {

    ICMDObservableList<IMDShape> getShapes();
    ICMDObservableList<IMDShapeSegment> getSegments();
    ICMDObservableList<IMDTransform> getTransforms();
    ICMDObservableList<IMDAnimation> getAnimations();
}
