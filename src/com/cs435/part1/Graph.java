package com.cs435.part1;

import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
    LinkedList<Node> graph = new LinkedList<>();

    public void addNode(final int val){
        graph.add(new Node(val));
    }

    public void addUndirectedEdge(final Node first, final Node second){
        first.addEdge(second);
        second.addEdge(first);
    }

    public void removeUndriectedEdge(final Node first, final Node second){
        first.removeEdge(second);
        second.removeEdge(first);
    }

    public HashSet<Node> getAllNodes(){
        return new HashSet<>(graph);
    }


}
