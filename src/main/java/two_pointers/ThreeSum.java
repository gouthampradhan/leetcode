package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 29/03/2017. Given an array S of n integers, are there elements
 * a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 *
 * <p>Note: The solution set must not contain duplicate triplets.
 *
 * <p>For example, given array S = [-1, 0, 1, 2, -1, -4],
 *
 * <p>A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class ThreeSum {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {
      -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1,
      2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4
    };
    System.out.println(new ThreeSum().threeSum(nums));
  }

  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length < 3) return result;
    Arrays.sort(nums);
    for (int i = 0, l = nums.length; i < l - 2; i++) {
      if (i == 0 || nums[i] != nums[i - 1]) {
        int j = i + 1, k = l - 1;
        while (k > j) {
          if (j != i + 1 && nums[j] == nums[j - 1]) {
            j++;
            continue;
          }
          int sum = nums[i] + nums[j] + nums[k];
          if (sum == 0) {
            result.add(Arrays.asList(nums[i], nums[j], nums[k]));
            k--;
            j++;
          } else if (sum > 0) k--;
          else j++;
        }
      }
    }
    return result;
  }
}
