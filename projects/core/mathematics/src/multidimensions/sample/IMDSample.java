/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.sample;

import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public interface IMDSample {
    String getTitle();
    int[] getDimensions();
    IMDUniverse getUniverse(int dim, double radius, int M);
}
