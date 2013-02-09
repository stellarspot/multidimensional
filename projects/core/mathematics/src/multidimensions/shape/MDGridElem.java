/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import java.util.Arrays;
import multidimensions.datatype.CMDList;
import multidimensions.datatype.ICMDList;
import multidimensions.mathematics.CMDVector;
import multidimensions.mathematics.ICMDVector;

/**
 *
 * @author stellarspot
 */
public class MDGridElem implements IMDShapeElem {

    ICMDVector[] vertices;
    ICMDList<Segment> segments = new CMDList<Segment>();

    public MDGridElem(int dim, double radius, int M) {
        this(dim, radius / M, toGrid(dim, M));
    }

    public MDGridElem(int dim, double radius, int[] grid) {

        int[][] array = getGrid(dim, grid, segments);

        int N = dim;
        int NM = array.length;

        double d = radius / getMaxM(grid);
        //System.out.println("radius: " + radius + ", d: " + d);

        double[] centers = new double[N];
        for (int i = 0; i < N; i++) {
           //centers[i] = (grid[i] - 1) * d / 2;
            centers[i] = grid[i] * d / 2;
            //System.out.println("i: " + i + ", grid: " + grid[i] + ", center: " + centers[i]);

        }

        vertices = new ICMDVector[NM];
        for (int n2 = 0; n2 < NM; n2++) {
            double[] coordinats = new double[N];

            for (int i = 0; i < N; i++) {
                coordinats[i] = d * array[n2][i] - centers[i]; // TODO
            }

            vertices[n2] = new CMDVector(coordinats);
        }
    }

//    @Override
//    protected void init() {
//        super.init();
//
//        int N = dim;
//        int NM = array.length;
//
//        vertices = new ICMDVector[NM];
//        for (int n2 = 0; n2 < NM; n2++) {
//            double[] coordinats = new double[N];
//
//            for (int i = 0; i < N; i++) {
//                coordinats[i] = d * array[n2][i]; // TODO
//            }
//
//            vertices[n2] = new CMDVector(coordinats);
//        }
//    }
    @Override
    public ICMDVector[] getVertices() {
        return vertices;
    }

    @Override
    public ICMDList<Segment> getSegments() {
        return segments;
    }

    public static int getMaxM(int[] grid) {
        int M = 0;

        for (int n = 0; n < grid.length; n++) {
            if (M < grid[n]) {
                M = grid[n];
            }
        }
        return M;
    }

    public static int[] toGrid(int dim, int M) {
        int[] grid = new int[dim];
        Arrays.fill(grid, M);
        return grid;
    }

    public static int[][] getGrid(int dim, int[] cells, ICMDList<Segment> segments) {
        int N = dim;
        int NM = 1;


        int[] grid = new int[cells.length];
        for (int i = 0; i < cells.length; i++) {
            grid[i] = cells[i] + 1;
        }


        //int M1 = M - 1;
        int[] M1 = new int[dim];
        for (int i = 0; i < N; i++) {
            //NM *= M;
            NM *= grid[i];
            M1[i] = grid[i] - 1;
        }



        int[][] array = new int[NM][N];
        int[] counter = new int[N];
        int[] back = new int[N];
        int[] forward = new int[N];


        int l = 1;

        for (int n = 0; n < N; n++) {
            back[n] = l;
            //forward[n] = l * M1;
            forward[n] = l * M1[n];
            //l *= M;
            l *= grid[n];
        }

//        System.out.println("forward: " + toString(forward));
//        System.out.println("back: " + toString(back));

        for (int n2 = 0; n2 < NM; n2++) {
            array[n2] = Arrays.copyOf(counter, N);
            //System.out.println("counter: " + toString(counter));
            for (int n = 0; n < N; n++) {
                if (counter[n] == 0) {
                    segments.addTail(new MDSegment(n2, n2 + forward[n]));
                } else {
                    segments.addTail(new MDSegment(n2, n2 - back[n]));
                }

            }
            for (int n = 0; n < N; n++) {
                if (counter[n] == M1[n]) {
                    counter[n] = 0;
                } else {
                    counter[n]++;
                    break;
                }
            }
        }

        return array;
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

    public static class MDSegment implements Segment {

        int vertex1;
        int vertex2;

        public MDSegment(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        public int getVertex1() {
            return vertex1;
        }

        public int getVertex2() {
            return vertex2;
        }

        @Override
        public String toString() {
            return "Pair: " + vertex1 + ", " + vertex2;
        }
    }
}