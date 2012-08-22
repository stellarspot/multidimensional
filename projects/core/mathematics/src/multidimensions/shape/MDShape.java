/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.CMDObservableList;
import multidimensions.datatype.ICMDObservableList;
import multidimensions.mathematics.IMDTransform;

/**
 *
 * @author stellarspot
 */
public class MDShape implements IMDShape {

    protected ICMDObservableList<IMDShape> shapes = new CMDObservableList<IMDShape>();
    protected ICMDObservableList<IMDShapeSegment> segments = new CMDObservableList<IMDShapeSegment>();
    protected ICMDObservableList<IMDTransform> transforms = new CMDObservableList<IMDTransform>();

    
    public ICMDObservableList<IMDShapeSegment> getSegments() {
        return segments;
    }

    public ICMDObservableList<IMDShape> getShapes() {
        return shapes;
    }

    public ICMDObservableList<IMDTransform> getTransforms() {
        return transforms;
    }
}
