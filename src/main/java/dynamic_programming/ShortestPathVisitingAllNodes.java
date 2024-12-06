/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 16/10/2020
 *
 * <p>An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 *
 * <p>graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and
 * j are connected.
 *
 * <p>Return the length of the shortest path that visits every node. You may start and stop at any
 * node, you may revisit nodes multiple times, and you may reuse edges.
 *
 * <p>Example 1:
 *
 * <p>Input: [[1,2,3],[0],[0],[0]] Output: 4 Explanation: One possible path is [1,0,2,0,3] Example
 * 2:
 *
 * <p>Input: [[1],[0,2,4],[1,3,4],[2],[1,2]] Output: 4 Explanation: One possible path is [0,1,4,2,3]
 *
 * <p>Note:
 *
 * <p>1 <= graph.length <= 12 0 <= graph[i].length < graph.length
 */
public class ShortestPathVisitingAllNodes {
  public static void main(String[] args) {
    int[][] graph = {{2, 3, 4, 8}, {8}, {0}, {0, 8}, {0, 5, 6}, {4, 7}, {4}, {5}, {0, 3, 1}};
    System.out.println(new ShortestPathVisitingAllNodes().shortestPathLength(graph));
  }

  Stack<Node> stack;
  Set<Node> done;

  class Node {
    int v, s;

    Node(int v, int s) {
      this.v = v;
      this.s = s;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;
      Node node = (Node) o;
      return v == node.v && s == node.s;
    }

    @Override
    public int hashCode() {
      return Objects.hash(v, s);
    }
  }

  public int shortestPathLength(int[][] G) {
    int dest = (int) Math.pow(2, G.length) - 1;
    int[][] DP = new int[G.length][dest + 1];
    done = new HashSet<>();
    stack = new Stack<>();
    for (int i = 0; i < G.length; i++) {
      Node n = new Node(i, 1 << i);
      if (!done.contains(n)) {
        dfs(n, G);
      }
    }
    for (int i = 0; i < G.length; i++) {
      Arrays.fill(DP[i], Integer.MAX_VALUE);
    }
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      int[] children = G[node.v];
      int currDist = DP[node.v][node.s] == Integer.MAX_VALUE ? 0 : DP[node.v][node.s];
      for (int c : children) {
        if (DP[c][node.s | (1 << c)] < Integer.MAX_VALUE
            && ((currDist + 1) < DP[c][node.s | (1 << c)])) {
          stack.push(new Node(c, node.s | (1 << c)));
        }
        DP[c][node.s | (1 << c)] = Math.min(DP[c][node.s | (1 << c)], currDist + 1);
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < G.length; i++) {
      min = Math.min(min, DP[i][dest]);
    }
    return min == Integer.MAX_VALUE ? 0 : min;
  }

  private void dfs(Node n, int[][] graph) {
    done.add(n);
    int[] children = graph[n.v];
    if (children != null) {
      for (int c : children) {
        Node child = new Node(c, (n.s | (1 << c)));
        if (!done.contains(child)) {
          dfs(child, graph);
        }
      }
    }
    stack.push(n);
  }
}
