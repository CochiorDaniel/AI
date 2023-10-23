package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Problem problem=new Problem();
        //System.out.println(problem.initialize(Arrays.asList(1,2,3,4,5,6,7,0,8)));
        //System.out.println(problem.isFinal(problem.initialize(Arrays.asList(4,0,1,2,3,5,6,7,8))));

        // State s=problem.initialize(Arrays.asList(8, 6, 7, 2, 5, 4, 0, 3, 1));
        // State s=problem.initialize(Arrays.asList(2,5,3,1,0,6,4,7,8)); //4
         State s=problem.initialize(Arrays.asList(2,7,5,0,8,4,3,1,6)); //21

        //State s=problem.initialize(Arrays.asList(3,1,2,0,4,5,6,7,8));
//        s.printVecini();
//        problem.possibleTransitions(s);
        System.out.println(problem.IDDFS(s,30));
    }

    public static void test(State s)
    {
        Problem problem=new Problem();

        long start1 = System.currentTimeMillis();
        System.out.println(problem.IDDFS(s,21));
        long IDDFStime = System.currentTimeMillis() - start1;
        System.out.println("IDDFS time: "+IDDFStime + " miliseconds");

        long start2 = System.currentTimeMillis();
        System.out.println(problem.Greedy(s,"Manhattan"));
        long Manhattan = System.currentTimeMillis() - start2;
        System.out.println("Manhattan time: "+ Manhattan+ " miliseconds");

        long start3 = System.currentTimeMillis();
        System.out.println(problem.Greedy(s,"Hamming"));
        long Hamming = System.currentTimeMillis() - start3;
        System.out.println("Hamming time: "+ Hamming + " miliseconds");

        long start4 = System.currentTimeMillis();
        System.out.println(problem.Greedy(s,"Chebyshev"));
        long Chebyshev = System.currentTimeMillis() - start4;
        System.out.println("Chebyshev time: "+ Chebyshev + " miliseconds");

    }
}