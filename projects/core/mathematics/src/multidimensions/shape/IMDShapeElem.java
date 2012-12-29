/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.ICMDList;
import multidimensions.mathematics.ICMDVector;

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
