package array;

/**
 * Created by gouthamvidyapradhan on 06/08/2017. Given an array with n objects colored red, white or
 * blue, sort them so that objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 *
 * <p>Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue
 * respectively.
 *
 * <p>Note: You are not suppose to use the library's sort function for this problem.
 *
 * <p>Follow up: A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total
 * number of 0's, then 1's and followed by 2's.
 *
 * <p>Could you come up with an one-pass algorithm using only constant space?
 *
 * <p>Solution: The below solution works with one pass. The basic idea is to keep track of start and
 * end index of contiguous 1s and push the 0s to left of 1s and 2 to right of 1s.
 */
public class SortColors {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {2, 1, 0, 0, 1};
    new SortColors().sortColors(nums);
    for (int i : nums) System.out.println(i);
  }

  public void sortColors(int[] nums) {
    int s = nums[0]; // save the first index value
    nums[0] = 1; // overwrite with 1
    int l = 0, r = 0; // left and right index indicating the start and end index of 1s
    for (int i = 1; i < nums.length; i++) {
      switch (nums[i]) {
        case 0:
          nums[l] = 0;
          nums[r + 1] = 1;
          if (r + 1 != i) {
            nums[i] = 2;
          }
          l++;
          r++;
          break;

        case 1:
          nums[r + 1] = 1;
          if (r + 1 != i) {
            nums[i] = 2;
          }
          r++;
          break;
      }
    }
    // replace the initial overwritten value with the original value
    if (s == 0) nums[l] = 0;
    else if (s == 2) nums[r] = 2;
  }
}
