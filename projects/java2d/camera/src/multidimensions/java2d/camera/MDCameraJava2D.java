/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.Canvas;
import java.awt.Graphics;
import multidimensions.shape.camera.IMDCamera;
import multidimensions.shape.camera.IMDCameraElements;
import multidimensions.shape.camera.IMDCameraSegment;

/**
 *
 * @author stellarspot
 */
public class MDCameraJava2D implements IMDCamera {

    IMDCameraElements elements;
    CameraCanvas canvas = new CameraCanvas();

    @Override
    public void draw(IMDCameraElements elements) {
        System.out.println("[camera 2D] draw elements");
        for (IMDCameraSegment segment : elements.getSegments()) {
            System.out.println("camera segment: " + segment);
        }
        this.elements = elements;
    }

    class CameraCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
            
            if(elements == null){
                return;
            }
            
            
            for (IMDCameraSegment segment : elements.getSegments()) {
                System.out.println("[] camera segment: " + segment);
            }
        }

        
        
    }
}
