package array;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 17/12/2017. Given an unsorted array return whether an
 * increasing subsequence of length 3 exists or not in the array.
 *
 * <p>Formally the function should: Return true if there exists i, j, k such that arr[i] < arr[j] <
 * arr[k] given 0 ≤ i < j < k ≤ n-1 else return false. Your algorithm should run in O(n) time
 * complexity and O(1) space complexity.
 *
 * <p>Examples: Given [1, 2, 3, 4, 5], return true.
 *
 * <p>Given [5, 4, 3, 2, 1], return false.
 */
public class IncreasingTripletSubsequence {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 2, 3, 4, 5};
    System.out.println(new IncreasingTripletSubsequence().increasingTriplet(A));
  }

  public boolean increasingTriplet(int[] nums) {
    int[] A = new int[3];
    Arrays.fill(A, Integer.MAX_VALUE);
    for (int num : nums) {
      if (num < A[0]) {
        A[0] = num;
      } else if (num < A[1] && num > A[0]) {
        A[1] = num;
      } else if (num < A[2] && num > A[1]) {
        return true;
      }
    }
    return false;
  }
}
