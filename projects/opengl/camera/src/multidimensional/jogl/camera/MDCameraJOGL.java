/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.jogl.camera;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.SwingUtilities;
import multidimensional.datatype.IMDList;
import multidimensional.java2d.camera.IMDSwingCamera;
import multidimensional.mathematics.IMDVector;
import multidimensional.shape.IMDCameraElem;
import  multidimensional.shape.IMDCameraElem.Segment;

/**
 *
 * @author stellarspot
 */
public class MDCameraJOGL implements IMDSwingCamera {

    private GLCanvas canvas;
    private IMDList<IMDCameraElem> elems;
    private volatile boolean isPainted = false;

    public MDCameraJOGL() {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        canvas = new GLCanvas(caps);
        canvas.addGLEventListener(new SimpleScene());
    }

    @Override
    public GLCanvas getComponent() {
        return canvas;
    }

    @Override
    public void draw(final IMDList<IMDCameraElem> elems) {
        if (!isPainted) {
            isPainted = true;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    MDCameraJOGL.this.elems = elems;
                    canvas.repaint();
                }
            });
        }
    }

    class SimpleScene implements GLEventListener {

        @Override
        public void display(GLAutoDrawable drawable) {
            update();
            render(drawable);
        }

        @Override
        public void dispose(GLAutoDrawable drawable) {
        }

        @Override
        public void init(GLAutoDrawable drawable) {
        }

        @Override
        public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        }

        private void update() {
        }

        private void render(GLAutoDrawable drawable) {

            isPainted = true;

            try {

                if (elems == null) {
                    return;
                }

                float scale = 0.003f;
                GL2 gl = drawable.getGL().getGL2();

                gl.glClear(GL.GL_COLOR_BUFFER_BIT);
                gl.glBegin(GL.GL_LINES);

                for (IMDCameraElem elem : elems) {
                    IMDVector[] vertices = elem.getVertices();
                    for (Segment segment : elem.getSegments()) {
                        IMDVector v1 = vertices[segment.getVertex1()];
                        IMDVector v2 = vertices[segment.getVertex2()];

                        double x1 = v1.getElem(0);
                        double y1 = v1.getDim() < 2 ? 0 : v1.getElem(1);
                        double x2 = v2.getElem(0);
                        double y2 = v1.getDim() < 2 ? 0 : v2.getElem(1);

                        gl.glVertex2d(scale * x1, scale * y1);
                        gl.glVertex2d(scale * x2, scale * y2);
                    }
                }
                gl.glEnd();

            } finally {
                isPainted = false;
            }
        }
    }
}
