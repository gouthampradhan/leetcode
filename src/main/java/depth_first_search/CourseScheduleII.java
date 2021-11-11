package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/06/2017. There are a total of n courses you have to take,
 * labeled from 0 to n - 1.
 *
 * <p>Some courses may have prerequisites, for example to take course 0 you have to first take
 * course 1, which is expressed as a pair: [0,1]
 *
 * <p>Given the total number of courses and a list of prerequisite pairs, return the ordering of
 * courses you should take to finish all courses.
 *
 * <p>There may be multiple correct orders, you just need to return one of them. If it is impossible
 * to finish all courses, return an empty array.
 *
 * <p>For example:
 *
 * <p>2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1]
 *
 * <p>4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take course 3 you
 * should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you
 * finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering
 * is[0,2,1,3].
 *
 * <p>Note: The input prerequisites is a graph represented by a list of edges, not adjacency
 * matrices. Read more about how a graph is represented. You may assume that there are no duplicate
 * edges in the input prerequisites.
 */
public class CourseScheduleII {
  private Map<Integer, List<Integer>> graph;
  private BitSet visited;
  private Queue<Integer> toposorted;

  public static void main(String[] args) throws Exception {
    int[][] pre = {{1, 0}};
    int[] result = new CourseScheduleII().findOrder(2, pre);
    for (int i : result) System.out.print(i + " ");
    System.out.println();
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int j = 0;
    int[] courses = new int[numCourses];
    int[] result = new int[numCourses];
    for (int i = 0; i < numCourses; i++) courses[i] = j++;
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
    int i = 0;
    while (!toposorted.isEmpty()) {
      int v = toposorted.poll();
      if (visited.get(v)) return new int[0];
      relax(v);
      result[i++] = v;
      courses[v] = -1;
    }
    // add the remaining courses
    for (int c : courses) if (c != -1) result[i++] = c;
    return result;
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
