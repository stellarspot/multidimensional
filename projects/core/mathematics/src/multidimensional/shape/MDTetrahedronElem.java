/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.shape;

import multidimensional.datatype.CMDList;
import multidimensional.datatype.ICMDList;
import multidimensional.mathematics.CMDVector;
import multidimensional.mathematics.ICMDVector;

/**
 *
 * @author stellarspot
 */
public class MDTetrahedronElem implements IMDShapeElem {

    ICMDVector[] vertices;
    ICMDList<IMDShapeElem.Segment> segments = new CMDList<IMDShapeElem.Segment>();

    public MDTetrahedronElem(int dim, double r) {

        double R = 2 * r;

        ICMDVector v1 = new CMDVector(dim);
        ICMDVector v2 = new CMDVector(dim);
        v1.setElem(0, r);
        v2.setElem(0, -r);


        vertices = new ICMDVector[dim + 1];
        vertices[0] = v1;
        vertices[1] = v2;


        double a = r;

        for (int n = 2; n < dim + 1; n++) {

            int M = n + 1;
            double h = Math.sqrt(R * R - a * a);
            a = M * h / (M + 1);
            double b = h - a;

            for (int i = 0; i < n; i++) {
                vertices[i].setElem(n - 1, -b);

            }

            ICMDVector v = new CMDVector(dim);
            v.setElem(n - 1, a);

            vertices[n] = v;
        }


        for (int i = 0; i < dim + 1; i++) {
            for (int j = 0; j < i; j++) {
                segments.addTail(new MDGridElem.MDSegment(i, j));
            }
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
}
