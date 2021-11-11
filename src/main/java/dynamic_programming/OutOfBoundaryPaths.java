package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 15/05/2019 There is an m by n grid with a ball. Given the start
 * coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary
 * in four directions (up, down, left, right). However, you can at most move N times. Find out the
 * number of paths to move the ball out of grid boundary. The answer may be very large, return it
 * after mod 10 ^ 9 + 7.
 *
 * <p>Solution: O(m x n x N x 4) Move in all possible directions from the starting position (i, j)
 * and keep track of distance traversed and ensure the distance traversed does not exceed N. Keep
 * the count of number of possibilities to go out of the boundary for each cell reached. Return the
 * sum in cell (a, b)
 */
public class OutOfBoundaryPaths {

  final int[] R = {1, -1, 0, 0};
  final int[] C = {0, 0, 1, -1};
  int[][][] DP;
  int mod = 1000000007;

  public static void main(String[] args) {
    System.out.println(new OutOfBoundaryPaths().findPaths(2, 2, 2, 0, 0));
  }

  public int findPaths(int m, int n, int N, int a, int b) {
    if (N == 0) return 0;
    DP = new int[m][n][N + 1];

    for (int k = 1; k <= N; k++) {
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          for (int p = 0; p < 4; p++) {
            int newR = i + R[p];
            int newC = j + C[p];
            if (newR < 0 || newC < 0 || newR >= m || newC >= n) {
              DP[i][j][k] = ((DP[i][j][k] + 1) % mod);
            } else {
              DP[i][j][k] = (((DP[i][j][k] + DP[newR][newC][k - 1])) % mod);
            }
          }
        }
      }
    }

    return DP[a][b][N];
  }
}
