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
public class MDSphere extends MDShape {

    int dim;
    int M;
    double d;

    public MDSphere(int dim, double d, int M) {
        this.dim = dim;
        this.d = d;
        this.M = M;
        init();
    }

    protected void init() {
        int N = dim - 1;
        int NM = 1;

        for (int i = 0; i < N; i++) {
            NM *= M;
        }

        int M1 = M - 1;
//        System.out.println("N :" + N);
//        System.out.println("NM: " + NM);
//        System.out.println("M : " + M);
//        System.out.println("M1: " + M1);
//        System.out.println("d: " + d);

        int[][] array = new int[NM][N];
        int[] counter = new int[N];
        int[] back = new int[N];
        int[] forward = new int[N];



        ICMDList<Pair> pairs = new CMDList<Pair>();


        int l = 1;

        for (int n = 0; n < N; n++) {
            back[n] = l;
            forward[n] = l * M1;
            l *= M;
        }

//        System.out.println("forward: " + toString(forward));
//        System.out.println("back: " + toString(back));

        for (int n2 = 0; n2 < NM; n2++) {
            array[n2] = Arrays.copyOf(counter, N);
            //System.out.println("counter: " + toString(counter));
            for (int n = 0; n < N; n++) {
                if (counter[n] == 0) {
                    pairs.addTail(new Pair(n2, n2 + forward[n]));
                } else {
                    pairs.addTail(new Pair(n2, n2 - back[n]));
                }

            }
            for (int n = 0; n < N; n++) {
                if (counter[n] == M1) {
                    counter[n] = 0;
                } else {
                    counter[n]++;
                    break;
                }
            }
        }

        double deltaAngle = 2 * Math.PI / M;

        IMDShapeVertex[] vertices = new MDShapeVertex[NM];
        for (int n2 = 0; n2 < NM; n2++) {
            double[] coordinats = new double[N];
            for (int i = 0; i < N; i++) {
                coordinats[i] = deltaAngle * array[n2][i];
            }

            double[] res = new double[N + 1];
            //System.out.println("");
            getSphereVector(d, coordinats, N, res);
            //System.out.println("sphere vector: " + toString(res));

            vertices[n2] = new MDShapeVertex(res);
        }

        for (Pair pair : pairs) {
//            System.out.println("pair: " + pair);
//            System.out.println("vertex1: " + vertices[pair.index1]);
//            System.out.println("vertex2: " + vertices[pair.index2]);
//            System.out.println("");
            segments.addTail(new MDShapeSegment(vertices[pair.index1], vertices[pair.index2]));
        }

    }

    static void getSphereVector(double R, double[] angles, int N, double[] result) {
//        System.out.println("N: " + N);

        if (N == 1) {
            result[0] = R * Math.cos(angles[0]);
            result[1] = R * Math.sin(angles[0]);
        } else {
            result[N] = R * Math.sin(angles[N - 1]);
            getSphereVector(R * Math.cos(angles[N - 1]), angles, N - 1, result);
        }
//        System.out.println("angles: " + toString(angles));
//        System.out.println("res: " + toString(result));
    }

    static String toString(int[] array) {
        String res = "[ ";
        for (int j = array.length - 1; 0 <= j; j--) {
            res += array[j] + " ";
        }
        res += "]";
        return res;
    }

    static String toString(double[] array) {
        String res = "[ ";
        for (int j = array.length - 1; 0 <= j; j--) {
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
