package array;

/**
 * Created by gouthamvidyapradhan on 03/12/2017.
 *
 * <p>Given an unsorted array of integers, find the length of longest continuous increasing
 * subsequence (subarray).
 *
 * <p>Example 1: Input: [1,3,5,4,7] Output: 3 Explanation: The longest continuous increasing
 * subsequence is [1,3,5], its length is 3. Even though [1,3,5,7] is also an increasing subsequence,
 * it's not a continuous one where 5 and 7 are separated by 4. Example 2: Input: [2,2,2,2,2] Output:
 * 1 Explanation: The longest continuous increasing subsequence is [2], its length is 1. Note:
 * Length of the array will not exceed 10,000.
 */
public class LongestIncreasingSubsequence {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 3, 5, 4, 7};
    System.out.println(new LongestIncreasingSubsequence().findLengthOfLCIS(A));
  }

  public int findLengthOfLCIS(int[] nums) {
    int max = 1, count = 1;
    if (nums.length == 1) return max;
    if (nums.length == 0) return 0;
    for (int i = 0, j = i + 1; j < nums.length; ) {
      if (nums[j] > nums[i]) {
        count++;
        i++;
        j++;
      } else {
        max = Math.max(max, count);
        count = 0;
        i = j;
        j = i + 1;
      }
    }
    return max;
  }
}
