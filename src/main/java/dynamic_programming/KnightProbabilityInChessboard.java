package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 13/02/2018. On an NxN chessboard, a knight starts at the r-th
 * row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so
 * the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * <p>A chess knight has 8 possible moves it can make, as illustrated below. Each move is two
 * squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * <p>Each time the knight is to move, it chooses one of eight possible moves uniformly at random
 * (even if the piece would go off the chessboard) and moves there.
 *
 * <p>The knight continues moving until it has made exactly K moves or has moved off the chessboard.
 * Return the probability that the knight remains on the board after it has stopped moving.
 *
 * <p>Example: Input: 3, 2, 0, 0 Output: 0.0625 Explanation: There are two moves (to (1,2), (2,1))
 * that will keep the knight on the board. From each of those positions, there are also two moves
 * that will keep the knight on the board. The total probability the knight stays on the board is
 * 0.0625. Note: N will be between 1 and 25. K will be between 0 and 100. The knight always
 * initially starts on the board.
 *
 * <p>Solution: Solution O(N ^ 2 x K) DP top down memoization for each different states.
 */
public class KnightProbabilityInChessboard {

  int[] R = {1, -1, 2, -2, -1, -2, 2, 1};
  int[] C = {2, 2, 1, 1, -2, -1, -1, -2};

  double[][][] dp;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new KnightProbabilityInChessboard().knightProbability(3, 2, 0, 0));
  }

  public double knightProbability(int N, int K, int r, int c) {
    dp = new double[N][N][K + 1];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        Arrays.fill(dp[i][j], -1.0D);
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        dp[i][j][0] = 1.0D;
      }
    }
    return dp(dp, r, c, K, N);
  }

  private double dp(double[][][] dp, int r, int c, int K, int N) {
    if (r < 0 || r >= N || c < 0 || c >= N) return 0.0D;
    if (dp[r][c][K] != -1.0D) return dp[r][c][K];
    double sum = 0.0D;
    for (int i = 0; i < 8; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newR < N && newC >= 0 && newC < N) {
        sum += dp(dp, newR, newC, K - 1, N);
      }
    }
    dp[r][c][K] = (sum / 8.0);
    return dp[r][c][K];
  }
}
