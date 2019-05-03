package two_pointers;

/**
 * Created by gouthamvidyapradhan on 13/06/2017. Accepted Given an array nums, write a function to
 * move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * <p>For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3,
 * 12, 0, 0].
 *
 * <p>Note: You must do this in-place without making a copy of the array. Minimize the total number
 * of operations.
 */
public class MoveZeroes {
  public static void main(String[] args) throws Exception {
    int[] nums = {0, 0, 0, 0, 1, 0, 1, 0, 2};
    new MoveZeroes().moveZeroes(nums);
    for (int n : nums) System.out.print(n);
  }

  public void moveZeroes(int[] nums) {
    int i = 0;
    for (int j = 0, l = nums.length; j < l; ) {
      if (nums[j] == 0) j++;
      else {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++;
        j++;
      }
    }
    while (i < nums.length) nums[i++] = 0;
  }
}
