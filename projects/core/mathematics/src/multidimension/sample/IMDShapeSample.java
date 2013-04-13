/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimension.sample;

import multidimension.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public interface IMDShapeSample {
    String getTitle();
    int[] getDimensions();
    IMDUniverse getUniverse(int dim, double radius, int M);
}
