package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 14/03/2017. Given a set of distinct integers, nums, return all
 * possible subsets.
 *
 * <p>Note: The solution set must not contain duplicate subsets.
 *
 * <p>For example, If nums = [1,2,3], a solution is:
 *
 * <p>[ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */
public class Subsets {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] n = {1, 2, 3};
    List<List<Integer>> result = new Subsets().subsets(n);
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>()); // empty subset
    for (int i = 0, l = nums.length; i < l; i++) {
      for (int j = 0, resLen = result.size(); j < resLen; j++) {
        List<Integer> newList = new ArrayList<>(result.get(j));
        newList.add(nums[i]);
        result.add(newList);
      }
    }
    return result;
  }
}
