package reservoir_sampling;

import java.util.Random;

/**
 * Created by gouthamvidyapradhan on 10/12/2017. Given an array of integers with possible
 * duplicates, randomly output the index of a given target number. You can assume that the given
 * target number must exist in the array.
 *
 * <p>Note: The array size can be very large. Solution that uses too much extra space will not pass
 * the judge.
 *
 * <p>Example:
 *
 * <p>int[] nums = new int[] {1,2,3,3,3}; Solution solution = new Solution(nums);
 *
 * <p>// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal
 * probability of returning. solution.pick(3);
 *
 * <p>// pick(1) should return 0. Since in the array only nums[0] is equal to 1. solution.pick(1);
 */
public class RandomPickIndex {

  private int[] nums;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 2, 3, 3, 3};
    System.out.println(new RandomPickIndex(A).pick(1));
  }

  public RandomPickIndex(int[] nums) {
    this.nums = nums;
  }

  public int pick(int target) {
    int count = 0;
    for (int num : nums) {
      if (num == target) {
        count++;
      }
    }
    Random random = new Random();
    int nPick = 1 + random.nextInt(count);
    count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) {
        if (++count == nPick) {
          return i;
        }
      }
    }
    return 0; // this is impossible
  }
}
