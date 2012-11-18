/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.opengl.camera;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import multidimensions.shape.camera.IMDCamera;
import multidimensions.shape.camera.IMDCameraElements;

/**
 *
 * @author stellarspot
 */
public class MDCameraOpenGL implements IMDCamera{

    private GLCanvas canvas;

    public MDCameraOpenGL(){
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);

        canvas = new GLCanvas(caps);

    }

    @Override
    public void draw(IMDCameraElements elements) {

    }


}
