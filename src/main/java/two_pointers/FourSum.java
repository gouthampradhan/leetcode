package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 29/03/2017. Given an array S of n integers, are there elements
 * a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array
 * which gives the sum of target.
 *
 * <p>Note: The solution set must not contain duplicate quadruplets.
 *
 * <p>For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * <p>A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 */
public class FourSum {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {1, 0, -1, 0, -2, 2};
    System.out.println(new FourSum().fourSum(nums, 0));
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length < 4) return result;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      if (i == 0 || nums[i] != nums[i - 1]) {
        for (int j = i + 1; j < nums.length - 2; j++) {
          if (j == i + 1 || nums[j] != nums[j - 1]) {
            int k = j + 1, l = nums.length - 1;
            while (k < l) {
              if (k != j + 1 && nums[k] == nums[k + 1]) {
                k++;
                continue;
              }
              int sum = nums[i] + nums[j] + nums[k] + nums[l];
              if (sum == target) {
                result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                k++;
                l--;
              } else if (sum < target) {
                k++;
              } else {
                l--;
              }
            }
          }
        }
      }
    }
    return result;
  }
}
