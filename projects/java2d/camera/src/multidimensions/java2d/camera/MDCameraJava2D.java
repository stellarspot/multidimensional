/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import multidimensions.datatype.IMDList;
import multidimensions.mathematics.IMDVector;
import multidimensions.shape.IMDCameraElem;

/**
 *
 * @author stellarspot
 */
public class MDCameraJava2D implements IMDSwingCamera {

    IMDList<IMDCameraElem> elems;
    private CameraCanvas canvas = new CameraCanvas();
    private volatile boolean isPainted = false;

    @Override
    public void draw(final IMDList<IMDCameraElem> elems) {

        if (!isPainted) {
            isPainted = true;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    MDCameraJava2D.this.elems = elems;
                    canvas.repaint();
                }
            });
        }
    }

    @Override
    public Component getComponent() {
        return canvas;
    }

    class CameraCanvas extends JComponent {

        @Override
        public void paint(Graphics g) {

            isPainted = true;

            try {
                if (elems == null) {
                    return;
                }

                Graphics2D g2 = (Graphics2D) g;

                g2.translate(MDFrameJava2D.WIDTH / 2, MDFrameJava2D.HEIGHT / 2);
                g2.scale(1, -1);

                for (IMDCameraElem elem : elems) {
                    drawElem(g2, elem);
                }

            } finally {
                isPainted = false;
            }
        }

        void drawElem(Graphics2D g, IMDCameraElem elem) {

            IMDVector[] vertices = elem.getVertices();

            for (IMDCameraElem.Segment segment : elem.getSegments()) {
                drawSegment(g, segment, vertices);
            }
        }

        void drawSegment(Graphics2D g, IMDCameraElem.Segment segment, IMDVector[] vertices) {

            IMDVector v1 = vertices[segment.getVertex1()];
            IMDVector v2 = vertices[segment.getVertex2()];

            //System.out.println("segment: " + v1 + ", " + v2);

            int x1 = (int) v1.getElem(0);
            int y1 = v1.getDim() < 2 ? 0 : (int) v1.getElem(1);
            int x2 = (int) v2.getElem(0);
            int y2 = v1.getDim() < 2 ? 0 : (int) v2.getElem(1);

            //System.out.printf("x1: %d, y1: %d, x2: %d, y2: %d\n", x1, y1, x2, y2);

            g.drawLine(x1, y1, x2, y2);
        }
    }
}
