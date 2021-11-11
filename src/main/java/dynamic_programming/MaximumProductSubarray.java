package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 02/04/2017. Find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 *
 * <p>For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product
 * = 6.
 */
public class MaximumProductSubarray {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {2, 3, -2, 4};
    System.out.println(new MaximumProductSubarray().maxProduct(A));
  }

  public int maxProduct(int[] nums) {
    if (nums.length == 1) return nums[0];
    int min = nums[0];
    int max = nums[0];
    int result = max;
    for (int i = 1; i < nums.length; i++) {
      int prevMin = min, prevMax = max;
      min = Math.min(nums[i], Math.min(nums[i] * prevMin, nums[i] * prevMax));
      max = Math.max(nums[i], Math.max(nums[i] * prevMin, nums[i] * prevMax));
      result = Math.max(result, max);
    }
    return result;
  }
}
