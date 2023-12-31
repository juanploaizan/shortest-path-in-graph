package co.edu.uniquindio.graphanalysis.Algorithms;

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;
import java.lang.*;
// 3
// Java program for Floyd Warshall All Pairs Shortest Path algorithm.

class AllPairShortestPath {
    final static int INF = 99999, V = 8192;

    static void floydWarshall(int[][] dist) {
        int i, j, k;

        /* Add all vertices one by one
           to the set of intermediate
           vertices.
          ---> Before start of an iteration,
               we have shortest
               distances between all pairs
               of vertices such that
               the shortest distances consider
               only the vertices in
               set {0, 1, 2, .. k-1} as
               intermediate vertices.
          ----> After the end of an iteration,
                vertex no. k is added
                to the set of intermediate
                vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // dist[i][j]
                    if (dist[i][k] + dist[k][j]
                            < dist[i][j])
                        dist[i][j]
                                = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist);
    }

    static void printSolution(int dist[][]) {
        System.out.println(
                "The following matrix shows the shortest "
                        + "distances between every pair of vertices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] loadedGraph = TestCasesGenerator.loadGraphFromFile("graph"+V+".txt", V);
        int[][] graph = new int[V][V];

        for(int i=0; i<V; i++) {
            graph[i][i] = 0;

            for(int j=i+1; j<V; j++) {
                if(loadedGraph[i][j] == 0) {
                    graph[i][j] = INF;
                    graph[j][i] = INF;
                }
                else {
                    graph[i][j] = loadedGraph[i][j];
                    graph[j][i] = loadedGraph[j][i];
                }
            }
        }

        long startTime = System.currentTimeMillis();
            floydWarshall(graph);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Duration: " + duration + "ms");

        // Save the result
        TestCasesGenerator.saveResult(duration, 3, V+".txt");
    }
}