package greedy;

/**
 * Created by gouthamvidyapradhan on 02/04/2017. Given an array of non-negative integers, you are
 * initially positioned at the first index of the array.
 *
 * <p>Each element in the array represents your maximum jump length at that position.
 *
 * <p>Your goal is to reach the last index in the minimum number of jumps.
 *
 * <p>For example: Given array A = [2,3,1,1,4]
 *
 * <p>The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then
 * 3 steps to the last index.)
 *
 * <p>Note: You can assume that you can always reach the last index.
 */
public class JumpGameII {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public int jump(int[] nums) {
    int step = 0;
    int e = 0, max = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      max = Math.max(max, i + nums[i]);
      if (i == e) {
        step++;
        e = max;
      }
    }
    return step;
  }
}
