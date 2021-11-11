package array;

/**
 * Created by gouthamvidyapradhan on 08/05/2019 Given a binary array, find the maximum number of
 * consecutive 1s in this array.
 *
 * <p>Example 1: Input: [1,1,0,1,1,1] Output: 3 Explanation: The first two digits or the last three
 * digits are consecutive 1s. The maximum number of consecutive 1s is 3. Note:
 *
 * <p>The input array will only contain 0 and 1. The length of input array is a positive integer and
 * will not exceed 10,000
 */
public class MaxConsecutiveOnes {
  public static void main(String[] args) {
    //
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;
    boolean flag = false;
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        if (!flag) {
          flag = true;
        }
        count++;
        max = Math.max(max, count);
      } else {
        count = 0;
        flag = false;
      }
    }
    return max;
  }
}
