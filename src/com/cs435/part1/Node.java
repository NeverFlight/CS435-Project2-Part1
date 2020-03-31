package com.cs435.part1;

import java.util.HashSet;
import java.util.Set;


public class Node {
    int val;
    Set<Node> adj;
    public Node(int val){
        this.val = val;
        adj = new HashSet<>();
    }

    public void addEdge(Node that){
        this.adj.add(that);
    }

    public void removeEdge(Node that){
        this.adj.remove(that);
    }
}
