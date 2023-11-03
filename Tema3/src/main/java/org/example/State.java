package org.example;
import org.javatuples.Pair;

import java.util.Map;

public class State {
    private int[][] table = new int[3][3];
    private static int player = 0;
    private static Map<Integer, Pair<Integer,Integer>> asociere;
    static {
        asociere.put(2, new Pair<>(0,0));
        asociere.put(7, new Pair<>(0,1));
        asociere.put(6, new Pair<>(0,2));
        asociere.put(9, new Pair<>(1,0));
        asociere.put(5, new Pair<>(1,1));
        asociere.put(1, new Pair<>(1,2));
        asociere.put(4, new Pair<>(2,0));
        asociere.put(3, new Pair<>(2,1));
        asociere.put(8, new Pair<>(2,2));
    }

    public State(int[][] table) {
        this.table = table;
    }

    public State(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                table[i][j]=-1;
            }
        }
    }

    public int[][] getTable() {
        return table;
    }

    public static Map<Integer, Pair<Integer, Integer>> getAsociere() {
        return asociere;
    }

    public static int getPlayer() {
        return player;
    }

    public static void changePlayer(){
        player = 1-player; // suspect!
    }
}
