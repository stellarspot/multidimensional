/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.datatype.CMDObservableList;
import multidimensions.datatype.ICMDObservableList;
import multidimensions.shape.camera.IMDCamera;
import multidimensions.shape.camera.IMDCameraElements;

/**
 *
 * @author stellarspot
 */
public class MDUniverse implements IMDUniverse {

    //protected IMDShape shape;
    protected IMDUniverseCarcass carcass;
    protected ICMDObservableList<IMDCamera> cameras = new CMDObservableList<IMDCamera>();

    
    
    public MDUniverse(IMDShape shape) {        
        this(new MDUniverseCarcas(), shape);
    }
    
    public MDUniverse(IMDUniverseCarcass carcass, IMDShape shape){
        this.carcass = carcass;
        carcass.setRoot(shape);
    }
    
    
    public IMDShape getShape() {
        return carcass.getRoot();
    }

    public void setShape(IMDShape shape) {
        carcass.setRoot(shape);
    }

    public ICMDObservableList<IMDCamera> getCameras() {
        return cameras;
    }

    public void evaluate() {
        
        carcass.evaluate();
        IMDCameraElements elems = carcass.getCameraElements();
        
        
        
        for(IMDCamera camera:cameras){
            camera.draw(elems);
        }
    }
}
