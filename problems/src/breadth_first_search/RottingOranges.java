package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 04/12/2019 In a given grid, each cell can have one of three
 * values:
 *
 * <p>the value 0 representing an empty cell; the value 1 representing a fresh orange; the value 2
 * representing a rotten orange. Every minute, any fresh orange that is adjacent (4-directionally)
 * to a rotten orange becomes rotten.
 *
 * <p>Return the minimum number of minutes that must elapse until no cell has a fresh orange. If
 * this is impossible, return -1 instead.
 *
 * <p>Example 1:
 *
 * <p>Input: [[2,1,1],[1,1,0],[0,1,1]] Output: 4 Example 2:
 *
 * <p>Input: [[2,1,1],[0,1,1],[1,0,1]] Output: -1 Explanation: The orange in the bottom left corner
 * (row 2, column 0) is never rotten, because rotting only happens 4-directionally. Example 3:
 *
 * <p>Input: [[0,2]] Output: 0 Explanation: Since there are already no fresh oranges at minute 0,
 * the answer is just 0.
 *
 * <p>Note:
 *
 * <p>1 <= grid.length <= 10 1 <= grid[0].length <= 10 grid[i][j] is only 0, 1, or 2.
 */
public class RottingOranges {
  final int[] R = {1, -1, 0, 0};
  final int[] C = {0, 0, 1, -1};

  public static void main(String[] args) {
    int[][] A = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    System.out.println(new RottingOranges().orangesRotting(A));
  }

  private class Node {
    int r, c, v;

    Node(int r, int c, int v) {
      this.r = r;
      this.c = c;
      this.v = v;
    }
  }

  public int orangesRotting(int[][] grid) {
    Queue<Node> queue = new ArrayDeque<>();
    boolean[][] done = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 2) {
          queue.offer(new Node(i, j, 0));
          done[i][j] = true;
        }
      }
    }
    int max = 0;
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      for (int i = 0; i < 4; i++) {
        int newR = curr.r + R[i];
        int newC = curr.c + C[i];
        if (newR >= 0 && newR < grid.length && newC >= 0 && newC < grid[0].length) {
          if (!done[newR][newC] && grid[newR][newC] != 0) {
            done[newR][newC] = true;
            max = Math.max(max, curr.v + 1);
            queue.offer(new Node(newR, newC, curr.v + 1));
          }
        }
      }
    }
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1 && !done[i][j]) {
          return -1;
        }
      }
    }
    return max;
  }
}
