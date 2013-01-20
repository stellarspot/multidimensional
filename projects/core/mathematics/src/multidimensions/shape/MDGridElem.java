/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import multidimensions.mathematics.CMDVector;
import multidimensions.mathematics.ICMDVector;

/**
 *
 * @author stellarspot
 */
public class MDGridElem extends MDShapeElemSet {

    ICMDVector[] vertices;

    public MDGridElem(int dim, double radius, int[] grid) {
        super(dim, radius / grid[0], grid);
    }

    public MDGridElem(int dim, double radius, int M) {
        super(dim, radius / M, M);
    }

    @Override
    protected void init() {
        super.init();

        int N = dim;
        int NM = array.length;

        vertices = new ICMDVector[NM];
        for (int n2 = 0; n2 < NM; n2++) {
            double[] coordinats = new double[N];

            for (int i = 0; i < N; i++) {
                coordinats[i] = d * array[n2][i]; // TODO
            }

            vertices[n2] = new CMDVector(coordinats);
        }
    }




    @Override
    public ICMDVector[] getVertices() {
        return vertices;
    }
}
