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
public class MDSphereElem extends MDShapeElemSet {

    ICMDVector[] vertices;

    public MDSphereElem(int dim, double radius, int M) {
        super(dim - 1, radius, M);
    }

    @Override
    protected void init() {
        super.init();

        int N = dim;
        int NM = array.length;

        vertices = new ICMDVector[NM];

        //System.out.println("N: " + N);
        //System.out.println("NM: " + NM);

        double delta = 2 * Math.PI / M;

        for (int n2 = 0; n2 < NM; n2++) {
            double[] coordinats = new double[N];

            for (int i = 0; i < N; i++) {
                coordinats[i] = delta * array[n2][i]; // TODO
            }

            double[] res = new double[N + 1];
            //System.out.println("d: " + d);
            getSphereVector(d, coordinats, N, res);
            vertices[n2] = new CMDVector(res);
        }

    }

    static void getSphereVector(double R, double[] angles, int N, double[] result) {

        if (N == 1) {
            result[0] = R * Math.cos(angles[0]);
            result[1] = R * Math.sin(angles[0]);
        } else {
            result[N] = R * Math.sin(angles[N - 1]);
            getSphereVector(R * Math.cos(angles[N - 1]), angles, N - 1, result);
        }
    }

    @Override
    public ICMDVector[] getVertices() {
        return vertices;
    }
}
