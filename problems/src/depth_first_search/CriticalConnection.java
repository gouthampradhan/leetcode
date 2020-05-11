package depth_first_search;

import java.util.*;

/** Created by gouthamvidyapradhan on 05/12/2019 */
public class CriticalConnection {
  public static void main(String[] args) {
    CriticalConnection task = new CriticalConnection();
    List<Integer> c = new ArrayList<>();
    c.add(0);
    c.add(1);

    List<Integer> c1 = new ArrayList<>();
    c1.add(1);
    c1.add(2);

    List<Integer> c2 = new ArrayList<>();
    c2.add(2);
    c2.add(0);

    List<Integer> c3 = new ArrayList<>();
    c3.add(1);
    c3.add(3);

    List<List<Integer>> connections = new ArrayList<>();
    connections.add(c);
    connections.add(c1);
    connections.add(c2);
    connections.add(c3);
    List<List<Integer>> result = task.criticalConnections(4, connections);
    System.out.println();
  }

  private int[] dLow;
  private int[] dNum;
  private int num = 0;

  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    dLow = new int[n];
    dNum = new int[n];
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Arrays.fill(dLow, -1);
    Arrays.fill(dNum, -1);
    for (List<Integer> connection : connections) {
      graph.putIfAbsent(connection.get(0), new ArrayList<>());
      graph.putIfAbsent(connection.get(1), new ArrayList<>());
      graph.get(connection.get(0)).add(connection.get(1));
      graph.get(connection.get(1)).add(connection.get(0));
    }
    dfs(-1, 0, graph);
    List<List<Integer>> result = new ArrayList<>();
    for (List<Integer> connection : connections) {
      if (dLow[connection.get(1)] > dNum[connection.get(0)]
          || dLow[connection.get(0)] > dNum[connection.get(1)]) {
        result.add(connection);
      }
    }
    return result;
  }

  private int dfs(int u, int v, Map<Integer, List<Integer>> graph) {
    int n = num++;
    dNum[v] = n;
    dLow[v] = n;
    List<Integer> children = graph.get(v);
    if (children != null) {
      for (int c : children) {
        if (c != u) {
          if (dNum[c] == -1) {
            dLow[v] = Math.min(dLow[v], dfs(v, c, graph));
          } else {
            dLow[v] = Math.min(dLow[c], dLow[v]);
          }
        }
      }
    }
    return dLow[v];
  }
}
