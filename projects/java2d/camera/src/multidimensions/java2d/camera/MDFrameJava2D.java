/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDFrameJava2D extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    MDCameraJava2D camera;

    public MDFrameJava2D(IMDUniverse universe) {
        this.camera = camera;
        setTitle("Java2D Frame");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        camera = new MDCameraJava2D();

        universe.getCameras().addLast(camera);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(camera.canvas, BorderLayout.CENTER);

        getContentPane().add(panel);
        setVisible(true);

        animate(universe);
    }

    void animate(final IMDUniverse universe) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                    }
                    universe.evaluate();
                }
            }
        }).start();
    }

    public static void invokeOnEDT(final IMDUniverse universe) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                new MDFrameJava2D(universe);
            }
        });
    }
}
