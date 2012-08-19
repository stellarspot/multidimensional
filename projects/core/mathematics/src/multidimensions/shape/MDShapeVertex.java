/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.shape;

import multidimensions.mathematics.IMDVector;

/**
 *
 * @author stellarspot
 */
public class MDShapeVertex implements IMDShapeVertex{
    
    
    private IMDVector coordinats;

    public MDShapeVertex(IMDVector coordinats) {
        this.coordinats = coordinats;
    }
    

    public IMDVector getCoordinats() {
        return coordinats;
    }

    @Override
    public String toString() {
        return "" + coordinats;
    }
    
    

}
