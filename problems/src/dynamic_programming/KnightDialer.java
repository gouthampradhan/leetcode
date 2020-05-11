package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 24/09/2019 A chess knight can move as indicated in the chess
 * diagram below:
 *
 * <p>.
 *
 * <p>This time, we place our chess knight on any numbered key of a phone pad (indicated above), and
 * the knight makes N-1 hops. Each hop must be from one key to another numbered key.
 *
 * <p>Each time it lands on a key (including the initial placement of the knight), it presses the
 * number of that key, pressing N digits total.
 *
 * <p>How many distinct numbers can you dial in this manner?
 *
 * <p>Since the answer may be large, output the answer modulo 10^9 + 7.
 *
 * <p>Example 1:
 *
 * <p>Input: 1 Output: 10 Example 2:
 *
 * <p>Input: 2 Output: 20 Example 3:
 *
 * <p>Input: 3 Output: 46
 *
 * <p>Note:
 *
 * <p>1 <= N <= 5000
 *
 * <p>Solution: O(N x 4 x 3) Visit all different possible states and sum up the total possible
 * moves. Cache the states to avoid recalculating.
 */
public class KnightDialer {

  final int[] R = {-1, -1, -2, -2, 1, 1, 2, 2};
  final int[] C = {2, -2, -1, 1, 2, -2, 1, -1};

  public static void main(String[] args) {
    System.out.println(new KnightDialer().knightDialer(2));
  }

  int[][][] DP;

  public int knightDialer(int N) {
    DP = new int[4][3][N + 1];
    int ans = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i == 3 && j == 0) || (i == 3 && j == 2)) continue;
        DP[i][j][0] = 1;
      }
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i == 3 && j == 0) || (i == 3 && j == 2)) continue;
        ans += (dp(N - 1, i, j) % 10e9 + 7);
        ans %= 10e9 + 7;
      }
    }
    return ans;
  }

  private int dp(int N, int r, int c) {
    if (N < 0) return 0;
    if (r == 3 && c == 0) return 0;
    if (r == 3 && c == 2) return 0;
    if (DP[r][c][N] != 0) return DP[r][c][N];
    int sum = 0;
    for (int i = 0; i < 8; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newC >= 0 && newR < 4 && newC < 3) {
        sum += (dp(N - 1, newR, newC) % 10e9 + 7);
        sum %= 10e9 + 7;
      }
    }
    DP[r][c][N] = sum;
    return sum;
  }
}
