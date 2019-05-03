package dynamic_programming;

import java.util.Arrays;

/**
 * Created by pradhang on 7/11/2017. After robbing those houses on that street, the thief has found
 * himself a new place for his thievery so that he will not get too much attention. This time, all
 * houses at this place are arranged in a circle. That means the first house is the neighbor of the
 * last one. Meanwhile, the security system for these houses remain the same as for those in the
 * previous street.
 *
 * <p>Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobberII {
  public static void main(String[] args) throws Exception {
    int[] nums = {6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3};
    System.out.println(new HouseRobberII().rob(nums));
  }

  public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    else if (nums.length == 2) {
      if (nums[0] > nums[1]) return nums[0];
      return nums[1];
    } else if (nums.length == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);
    int[] DP = new int[nums.length];
    for (int i = nums.length - 1; i > 0; i--) {
      if (i + 3 < nums.length) DP[i] = Math.max(nums[i] + DP[i + 2], nums[i] + DP[i + 3]);
      else if (i + 2 < nums.length) DP[i] = nums[i] + DP[i + 2];
      else DP[i] = nums[i];
    }
    int max = Math.max(DP[1], DP[2]);
    Arrays.fill(DP, 0); // reset
    for (int i = nums.length - 2; i >= 0; i--) {
      if (i + 3 < nums.length) DP[i] = Math.max(nums[i] + DP[i + 2], nums[i] + DP[i + 3]);
      else if (i + 2 < nums.length) DP[i] = nums[i] + DP[i + 2];
      else DP[i] = nums[i];
    }
    max = Math.max(max, Math.max(DP[0], DP[1]));
    return max;
  }
}
