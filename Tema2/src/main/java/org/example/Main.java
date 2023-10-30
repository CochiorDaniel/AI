package org.example;

import org.javatuples.Pair;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       int [][] instTable = {{1,0,3,4,5,0,0,0,0},
                             {0,8,0,0,7,0,0,2,0},
                             {7,0,0,0,0,1,5,0,0},
                             {0,0,0,0,6,0,0,0,0},
                             {0,0,0,7,1,0,3,0,0},
                             {9,6,0,0,0,0,8,0,0},
                             {0,1,0,9,0,5,2,7,0},
                             {2,0,0,0,0,0,0,1,0},
                             {0,0,9,0,0,3,0,8,0}};

        List<Pair<Integer,Integer>> instPozPare =
                List.of(new Pair<>(0,3), new Pair<>(0,5), new Pair<>(0,7), new Pair<>(0,8),
                        new Pair<>(1,1), new Pair<>(1,2), new Pair<>(1,5), new Pair<>(1,7),
                        new Pair<>(2,1), new Pair<>(2,2), new Pair<>(2,3), new Pair<>(2,8),
                        new Pair<>(3,1), new Pair<>(3,4), new Pair<>(3,5), new Pair<>(3,8),
                        new Pair<>(4,0), new Pair<>(4,2), new Pair<>(4,7), new Pair<>(4,8),
                        new Pair<>(5,1), new Pair<>(5,3), new Pair<>(5,5), new Pair<>(5,6),
                        new Pair<>(6,0), new Pair<>(6,2), new Pair<>(6,4), new Pair<>(6,6),
                        new Pair<>(7,0), new Pair<>(7,3), new Pair<>(7,4), new Pair<>(7,6),
                        new Pair<>(8,0), new Pair<>(8,4), new Pair<>(8,6), new Pair<>(8,7));

        Problem problem=new Problem();
        Sudoku s = problem.initialize(instTable,instPozPare);
<<<<<<< HEAD
        int [][] reaz = problem.BKT_with_FC_MRV(s);
        for(int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(reaz[i][j] + " ");
            }
            System.out.println();
        }
=======
        System.out.println(problem.BKT_with_FC(s));
>>>>>>> origin/main


    }
}