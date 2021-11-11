package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 10/12/2017. Given a list of non-negative numbers and a target
 * integer k, write a function to check if the array has a continuous subarray of size at least 2
 * that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 *
 * <p>Example 1: Input: [23, 2, 4, 6, 7], k=6 Output: True Explanation: Because [2, 4] is a
 * continuous subarray of size 2 and sums up to 6. Example 2: Input: [23, 2, 6, 4, 7], k=6 Output:
 * True Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * Note: The length of the array won't exceed 10,000. You may assume the sum of all the numbers is
 * in the range of a signed 32-bit integer.
 *
 * <p>Solution: O(n) sum the elements and maintain a hashmap of key value pair of (sum % k) ->
 * index. If the key is already found in the hashmap and the difference in the current_index and
 * hashmap index is > 1 then return true.
 */
public class ContinuousSubarraySum {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 3, 6, 12, 7};
    System.out.println(new ContinuousSubarraySum().checkSubarraySum(A, 6));
  }

  public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      int mod = (k == 0) ? sum : sum % k; // this is to handle case where k is 0
      if (map.containsKey(mod)) {
        if (i - map.get(mod) > 1) {
          return true;
        }
      } else {
        map.put(mod, i);
      }
    }
    return false;
  }
}
