/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.shape;

import multidimensional.datatype.IMDList;
import multidimensional.mathematics.IMDVector;

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
