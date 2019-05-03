package array;

/**
 * Created by gouthamvidyapradhan on 09/02/2018. In a given integer array nums, there is always
 * exactly one largest element.
 *
 * <p>Find whether the largest element in the array is at least twice as much as every other number
 * in the array.
 *
 * <p>If it is, return the index of the largest element, otherwise return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [3, 6, 1, 0] Output: 1 Explanation: 6 is the largest integer, and for every
 * other number in the array x, 6 is more than twice as big as x. The index of value 6 is 1, so we
 * return 1.
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [1, 2, 3, 4] Output: -1 Explanation: 4 isn't at least as big as twice the value
 * of 3, so we return -1.
 *
 * <p>Note:
 *
 * <p>nums will have a length in the range [1, 50]. Every nums[i] will be an integer in the range
 * [0, 99].
 */
public class LargestNumberAtLeastTwice {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public int dominantIndex(int[] nums) {
    int index = 0, max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > max) {
        max = nums[i];
        index = i;
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (i == index) continue;
      if (((long) nums[i] * 2) > max) {
        return -1;
      }
    }
    return index;
  }
}
