package array;

/**
 * Created by gouthamvidyapradhan on 08/05/2019 Given a binary array, find the maximum number of
 * consecutive 1s in this array if you can flip at most one 0.
 *
 * <p>Example 1: Input: [1,0,1,1,0] Output: 4 Explanation: Flip the first zero will get the the
 * maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.
 * Note:
 *
 * <p>The input array will only contain 0 and 1. The length of input array is a positive integer and
 * will not exceed 10,000 Follow up: What if the input numbers come in one by one as an infinite
 * stream? In other words, you can't store all numbers coming from the stream as it's too large to
 * hold in memory. Could you solve it efficiently?
 *
 * <p>Solution: O(N) Maintain a left and right auxiliary array with counts of contagious 1's from
 * both directions. Now, iterate through the array and flip a 0 to 1 and sum up left and right
 * contagious sum of 1's and return the max sum as the answer
 */
public class MaxConsecutiveOnesII {
  public static void main(String[] args) {
    //
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int[] L = new int[nums.length];
    int[] R = new int[nums.length];
    boolean flag = false;
    int count = 0;
    int max = 0;
    for (int j = 0; j < nums.length; j++) {
      if (nums[j] == 1) {
        if (!flag) {
          flag = true;
        }
        count++;
        L[j] = count;
      } else {
        count = 0;
        flag = false;
        L[j] = count;
      }
      max = Math.max(max, count);
    }

    flag = false;
    count = 0;
    for (int j = nums.length - 1; j >= 0; j--) {
      if (nums[j] == 1) {
        if (!flag) {
          flag = true;
        }
        count++;
        R[j] = count;
      } else {
        count = 0;
        flag = false;
        R[j] = count;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        int l = i == 0 ? 0 : L[i - 1];
        int r = i == nums.length - 1 ? 0 : R[i + 1];
        max = Math.max(max, l + r + 1);
      }
    }
    return max;
  }
}
