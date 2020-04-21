package com.cs435.part1;

import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
    LinkedList<Node> graphList = new LinkedList<>();

    public void addNode(final int val){
        graphList.add(new Node(val));
    }

    public void addUndirectedEdge(final Node first, final Node second){
        if(first != null && second != null) {
            first.addEdge(second);
            second.addEdge(first);
        }else{
            System.out.println("One node didn't exist!");
        }
    }

    public void removeUndirectedEdge(final Node first, final Node second){
        if(first != null && second != null) {
            first.removeEdge(second);
            second.removeEdge(first);
        }else{
            System.out.println("One node doesn't exist!");
        }
    }

    public HashSet<Node> getAllNodes(){
        return new HashSet<>(graphList);
    }


}
