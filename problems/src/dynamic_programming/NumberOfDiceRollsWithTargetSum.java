package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 29/11/2019 You have d dice, and each die has f faces numbered
 * 1, 2, ..., f.
 *
 * <p>Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so
 * the sum of the face up numbers equals target.
 *
 * <p>Example 1:
 *
 * <p>Input: d = 1, f = 6, target = 3 Output: 1 Explanation: You throw one die with 6 faces. There
 * is only one way to get a sum of 3. Example 2:
 *
 * <p>Input: d = 2, f = 6, target = 7 Output: 6 Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1. Example 3:
 *
 * <p>Input: d = 2, f = 5, target = 10 Output: 1 Explanation: You throw two dice, each with 5 faces.
 * There is only one way to get a sum of 10: 5+5. Example 4:
 *
 * <p>Input: d = 1, f = 2, target = 3 Output: 0 Explanation: You throw one die with 2 faces. There
 * is no way to get a sum of 3. Example 5:
 *
 * <p>Input: d = 30, f = 30, target = 500 Output: 222616187 Explanation: The answer must be returned
 * modulo 10^9 + 7.
 *
 * <p>Constraints:
 *
 * <p>1 <= d, f <= 30 1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {
  public static void main(String[] args) {
    System.out.println(new NumberOfDiceRollsWithTargetSum().numRollsToTarget(3, 3, 3));
  }

  private final int MOD = 1000000007;

  public int numRollsToTarget(int d, int f, int target) {
    int[][] DP = new int[d + 1][target + 1];
    for (int i = 1; i <= Math.min(f, target); i++) {
      DP[1][i] = 1;
    }
    for (int i = 2; i <= d; i++) {
      for (int j = 1; j <= target; j++) {
        for (int k = 1; k <= Math.min(f, j); k++) {
          DP[i][j] = (DP[i - 1][j - k]) == 0 ? DP[i][j] : ((DP[i][j] + (DP[i - 1][j - k])) % MOD);
        }
      }
    }
    return DP[d][target];
  }
}
