package org.example;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    private int[][] table = new int[9][9];
    private List<Pair<Integer, Integer>> pozPare = new ArrayList<>();
    private List<Integer>[][] domeniu = new List[9][9];

    public int[][] getTable() {
        return table;
    }

    public List<Pair<Integer, Integer>> getPozPare() {
        return pozPare;
    }

    public List<Integer>[][] getDomeniu() {
        return domeniu;
    }

    public Sudoku(int[][] table, List<Pair<Integer, Integer>> pozPare) {
        this.table = table;
        this.pozPare = pozPare;
        createDomains();
    }

    private void createDomains() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                domeniu[i][j] = new ArrayList<>();
                if (table[i][j] == 0) {
                    if (pozPare.contains(new Pair<>(i, j))) {
                        for (int z = 2; z < 9; z = z + 2) {
                            if (table[i][j] != z) {
                                domeniu[i][j].add(z);
                            }
                        }
                    } else {
                        for (int z = 1; z <= 9; z++) {
                            if (table[i][j] != z) {
                                domeniu[i][j].add(z);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(table[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setDomeniu(int x, int y) {
        for (int i = 0; i < 9; i++) {
            int j = y;
            domeniu[i][j] = new ArrayList<>();
            if (table[i][j] == 0) {
                if (pozPare.contains(new Pair<>(i, j))) {
                    for (int z = 2; z < 9; z = z + 2) {
                        if (table[i][j] != z && z != table[x][y]) {
                            domeniu[i][j].add(z);
                        }
                    }
                } else {
                    for (int z = 1; z <= 9; z++) {
                        if (table[i][j] != z && z != table[x][y]) {
                            domeniu[i][j].add(z);
                        }
                    }
                }
            }
        }

        for (int j = 0; j < 9; j++) {
            int i = x;
            domeniu[i][j] = new ArrayList<>();
            if (table[i][j] == 0) {
                if (pozPare.contains(new Pair<>(i, j))) {
                    for (int z = 2; z < 9; z = z + 2) {
                        if (table[i][j] != z && z != table[x][y]) {
                            domeniu[i][j].add(z);
                        }
                    }
                } else {
                    for (int z = 1; z <= 9; z++) {
                        if (table[i][j] != z && z != table[x][y]) {
                            domeniu[i][j].add(z);
                        }
                    }
                }
            }
        }

        int x1, x2;
        if(x<3){
            x1=0;
        }
        else if(x<6){
            x1=3;
        }
        else{
            x1=6;
        }
        if(y<3){
            x2=0;
        }
        else if(y<6){
            x2=3;
        }
        else{
            x2=6;
        }

        for (int i = x1; i < x1+3; i++) {
            for (int j = x2; j < x2+3; j++) {
                if (pozPare.contains(new Pair<>(i, j))) {
                    for (int z = 2; z < 9; z = z + 2) {
                        if (table[i][j] != z && z != table[x][y]) {
                            domeniu[i][j].add(z);
                        }
                    }
                } else {
                    for (int z = 1; z <= 9; z++) {
                        if (table[i][j] != z && z != table[x][y]) {
                            domeniu[i][j].add(z);
                        }
                    }
                }


            }
        }
    }
}
