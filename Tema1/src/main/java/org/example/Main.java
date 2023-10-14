package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Problem problem=new Problem();
        //System.out.println(problem.initialize(Arrays.asList(1,2,3,4,5,6,7,0,8)));
        //System.out.println(problem.isFinal(problem.initialize(Arrays.asList(1,2,3,4,5,6,7,0,8))));
        State s=problem.initialize(Arrays.asList(8,0,6,5,4,7,2,3,1));
//        s.printVecini();
        problem.possibleTransitions(s);
        // ai caramba

    }
}