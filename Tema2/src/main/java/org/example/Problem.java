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

<<<<<<< HEAD
    private Pair<Integer, Integer> next_unasigned_var_MRV(int[][] table, List<Integer>[][] domenii) {
        int min = 10;
        Pair<Integer, Integer> poz = null;
        for(int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] == 0) {
                    if(domenii[i][j].size() < min) {
                        min = domenii[i][j].size();
                        poz = new Pair<>(i, j);
                    }
                }
            }
        }
        //System.out.println(domenii[poz.getValue0()][poz.getValue1()].size());
        return poz;
    }

    public boolean isEmpty(List<Integer>[][] l, int [][] asigment) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (l[i][j].isEmpty()) {
                    if(asigment[i][j] == 0)
                        return true;
=======
    public boolean isEmpty(List<Integer>[][] l){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(l[i][j].isEmpty()){
                    return true;
>>>>>>> origin/main
                }
            }
        }
        return false;
    }

    public int[][] BKT_with_FC(Sudoku sudoku) {
<<<<<<< HEAD
        int[][] sud = sudoku.getTable();
        int[][] assigment = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++)
                assigment[i][j] = sud[i][j];
        }

=======
        int[][] assigment = sudoku.getTable();
>>>>>>> origin/main
        List<Integer>[][] domenii = sudoku.getDomeniu();
        int[][] result = new int[9][9];

        if (isComplete(assigment)) {
            return assigment;
        }

        Pair<Integer, Integer> crtPoz = next_unasigned_var(assigment);
        int x = crtPoz.getValue0();
        int y = crtPoz.getValue1();
<<<<<<< HEAD
        for (var value : domenii[x][y]) {
            if (consistent(assigment, crtPoz, value)) {
                assigment[x][y] = value;
                Sudoku s = new Sudoku(assigment, sudoku.getPozPare());
                List<Integer>[][] new_domains = s.getDomeniu();
                if (!isEmpty(new_domains, assigment)) {
                    result = BKT_with_FC(s);
                    if (result != null) {
                        return result;
                    }
                }

            }
        }
        return null;
    }

    public int[][] BKT_with_FC_MRV(Sudoku sudoku) {
        int[][] sud = sudoku.getTable();
        int[][] assigment = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++)
                assigment[i][j] = sud[i][j];
        }

        List<Integer>[][] domenii = sudoku.getDomeniu();
        int[][] result = new int[9][9];

        if (isComplete(assigment)) {
            return assigment;
        }

        Pair<Integer, Integer> crtPoz = next_unasigned_var_MRV(assigment, domenii);
        int x = crtPoz.getValue0();
        int y = crtPoz.getValue1();
        for (var value : domenii[x][y]) {
            if (consistent(assigment, crtPoz, value)) {
                assigment[x][y] = value;
                Sudoku s = new Sudoku(assigment, sudoku.getPozPare());
                List<Integer>[][] new_domains = s.getDomeniu();
                if (!isEmpty(new_domains, assigment)) {
                    result = BKT_with_FC_MRV(s);
                    if (result != null) {
                        return result;
                    }
                }

=======
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
>>>>>>> origin/main
            }
        }
        return null;
    }
}