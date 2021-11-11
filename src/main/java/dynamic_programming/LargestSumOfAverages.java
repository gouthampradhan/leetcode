package dynamic_programming;
/**
 * Created by gouthamvidyapradhan on 04/05/2018. We partition a row of numbers A into at most K
 * adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the
 * largest score we can achieve?
 *
 * <p>Note that our partition must use every number in A, and that scores are not necessarily
 * integers.
 *
 * <p>Example: Input: A = [9,1,2,3,9] K = 3 Output: 20 Explanation: The best choice is to partition
 * A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20. We could have also
 * partitioned A into [9, 1], [2], [3, 9], for example. That partition would lead to a score of 5 +
 * 2 + 6 = 13, which is worse.
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 100. 1 <= A[i] <= 10000. 1 <= K <= A.length. Answers within 10^-6 of the
 * correct answer will be accepted as correct.
 *
 * <p>Solution: O(N x N x K): Calculate average for each sub-array starting from the right.
 */
public class LargestSumOfAverages {

  public static void main(String[] args) {
    int[] A = {9, 1, 2, 3, 9};
    System.out.println(new LargestSumOfAverages().largestSumOfAverages(A, 3));
  }

  public double largestSumOfAverages(int[] A, int K) {
    double[][] dp = new double[K][A.length];
    for (int i = dp[0].length - 1; i >= 0; i--) {
      dp[0][i] = A[i];
      if (i + 1 < dp[0].length) {
        dp[0][i] += dp[0][i + 1];
      }
    }

    for (int i = dp[0].length - 1, j = 1; i >= 0; i--, j++) {
      dp[0][i] = dp[0][i] / j;
    }

    for (int i = dp[0].length - 1; i >= 0; i--) {
      for (int j = 1; j < dp.length; j++) {
        double sum = 0.0D;
        for (int k = i, c = 1; k < dp[0].length; k++, c++) {
          sum += A[k];
          if (k + 1 < dp[0].length) {
            double avg = sum / c;
            avg += dp[j - 1][k + 1];
            dp[j][i] = Math.max(dp[j][i], avg);
          }
        }
      }
    }
    return dp[K - 1][0];
  }
}
