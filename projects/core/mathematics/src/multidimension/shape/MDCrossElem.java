/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.shape;

import multidimension.datatype.CMDList;
import multidimension.datatype.ICMDList;
import multidimension.mathematics.CMDVector;
import multidimension.mathematics.ICMDVector;

/**
 *
 * @author stellarspot
 */
public class MDCrossElem implements IMDShapeElem {


    int dim;
    double d;

    ICMDVector[] vertices;
    ICMDList<IMDShapeElem.Segment> segments = new CMDList<IMDShapeElem.Segment>();

    public MDCrossElem(int dim, double d) {
        this.dim = dim;
        this.d = d;

        int n = 0;
        vertices = new ICMDVector[2 * dim];
        for (int i = 0; i < dim; i++) {
            CMDVector vertex1 = new CMDVector(dim);
            CMDVector vertex2 = new CMDVector(dim);
            vertex1.setElem(i, d);
            vertex2.setElem(i, -d);

            vertices[n] = vertex1;
            vertices[n + 1] = vertex2;

            segments.addTail(new Segment(n));
            n += 2;
        }
    }


    @Override
    public ICMDVector[] getVertices() {
        return vertices;
    }

    @Override
    public ICMDList<IMDShapeElem.Segment> getSegments() {
        return segments;
    }

    static class Segment implements IMDShapeElem.Segment {

        int vertex;

        public Segment(int vertex) {
            this.vertex = vertex;
        }

        public int getVertex1() {
            return vertex;
        }

        public int getVertex2() {
            return vertex + 1;
        }
    }

}
