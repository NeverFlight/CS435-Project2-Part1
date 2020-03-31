package com.cs435.part1;

import java.util.*;

public class Main {
    Random rand = new Random();
    public static void main(String[] args) {
        Main run = new Main();
        List<Node> ret1 = run.BFTRecLinkedList(100000);
        List<Node> ret2 = run.BFTIterLinkedList(10000);
    }

    public Graph createRandomUnweighedGraphIter(int n){
        Graph graph = new Graph();
        for(int i = 0; i < n;i++){
            graph.addNode(i);
        }
        for(int i = 0; i < graph.graph.size();i++){
            Node curr = graph.graph.get(i);
            for(int j = i + 1; j <graph.graph.size();j++){
                if(rand.nextInt(2) == 0)
                    graph.addUndirectedEdge(curr, graph.graph.get(j));
            }
        }
        return graph;
    }

    public Graph createLinkedList(int n){
        Graph graph = new Graph();
        for(int i = 0; i < n;i++){
            graph.addNode(i);
        }
        Collections.shuffle(graph.graph);
        for(int i = 0; i < graph.graph.size() - 1;i++){
            graph.addUndirectedEdge(graph.graph.get(i), graph.graph.get(i+1));
        }
        return graph;
    }
    public ArrayList<Node> DFSIter(final Node start, final Node end){
        ArrayList<Node> ret = new ArrayList<Node>();
        Set<Integer> visited = new HashSet<>();
        Stack<Node> stk = new Stack<>();
        stk.push(start);
        ret.add(start);
        visited.add(start.val);
        while(!stk.isEmpty()){
            Node curr = stk.pop();
            for(Node adj : curr.adj){
                if(!visited.contains(curr.val)) {
                    visited.add(curr.val);
                    ret.add(adj);
                    stk.push(adj);
                }
            }
        }
        return ret;
    }

    public ArrayList<Node> DFSRec(final Node start, final Node end){
        ArrayList<Node> ret = new ArrayList<Node>();
        Set<Integer> visited = new HashSet<>();
        DFSRecHelper(start, ret, end, visited);
        return ret;
    }

    public void DFSRecHelper(Node curr, ArrayList<Node> ret, Node end, Set<Integer> visited){
        if(curr == null) return;
        ret.add(curr);
        for(Node adj : curr.adj){
            if(curr.val == end.val){
                ret.add(curr);
                break;
            }
            if(!visited.contains(curr.val)){
                visited.add(curr.val);
                DFSRecHelper(adj, ret, end, visited);
            }
        }
    }

    public ArrayList<Node> BFTIter(final Graph graph){
        boolean[] visited = new boolean[graph.graph.size()];
        ArrayList<Node> ret = new ArrayList<Node>();
        Queue<Node> q;
        for(Node curr : graph.graph){
            if(visited[curr.val]) continue;
            q = new LinkedList<>();
            q.offer(curr);
            ret.add(curr);
            visited[curr.val] = true;
            while(!q.isEmpty()){
                Node first = q.poll();
                for(Node adj : first.adj){
                    if(visited[adj.val]) continue;
                    visited[adj.val] = true;
                    q.offer(adj);
                    ret.add(adj);
                }
            }
        }
        return ret;
    }

    public ArrayList<Node> BFTRec(final Graph graph){
        boolean[] visited = new boolean[graph.graph.size()];
        ArrayList<Node> ret = new ArrayList<Node>();
        Queue<Node> q;
        for(Node curr : graph.graph){
            if(visited[curr.val]) continue;
            q = new LinkedList<>();
            q.offer(curr);
            BFTRecHelper(q, visited, ret);
        }
        return ret;
    }

    public void BFTRecHelper(Queue<Node> q, boolean[] visited, ArrayList<Node> ret){
        if(q.isEmpty()) return;
        Node curr = q.poll();
        if(visited[curr.val]) return;
        ret.add(curr);
        visited[curr.val] = true;
        for(Node adj : curr.adj){
            q.offer(adj);
        }
        BFTRecHelper(q, visited, ret);

    }

    public ArrayList<Node> BFTRecLinkedList(int n){
        Graph graph = createLinkedList(n);
        return BFTRec(graph);
    }

    public ArrayList<Node> BFTIterLinkedList(int n){
        Graph graph = createLinkedList(n);
        return BFTIter(graph);
    }

}
