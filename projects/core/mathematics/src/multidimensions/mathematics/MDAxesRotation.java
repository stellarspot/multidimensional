/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.mathematics;

/**
 *
 * @author stellarspot
 */
public class MDAxesRotation implements IMDTransform{


    int n;
    int m;
    double angle;

    double cos;
    double sin;

    public MDAxesRotation(int n, int m, double angle) {
        this.n = n;
        this.m = m;
        this.angle = angle;

        cos = Math.cos(angle);
        sin = Math.sin(angle);
    }

    
    public void setAngle(double angle){
        this.angle = angle;

        cos = Math.cos(angle);
        sin = Math.sin(angle);
    }

    public IMDVector transform(IMDVector vector) {

        //ICMDVector v = vector.getCVector();
        
        int dim = vector.getDim();
        
        double[] elems = vector.getElems();
        
        double x1 = elems[n];
        double y1 = elems[m];

        double x2 = x1 * cos + y1 * sin;
        double y2 = -x1 * sin + y1 * cos;
        
        elems[n] = x2;
        elems[m] = y2;
        
        return new MDVector(elems);
    }

//    public static MDAxesRotation[] getRotation(int dim, double angle){
//        int len = dim * (dim-1) / 2;
//        MDAxesRotation[] transforms = new MDAxesRotation[len];
//
//        int l = 0;
//        for(int n = 0; n < dim; n++){
//            for(int m = 0; m < n; m++){
//                transforms[l] = new MDAxesRotation(n, m, angle);
//                l++;
//            }
//        }
//
//        return transforms;
//
//    }

}
