/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.IMDList;
import multidimensions.mathematics.IMDVector;

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
