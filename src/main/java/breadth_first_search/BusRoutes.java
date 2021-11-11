package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/08/2018 We have a list of bus routes. Each routes[i] is a
 * bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means
 * that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->. .. forever.
 *
 * <p>We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling
 * by buses only, what is the least number of buses we must take to reach our destination? Return -1
 * if it is not possible.
 *
 * <p>Example: Input: routes = [[1, 2, 7], [3, 6, 7]] S = 1 T = 6 Output: 2 Explanation: The best
 * strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Note:
 *
 * <p>1 <= routes.length <= 500. 1 <= routes[i].length <= 500. 0 <= routes[i][j] < 10 ^ 6.
 *
 * <p>Solution: Model a graph based on interconnection of routes and then run a BFS to find the
 * shortest distance.
 */
public class BusRoutes {

  private class Node {
    int v, dist;

    Node(int v, int dist) {
      this.v = v;
      this.dist = dist;
    }
  }

  private Map<Integer, Set<Integer>> routeGraph = new HashMap<>();
  private Map<Integer, List<Integer>> stationRouteMap = new HashMap<>();
  private BitSet done = new BitSet();
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] R = {
      {1, 2, 3, 9}, {9, 3, 4, 5, 8}, {5, 6, 7, 8}, {9, 8, 10, 11}, {12, 13, 14, 6, 1, 2, 3, 5, 7}
    };
    System.out.println(new BusRoutes().numBusesToDestination(R, 1, 14));
  }

  public int numBusesToDestination(int[][] routes, int S, int T) {
    if (S == T) return 0;
    for (int i = 0; i < routes.length; i++) {
      Arrays.sort(routes[i]);
      int[] n = routes[i];
      for (int j : n) {
        if (j == S || j == T) {
          stationRouteMap.putIfAbsent(j, new ArrayList<>());
          stationRouteMap.get(j).add(i);
        }
      }
    }
    for (int i = 0; i < routes.length; i++) {
      int[] A = routes[i];
      for (int j = i + 1; j < routes.length; j++) {
        int[] B = routes[j];
        if (intersect(A, B)) {
          routeGraph.putIfAbsent(i, new HashSet<>());
          routeGraph.putIfAbsent(j, new HashSet<>());
          routeGraph.get(i).add(j);
          routeGraph.get(j).add(i);
        }
      }
    }
    List<Integer> start = stationRouteMap.get(S);
    if (!stationRouteMap.containsKey(T)) return -1;
    Set<Integer> destination = new HashSet<>(stationRouteMap.get(T));
    Queue<Node> queue = new ArrayDeque<>();
    for (int r : start) {
      if (destination.contains(r)) return 1;
      done.set(r);
      queue.offer(new Node(r, 0));
    }
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      Set<Integer> children = routeGraph.get(curr.v);
      if (children != null) {
        for (int c : children) {
          if (!done.get(c)) {
            done.set(c);
            Node child = new Node(c, curr.dist + 1);
            if (destination.contains(child.v)) {
              return child.dist + 1;
            } else {
              queue.offer(child);
            }
          }
        }
      }
    }
    return -1;
  }

  private boolean intersect(int[] A, int[] B) {
    for (int i = 0, j = 0; i < A.length && j < B.length; ) {
      if (A[i] == B[j]) return true;
      else if (A[i] < B[j]) i++;
      else j++;
    }
    return false;
  }
}
