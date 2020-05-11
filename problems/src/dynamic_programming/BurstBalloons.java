package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 02/01/2018. Given n balloons, indexed from 0 to n-1. Each
 * balloon is painted with a number on it represented by array nums. You are asked to burst all the
 * balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here
 * left and right are adjacent indices of i. After the burst, the left and right then becomes
 * adjacent.
 *
 * <p>Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * <p>Note: (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not
 * burst them. (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * <p>Example:
 *
 * <p>Given [3, 1, 5, 8]
 *
 * <p>Return 167
 *
 * <p>nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> [] coins = 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 =
 * 167
 *
 * <p>Solution: O(N ^ 3) The recursive top-down dp memorization solution is based on the idea where
 * each balloon is considered as the last balloon to be burst. For the above example 1,3,1,5,8,1 (1
 * included at either end to indicate boundary) each balloon starting from 3 until 8 is chosen each
 * time as a the last balloon to be burst using the boundary 1 on either side. So, for the first
 * iteration the result is calculated as 3*1(left boundary)*1(right boundary) + dp(1, 3)
 * (left-sub-problem having 1 and 3 as the boundary) + dp(3, 1) (right-sub-problem having 3 and 1 as
 * the boundary)
 */
public class BurstBalloons {
  private int[][] dp;
  private int[] N;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {3, 1, 5, 8};
    System.out.println(new BurstBalloons().maxCoins(A));
  }

  public int maxCoins(int[] nums) {
    N = new int[nums.length + 2];
    N[0] = N[N.length - 1] = 1; // boundary
    for (int i = 0; i < nums.length; i++) {
      N[i + 1] = nums[i];
    }
    int[][] DP = new int[N.length][N.length];
    for (int r = 2; r < N.length; r++) {
      for (int i = 0; i < N.length; i++) {
        int j = i + r;
        if (j < N.length) {
          int max = Integer.MIN_VALUE;
          for (int t = i + 1; t < j; t++) {
            max = Math.max(max, N[t] * N[i] * N[j] + DP[t][j] + DP[i][t]);
          }
          DP[i][j] = max;
        }
      }
    }
    return DP[0][N.length - 1];
    /*    for (int i = 0; i < nums.length; i++) {
      N[i + 1] = nums[i];
    }
    dp = new int[N.length][N.length];
    for (int[] aDp : dp) {
      Arrays.fill(aDp, -1);
    }*/
    //    return dp(0, N.length - 1);
  }

  private int dp(int l, int r) {
    if (l + 1 == r) return 0;
    if (dp[l][r] != -1) return dp[l][r];
    int result = 0;
    for (int i = l + 1; i < r; i++) {
      result = Math.max(result, N[i] * N[l] * N[r] + dp(l, i) + dp(i, r));
    }
    dp[l][r] = result;
    return dp[l][r];
  }
}
