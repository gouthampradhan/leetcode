package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 05/04/2020 You have a pointer at index 0 in an array of size
 * arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array
 * or stay in the same place (The pointer should not be placed outside the array at any time).
 *
 * <p>Given two integers steps and arrLen, return the number of ways such that your pointer still at
 * index 0 after exactly steps steps.
 *
 * <p>Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * <p>Example 1:
 *
 * <p>Input: steps = 3, arrLen = 2 Output: 4 Explanation: There are 4 differents ways to stay at
 * index 0 after 3 steps. Right, Left, Stay Stay, Right, Left Right, Stay, Left Stay, Stay, Stay
 * Example 2:
 *
 * <p>Input: steps = 2, arrLen = 4 Output: 2 Explanation: There are 2 differents ways to stay at
 * index 0 after 2 steps Right, Left Stay, Stay Example 3:
 *
 * <p>Input: steps = 4, arrLen = 2 Output: 8
 *
 * <p>Constraints:
 *
 * <p>1 <= steps <= 500 1 <= arrLen <= 10^6
 *
 * <p>Solution O(S x S) where S is number of steps. This is quite a straight forward problem. Every
 * state is a combination of position in the array and the number of steps. From every state we can
 * traverse in three direction remain in the same position i.e (i, n - 1), move right (i + 1, n - 1)
 * and move left (i - 1, n - 1). The base state will be (0, 0) which is equal to count of 1, memoize
 * each state and do a dop down dp staring from state (0, N).
 */
public class NumberOfWaysToStayInTheSamePlace {

  private static final int MOD = (int) (1e9 + 7);

  public static void main(String[] args) {
    System.out.println(new NumberOfWaysToStayInTheSamePlace().numWays(500, 1000000));
  }

  int[][] DP;

  public int numWays(int steps, int arrLen) {
    int colLimit = arrLen < steps ? arrLen : steps;
    DP = new int[colLimit + 1][steps + 1];
    for (int i = 0; i <= colLimit; i++) {
      Arrays.fill(DP[i], -1);
    }
    DP[0][0] = 1;
    return (int) dp(0, steps, arrLen);
  }

  private long dp(int i, int n, int A) {
    if (i < 0 || i >= A) return 0;
    else if (n < 0) return 0;
    if (DP[i][n] != -1) return DP[i][n];
    DP[i][n] =
        (int)
            (((((dp(i, n - 1, A) % MOD) + (dp(i - 1, n - 1, A) % MOD)) % MOD)
                    + (dp(i + 1, n - 1, A) % MOD))
                % MOD);
    return DP[i][n];
  }
}
