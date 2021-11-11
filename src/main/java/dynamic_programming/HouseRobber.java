package dynamic_programming;

/**
 * Created by pradhang on 4/3/2017. You are a professional robber planning to rob houses along a
 * street. Each house has a certain amount of money stashed, the only constraint stopping you from
 * robbing each of them is that adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * <p>Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
  private int[] max;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    max = new int[nums.length];
    if (nums.length == 1) return nums[0];
    max[nums.length - 1] = nums[nums.length - 1];
    max[nums.length - 2] = Math.max(nums[nums.length - 1], nums[nums.length - 2]);
    for (int i = nums.length - 3; i >= 0; i--) {
      max[i] = Math.max(max[i + 1], nums[i] + max[i + 2]);
    }
    return max[0];
  }
}
