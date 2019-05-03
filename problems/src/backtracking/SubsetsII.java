package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 14/03/2017. Given a collection of integers that might contain
 * duplicates, nums, return all possible subsets.
 *
 * <p>Note: The solution set must not contain duplicate subsets.
 *
 * <p>For example, If nums = [1,2,2], a solution is:
 *
 * <p>[ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 */
public class SubsetsII {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] n = {1, 2, 3};
    List<List<Integer>> result = new SubsetsII().subsetsWithDup(n);
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>()); // empty subset
    int start = 0, newStart = 0;
    Arrays.sort(nums);
    for (int i = 0, l = nums.length; i < l; i++) {
      newStart = result.size();
      if (i == 0 || nums[i] != nums[i - 1]) {
        start = 0;
      }
      for (int j = start, resLen = result.size(); j < resLen; j++) {
        List<Integer> newList = new ArrayList<>(result.get(j));
        newList.add(nums[i]);
        result.add(newList);
      }
      start = newStart;
    }
    return result;
  }
}
