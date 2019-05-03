package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 02/04/2017. Given an unsorted array of integers, find the
 * length of longest increasing subsequence.
 *
 * <p>For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3,
 * 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is
 * only necessary for you to return the length.
 *
 * <p>Your algorithm should run in O(n2) complexity.
 *
 * <p>Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {9, 8, 7, 6};
    System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
  }

  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    int[] A = new int[nums.length];
    int max = Integer.MIN_VALUE;
    for (int i = 0, l = nums.length; i < l; i++) {
      int lis = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) lis = Math.max(lis, A[j] + 1);
      }
      A[i] = lis;
      max = Math.max(max, A[i]);
    }
    return max;
  }
}
