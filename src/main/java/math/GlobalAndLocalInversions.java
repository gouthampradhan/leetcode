package math;

/**
 * Created by gouthamvidyapradhan on 01/02/2018.
 *
 * <p>We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 *
 * <p>The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 *
 * <p>The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 *
 * <p>Return true if and only if the number of global inversions is equal to the number of local
 * inversions.
 *
 * <p>Example 1:
 *
 * <p>Input: A = [1,0,2] Output: true Explanation: There is 1 global inversion, and 1 local
 * inversion. Example 2:
 *
 * <p>Input: A = [1,2,0] Output: false Explanation: There are 2 global inversions, and 1 local
 * inversion. Note:
 *
 * <p>A will be a permutation of [0, 1, ..., A.length - 1]. A will have length in range [1, 5000].
 * The time limit for this problem has been reduced.
 *
 * <p>Solution: O(N) For every i, Maintain a max value up until (i - 1). If the current element at i
 * < max value return false
 */
public class GlobalAndLocalInversions {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public boolean isIdealPermutation(int[] A) {
    if (A.length == 0 || A.length == 1) return true;
    int max = Integer.MIN_VALUE;
    for (int i = 1; i < A.length; i++) {
      if (A[i] < max) {
        return false;
      } else {
        max = Math.max(max, A[i - 1]);
      }
    }
    return true;
  }
}
