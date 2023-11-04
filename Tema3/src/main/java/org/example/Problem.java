package org.example;

import org.javatuples.Pair;

import java.util.Map;
import java.util.Scanner;

public class Problem {
    private State state;

    public State initialize() {
        this.state = new State();
        return state;
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

    public State tranzitie(State state, int move) {
        int i = State.getAsociere().get(move).getValue0();
        int j = State.getAsociere().get(move).getValue1();
        int[][] table = new int[3][3];
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                table[k][l] = state.getTable()[k][l];
            }
        }
        table[i][j] = State.getPlayer();
        return new State(table);
    }

    private boolean validateMove(State state, int move) {
        if (state.getTable()[State.getAsociere().get(move).getValue0()][State.getAsociere().get(move).getValue1()] != -1 || move <= 0 || move >= 10) {
            return false;
        }
        return true;
    }

    public int heuristic(State state) {
        int[][] table = state.getTable();
        int h = 0, hmin = 100;
        int nrPosibilitatiOm = 0;
        int nrPosibilitatiCalculator = 0;
        int j = 0;
        Pair<Integer, Integer> poz = new Pair<>(0, 0);
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (table[k][l] == -1) {
                    table[k][l] = 1;
                    nrPosibilitatiOm = 0;
                    nrPosibilitatiCalculator = 0;
                    for (int i = 0; i < 3; i++) {
                        if (table[i][j] != 1 && table[i][j + 1] != 1 && table[i][j + 2] != 1) {
                            nrPosibilitatiOm++;
                        }
                        if (table[i][j] != 0 && table[i][j + 1] != 0 && table[i][j + 2] != 0) {
                            nrPosibilitatiCalculator++;
                        }
                        if (table[j][i] != 1 && table[j + 1][i] != 1 && table[j + 2][i] != 1) {
                            nrPosibilitatiOm++;
                        }
                        if (table[j][i] != 0 && table[j + 1][i] != 0 && table[j + 2][i] != 0) {
                            nrPosibilitatiCalculator++;
                        }
                    }
                    if (table[j][j] != 1 && table[j + 1][j + 1] != 1 && table[j + 2][j + 2] != 1) {
                        nrPosibilitatiOm++;
                    }
                    if (table[j][j] != 0 && table[j + 1][j + 1] != 0 && table[j + 2][j + 2] != 0) {
                        nrPosibilitatiCalculator++;
                    }
                    if (table[j][j + 2] != 1 && table[j + 1][j + 1] != 1 && table[j + 2][j] != 1) {
                        nrPosibilitatiOm++;
                    }
                    if (table[j][j + 2] != 0 && table[j + 1][j + 1] != 0 && table[j + 2][j] != 0) {
                        nrPosibilitatiCalculator++;
                    }
                    h = nrPosibilitatiOm - nrPosibilitatiCalculator;
                    //System.out.println("Nr posibilitati: " + nrPosibilitatiOm + " Nr posibilitati adversar: " + nrPosibilitatiCalculator);
                    //System.out.println("k: " + k + ",l: " + l);
                    table[k][l] = -1;
                    if (h < hmin) {
                        hmin = h;
                        poz = new Pair<>(k, l);
                    }


                }
            }
        }
        //System.out.println(hmin);
        Map<Integer, Pair<Integer, Integer>> asociere = State.getAsociere();
        for (int i = 1; i <= 9; i++) {
            if (asociere.get(i).getValue0() == poz.getValue0() && asociere.get(i).getValue1() == poz.getValue1()) {
                return i;
            }
        }
        return 0;
    }

    public int MiniMax(State state, int depth, boolean isMaximizing) {
        if (isFinal(state) || depth == 0) {
            return heuristic(state);
        }
        if (isMaximizing) {
            int bestScore = -1000;
            for (int i = 1; i <= 9; i++) {
                if (validateMove(state, i)) {
                    State newState = tranzitie(state, i);
                    int score = MiniMax(newState, depth - 1, false);
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = 100;
            for (int i = 1; i <= 9; i++) {
                if (validateMove(state, i)) {
                    State newState = tranzitie(state, i);
                    int score = MiniMax(newState, depth - 1, true);
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    public int gameTime(State state) {
        while (!isFinal(state)) {
            int move = 0;
            if (State.getPlayer() == 0) {
                Scanner scanner = new Scanner(System.in);
                move = scanner.nextInt();
            } else {
                move = heuristic(state);
            }
            if (validateMove(state, move)) {
                state = tranzitie(state, move);
                State.changePlayer();
            }
            System.out.println(state);
        }
        return 0; //momentat + functie de evaluare
    }

    public int MiniMaxGame(State state) {
        while (!isFinal(state)) {
            int move = 0;
            if (State.getPlayer() == 0) {
                Scanner scanner = new Scanner(System.in);
                move = scanner.nextInt();
            } else {
                int bestScore = -1000;
               for(int i=0;i<3;i++){
                   for(int j=0;j<3;j++) {
                        if(state.getTable()[i][j]==-1){
                           state.getTable()[i][j] = 1;
                           int score = MiniMax(state, 2, true);
                           state.getTable()[i][j] = -1;
                            if(score>bestScore){
                                 bestScore = score;
                                int finalI = i;
                                int finalJ = j;
                                move = State.getAsociere().entrySet().stream().filter(entry -> entry.getValue().getValue0() == finalI && entry.getValue().getValue1() == finalJ).findFirst().get().getKey();
                            }
                        }
                   }
               }

            }
            if (validateMove(state, move)) {
                state = tranzitie(state, move);
                State.changePlayer();
            }
            System.out.println(state);
        }
        return 0; //momentat + functie de evaluare
    }
}
