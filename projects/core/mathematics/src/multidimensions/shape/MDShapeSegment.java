/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.shape;

import multidimensions.mathematics.IMDVector;

/**
 *
 * @author stellarspot
 */
public class MDShapeSegment implements IMDShapeSegment{

    IMDShapeVertex vertex1;
    IMDShapeVertex vertex2;

    public MDShapeSegment(IMDVector v1, IMDVector v2) {
        this(new MDShapeVertex(v1), new MDShapeVertex(v2));
    }
    
    public MDShapeSegment(IMDShapeVertex vertex1, IMDShapeVertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    
    
    public IMDShapeVertex getVertex1() {
        return vertex1;
    }

    public IMDShapeVertex getVertex2() {
        return vertex2;
    }

    @Override
    public String toString() {
        return "(" + vertex1 + ", " + vertex2 +")";
    }

    
}
