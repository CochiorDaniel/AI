package org.example;

import org.javatuples.Pair;

import java.util.*;

public class State {
    private int matrix[][] = new int[3][3];
    private Pair<Integer,Integer> pair;
    private List<Integer>[][] vecini = new ArrayList[3][3];

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
        matriceVecini();
    }

    @Override
    public String toString() {
        return "State{" +
                "matrix=" + Arrays.deepToString(matrix) +
                ", pair=" + pair +
                '}';
    }
    private void matriceVecini()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                List<Integer> list_aux=new ArrayList<>();
                if(i!=0)
                {
                    list_aux.add(matrix[i-1][j]);
                }
                if(i!=2){
                    list_aux.add(matrix[i+1][j]);
                }
                if(j!=0){
                    list_aux.add(matrix[i][j-1]);
                }
                if(j!=2){
                    list_aux.add(matrix[i][j+1]);
                }
                vecini[i][j]=list_aux;
            }
        }
    }

    public void printVecini()
    {
         for(int i=0;i<3;i++)
          {
                for(int j=0;j<3;j++)
                {
                 System.out.println("vecinii lui "+matrix[i][j]+" sunt: "+vecini[i][j]);
                }
          }

    }
}
