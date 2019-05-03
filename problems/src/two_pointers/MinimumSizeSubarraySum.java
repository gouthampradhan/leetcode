package two_pointers;

/**
 * Created by gouthamvidyapradhan on 03/12/2017.
 *
 * <p>Given an array of n positive integers and a positive integer s, find the minimal length of a
 * contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * <p>For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal
 * length under the problem constraint.
 *
 * <p>click to show more practice.
 *
 * <p>Credits: Special thanks to @Freezen for adding this problem and creating all test cases.
 *
 * <p>Solution: O(n) solution. Solve using sliding window sub-array sum using two pointers.
 */
public class MinimumSizeSubarraySum {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {2, 3, 1, 2, 4, 3};
    System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(7, nums));
  }

  public int minSubArrayLen(int s, int[] nums) {
    int sum = 0, count = 0, min = Integer.MAX_VALUE;
    for (int i = 0, j = 0; j < nums.length; ) {
      if (nums[j] >= s) {
        return 1;
      } else {
        sum += nums[j];
        count++;
        if (sum >= s) {
          min = Math.min(min, count);
          while (j > i) {
            sum -= nums[i];
            count--;
            i++;
            if (sum < s) break;
            min = Math.min(min, count);
          }
        }
      }
      j++;
    }
    if (min == Integer.MAX_VALUE) {
      return 0;
    }
    return min;
  }
}
