package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 27/01/2018. In a N x N grid representing a field of cherries,
 * each cell is one of three possible integers.
 *
 * <p>0 means the cell is empty, so you can pass through; 1 means the cell contains a cherry, that
 * you can pick up and pass through; -1 means the cell contains a thorn that blocks your way. Your
 * task is to collect maximum number of cherries possible by following the rules below:
 *
 * <p>Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid
 * path cells (cells with value 0 or 1); After reaching (N-1, N-1), returning to (0, 0) by moving
 * left or up through valid path cells; When passing through a path cell containing a cherry, you
 * pick it up and the cell becomes an empty cell (0); If there is no valid path between (0, 0) and
 * (N-1, N-1), then no cherries can be collected. Example 1: Input: grid = [[0, 1, -1], [1, 0, -1],
 * [1, 1, 1]] Output: 5 Explanation: The player started at (0, 0) and went down, down, right right
 * to reach (2, 2). 4 cherries were picked up during this single trip, and the matrix becomes
 * [[0,1,-1],[0,0,-1],[0,0,0]]. Then, the player went left, up, up, left to return home, picking up
 * one more cherry. The total number of cherries picked up is 5, and this is the maximum possible.
 * Note:
 *
 * <p>grid is an N by N 2D array, with 1 <= N <= 50. Each grid[i][j] is an integer in the set {-1,
 * 0, 1}. It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 *
 * <p>Solution O(N ^ 3) time-complexity. Traversing from (0, 0) -> (n - 1, n - 1) or the other way
 * around both are the same. The key point to note here is the traversal should be done by two
 * person simultaneously starting from (0, 0). Notice after t steps, each position (r, c) we could
 * be, is on the line r + c = t (along the diagonal). So if we have two people at positions (r1, c1)
 * and (r2, c2), then r2 = r1 + c1 - c2.
 */
public class CherryPickup {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
    System.out.println(new CherryPickup().cherryPickup(A));
  }

  public int cherryPickup(int[][] grid) {
    int[][][] DP = new int[grid.length][grid.length][grid.length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        Arrays.fill(DP[i][j], -1);
      }
    }
    int result = dp(grid.length, 0, 0, 0, DP, grid);
    if (result < 0) return 0;
    else return result;
  }

  private int dp(int N, int r1, int c1, int c2, int[][][] DP, int[][] grid) {
    int r2 = r1 + (c1 - c2);
    if (r1 >= N || c1 >= N || c2 >= N || r2 >= N || grid[r1][c1] == -1 || grid[r2][c2] == -1)
      return Integer.MIN_VALUE;
    else if (DP[r1][c1][c2] != -1) return DP[r1][c1][c2];
    else if (r1 == N - 1 && c1 == N - 1) return grid[N - 1][N - 1];
    else {
      int max = (c1 == c2) ? grid[r1][c1] : (grid[r1][c1] + grid[r2][c2]);
      // verify all the 4 possibilities. (P1 down + P2 right), (P1 right, P2 right), (P1 right + P2
      // down),
      // (P1 down, P2 down)
      max +=
          Math.max(
              Math.max(
                  Math.max(dp(N, r1 + 1, c1, c2, DP, grid), dp(N, r1 + 1, c1, c2 + 1, DP, grid)),
                  dp(N, r1, c1 + 1, c2, DP, grid)),
              dp(N, r1, c1 + 1, c2 + 1, DP, grid));
      DP[r1][c1][c2] = max;
      return max;
    }
  }
}
