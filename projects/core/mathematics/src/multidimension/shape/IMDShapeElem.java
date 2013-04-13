/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.shape;

import multidimension.datatype.ICMDList;
import multidimension.mathematics.ICMDVector;

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
