package array;

import java.util.TreeSet;

/**
 * Created by gouthamvidyapradhan on 01/01/2018. There is a garden with N slots. In each slot, there
 * is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly
 * one flower blooming and it will be in the status of blooming since then.
 *
 * <p>Given an array flowers consists of number from 1 to N. Each number in the array represents the
 * place where the flower will open in that day.
 *
 * <p>For example, flowers[i] = x means that the unique flower that blooms at day i will be at
 * position x, where i and x will be in the range from 1 to N.
 *
 * <p>Also given an integer k, you need to output in which day there exists two flowers in the
 * status of blooming, and also the number of flowers between them is k and these flowers are not
 * blooming.
 *
 * <p>If there isn't such day, output -1.
 *
 * <p>Example 1: Input: flowers: [1,3,2] k: 1 Output: 2 Explanation: In the second day, the first
 * and the third flower have become blooming. Example 2: Input: flowers: [1,2,3] k: 1 Output: -1
 * Note: The given array will be in the range [1, 20000].
 *
 * <p>Solution: O(n log n). Maintain a tree-set of bloomed flowers and for every element in the
 * array find the upper and lower bound bloomed flowers and calculate their difference with the
 * current. If the difference is k return the current day, if none found then return -1
 */
public class KEmptySlots {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 3, 2};
    System.out.println(new KEmptySlots().kEmptySlots(A, 2));
  }

  public int kEmptySlots(int[] flowers, int k) {
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = 0; i < flowers.length; i++) {
      Integer lowerBound = set.floor(flowers[i]);
      Integer upperBound = set.ceiling(flowers[i]);
      if (lowerBound != null) {
        if ((Math.abs(flowers[i] - lowerBound) + 1) == k) {
          return i + 1;
        }
      }
      if (upperBound != null) {
        if ((Math.abs(flowers[i] - upperBound) + 1) == k) {
          return i + 1;
        }
      }
      set.add(flowers[i]);
    }
    return -1;
  }
}
