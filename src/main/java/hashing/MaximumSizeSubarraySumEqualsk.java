package hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 18/10/2017. Given an array nums and a target value k, find the
 * maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 *
 * <p>Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer
 * range.
 *
 * <p>Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5,
 * -2] sums to 3 and is the longest)
 *
 * <p>Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to
 * 1 and is the longest)
 *
 * <p>Follow Up: Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsk {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, -1, 5, -2, 3};
    System.out.println(new MaximumSizeSubarraySumEqualsk().maxSubArrayLen(A, 10));
  }

  public int maxSubArrayLen(int[] nums, int k) {
    Map<Integer, Integer> index = new HashMap<>();
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      index.putIfAbsent(sum, i);
    }
    sum = 0;
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (sum == k) {
        ans = Math.max(ans, i + 1);
      } else {
        int exp = sum - k;
        if (index.containsKey(exp)) {
          int farLeft = index.get(exp);
          if (farLeft < i) {
            ans = Math.max(ans, i - index.get(exp));
          }
        }
      }
    }
    return ans;
  }
}
