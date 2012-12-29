/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.ICMDObservableList;
import multidimensions.mathematics.IMDTransform;
import multidimensions.shape.IMDAnimation;

/**
 *
 * @author stellarspot
 */
public interface IMDShape {

    ICMDObservableList<IMDShape> getShapes();
    ICMDObservableList<IMDShapeElem> getElems();

    ICMDObservableList<IMDTransform> getTransforms();
    ICMDObservableList<IMDAnimation> getAnimations();

}
