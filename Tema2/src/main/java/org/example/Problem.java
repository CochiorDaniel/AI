package org.example;

import org.javatuples.Pair;

import java.util.List;

public class Problem {
    private Sudoku sudoku;

    public Sudoku initialize(int[][] table, List<Pair<Integer, Integer>> pozPare) {
        Sudoku s = new Sudoku(table, pozPare);
        sudoku = new Sudoku(table, pozPare);
        //System.out.println(restrictii(table, pozPare));

        return s;
    }

    private boolean restrictii(int[][] table, List<Pair<Integer, Integer>> pozPare) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (table[i][j] != 0) {
                        if (table[i][j] == table[i][k] && j != k) {
                            return false;
                        }
                        if (table[i][j] == table[k][j] && i != k) {
                            return false;
                        }
                        if (pozPare.contains(new Pair<>(i, j))) {
                            if (table[i][j] % 2 != 0) {
                                return false;
                            }
                        }

                    }

                }
            }
        }


        int x1 = 0, x2 = 0, y1 = 3, y2 = 3;
        int contor = 1;
        while (contor < 10) {
            for (int i = x1; i < y1; i++) {
                for (int j = x2; j < y2; j++) {
                    for (int k = x1; k < y1; k++) {
                        for (int t = x2; t < y2; t++) {
                            if (table[i][j] == table[k][t] && table[i][j] != 0 && table[k][t] != 0 && i != k && j != t) {
                                return false;
                            }
                        }
                    }
                }
            }
            if (contor % 3 == 1 || contor % 3 == 2) {
                x2 += 3;
                y2 += 3;
            } else {
                if (contor % 3 == 0) {
                    x1 += 3;
                    y1 += 3;
                    x2 = 0;
                    y2 = 3;
                }
            }
            contor++;
        }
        return true;
    }

    public boolean consistent(int[][] table, Pair<Integer, Integer> poz, int value) {
        int table2[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                table2[i][j] = table[i][j];
                if (i == poz.getValue0() && j == poz.getValue1()) {
                    table2[i][j] = value;
                }
            }
        }

        return restrictii(table2, sudoku.getPozPare());
    }

    private boolean isComplete(int[][] table) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private Pair<Integer, Integer> next_unasigned_var(int[][] table) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] == 0) {
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
    }

    public boolean isEmpty(List<Integer>[][] l){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(l[i][j].isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] BKT_with_FC(Sudoku sudoku) {
        int[][] assigment = sudoku.getTable();
        List<Integer>[][] domenii = sudoku.getDomeniu();
        int[][] result = new int[9][9];

        if (isComplete(assigment)) {
            return assigment;
        }

        Pair<Integer, Integer> crtPoz = next_unasigned_var(assigment);
        int x = crtPoz.getValue0();
        int y = crtPoz.getValue1();
        if(assigment[x][y] == 0) {
            for (var value : domenii[x][y]) {
                if (consistent(assigment, crtPoz, value)) {
                    assigment[x][y] = value;
                    Sudoku s = new Sudoku(assigment, sudoku.getPozPare());
                    List<Integer>[][] new_domains = s.getDomeniu();
                    if (!isEmpty(new_domains)) {
                        result = BKT_with_FC(s);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }
}