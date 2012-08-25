/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.shape;

/**
 *
 * @author stellarspot
 */
public class MDCross extends MDShape{

    int dim;
    double d;

    public MDCross(int dim, double d) {
        this.dim = dim;
        this.d = d;
        init();
    }

    protected void init(){
        for(int i=0; i < dim; i++){
            double[] v1 = new double[dim];
            double[] v2 = new double[dim];
            v1[i] = d;
            v2[i] = -d;

            getSegments().addLast(new MDShapeSegment(new MDShapeVertex(v1), new MDShapeVertex(v2)));

        }

    }

}
