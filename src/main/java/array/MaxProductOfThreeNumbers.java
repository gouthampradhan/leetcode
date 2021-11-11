package array;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 27/06/2017. Given an integer array, find three numbers whose
 * product is maximum and output the maximum product.
 *
 * <p>Example 1: Input: [1,2,3] Output: 6 Example 2: Input: [1,2,3,4] Output: 24 Note: The length of
 * the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaxProductOfThreeNumbers {
  public static void main(String[] args) {
    int[] A = {1, 2, 3};
    System.out.println(new MaxProductOfThreeNumbers().maximumProduct(A));
  }

  public int maximumProduct(int[] nums) {
    Arrays.sort(nums);
    int prod1 = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
    int prod2 = nums[nums.length - 1] * nums[0] * nums[1];
    return prod1 > prod2 ? prod1 : prod2;
  }
}
