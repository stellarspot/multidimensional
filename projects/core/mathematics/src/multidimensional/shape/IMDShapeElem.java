/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.shape;

import multidimensional.datatype.ICMDList;
import multidimensional.mathematics.ICMDVector;

/**
 *
 * @author stellarspot
 */
public interface IMDShapeElem {

    ICMDVector[] getVertices();
    ICMDList<Segment> getSegments();

    interface Segment {

        int getVertex1();
        int getVertex2();
    }
}
