/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author stellarspot
 */
public class MDCube {

    int dim;
    double d;

    public MDCube(int dim, double d) {
        this.dim = dim;
        this.d = d;
        init();
    }

    void init() {
        int N = dim;
        int NN = 1 << N;
        int V = 2;

        System.out.println("NN: " + NN);

        double[][] vertices = new double[NN][];
        double[][] copy = new double[NN][];
        //CMDList<Pair> pairs = new CMDList<Pair>();
        //CMDList<Object> pairs = new CMDList<Object>();
        List<Pair> pairs = new LinkedList<Pair>();

        double[] v1 = new double[N];
        double[] v2 = new double[N];
        v1[0] = d;
        v2[0] = -d;

        vertices[0] = v1;
        vertices[1] = v2;

        pairs.add(new Pair(v1, v2));

        for (int i = 1; i < N; i++) {
            System.out.println("V: " + V);
            //show("Vertices: ", vertices);
            copy(vertices, copy, V);
            //show("Copy: ", copy);

            for (int j = 0; j < V; j++) {
                vertices[j][i] = d;
                copy[j][i] = -d;

                pairs.add(new Pair(vertices[j], copy[j]));

                for (int v = 0; v < V; v++) {
                    vertices[V + v] = copy[v];
                }
            }
            V *= 2;
            show("Vertices: ", vertices);
        }

        System.out.println("pairs: " + pairs.size());

        for(Pair pair: pairs){
            System.out.println(pair);
        }

    }

    void copy(double[][] from, double[][] to, int N) {
        for (int i = 0; i < N; i++) {
            to[i] = Arrays.copyOf(from[i], dim);
        }
    }

    void show(String text, double[][] array) {
        System.out.println(text + ": " + array.length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                break;
            }

            System.out.println(i + " " + toString(array[i]));
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

        double[] v1;
        double[] v2;

        public Pair(double[] v1, double[] v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public String toString() {
            return "Pair: " + toString(v1) + ", " + toString(v2);
        }
    }
}
