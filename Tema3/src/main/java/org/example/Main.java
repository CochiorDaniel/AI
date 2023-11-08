package org.example;

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();
        //problem.gameTime(problem.initialize());
        System.out.println(problem.MiniMaxGame(problem.initialize()));
    }
}