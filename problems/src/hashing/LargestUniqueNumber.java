package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/11/2019 Given an array of integers A, return the largest
 * integer that only occurs once.
 *
 * <p>If no integer occurs once, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: [5,7,3,9,4,9,8,3,1] Output: 8 Explanation: The maximum integer in the array is 9 but it
 * is repeated. The number 8 occurs only once, so it's the answer. Example 2:
 *
 * <p>Input: [9,9,8,8] Output: -1 Explanation: There is no number that occurs only once.
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 2000 0 <= A[i] <= 1000
 */
public class LargestUniqueNumber {
  public static void main(String[] args) {
    //
  }

  public int largestUniqueNumber(int[] A) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : A) {
      map.putIfAbsent(i, 0);
      int v = map.get(i) + 1;
      map.put(i, v);
    }
    int max = -1;
    for (int k : map.keySet()) {
      if (map.get(k) == 1) {
        max = Math.max(max, k);
      }
    }
    return max;
  }
}
