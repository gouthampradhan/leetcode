package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 07/07/2017. Find the contiguous subarray within an array
 * (containing at least one number) which has the largest sum.
 *
 * <p>For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has
 * the largest sum = 6.
 */
public class MaximumSubarray {
  public static void main(String[] args) throws Exception {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(new MaximumSubarray().maxSubArray(nums));
  }

  public int maxSubArray(int[] nums) {
    if (nums.length == 1) return nums[0];
    int max = nums[nums.length - 1];
    for (int i = nums.length - 2; i >= 0; i--) {
      nums[i] = Math.max(nums[i], nums[i] + nums[i + 1]);
      max = Math.max(max, nums[i]);
    }
    return max;
  }
}
