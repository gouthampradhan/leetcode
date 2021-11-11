package two_pointers;
/**
 * Created by gouthamvidyapradhan on 17/02/2018. Your are given an array of positive integers nums.
 *
 * <p>Count and print the number of (contiguous) subarrays where the product of all the elements in
 * the subarray is less than k.
 *
 * <p>Example 1: Input: nums = [10, 5, 2, 6], k = 100 Output: 8 Explanation: The 8 subarrays that
 * have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]. Note
 * that [10, 5, 2] is not included as the product of 100 is not strictly less than k. Note:
 *
 * <p>0 < nums.length <= 50000. 0 < nums[i] < 1000. 0 <= k < 10^6.
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class SubarrayProductLessThanK {

  public static void main(String[] args) throws Exception {
    int[] A = {10, 2, 2, 5, 4, 4, 4, 3, 7, 7};
    System.out.println(new SubarrayProductLessThanK().numSubarrayProductLessThanK(A, 289));
  }

  public int numSubarrayProductLessThanK(int[] nums, int k) {
    long prod = 1;
    int count = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < k) {
        count++;
        if ((prod * nums[i]) < k) {
          prod *= nums[i];
          if (!queue.isEmpty()) {
            count += (i - queue.peek());
          }
        } else {
          while (!queue.isEmpty()) {
            int last = queue.poll();
            prod /= nums[last];
            if ((prod * nums[i]) < k) {
              prod = prod * nums[i];
              if (!queue.isEmpty()) {
                count += (i - queue.peek());
              }
              break;
            }
          }
        }
        if (queue.isEmpty()) {
          prod = nums[i];
        }
        queue.offer(i);
      } else {
        queue.clear();
      }
    }
    return count;
  }
}
