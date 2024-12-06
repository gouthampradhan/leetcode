/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 19/05/2020
 *
 * <p>You are installing a billboard and want it to have the largest height. The billboard will have
 * two steel supports, one on each side. Each steel support must be an equal height.
 *
 * <p>You are given a collection of rods that can be welded together. For example, if you have rods
 * of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
 *
 * <p>Return the largest possible height of your billboard installation. If you cannot support the
 * billboard, return 0.
 *
 * <p>Example 1:
 *
 * <p>Input: rods = [1,2,3,6] Output: 6 Explanation: We have two disjoint subsets {1,2,3} and {6},
 * which have the same sum = 6. Example 2:
 *
 * <p>Input: rods = [1,2,3,4,5,6] Output: 10 Explanation: We have two disjoint subsets {2,3,5} and
 * {4,6}, which have the same sum = 10. Example 3:
 *
 * <p>Input: rods = [1,2] Output: 0 Explanation: The billboard cannot be supported, so we return 0.
 *
 * <p>Constraints:
 *
 * <p>1 <= rods.length <= 20 1 <= rods[i] <= 1000 sum(rods[i]) <= 5000
 */
public class TallestBillboard {

  public static void main(String[] args) {
    int[] A = {1, 2, 3, 4, 5, 6};
    System.out.println(new TallestBillboard().tallestBillboard(A));
  }

  public int tallestBillboard(int[] rods) {
    if (rods.length == 0) return 0;
    Map<Integer, Integer> leftMap = partition(rods, 0, rods.length / 2);
    Map<Integer, Integer> rightMap = partition(rods, (rods.length / 2) + 1, rods.length - 1);
    int max = 0;
    for (int d : leftMap.keySet()) {
      if (rightMap.containsKey(d)) {
        int m1 = leftMap.get(d);
        int m2 = rightMap.get(d);
        max = Math.max(max, m1 + (m2 - d));
        max = Math.max(max, m2 + (m1 - d));
      }
    }
    return max;
  }

  private Map<Integer, Integer> partition(int[] rods, int i, int j) {
    if (i > j) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, 0);
      return map;
    } else if (i == j) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(rods[i], rods[i]);
      map.put(0, 0);
      return map;
    } else {
      int m = (i + (j - i) / 2);
      Map<Integer, Integer> left = partition(rods, i, m);
      Map<Integer, Integer> right = partition(rods, m + 1, j);
      Map<Integer, Integer> newMap = new HashMap<>();
      for (int lDiff : left.keySet()) {
        int lMax = left.get(lDiff);
        for (int rDiff : right.keySet()) {
          int rMax = right.get(rDiff);
          int r1, r2, r3, r4;
          r1 = lMax;
          r2 = lMax - lDiff;
          r3 = rMax;
          r4 = rMax - rDiff;
          update(newMap, Math.abs(((r1 + r3) - (r2 + r4))), r1 + r3, r2 + r4);
          update(newMap, Math.abs(((r1 + r4) - (r2 + r3))), r1 + r4, r2 + r3);
        }
      }
      return newMap;
    }
  }

  private void update(Map<Integer, Integer> map, int diff, int rod1, int rod2) {
    if (map.getOrDefault(diff, 0) < Math.max(rod1, rod2)) {
      map.put(diff, Math.max(rod1, rod2));
    }
  }
}
