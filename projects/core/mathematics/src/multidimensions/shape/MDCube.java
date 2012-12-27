/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import java.util.Arrays;
import multidimensions.datatype.CMDList;
import multidimensions.datatype.ICMDList;

/**
 *
 * @author stellarspot
 */
public class MDCube extends MDShape {

    int dim;
    double d;

    public MDCube(int dim, double d) {
        this.dim = dim;
        this.d = d;
        init();
    }

    protected void init() {
        int N = dim;
        int N2 = 1 << N;
        double radius = d / 2;

        double[][] array = new double[N2][N];
        double[] counter = new double[N];

        ICMDList<Pair> pairs = new CMDList<Pair>();

        for (int n = 0; n < N; n++) {
            counter[n] = -radius;
        }

        for (int n2 = 0; n2 < N2; n2++) {
            array[n2] = Arrays.copyOf(counter, N);
            boolean incFlag = true;

            for (int n = 0; n < N; n++) {
                if (counter[n] == -radius) {
                    if (incFlag) {
                        counter[n] = radius;
                        incFlag = false;
                    }
                } else {
                    if (incFlag) {
                        counter[n] = -radius;

                    }
                    pairs.addTail(new Pair(n2, n2 - (1 << n)));
                }
            }
        }

        IMDShapeVertex[] vertices = new MDShapeVertex[N2];
        for (int n2 = 0; n2 < N2; n2++) {
            vertices[n2] = new MDShapeVertex(array[n2]);
        }

        for (Pair pair : pairs) {
            segments.addTail(new MDShapeSegment(vertices[pair.index1], vertices[pair.index2]));
        }

    }

    String toString(double[] array) {
        String res = "[ ";
        for (int j = 0; j < dim; j++) {
            res += array[j] + " ";
        }
        res += "]";
        return res;
    }

    class Pair {

        int index1;
        int index2;

        public Pair(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public String toString() {
            return "Pair: " + index1 + ", " + index2;
        }
    }
}
