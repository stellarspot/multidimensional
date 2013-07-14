/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.shape;

import multidimensional.datatype.ICMDObservableList;
import multidimensional.mathematics.IMDTransform;
import multidimensional.shape.IMDAnimation;

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
