package depth_first_search;

/**
 * Created by gouthamvidyapradhan on 28/05/2019 Given a non-empty 2D array grid of 0's and 1's, an
 * island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * <p>Find the maximum area of an island in the given 2D array. (If there is no island, the maximum
 * area is 0.)
 *
 * <p>Example 1:
 *
 * <p>[[0,0,1,0,0,0,0,1,0,0,0,0,0], [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0], [0,1,0,0,1,1,0,0,1,1,1,0,0], [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,0,0,0,0,0,0,1,1,0,0,0,0]] Given the above grid, return 6. Note
 * the answer is not 11, because the island must be connected 4-directionally. Example 2:
 *
 * <p>[[0,0,0,0,0,0,0,0]] Given the above grid, return 0. Note: The length of each dimension in the
 * given grid does not exceed 50.
 *
 * <p>Solution: O(N x M) Do a dfs and keep track of max connected 1's
 */
public class MaxAreaOfIsland {
  final int[] R = {0, 0, -1, 1};
  final int[] C = {1, -1, 0, 0};

  int count = 0;
  int max = 0;
  boolean[][] done;

  public static void main(String[] args) {
    int[][] grid = {
      {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
      {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
      {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    };

    System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(grid));
  }

  public int maxAreaOfIsland(int[][] grid) {
    done = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1 && !done[i][j]) {
          count = 0;
          dfs(grid, i, j);
          max = Math.max(max, count);
        }
      }
    }
    return max;
  }

  private void dfs(int[][] grid, int r, int c) {
    done[r][c] = true;
    count++;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0
          && newC >= 0
          && newR < grid.length
          && newC < grid[0].length
          && !done[newR][newC]
          && grid[newR][newC] == 1) {
        dfs(grid, newR, newC);
      }
    }
  }
}
