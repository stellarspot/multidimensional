/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import multidimensions.mathematics.IMDVector;
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
        System.out.println("Repaint");
        canvas.repaint();
    }

    class CameraCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {


            //System.out.println("Paint: " + elements);

            if (elements == null) {
                return;
            }


            Graphics2D g2 = (Graphics2D) g;

            g2.translate(MDFrameJava2D.WIDTH / 2, MDFrameJava2D.HEIGHT / 2);
            g2.scale(1, -1);
            
            //g2.drawOval(-100, -100, 200, 200);

            for (IMDCameraSegment segment : elements.getSegments()) {
                //System.out.println("[] camera segment: " + segment);
                drawSegment(g2, segment);
            }
        }
        
        void drawSegment(Graphics2D g, IMDCameraSegment segment){
            
            IMDVector v1 = segment.getVertex1().getCordinats();
            IMDVector v2 = segment.getVertex2().getCordinats();
            
            int x1 = (int) v1.getElem(0);
            int y1 = (int) v1.getElem(1);
            int x2 = (int) v2.getElem(0);
            int y2 = (int) v2.getElem(1);
            
            //System.out.printf("x1: %d, y1: %d, x2: %d, y2: %d\n", x1, y1, x2, y2);
            
            g.drawLine(x1, y1, x2, y2);
        }
        
        
    }
}
