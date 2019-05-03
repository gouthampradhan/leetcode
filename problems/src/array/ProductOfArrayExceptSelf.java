package array;

/**
 * Created by gouthamvidyapradhan on 04/05/2017.
 *
 * <p>Given an array of n integers where n > 1, nums, return an array output such that output[i] is
 * equal to the product of all the elements of nums except nums[i].
 *
 * <p>Solve it without division and in O(n).
 *
 * <p>For example, given [1,2,3,4], return [24,12,8,6].
 *
 * <p>Follow up: Could you solve it with constant space complexity? (Note: The output array does not
 * count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4};
    int[] result = new ProductOfArrayExceptSelf().productExceptSelf(nums);
    for (int r : result) System.out.print(r + " ");
  }

  public int[] productExceptSelf(int[] nums) {
    int[] result = new int[nums.length];
    for (int i = 0, temp = 1, l = nums.length; i < l; i++) {
      result[i] = temp;
      temp *= nums[i];
    }
    for (int i = nums.length - 1, temp = 1; i >= 0; i--) {
      result[i] = result[i] * temp;
      temp *= nums[i];
    }
    return result;
  }
}
