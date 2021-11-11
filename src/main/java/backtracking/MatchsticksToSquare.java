package backtracking;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/05/2019 Remember the story of Little Match Girl? By now, you
 * know exactly what matchsticks the little match girl has, please find out a way you can make one
 * square by using up all those matchsticks. You should not break any stick, but you can link them
 * up, and each matchstick must be used exactly one time.
 *
 * <p>Your input will be several matchsticks the girl has, represented with their stick length. Your
 * output will either be true or false, to represent whether you could make one square using all the
 * matchsticks the little match girl has.
 *
 * <p>Example 1: Input: [1,1,2,2,2] Output: true
 *
 * <p>Explanation: You can form a square with length 2, one side of the square came two sticks with
 * length 1. Example 2: Input: [3,3,3,3,4] Output: false
 *
 * <p>Explanation: You cannot find a way to form a square with all the matchsticks. Note: The length
 * sum of the given matchsticks is in the range of 0 to 10^9. The length of the given matchstick
 * array will not exceed 15.
 *
 * <p>Solution: O(2 ^ N): Generate a power set of all combination of numbers for the given array
 * which sum up to the length of a side of square. Now, to check if a square can be made using all
 * the sides sticks of different length, generate a hash for for each of the combination which was
 * generated in the previous step. The hash function should be such that it uses unique indexes of
 * each match stick. If 4 different hash values are formed using unique and all indices then a
 * square is possible.
 */
public class MatchsticksToSquare {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] A = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 6, 10, 10};
    System.out.println(new MatchsticksToSquare().makesquare(A));
  }

  class Pair {
    int value, i;

    Pair(int value, int i) {
      this.value = value;
      this.i = i;
    }
  }

  public boolean makesquare(int[] nums) {
    if (nums.length == 0) return false;
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }
    int side = sum / 4;
    if ((sum % 4) != 0) return false;
    List<List<Pair>> list = powerSet(nums, side);
    Set<Integer> hashIndex = new HashSet<>();
    int cons = 0;
    for (int i = 0; i < nums.length; i++) {
      cons |= (1 << i);
    }
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        Set<Integer> indexList = new HashSet<>();
        List<Pair> list1 = list.get(i);
        List<Pair> list2 = list.get(j);
        int hash = 0;
        for (Pair l1 : list1) {
          indexList.add(l1.i);
          hash |= (1 << l1.i);
        }
        boolean allUnique = true;
        for (Pair l2 : list2) {
          if (indexList.contains(l2.i)) {
            allUnique = false;
            break;
          }
          indexList.add(l2.i);
          hash |= (1 << l2.i);
        }
        if (allUnique) {
          hashIndex.add(hash);
          int complement = ((~hash) & cons);
          if (hashIndex.contains(complement)) return true;
        }
      }
    }
    return false;
  }

  private List<List<Pair>> powerSet(int[] nums, int expectedSum) {
    List<List<Pair>> result = new ArrayList<>();
    generate(0, nums, new ArrayList<>(), result, 0, expectedSum);
    return result;
  }

  private void generate(
      int i, int[] nums, List<Pair> subList, List<List<Pair>> result, int sum, int expected) {
    if (i >= nums.length) {
      if (sum == expected) {
        List<Pair> pairs = new ArrayList<>(subList);
        result.add(pairs);
      }
    } else {
      if (sum + nums[i] <= expected) {
        subList.add(new Pair(nums[i], i));
        generate(i + 1, nums, subList, result, sum + nums[i], expected);
        subList.remove(subList.size() - 1);
      }
      generate(i + 1, nums, subList, result, sum, expected);
    }
  }
}
