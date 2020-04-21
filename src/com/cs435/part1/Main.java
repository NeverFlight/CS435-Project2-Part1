package com.cs435.part1;

import java.util.*;

public class Main {
    Random rand = new Random();
    public static void main(String[] args) {
        Main run = new Main();
        List<Node> ret1 = run.BFTRecLinkedList(10000);
        List<Node> ret2 = run.BFTIterLinkedList(10000);
    }

    public Graph createRandomUnweighedGraphIter(int n){
        Graph graph = new Graph();
        for(int i = 0; i < n;i++){
            graph.addNode(i);
        }
        for(int i = 0; i < graph.graphList.size(); i++){
            Node curr = graph.graphList.get(i);
            for(int j = i + 1; j <graph.graphList.size(); j++){
                if(rand.nextInt(2) == 0)
                    graph.addUndirectedEdge(curr, graph.graphList.get(j));
            }
        }
        return graph;
    }

    public Graph createLinkedList(int n){
        Graph graph = new Graph();
        for(int i = 0; i < n;i++){
            graph.addNode(i);
        }
        Collections.shuffle(graph.graphList);
        for(int i = 0; i < graph.graphList.size() - 1; i++){
            graph.addUndirectedEdge(graph.graphList.get(i), graph.graphList.get(i+1));
        }
        return graph;
    }

    public ArrayList<Node> DFSIter(final Node start, final Node end){
        ArrayList<Node> DFSTraverseList = new ArrayList<Node>();
        Set<Integer> visited = new HashSet<>();
        Stack<Node> stk = new Stack<>();
        stk.push(start);
        DFSTraverseList.add(start);
        visited.add(start.val);
        while(!stk.isEmpty()){
            Node curr = stk.pop();
            DFSTraverseList.add(curr);
            if(curr.val == end.val)
                return DFSTraverseList;
            visited.add(curr.val);
            for(Node adj : curr.adj){
                if(!visited.contains(adj.val)) {
                    visited.add(adj.val);
                    DFSTraverseList.add(adj);
                    stk.push(adj);
                }
            }
        }
        // No end value find in the DFS, return null.
        return null;
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
        if(curr.val == end.val) return;
        for(Node adj : curr.adj){
            if(adj.val == end.val){
                ret.add(adj);
                break;
            }
            if(!visited.contains(adj.val)){
                visited.add(adj.val);
                DFSRecHelper(adj, ret, end, visited);
            }
        }
    }

    public ArrayList<Node> BFTIter(final Graph graph){
        boolean[] visited = new boolean[graph.graphList.size()];
        ArrayList<Node> BFTList = new ArrayList<Node>();
        Queue<Node> q;
        for(Node curr : graph.graphList){
            if(visited[curr.val]) continue;
            q = new LinkedList<>();
            q.offer(curr);
            BFTList.add(curr);
            visited[curr.val] = true;
            while(!q.isEmpty()){
                Node first = q.poll();
                for(Node adj : first.adj){
                    if(visited[adj.val]) continue;
                    visited[adj.val] = true;
                    q.offer(adj);
                    BFTList.add(adj);
                }
            }
        }
        return BFTList;
    }

    public ArrayList<Node> BFTRec(final Graph graph){
        boolean[] visited = new boolean[graph.graphList.size()];
        ArrayList<Node> BFTList = new ArrayList<Node>();
        Queue<Node> q;
        for(Node curr : graph.graphList){
            if(visited[curr.val]) continue;
            q = new LinkedList<>();
            q.offer(curr);
            BFTRecHelper(q, visited, BFTList);
        }
        return BFTList;
    }

    public void BFTRecHelper(Queue<Node> q, boolean[] visited, ArrayList<Node> BFTList){
        if(q.isEmpty()) return;
        Node curr = q.poll();
        if(visited[curr.val]) return;
        BFTList.add(curr);
        visited[curr.val] = true;
        for(Node adj : curr.adj){
            q.offer(adj);
        }
        BFTRecHelper(q, visited, BFTList);

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
