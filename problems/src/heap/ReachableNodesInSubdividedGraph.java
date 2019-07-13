package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 07/05/2019 Starting with an undirected graph (the "original
 * graph") with nodes from 0 to N-1, subdivisions are made to some of the edges.
 *
 * <p>The graph is given as follows: edges[k] is a list of integer pairs (i, j, n) such that (i, j)
 * is an edge of the original graph,
 *
 * <p>and n is the total number of new nodes on that edge.
 *
 * <p>Then, the edge (i, j) is deleted from the original graph, n new nodes (x_1, x_2, ..., x_n) are
 * added to the original graph,
 *
 * <p>and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) are added to
 * the original graph.
 *
 * <p>Now, you start at node 0 from the original graph, and in each move, you travel along one edge.
 *
 * <p>Return how many nodes you can reach in at most M moves.
 *
 * <p>Example 1:
 *
 * <p>Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3 Output: 13 Explanation: The nodes that
 * are reachable in the final graph after M = 6 moves are indicated below.
 *
 * <p>Example 2:
 *
 * <p>Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4 Output: 23
 *
 * <p>Note:
 *
 * <p>0 <= edges.length <= 10000 0 <= edges[i][0] < edges[i][1] < N There does not exist any i != j
 * for which edges[i][0] == edges[j][0] and edges[i][1] == edges[j][1]. The original graph has no
 * parallel edges. 0 <= edges[i][2] <= 10000 0 <= M <= 10^9 1 <= N <= 3000 A reachable node is a
 * node that can be travelled to using at most M moves starting from node 0.
 *
 * <p>Solution: O(E log N) E is the length of edges and N is the number of nodes. The n nodes on a
 * edge form a weight and thus the graph becomes a weighted graph. Keep track of number of moves
 * available and run a Dijkstra's algorithm.
 */
public class ReachableNodesInSubdividedGraph {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] edges = {{0, 1, 1000}, {0, 2, 1}, {1, 2, 1}};
    System.out.println(new ReachableNodesInSubdividedGraph().reachableNodes(edges, 200, 3));
  }

  static class Node {
    int n, w;

    Node(int n, int w) {
      this.n = n;
      this.w = w;
    }
  }

  public int reachableNodes(int[][] edges, int M, int N) {
    Map<Integer, List<Node>> graph = new HashMap<>();
    for (int[] e : edges) {
      graph.putIfAbsent(e[0], new ArrayList<>());
      graph.get(e[0]).add(new Node(e[1], e[2] + 1));

      graph.putIfAbsent(e[1], new ArrayList<>());
      graph.get(e[1]).add(new Node(e[0], e[2] + 1));
    }

    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
    Map<Integer, Integer> distance = new HashMap<>();
    int count = 0;
    pq.offer(new Node(0, 0));
    while (!pq.isEmpty()) {
      Node curr = pq.poll();
      if (!distance.containsKey(curr.n)) {
        count += 1;
        distance.put(curr.n, curr.w);
        List<Node> children = graph.get(curr.n);
        if (children != null) {
          for (Node c : children) {
            if (!distance.containsKey(c.n)) {
              int availableMoves = M - curr.w;
              int nodesInBetween = c.w - 1;
              if (nodesInBetween >= availableMoves) {
                count += availableMoves;
              } else {
                count += nodesInBetween;
                if (availableMoves >= c.w) {
                  Node child = new Node(c.n, distance.get(curr.n) + c.w);
                  pq.offer(child);
                }
              }
            } else {
              int childAvailableMoves = M - distance.get(c.n);
              int nodesInBetween = c.w - 1;
              int unvisitedNodes = nodesInBetween - childAvailableMoves;
              if (unvisitedNodes > 0) {
                int availableMovesForCurr = M - distance.get(curr.n);
                count +=
                    (unvisitedNodes >= availableMovesForCurr
                        ? availableMovesForCurr
                        : unvisitedNodes);
              }
            }
          }
        }
      }
    }
    return count;
  }
}
