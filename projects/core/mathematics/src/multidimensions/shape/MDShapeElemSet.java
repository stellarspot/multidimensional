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
public abstract class MDShapeElemSet implements IMDShapeElem {

    protected int dim;
    //protected int M;
    protected double d;
    ICMDList<Segment> segments = new CMDList<Segment>();
    protected int[][] array;
    int[] grid;

    public MDShapeElemSet(int dim, double d, int M) {
        this(dim, d, toGrid(dim, M));
    }

    public MDShapeElemSet(int dim, double d, int[] grid) {
        this.dim = dim;
        this.d = d;
        this.grid = grid;
        init();
    }

    static int[] toGrid(int dim, int M) {
        int[] grid = new int[dim];
        Arrays.fill(grid, M);
        return grid;
    }

    protected int getMaxM(){
        int M = 0;

        for (int n = 0; n < dim; n++) {
            if(M < grid[n]){
                M = grid[n];
            }
        }
        return M;
    }

    protected void init() {
        int N = dim;
        int NM = 1;

        //int M1 = M - 1;
        int[] M1 = new int[dim];
        for (int i = 0; i < N; i++) {
            //NM *= M;
            NM *= grid[i];
            M1[i] = grid[i] - 1;
        }



        array = new int[NM][N];
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

    }

//    protected void init() {
//        int N = dim;
//        int NM = 1;
//
//        for (int i = 0; i < N; i++) {
//            NM *= M;
//        }
//
//        int M1 = M - 1;
//
//        array = new int[NM][N];
//        int[] counter = new int[N];
//        int[] back = new int[N];
//        int[] forward = new int[N];
//
//
//        int l = 1;
//
//        for (int n = 0; n < N; n++) {
//            back[n] = l;
//            forward[n] = l * M1;
//            l *= M;
//        }
//
////        System.out.println("forward: " + toString(forward));
////        System.out.println("back: " + toString(back));
//
//        for (int n2 = 0; n2 < NM; n2++) {
//            array[n2] = Arrays.copyOf(counter, N);
//            //System.out.println("counter: " + toString(counter));
//            for (int n = 0; n < N; n++) {
//                if (counter[n] == 0) {
//                    segments.addTail(new MDSegment(n2, n2 + forward[n]));
//                } else {
//                    segments.addTail(new MDSegment(n2, n2 - back[n]));
//                }
//
//            }
//            for (int n = 0; n < N; n++) {
//                if (counter[n] == M1) {
//                    counter[n] = 0;
//                } else {
//                    counter[n]++;
//                    break;
//                }
//            }
//        }
//
//    }
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

    @Override
    public ICMDList<Segment> getSegments() {
        return segments;
    }

    protected static class MDSegment implements Segment {

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
