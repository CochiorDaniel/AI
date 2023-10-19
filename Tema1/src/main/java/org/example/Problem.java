package org.example;

import org.javatuples.Pair;

import java.util.*;

public class Problem {
    private Map<State, State> parinte = new HashMap<>();

    public Problem(){}
    public void addParinte(State s, State p)
    {
        parinte.put(s,p);
    }
    public boolean isFinal(State state){
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i< 3; i++){
            for(int j=0; j<3; j++){
                if(state.getMatrix()[i][j] != 0)
                    l.add(state.getMatrix()[i][j]);
            }
        }

        for(int i=0; i<l.size()-1; i++){
            if(l.get(i) > l.get(i+1))
                return false;
        }
        return true;
    }
    public State initialize(List<Integer> arrayList)
    {
        int matrix[][] = new int[3][3];
        Pair<Integer,Integer> pair;
        if(arrayList.size() != 9)
        {
            System.out.println("Invalid input");
            return null;
        }
        int i = 0;
        int j = 0;
        for(Integer el : arrayList)
        {
            matrix[i][j] = el;
            j++;
            if(j == 3) {
                j = 0;
                i++;
            }
        }
        pair = new Pair<>(-1,-1);
        return new State(matrix,pair);
    }

    public State transition(State s, Pair<Integer, Integer> p, String direction ) {
        int matrix[][] = new int[3][3];
        for(int i = 0; i< 3; i++){
            for(int j=0; j<3; j++){
                matrix[i][j] = s.getMatrix()[i][j];
            }
        }
        int i = p.getValue0();
        int j = p.getValue1();
        int aux;
        switch (direction) {
            case ("up"):
                aux = matrix[i][j];
                matrix[i][j] = matrix[i-1][j];
                matrix[i-1][j] = aux;
                return new State(matrix, new Pair<>(i-1,j));
            case ("down"):
                aux = matrix[i][j];
                matrix[i][j] = matrix[i+1][j];
                matrix[i+1][j] = aux;
                return new State(matrix, new Pair<>(i+1,j));
            case ("left"):
                aux = matrix[i][j];
                matrix[i][j] = matrix[i][j-1];
                matrix[i][j-1] = aux;
                return new State(matrix, new Pair<>(i,j-1));
            case ("right"):
                aux = matrix[i][j];
                matrix[i][j] = matrix[i][j+1];
                matrix[i][j+1] = aux;
                return new State(matrix, new Pair<>(i,j+1));
            default:
                return null;
        }

    }

    public boolean validateTransition(State s, Pair<Integer, Integer> p, String direction){
        if(s.getMatrix()[p.getValue0()][p.getValue1()] == 0)
            return false;
        if(!validateGrid(p,direction))
            return false;
        if(!validateLastMove(s,p))
            return false;
        if(!validateDirection(s,p,direction))
            return false;
        return true;
    }

    public boolean validateGrid(Pair<Integer, Integer> p, String direction){
        int i = p.getValue0();
        int j = p.getValue1();

        if(i < 0 || i > 2 || j < 0 || j > 2)
            return false;

        switch (direction) {
            case ("up"):
                if(i-1 < 0)
                    return false;
                break;
            case ("down"):
                if(i+1 > 2)
                    return false;
                break;
            case ("left"):
                if(j-1 < 0)
                    return false;
                break;
            case ("right"):
                if(j+1 > 2)
                    return false;
                break;
            default:
                return true;
        }
        return true;
    }

    public boolean validateLastMove(State s, Pair<Integer, Integer> p){
        int i = p.getValue0();
        int j = p.getValue1();
        if( i == s.getPair().getValue0() && j == s.getPair().getValue1())
            return false;
        return true;
    }

    public boolean validateDirection(State s, Pair<Integer,Integer> p, String direction){
        int i = p.getValue0();
        int j = p.getValue1();
        switch (direction) {
            case ("up"):
                if(s.getMatrix()[i-1][j] != 0)
                    return false;
                break;
            case ("down"):
                if(s.getMatrix()[i+1][j] != 0)
                    return false;
                break;
            case ("left"):
                if(s.getMatrix()[i][j-1] != 0)
                    return false;
                break;
            case ("right"):
                if(s.getMatrix()[i][j+1] != 0)
                    return false;
                break;
            default:
                return true;
        }
        return true;
    }
    public List<State> drum(State state_final)
    {
        List<State> drum = new ArrayList<>();
        State s = state_final;
        while(s != null)
        {
            drum.add(s);
            s = parinte.get(s);
        }
        Collections.reverse(drum);
        return drum;
    }
    public State IDDFS(State initS, int maxDepth){
        State sol = null;
        for(int i=0; i<=maxDepth; i++){
            List<State> visited = new ArrayList<>(); // lista noua
            sol = depthLimitedDFS(initS,i,visited);
            if(sol != null) {
                //System.out.println(sol);
                System.out.println("Solutie gasita la adancimea " + i);
                //System.out.println(drum(sol).size());
                System.out.println(drum(sol));
                //System.out.println(visited);
                return sol;
            }
        }
        return null;
    }

    public State depthLimitedDFS(State s, int depth, List<State> visited ){
        if (isFinal(s))
            return s;
        if(depth == 0)
            return null;
        visited.add(s);
        State res = null;
        List<State> neighbors = possibleTransitions(s);
        for(State n : neighbors){
            addParinte(n,s);
            if(!visited.contains(n)){
                res = depthLimitedDFS(n,depth-1,visited);
                if(res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    public List<State> possibleTransitions(State s){
        List<State> list = new ArrayList<>();
        Pair<Integer,Integer> p;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                p = new Pair<>(i, j);
                if(validateTransition(s,p,"up"))
                    list.add(transition(s,p,"up"));
                if(validateTransition(s,p,"down"))
                    list.add(transition(s,p,"down"));
                if(validateTransition(s,p,"left"))
                    list.add(transition(s,p,"left"));
                if(validateTransition(s,p,"right"))
                    list.add(transition(s,p,"right"));
            }

        }
        return list;

    }

}
