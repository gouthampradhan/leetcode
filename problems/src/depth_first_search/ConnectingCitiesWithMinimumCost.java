package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/11/2019 There are N cities numbered from 1 to N.
 *
 * <p>You are given connections, where each connections[i] = [city1, city2, cost] represents the
 * cost to connect city1 and city2 together. (A connection is bidirectional: connecting city1 and
 * city2 is the same as connecting city2 and city1.)
 *
 * <p>Return the minimum cost so that for every pair of cities, there exists a path of connections
 * (possibly of length 1) that connects those two cities together. The cost is the sum of the
 * connection costs used. If the task is impossible, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]] Output: 6 Explanation: Choosing any 2
 * edges will connect all cities so we choose the minimum 2. Example 2:
 *
 * <p>Input: N = 4, connections = [[1,2,3],[3,4,4]] Output: -1 Explanation: There is no way to
 * connect all cities even if all edges are used.
 *
 * <p>Note:
 *
 * <p>1 <= N <= 10000 1 <= connections.length <= 10000 1 <= connections[i][0], connections[i][1] <=
 * N 0 <= connections[i][2] <= 10^5 connections[i][0] != connections[i][1]
 */
public class ConnectingCitiesWithMinimumCost {

  /** @author gouthamvidyapradhan Class to represent UnionFind Disjoint Set */
  private class UnionFind {
    private int[] p;
    private int[] rank;
    private int numOfDisjoinSet;

    UnionFind(int s) {
      this.p = new int[s];
      this.rank = new int[s];
      this.numOfDisjoinSet = s - 1;
      init();
    }

    /** Initialize with its same index as its parent */
    public void init() {
      for (int i = 0; i < p.length; i++) p[i] = i;
    }
    /**
     * Find the representative vertex
     *
     * @param i
     * @return
     */
    private int findSet(int i) {
      if (p[i] != i) p[i] = findSet(p[i]);
      return p[i];
    }
    /**
     * Perform union of two vertex
     *
     * @param i
     * @param j
     * @return true if union is performed successfully, false otherwise
     */
    public boolean union(int i, int j) {
      int x = findSet(i);
      int y = findSet(j);
      if (x != y) {
        if (rank[x] > rank[y]) p[y] = p[x];
        else {
          p[x] = p[y];
          if (rank[x] == rank[y]) rank[y]++; // increment the rank
        }
        numOfDisjoinSet--;
        return true;
      }
      return false;
    }
  }

  private class Edge {
    int v1;
    int v2;
    int distance;

    Edge(int v1, int v2, int distance) {
      this.v1 = v1;
      this.v2 = v2;
      this.distance = distance;
    }
  }

  private List<Edge> edges = new ArrayList<>();
  int min = 0;

  public static void main(String[] args) {
    int[][] A = {{1, 2, 3}, {3, 4, 4}};
    System.out.println(new ConnectingCitiesWithMinimumCost().minimumCost(4, A));
  }

  public int minimumCost(int N, int[][] connections) {
    UnionFind uF = new UnionFind(N + 1);
    for (int i = 0; i < connections.length; i++) {
      edges.add(new Edge(connections[i][0], connections[i][1], connections[i][2]));
    }
    edges.sort(Comparator.comparingInt(o -> o.distance));
    for (Edge e : edges) {
      if (uF.union(e.v1, e.v2)) {
        min += e.distance;
      }
      if (uF.numOfDisjoinSet == 1) {
        return min;
      }
    }
    return -1;
  }
}
