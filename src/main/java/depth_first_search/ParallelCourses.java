package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/11/2019 There are N courses, labelled from 1 to N.
 *
 * <p>We are given relations[i] = [X, Y], representing a prerequisite relationship between course X
 * and course Y: course X has to be studied before course Y.
 *
 * <p>In one semester you can study any number of courses as long as you have studied all the
 * prerequisites for the course you are studying.
 *
 * <p>Return the minimum number of semesters needed to study all courses. If there is no way to
 * study all the courses, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: N = 3, relations = [[1,3],[2,3]] Output: 2 Explanation: In the first semester, courses
 * 1 and 2 are studied. In the second semester, course 3 is studied. Example 2:
 *
 * <p>Input: N = 3, relations = [[1,2],[2,3],[3,1]] Output: -1 Explanation: No course can be studied
 * because they depend on each other.
 *
 * <p>Note:
 *
 * <p>1 <= N <= 5000 1 <= relations.length <= 5000 relations[i][0] != relations[i][1] There are no
 * repeated relations in the input.
 */
public class ParallelCourses {
  public static void main(String[] args) {
    int[][] A = {{1, 3}, {2, 3}};
    System.out.println(new ParallelCourses().minimumSemesters(3, A));
  }

  Map<Integer, List<Integer>> graph;
  Set<Integer> done;
  Set<Integer> visited;

  public int minimumSemesters(int N, int[][] relations) {
    graph = new HashMap<>();
    for (int[] E : relations) {
      graph.putIfAbsent(E[0], new ArrayList<>());
      graph.get(E[0]).add(E[1]);
    }
    done = new HashSet<>();
    visited = new HashSet<>();
    Stack<Integer> stack = new Stack<>();
    for (int v : graph.keySet()) {
      if (!done.contains(v)) {
        boolean status = dfs(v, stack); // toposort and return false if a cycle is found
        if (!status) return -1;
      }
    }
    int[] DP = new int[N + 1];
    int max = 0;
    while (!stack.isEmpty()) {
      int v = stack.pop();
      List<Integer> children = graph.get(v);
      if (children != null) {
        for (int c : children) {
          DP[c] = Math.max(DP[c], DP[v] + 1);
          max = Math.max(max, DP[c]);
        }
      }
    }
    return max + 1;
  }

  private boolean dfs(int v, Stack<Integer> stack) {
    done.add(v);
    visited.add(v);
    List<Integer> children = graph.get(v);
    if (children != null) {
      for (int c : children) {
        if (!visited.contains(c)) {
          if (!done.contains(c)) {
            boolean status = dfs(c, stack);
            if (!status) return false;
          }
        } else {
          return false;
        }
      }
    }
    visited.remove(v);
    stack.push(v);
    return true;
  }
}
