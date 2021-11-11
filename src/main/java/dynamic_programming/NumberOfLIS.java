package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 13/12/2017. Given an unsorted array of integers, find the
 * number of longest increasing subsequence.
 *
 * <p>Example 1: Input: [1,3,5,4,7] Output: 2 Explanation: The two longest increasing subsequence
 * are [1, 3, 4, 7] and [1, 3, 5, 7]. Example 2: Input: [2,2,2,2,2] Output: 5 Explanation: The
 * length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is
 * 1, so output 5. Note: Length of the given array will be not exceed 2000 and the answer is
 * guaranteed to be fit in 32-bit signed int.
 *
 * <p>Solution O(n ^ 2) compute the LIS and save the results in length also save the max length of
 * LIS in maxVal. Calculate the count as below
 *
 * <p>For every pair of (i, j) count[i] = count[i] + count[j] where length[i] == length[j] + 1 and
 * nums[j] < nums[i]
 *
 * <p>sum-up the count for every length where length[i] == maxVal
 */
public class NumberOfLIS {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 12, 11, 1, 1, 1, 12};
    System.out.println(new NumberOfLIS().findNumberOfLIS(A));
  }

  public int findNumberOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    int[] length = new int[nums.length];
    length[0] = 1;
    int maxVal = 1;
    for (int i = 1; i < nums.length; i++) {
      int max = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          max = Math.max(max, length[j] + 1);
          maxVal = Math.max(maxVal, max);
        }
      }
      length[i] = max;
    }
    int[] count = new int[nums.length];
    count[0] = 1;
    for (int i = 1; i < length.length; i++) {
      for (int j = 0; j < i; j++) {
        if ((length[j] + 1 == length[i]) && (nums[j] < nums[i])) {
          count[i] += count[j];
        }
      }
      if (count[i] == 0) {
        count[i] = 1; // default is just 1
      }
    }
    int ans = 0;
    for (int i = 0; i < length.length; i++) {
      if (length[i] == maxVal) {
        ans += count[i];
      }
    }
    return ans;
  }
}
