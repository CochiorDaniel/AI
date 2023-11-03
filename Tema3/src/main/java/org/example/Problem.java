package org.example;

import java.util.Scanner;

public class Problem {
    private State state;

    public void initialize() {
        this.state = new State();
    }

    public boolean isFinal(State s) {
        int[][] table = s.getTable();
        int full = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == -1) {
                    full++;
                }
            }
        }
        if (full == 0) {
            return true;
        } else {
            for (int i = 0; i < 3; i++) {
                if (table[i][0] == table[i][1] && table[i][1] == table[i][2] && table[i][0] != -1) {
                    return true;
                }
                if (table[0][i] == table[1][i] && table[1][i] == table[2][i] && table[0][i] != -1) {
                    return true;
                }
            }
            if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && table[0][0] != -1) {
                return true;
            }
            if (table[0][2] == table[1][1] && table[1][1] == table[2][0] && table[0][2] != -1) {
                return true;
            }
        }
        return false;
    }

    public State tranzitie(State state, int move){
        int i = State.getAsociere().get(move).getValue0();
        int j = State.getAsociere().get(move).getValue1();
        int[][] table = new int[3][3];
        for(int k=0;k<3;k++){
            for(int l=0;l<3;l++){
                table[k][l]=state.getTable()[k][l];
            }
        }
        table[i][j]=State.getPlayer();
        return new State(table);
    }

    private boolean validateMove(State state, int move) {
        if (state.getTable()[State.getAsociere().get(move).getValue0()][State.getAsociere().get(move).getValue1()] != -1 || move <= 0 || move >= 10) {
            return false;
        }
        return true;
    }

    public int gameTime(State state) {
        while (!isFinal(state)) {
            int move = 0;
            if (State.getPlayer() == 0) {
                Scanner scanner = new Scanner(System.in);
                move = scanner.nextInt();
            }
            else{
                //TODO: implementare euristica
            }
            if (validateMove(state, move)) {
                state =tranzitie(state, move);;
                State.changePlayer();
            }
        }
        return 0; // momentan
    }
}
