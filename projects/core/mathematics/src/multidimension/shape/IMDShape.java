/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.shape;

import multidimension.datatype.ICMDObservableList;
import multidimension.mathematics.IMDTransform;
import multidimension.shape.IMDAnimation;

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
