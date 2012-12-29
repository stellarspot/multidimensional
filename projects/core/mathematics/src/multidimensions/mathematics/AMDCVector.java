/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.mathematics;

/**
 *
 * @author stellarspot
 */
public abstract class AMDCVector extends AMDBaseVector implements ICMDVector {

    @Override
    public IMDVector getIMDVector() {
        return new MDVector(this);
    }


}
