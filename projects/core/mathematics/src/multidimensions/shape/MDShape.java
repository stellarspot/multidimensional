/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.CMDObservableList;
import multidimensions.datatype.ICMDObservableList;
import multidimensions.mathematics.IMDTransform;
import multidimensions.shape.IMDAnimation;

/**
 *
 * @author stellarspot
 */
public class MDShape implements IMDShape {

    protected ICMDObservableList<IMDShape> shapes = new CMDObservableList<IMDShape>();
    protected ICMDObservableList<IMDShapeElem> elems = new CMDObservableList<IMDShapeElem>();
    protected ICMDObservableList<IMDTransform> transforms = new CMDObservableList<IMDTransform>();
    protected ICMDObservableList<IMDAnimation> animations = new CMDObservableList<IMDAnimation>();

    public MDShape(IMDShapeElem... elems) {
        this.elems.addTail(elems);
    }

    @Override
    public ICMDObservableList<IMDShape> getShapes() {
        return shapes;
    }

    @Override
    public ICMDObservableList<IMDShapeElem> getElems() {
        return elems;
    }

    public ICMDObservableList<IMDTransform> getTransforms() {
        return transforms;
    }

    public ICMDObservableList<IMDAnimation> getAnimations() {
        return animations;
    }
}
