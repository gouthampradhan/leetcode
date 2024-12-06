/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 28/07/2020 Given a rows x cols matrix grid representing a field
 * of cherries. Each cell in grid represents the number of cherries that you can collect.
 *
 * <p>You have two robots that can collect cherries for you, Robot #1 is located at the top-left
 * corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 *
 * <p>Return the maximum number of cherries collection using both robots by following the rules
 * below:
 *
 * <p>From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1). When any robot
 * is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * When both robots stay on the same cell, only one of them takes the cherries. Both robots cannot
 * move outside of the grid at any moment. Both robots should reach the bottom row in the grid.
 *
 * <p>Example 1:
 *
 * <p>Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]] Output: 24 Explanation: Path of robot #1 and
 * #2 are described in color green and blue respectively. Cherries taken by Robot #1, (3 + 2 + 5 +
 * 2) = 12. Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12. Total of cherries: 12 + 12 = 24.
 * Example 2:
 *
 * <p>Input: grid =
 * [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]] Output: 28
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively. Cherries
 * taken by Robot #1, (1 + 9 + 5 + 2) = 17. Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11. Total
 * of cherries: 17 + 11 = 28. Example 3:
 *
 * <p>Input: grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]] Output: 22 Example 4:
 *
 * <p>Input: grid = [[1,1],[1,1]] Output: 4
 *
 * <p>Constraints:
 *
 * <p>rows == grid.length cols == grid[i].length 2 <= rows, cols <= 70 0 <= grid[i][j] <= 100
 */
public class CherryPickupII {
  private final int[] R = {1, 1, 1};
  private final int[] C = {0, -1, 1};

  public static void main(String[] args) {
    int[][] A = {
      {1, 0, 0, 3},
      {2, 0, 0, 0, 0, 3, 0},
      {2, 0, 9, 0, 0, 0, 0},
      {0, 3, 0, 5, 4, 0, 0},
      {1, 0, 2, 3, 0, 0, 6}
    };
    System.out.println(new CherryPickupII().cherryPickup(A));
  }

  int[][][] DP;

  public int cherryPickup(int[][] grid) {
    DP = new int[grid.length][grid[0].length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        Arrays.fill(DP[i][j], -1);
      }
    }
    return dp(0, 0, grid[0].length - 1, grid);
  }

  private int dp(int r, int c1, int c2, int[][] grid) {
    if (DP[r][c1][c2] != -1) return DP[r][c1][c2];
    else {
      int count = (c1 == c2) ? grid[r][c1] : (grid[r][c1] + grid[r][c2]);
      int max = count;
      for (int i = 0; i < 3; i++) {
        int newR = r + R[i];
        int newC1 = c1 + C[i];
        if (newR >= 0 && newR < grid.length && newC1 >= 0 && newC1 < grid[0].length) {
          for (int j = 0; j < 3; j++) {
            int newC2 = c2 + C[j];
            if (newC2 >= 0 && newC2 < grid[0].length) {
              max = Math.max(max, count + dp(newR, newC1, newC2, grid));
            }
          }
        }
      }
      DP[r][c1][c2] = max;
      return max;
    }
  }
}
