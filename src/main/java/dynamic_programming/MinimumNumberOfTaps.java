package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 01/03/2020 There is a one-dimensional garden on the x-axis. The
 * garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
 *
 * <p>There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 *
 * <p>Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed)
 * means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 *
 * <p>Return the minimum number of taps that should be open to water the whole garden, If the garden
 * cannot be watered return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 5, ranges = [3,4,1,1,0,0] Output: 1 Explanation: The tap at point 0 can cover the
 * interval [-3,3] The tap at point 1 can cover the interval [-3,5] The tap at point 2 can cover the
 * interval [1,3] The tap at point 3 can cover the interval [2,4] The tap at point 4 can cover the
 * interval [4,4] The tap at point 5 can cover the interval [5,5] Opening Only the second tap will
 * water the whole garden [0,5] Example 2:
 *
 * <p>Input: n = 3, ranges = [0,0,0,0] Output: -1 Explanation: Even if you activate all the four
 * taps you cannot water the whole garden. Example 3:
 *
 * <p>Input: n = 7, ranges = [1,2,1,0,2,1,0,1] Output: 3 Example 4:
 *
 * <p>Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4] Output: 2 Example 5:
 *
 * <p>Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4] Output: 1
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 10^4 ranges.length == n + 1 0 <= ranges[i] <= 100
 */
public class MinimumNumberOfTaps {
  public static void main(String[] args) {
    int[] A = {0, 1, 2, 0, 0, 1, 1, 0};
    System.out.println(new MinimumNumberOfTaps().minTaps(7, A));
  }

  int[] DP;

  public int minTaps(int n, int[] ranges) {
    DP = new int[n + 1];
    Arrays.fill(DP, -2);
    return dp(0, 0, ranges, n);
  }

  private int dp(int i, int prev, int[] R, int n) {
    if (i > n) return 0;
    else if (DP[i] != -2) return DP[i];
    else {
      int min = Integer.MAX_VALUE;
      int start = R[prev] > 0 ? prev : i;
      for (int j = start; j < start + 100 && j <= n; j++) {
        if (j - R[j] <= prev) {
          int result = dp(j + R[j] + 1, j + R[j], R, n);
          if (result >= 0) {
            min = Math.min(min, result + 1);
          }
        }
      }
      DP[i] = (min == Integer.MAX_VALUE ? -1 : min);
      return DP[i];
    }
  }
}
