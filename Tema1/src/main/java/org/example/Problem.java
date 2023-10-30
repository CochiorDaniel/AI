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
    public Pair<List<State>, Integer> drum(State state_final)
    {
        List<State> drum = new ArrayList<>();
        State s = state_final;
        while(s != null)
        {
            drum.add(s);
            s = parinte.get(s);
        }
        Collections.reverse(drum);
        int length = drum.size();
        Pair<List<State>,Integer> pair = new Pair<>(drum,length);
        return pair;
    }
    public State IDDFS(State initS, int maxDepth){
        State sol = null;
        for(int i=0; i<=maxDepth; i++){
            List<State> visited = new ArrayList<>(); // lista noua
            sol = depthLimitedDFS(initS,i,visited);
            if(sol != null) {
                //System.out.println(sol);
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

    //pentru matricea finala cu 0 pe aceeasi pozitie
//    public int[][] finalMatrix(State s){
//        int[][] matrix = new int[3][3];
//        int k = 1;
//        for(int i=0; i<3; i++){
//            for(int j=0; j<3; j++){
//                if(s.getMatrix()[i][j] != 0) {
//                    matrix[i][j] = k;
//                    k++;
//                }
//            }
//        }
//        return matrix;
//    }

    public List<int[][]> stariFinale(){
        Problem problem=new Problem();
        State f1 = problem.initialize(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        State f2 = problem.initialize(Arrays.asList(1, 0, 2, 3, 4, 5, 6, 7, 8));
        State f3 = problem.initialize(Arrays.asList(1, 2, 0, 3, 4, 5, 6, 7, 8));
        State f4 = problem.initialize(Arrays.asList(1, 2, 3, 0, 4, 5, 6, 7, 8));
        State f5 = problem.initialize(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7, 8));
        State f6 = problem.initialize(Arrays.asList(1, 2, 3, 4, 5, 0, 6, 7, 8));
        State f7 = problem.initialize(Arrays.asList(1, 2, 3, 4, 5, 6, 0, 7, 8));
        State f8 = problem.initialize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0, 8));
        State f9 = problem.initialize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));
        List<int[][]> finalStates = new ArrayList<>();
        finalStates.add(f1.getMatrix());
        finalStates.add(f2.getMatrix());
        finalStates.add(f3.getMatrix());
        finalStates.add(f4.getMatrix());
        finalStates.add(f5.getMatrix());
        finalStates.add(f6.getMatrix());
        finalStates.add(f7.getMatrix());
        finalStates.add(f8.getMatrix());
        finalStates.add(f9.getMatrix());
        return finalStates;
    }

    public State Greedy(State initialState, String heuristic){
        Comparator<Pair<State,Integer>> comparator = new Comparator<Pair<State,Integer>>() {
            @Override
            public int compare(Pair<State,Integer> p1, Pair<State,Integer> p2) {
                return p1.getValue1() - p2.getValue1();
            }
        };
        Queue<Pair<State,Integer>> pq = new PriorityQueue<>(comparator);
        List<int [][]> finalM=stariFinale();
        switch (heuristic) {
            case "Manhattan":
                Pair<State, Integer> p = new Pair<>(initialState, Manhattan(initialState, finalM));
                pq.add(p);
                break;
            case "Hamming":
                Pair<State, Integer> p1 = new Pair<>(initialState, Hamming(initialState, finalM));
                pq.add(p1);
                break;
            case "Chebyshev":
                Pair<State, Integer> p2 = new Pair<>(initialState, Chebyshev(initialState, finalM));
                pq.add(p2);
                break;
        }

        List<State> visited = new ArrayList<>();
        visited.add(initialState);

        while(!pq.isEmpty()){
            State crt = pq.poll().getValue0();
            if(isFinal(crt)) {
                System.out.println(drum(crt));
                return crt;
            }
            List<State> neighbors = possibleTransitions(crt);
            for(State n : neighbors){
                if(!visited.contains(n)){
                    addParinte(n,crt);
                    switch (heuristic) {
                        case "Manhattan":
                            Pair<State, Integer> pair = new Pair<>(n, Manhattan(n, finalM));
                            pq.add(pair);
                            break;
                        case "Hamming":
                            Pair<State, Integer> pair1 = new Pair<>(n, Hamming(n, finalM));
                            pq.add(pair1);
                            break;
                        case "Chebyshev":
                            Pair<State, Integer> pair2 = new Pair<>(n, Chebyshev(n, finalM));
                            pq.add(pair2);
                            break;
                    }
                    visited.add(n);
                }
            }
        }

        return null;
    }

    public int Manhattan(final State s, List<int[][]> finalM){
        int m = 0;
        int[][] sM = s.getMatrix();

        for ( var fMatrix: finalM){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    int val = sM[i][j];
                    if(val != 0){
                        for(int k=0; k<3; k++){
                            for(int l=0; l<3; l++){
                                if(val == fMatrix[k][l]){
                                    m += Math.abs(i-k) + Math.abs(j-l);
                                }
                            }
                        }
                    }
                }
            }
        }
        return m;
    }

    public int Hamming(final State s, List<int[][]> finalM){
        int h = 0;
        int[][] sM = s.getMatrix();
        for ( var fMatrix: finalM) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int val = sM[i][j];
                    if (val != 0) {
                        if (val != fMatrix[i][j]) {
                            h += 1;
                        }
                    }
                }
            }
        }
        return h;
    }

    public int Chebyshev(final State s, List<int[][]> finalM){
        int c = 0;
        int[][] sM = s.getMatrix();

        for ( var fMatrix: finalM) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int val = sM[i][j];
                    if (val != 0) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if (val == fMatrix[k][l]) {
                                    c += Math.max(Math.abs(i - k), Math.abs(j - l));
                                }
                            }
                        }
                    }
                }
            }
        }
        return c;
    }


}
