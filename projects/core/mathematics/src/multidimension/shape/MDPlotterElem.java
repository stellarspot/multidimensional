/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.shape;

import multidimension.datatype.CMDList;
import multidimension.datatype.ICMDList;
import multidimension.mathematics.ICMDVector;
import multidimension.mathematics.IMDTransform;

/**
 *
 * @author stellarspot
 */
public class MDPlotterElem implements IMDShapeElem {

    private IMDTransform transform;
    private IMDShapeElem set;
    ICMDVector[] vertices;
    //ICMDList<IMDShapeElem.Segment> segments = new CMDList<IMDShapeElem.Segment>();

    public MDPlotterElem(IMDTransform transform, IMDShapeElem set) {
        this.transform = transform;
        this.set = set;

        
        ICMDVector[] setVectors = set.getVertices();
        vertices = new ICMDVector[setVectors.length];

        for (int i = 0; i < setVectors.length; i++) {
            vertices[i] = transform.transform(setVectors[i]).getCVector();
        }
    }

    @Override
    public ICMDVector[] getVertices() {
        return vertices;
    }

    @Override
    public ICMDList<Segment> getSegments() {
        return set.getSegments();
    }
}
