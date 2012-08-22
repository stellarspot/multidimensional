/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.shape;

import multidimensions.shape.camera.IMDCameraElements;

/**
 *
 * @author stellarspot
 */
public interface IMDUniverseCarcass {
    void setRoot(IMDShape shape);
    IMDShape getRoot();
    IMDCameraElements getCameraElements();
}
