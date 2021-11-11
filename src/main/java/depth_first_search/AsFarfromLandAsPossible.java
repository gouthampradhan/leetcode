package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 28/08/2019 Given an N x N grid containing only values 0 and 1,
 * where 0 represents water and 1 represents land, find a water cell such that its distance to the
 * nearest land cell is maximized and return the distance.
 *
 * <p>The distance used in this problem is the Manhattan distance: the distance between two cells
 * (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * <p>If no land or water exists in the grid, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: [[1,0,1],[0,0,0],[1,0,1]] Output: 2 Explanation: The cell (1, 1) is as far as possible
 * from all the land with distance 2. Example 2:
 *
 * <p>Input: [[1,0,0],[0,0,0],[0,0,0]] Output: 4 Explanation: The cell (2, 2) is as far as possible
 * from all the land with distance 4.
 *
 * <p>Note:
 *
 * <p>1 <= grid.length == grid[0].length <= 100 grid[i][j] is 0 or 1
 *
 * <p>Solution: O(N x N) Do a multi-sources BFS starting from each of the water cell and end as soon
 * as a land cell is found. Record the maximum distance to the land cell and return the value.
 */
public class AsFarfromLandAsPossible {
  final int[] R = {1, -1, 0, 0};
  final int[] C = {0, 0, -1, 1};

  public static void main(String[] args) {
    int[][] G = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
    System.out.println(new AsFarfromLandAsPossible().maxDistance(G));
  }

  private class Node {
    int r, c, d;

    Node(int r, int c, int d) {
      this.r = r;
      this.c = c;
      this.d = d;
    }
  }

  public int maxDistance(int[][] grid) {
    int[][] D = new int[grid.length][grid[0].length];
    Queue<Node> queue = new ArrayDeque<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          queue.offer(new Node(i, j, 0));
        } else {
          D[i][j] = -1;
        }
      }
    }
    if (queue.isEmpty()) return -1;
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      for (int i = 0; i < 4; i++) {
        int newR = current.r + R[i];
        int newC = current.c + C[i];
        if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length) {
          if (D[newR][newC] < 0) {
            D[newR][newC] = current.d + 1;
            Node child = new Node(newR, newC, current.d + 1);
            queue.offer(child);
          }
        }
      }
    }
    int max = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0) {
          max = Math.max(max, D[i][j]);
        }
      }
    }
    return max == 0 ? -1 : max;
  }
}
