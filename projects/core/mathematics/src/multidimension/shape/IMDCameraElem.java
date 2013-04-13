/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimension.shape;

import multidimension.datatype.IMDList;
import multidimension.mathematics.IMDVector;

/**
 *
 * @author stellarspot
 */
public interface IMDCameraElem {

    IMDVector[] getVertices();
    IMDList<Segment> getSegments();

    interface Segment {

        int getVertex1();
        int getVertex2();
    }
}
