/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.java2d.camera;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import multidimensions.shape.IMDUniverse;

/**
 *
 * @author stellarspot
 */
public class MDFrameJava2D extends JFrame {

    MDCameraJava2D camera;

    public MDFrameJava2D(IMDUniverse universe) {
        this.camera = camera;
        setTitle("Java2D Frame");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        camera = new MDCameraJava2D();

        universe.getCameras().addLast(camera);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(camera.canvas, BorderLayout.CENTER);
        setVisible(true);

    }
    
    public static void invokeOnEDT(final IMDUniverse universe) throws Exception{
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                new MDFrameJava2D(universe);
            }
        });
    }
}
