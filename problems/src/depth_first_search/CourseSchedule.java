package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 22/06/2017. There are a total of n courses you have to take,
 * labeled from 0 to n - 1.
 *
 * <p>Some courses may have prerequisites, for example to take course 0 you have to first take
 * course 1, which is expressed as a pair: [0,1]
 *
 * <p>Given the total number of courses and a list of prerequisite pairs, is it possible for you to
 * finish all courses?
 *
 * <p>For example:
 *
 * <p>2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So it is possible.
 *
 * <p>2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0, and to take course 0 you should also have finished course 1. So it is
 * impossible.
 *
 * <p>Note: The input prerequisites is a graph represented by a list of edges, not adjacency
 * matrices. Read more about how a graph is represented. You may assume that there are no duplicate
 * edges in the input prerequisites.
 *
 * <p>Solution: 1. Topologically sort the vertices. 2. Pick each sorted vertex and mark each of its
 * neighbours as visited, if you encounter a vertex which is already visited then return false
 * otherwise return true
 */
public class CourseSchedule {
  private Map<Integer, List<Integer>> graph;
  private BitSet visited;
  private Queue<Integer> toposorted;

  public static void main(String[] args) throws Exception {
    int[][] pre = {{1, 0}};
    System.out.println(new CourseSchedule().canFinish(2, pre));
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    graph = new HashMap<>();
    visited = new BitSet();
    toposorted = new ArrayDeque<>();
    // build graph
    for (int[] children : prerequisites) {
      graph.putIfAbsent(children[0], new ArrayList<>());
      graph.get(children[0]).add(children[1]);
    }
    graph.keySet().stream().filter(v -> !visited.get(v)).forEach(this::dfs);

    visited.clear();

    while (!toposorted.isEmpty()) {
      int v = toposorted.poll();
      if (visited.get(v)) return false;
      relax(v);
    }
    return true;
  }

  /**
   * Mark a vetex and its connected vertices as visited.
   *
   * @param v vertex
   */
  private void relax(int v) {
    visited.set(v);
    List<Integer> children = graph.get(v);
    if (children != null) {
      for (int c : children) visited.set(c);
    }
  }

  /**
   * Toposort
   *
   * @param v vertex
   */
  private void dfs(int v) {
    visited.set(v);
    List<Integer> children = graph.get(v);
    if (children != null) {
      for (int c : children) if (!visited.get(c)) dfs(c);
    }
    toposorted.offer(v);
  }
}
