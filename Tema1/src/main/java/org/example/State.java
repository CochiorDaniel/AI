package org.example;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private int matrix[][] = new int[3][3];
    private Pair<Integer,Integer> pair;

    public int[][] getMatrix() {
        return matrix;
    }
    public Pair<Integer,Integer> getPair()
    {
        return pair;
    }

    public State(int[][] matrix, Pair<Integer, Integer> pair) {
        this.matrix = matrix;
        this.pair = pair;
    }

    @Override
    public String toString() {
        return "State{" +
                "matrix=" + Arrays.deepToString(matrix) +
                ", pair=" + pair +
                '}';
    }
}
