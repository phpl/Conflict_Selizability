package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class GraphAllTopSorts {
    private int V;
    private LinkedList[] adj;
    private boolean[] marked;
    private List<Integer> list;
    private int[] indegree;

    GraphAllTopSorts(int v) {
        this.V = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
        this.indegree = new int[v];
        this.marked = new boolean[v];
        list = new ArrayList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
    }

    void alltopologicalSorts() {
        boolean flag = false;

        for (int w = 0; w < V; w++) {
            if (!marked[w] && indegree[w] == 0) {
                marked[w] = true;
                Iterator<Integer> iter = adj[w].listIterator();
                while (iter.hasNext()) {
                    int k = iter.next();
                    indegree[k]--;
                }

                list.add(w);
                alltopologicalSorts();

                marked[w] = false;
                iter = adj[w].listIterator();
                while (iter.hasNext()) {
                    int k = iter.next();
                    indegree[k]++;
                }
                list.remove(list.indexOf(w));

                flag = true;
            }
        }

        if (!flag) {
            for (int w = 0; w < V; w++) {
                System.out.print((list.get(w) + 1) + " ");
            }
            System.out.print("\n");
        }
    }
}