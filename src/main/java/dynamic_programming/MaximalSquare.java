package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 28/11/2017. Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * <p>For example, given the following matrix:
 *
 * <p>1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0 Return 4.
 *
 * <p>Solution: O(n * m) time and space complexity. Calculate the max length of a square using DP(i,
 * j) = min(DP[i - 1][j], DP[i][j - 1], DP[i - 1][j - 1]) + 1 Return the square of the answer.
 */
public class MaximalSquare {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    char[][] A = {
      {'1', '0', '1', '0', '0'},
      {'1', '0', '1', '1', '1'},
      {'1', '1', '1', '1', '1'},
      {'1', '0', '0', '1', '0'}
    };
    System.out.println(new MaximalSquare().maximalSquare(A));
  }

  public int maximalSquare(char[][] matrix) {
    if (matrix.length == 0) return 0;
    int[][] dp = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (j - 1 >= 0 && i - 1 >= 0) {
          if (matrix[i][j] == '1') {
            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]) + 1;
          }
        } else {
          dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
        }
      }
    }
    int max = 0;
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        max = Math.max(max, dp[i][j]);
      }
    }
    return max * max;
  }
}
