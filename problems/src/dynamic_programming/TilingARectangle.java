package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 18/04/2020
 *
 * <p>Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile
 * the rectangle.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 2, m = 3 Output: 3 Explanation: 3 squares are necessary to cover the rectangle. 2
 * (squares of 1x1) 1 (square of 2x2) Example 2:
 *
 * <p>Input: n = 5, m = 8 Output: 5 Example 3:
 *
 * <p>Input: n = 11, m = 13 Output: 6
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 13 1 <= m <= 13
 */
public class TilingARectangle {

  public static void main(String[] args) {
    System.out.println(new TilingARectangle().tilingRectangle(11, 13));
  }

  Map<Long, Integer> DP;

  public int tilingRectangle(int n, int m) {
    DP = new HashMap<>();
    boolean[][] state = new boolean[n][m];
    return dp(state, 0, n, m);
  }

  private int dp(boolean[][] state, int r, int n, int m) {
    if (r >= n) return 0;
    int[] A = new int[m];
    for (int i = r; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (state[i][j]) {
          A[j]++;
        }
      }
    }
    long hashCode = 1;
    for (int i = 0; i < A.length; i++) {
      A[i] += r;
      hashCode = (hashCode * 31) + A[i];
    }
    if (DP.containsKey(hashCode)) return DP.get(hashCode);
    else {
      int min = Integer.MAX_VALUE;
      int c = m;
      for (int j = 0; j < m; j++) {
        if (!state[r][j]) {
          c = j;
          break;
        }
      }
      int k = 1;
      for (; k <= Math.min(n, m); k++) {
        if (r + k > n || c + k > m) break;
        else if (state[r][c + k - 1] || state[r + k - 1][c]) break;
        for (int a = r; a < (r + k); a++) {
          for (int b = c; b < (c + k); b++) {
            state[a][b] = true;
            A[b]++;
          }
        }
        int next = n;
        int j = c + k;
        for (int i = r; i < n; i++) {
          for (; j < m; j++) {
            if (!state[i][j]) {
              next = i;
              break;
            }
          }
          if (next != n) break;
          j = 0;
        }
        int result = dp(state, next, n, m);
        if (result > -1) {
          min = Math.min(min, result + 1);
        }
      }
      k--;
      for (int a = r; a < (r + k); a++) {
        for (int b = c; b < (c + k); b++) {
          state[a][b] = false;
        }
      }
      DP.put(hashCode, min == Integer.MAX_VALUE ? -1 : min);
      return DP.get(hashCode);
    }
  }
}
