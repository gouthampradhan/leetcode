package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 28/03/2019 Given a directed, acyclic graph of N nodes. Find all
 * possible paths from node 0 to node N-1, and return them in any order.
 *
 * <p>The graph is given as follows: the nodes are 0, 1, ..., graph.length - 1. graph[i] is a list
 * of all nodes j for which the edge (i, j) exists.
 *
 * <p>Example: Input: [[1,2], [3], [3], []] Output: [[0,1,3],[0,2,3]] Explanation: The graph looks
 * like this: 0--->1 | | v v 2--->3 There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3. Note:
 *
 * <p>The number of nodes in the graph will be in the range [2, 15]. You can print different paths
 * in any order, but you should keep the order of nodes inside one path.
 *
 * <p>Solution: Do a dfs to reach every path. Since its a DAG there can be no cycles and safe to
 * proceed without checking if the node has already been visited. Maintain a stack to keep track of
 * the path and when a leaf node has been reached add the elements in the stack to the result array
 */
public class AllPathsFromSourceToTarget {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] graph = {{1, 2}, {3}, {3}, {}};
    System.out.println(new AllPathsFromSourceToTarget().allPathsSourceTarget(graph));
  }

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    Set<Integer> done = new HashSet<>();
    Stack<Integer> stack = new Stack<>();
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, done, 0, stack, graph);
    return result;
  }

  private void dfs(
      List<List<Integer>> result, Set<Integer> done, int i, Stack<Integer> stack, int[][] graph) {
    done.add(i);
    stack.push(i);
    int[] children = graph[i];
    if (children.length == 0) {
      List<Integer> childList = new ArrayList<>(stack);
      result.add(childList);
    } else {
      for (int c : children) {
        dfs(result, done, c, stack, graph);
      }
    }
    stack.pop();
    done.remove(i);
  }
}
