/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
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
    private volatile boolean isPainted = false;

    @Override
    public void draw(final IMDCameraElements elements) {

        if (!isPainted) {
            isPainted = true;
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    MDCameraJava2D.this.elements = elements;
                    canvas.repaint();
                }
            });
        }
    }

    //class CameraCanvas extends Canvas {
    class CameraCanvas extends JComponent {

        @Override
        public void paint(Graphics g) {

            isPainted = true;

            try {


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

            } finally {
                isPainted = false;
            }
        }

        void drawSegment(Graphics2D g, IMDCameraSegment segment) {

            IMDVector v1 = segment.getVertex1().getCordinats();
            IMDVector v2 = segment.getVertex2().getCordinats();

            int x1 = (int) v1.getElem(0);
            int y1 = v1.getDim() < 2 ? 0 : (int) v1.getElem(1);
            int x2 = (int) v2.getElem(0);
            int y2 = v1.getDim() < 2 ? 0 : (int) v2.getElem(1);

            //System.out.printf("x1: %d, y1: %d, x2: %d, y2: %d\n", x1, y1, x2, y2);

            g.drawLine(x1, y1, x2, y2);
        }
    }
}
