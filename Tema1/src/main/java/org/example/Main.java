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
       State s=problem.initialize(Arrays.asList(2,5,3,1,0,6,4,7,8)); //4
        // State s=problem.initialize(Arrays.asList(2,7,5,0,8,4,3,1,6)); //21

        //State s=problem.initialize(Arrays.asList(3,1,2,0,4,5,6,7,8));
//        s.printVecini();
        problem.possibleTransitions(s);
        // ai caramba

    }
}