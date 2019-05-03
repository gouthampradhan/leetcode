package greedy;

/**
 * Created by gouthamvidyapradhan on 17/03/2017. Given an array of non-negative integers, you are
 * initially positioned at the first index of the array.
 *
 * <p>Each element in the array represents your maximum jump length at that position.
 *
 * <p>Determine if you are able to reach the last index.
 *
 * <p>For example: A = [2,3,1,1,4], return true.
 *
 * <p>A = [3,2,1,0,4], return false.
 */
public class JumpGame {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {1, 2, 1, 0, 4};
    System.out.println(new JumpGame().canJump(nums));
  }

  public boolean canJump(int[] nums) {
    if (nums.length == 0) return false;
    int min = nums.length - 1, max = nums.length - 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if ((nums[i] + i) >= min) min = i;
    }
    return (min == 0);
  }
}
