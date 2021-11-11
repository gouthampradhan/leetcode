package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 24/12/2017. Given an array which consists of non-negative
 * integers and an integer m, you can split the array into m non-empty continuous subarrays. Write
 * an algorithm to minimize the largest sum among these m subarrays.
 *
 * <p>Note: If n is the length of array, assume the following constraints are satisfied:
 *
 * <p>1 ≤ n ≤ 1000 1 ≤ m ≤ min(50, n) Examples:
 *
 * <p>Input: nums = [7,2,5,10,8] m = 2
 *
 * <p>Output: 18
 *
 * <p>Explanation: There are four ways to split nums into two subarrays. The best way is to split it
 * into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 *
 * <p>Solution O(n ^ 2 * k) Build a bottom up min-max dp table for each sub-array ranging from n ->
 * 0
 */
public class SplitArrayLargestSum {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {7, 2, 5, 10, 8};
    System.out.println(new SplitArrayLargestSum().splitArray(A, 2));
  }

  public int splitArray(int[] nums, int m) {
    int[][] dp = new int[m][nums.length];
    for (int i = nums.length - 1; i >= 0; i--) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (j + 1 >= nums.length) break;
        for (int k = 0; k < m - 1; k++) {
          dp[k + 1][i] = (dp[k + 1][i] == 0) ? Integer.MAX_VALUE : dp[k + 1][i];
          int temp = Math.max(sum, dp[k][j + 1]);
          dp[k + 1][i] = Math.min(dp[k + 1][i], temp);
        }
      }
      dp[0][i] = sum;
    }
    return dp[m - 1][0];
  }
}
