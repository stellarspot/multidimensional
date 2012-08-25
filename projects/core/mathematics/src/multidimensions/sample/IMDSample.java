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
    IMDUniverse getUniverse(int dim);
}
