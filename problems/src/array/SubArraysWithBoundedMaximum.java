package array;

/**
 * Created by gouthamvidyapradhan on 29/03/2019 We are given an array A of positive integers, and
 * two positive integers L and R (L <= R).
 *
 * <p>Return the number of (contiguous, non-empty) subarrays such that the value of the maximum
 * array element in that subarray is at least L and at most R.
 *
 * <p>Example : Input: A = [2, 1, 4, 3] L = 2 R = 3 Output: 3 Explanation: There are three subarrays
 * that meet the requirements: [2], [2, 1], [3]. Note:
 *
 * <p>L, R and A[i] will be an integer in the range [0, 10^9]. The length of A will be in the range
 * of [1, 50000].
 */
public class SubArraysWithBoundedMaximum {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] A = {2, 1, 4, 3};
    System.out.println(new SubArraysWithBoundedMaximum().numSubarrayBoundedMax(A, 2, 3));
  }

  public int numSubarrayBoundedMax(int[] A, int L, int R) {
    int[] DP = new int[A.length];
    int v = -1;
    for (int i = A.length - 1; i >= 0; i--) {
      if (A[i] >= L && A[i] <= R) {
        if (v != -1) {
          DP[i] = v - i + 1;
        } else {
          DP[i] = 1;
          v = i;
        }
      } else if (A[i] < L) {
        if (v == -1) {
          v = i;
        }
        if (i + 1 < A.length) {
          if (A[i + 1] < L || (A[i + 1] >= L && A[i + 1] <= R)) {
            DP[i] = DP[i + 1];
          } else {
            DP[i] = 0;
          }
        }
      } else {
        v = -1;
      }
    }
    int sum = 0;
    for (int i = 0; i < DP.length; i++) {
      sum += DP[i];
    }
    return sum;
  }
}
