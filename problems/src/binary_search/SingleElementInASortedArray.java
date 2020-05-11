package binary_search;

/**
 * Created by gouthamvidyapradhan on 30/01/2020 You are given a sorted array consisting of only
 * integers where every element appears exactly twice, except for one element which appears exactly
 * once. Find this single element that appears only once.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,1,2,3,3,4,4,8,8] Output: 2 Example 2:
 *
 * <p>Input: [3,3,7,7,10,11,11] Output: 10
 *
 * <p>Note: Your solution should run in O(log n) time and O(1) space.
 */
public class SingleElementInASortedArray {
  public static void main(String[] args) {
    int[] A = {3, 3, 7, 7, 10, 11, 11};
    System.out.println(new SingleElementInASortedArray().singleNonDuplicate(A));
  }

  public int singleNonDuplicate(int[] nums) {
    if (nums.length == 1) return nums[0];
    int l = 0, h = nums.length - 1;
    while (l <= h) {
      int m = l + ((h - l) / 2);
      int N = nums[m];
      if (m + 1 >= nums.length) {
        if (nums[m - 1] != N) {
          return N;
        }
        h = m - 1;
      } else if (m - 1 < 0) {
        if (nums[m + 1] != N) {
          return N;
        }
        l = m + 1;
      } else {
        if (m % 2 == 0) {
          if (nums[m + 1] != N && nums[m - 1] != N) {
            return N;
          } else if (nums[m + 1] != N) {
            h = m - 1;
          } else l = m + 1;
        } else {
          if (nums[m + 1] != N && nums[m - 1] != N) {
            return N;
          } else if (nums[m - 1] != N) {
            h = m - 1;
          } else l = m + 1;
        }
      }
    }
    return -1;
  }
}
