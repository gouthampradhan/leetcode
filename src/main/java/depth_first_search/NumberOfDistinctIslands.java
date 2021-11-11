package depth_first_search;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 23/04/2018. Given a non-empty 2D array grid of 0's and 1's, an
 * island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * <p>Count the number of distinct islands. An island is considered to be the same as another if and
 * only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * <p>Example 1: 11000 11000 00011 00011 Given the above grid map, return 1. Example 2: 11011 10000
 * 00001 11011 Given the above grid map, return 3.
 *
 * <p>Notice that: 11 1 and 1 11 are considered different island islands, because we do not consider
 * reflection / rotation. Note: The length of each dimension in the given grid does not exceed 50.
 *
 * <p>Solution: O(N x M) create a signature of each island based on the direction and then use a
 * hashset to count the islands.
 */
public class NumberOfDistinctIslands {
  private int[] R = {0, 1, 0, -1};
  private int[] C = {1, 0, -1, 0};
  private boolean[][] done;
  private Set<String> islands;
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    int[][] N = {{1, 1, 1, 1}, {1, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 1, 1}, {1, 1, 0, 1}};
    System.out.println(new NumberOfDistinctIslands().numDistinctIslands(N));
  }

  public int numDistinctIslands(int[][] grid) {
    done = new boolean[grid.length][grid[0].length];
    islands = new HashSet<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (!done[i][j] && grid[i][j] == 1) {
          StringBuilder sb = new StringBuilder();
          dfs(i, j, grid, sb);
          islands.add(sb.toString());
        }
      }
    }
    return islands.size();
  }

  private void dfs(int r, int c, int[][] grid, StringBuilder sb) {
    done[r][c] = true;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length) {
        if (!done[newR][newC] && grid[newR][newC] == 1) {
          if (i == 0) {
            sb.append("R");
          } else if (i == 1) {
            sb.append("D");
          } else if (i == 2) {
            sb.append("L");
          } else {
            sb.append("U");
          }
          dfs(newR, newC, grid, sb);
        }
      }
    }
    sb.append("B");
  }
}
