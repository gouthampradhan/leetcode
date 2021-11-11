package hashing;

import java.util.HashMap;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given an array of integers, return indices of the
 * two numbers such that they add up to a specific target.
 *
 * <p>You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 *
 * <p>Example: Given nums = [2, 7, 11, 15], target = 9,
 *
 * <p>Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 */
public class TwoSum {
  HashMap<Integer, Integer> map = new HashMap<>();

  public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];

    for (int i : nums) {
      if (map.keySet().contains(i)) {
        int count = map.get(i);
        map.put(i, ++count);
      } else {
        map.put(i, 1);
      }
    }

    for (int i = 0, l = nums.length; i < l; i++) {
      int ele = nums[i];
      int req = target - ele;
      if (map.keySet().contains(req)) {
        result[0] = i;
        if (ele == req) {
          int count = map.get(req);
          if (count > 1) {
            for (int j = i + 1; j < l; j++) {
              if (nums[j] == req) {
                result[1] = j;
                return result;
              }
            }
          }
        } else {
          for (int j = i + 1; j < l; j++) {
            if (nums[j] == req) {
              result[1] = j;
              return result;
            }
          }
        }
      }
    }
    return result;
  }
}
