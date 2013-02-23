/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.opengl.camera;

import com.jogamp.opengl.util.FPSAnimator;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import multidimensions.datatype.IMDList;
import multidimensions.mathematics.IMDVector;
import multidimensions.shape.IMDCamera;
import multidimensions.shape.IMDCameraElem;
import multidimensions.shape.IMDCameraElem.Segment;

/**
 *
 * @author stellarspot
 */
public class MDCameraOpenGL implements IMDCamera {

    private GLCanvas canvas;
    IMDList<IMDCameraElem> elems;
    private volatile boolean isPainted = false;

    public MDCameraOpenGL() {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);

        canvas = new GLCanvas(caps);

        canvas.addGLEventListener(new SimpleScene());

//        FPSAnimator animator = new FPSAnimator(canvas, 60);
//        animator.add(canvas);
//        animator.start();
    }

    public GLCanvas getCanvas() {
        return canvas;
    }

    @Override
    public void draw(IMDList<IMDCameraElem> elems) {
        //System.out.println("Draw elemes: " + elems.getSize());

        if (!isPainted) {
            isPainted = true;
            this.elems = elems;
            canvas.repaint();
        }

    }

    class SimpleScene implements GLEventListener {

        private double theta = 0;
        private double s = 0;
        private double c = 0;

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


                float scale = 0.004f;
                GL2 gl = drawable.getGL().getGL2();

                gl.glClear(GL.GL_COLOR_BUFFER_BIT);
                //gl.glScalef(scale, scale, 1);

                gl.glBegin(GL.GL_LINES);


                for (IMDCameraElem elem : elems) {
                    IMDVector[] vertices = elem.getVertices();
                    for (Segment segment : elem.getSegments()) {
                        IMDVector v1 = vertices[segment.getVertex1()];
                        IMDVector v2 = vertices[segment.getVertex2()];

                        //System.out.println("segment: " + v1 + ", " + v2);

//                        int x1 = (int) v1.getElem(0);
//                        int y1 = v1.getDim() < 2 ? 0 : (int) v1.getElem(1);
//                        int x2 = (int) v2.getElem(0);
//                        int y2 = v1.getDim() < 2 ? 0 : (int) v2.getElem(1);
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
