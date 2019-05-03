package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 12/04/2017. Given a collection of numbers that might contain
 * duplicates, return all possible unique permutations.
 *
 * <p>For example, [1,1,2] have the following unique permutations: [ [1,1,2], [1,2,1], [2,1,1] ]
 */
public class PermutationsII {
  public static void main(String[] args) {
    int[] A = {1, 2, 2};
    System.out.println(new PermutationsII().permuteUnique(A));
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    nextPermutation(0, nums, result);
    return result;
  }

  private void nextPermutation(int i, int[] nums, List<List<Integer>> result) {
    if (i == nums.length - 1) {
      List<Integer> list = new ArrayList<>();
      for (int n : nums) list.add(n);
      result.add(list);
    } else {
      for (int j = i, l = nums.length; j < l; j++) {
        if (j > i && nums[j] == nums[i]) continue;
        swap(nums, i, j);
        nextPermutation(i + 1, Arrays.copyOf(nums, nums.length), result);
      }
    }
  }

  private void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}
