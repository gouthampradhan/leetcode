package array;

/**
 * Created by gouthamvidyapradhan on 06/08/2019 Given an array of 4 digits, return the largest 24
 * hour time that can be made.
 *
 * <p>The smallest 24 hour time is 00:00, and the largest is 23:59. Starting from 00:00, a time is
 * larger if more time has elapsed since midnight.
 *
 * <p>Return the answer as a string of length 5. If no valid time can be made, return an empty
 * string.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,2,3,4] Output: "23:41" Example 2:
 *
 * <p>Input: [5,5,5,5] Output: ""
 *
 * <p>Note:
 *
 * <p>A.length == 4 0 <= A[i] <= 9 Solution O(N ^ 4) Check all combinations of time possible and
 * return the maximum possible as the answer.
 */
public class LargestTimeForGivenDigits {
  public static void main(String[] args) {
    int[] A = {2, 0, 6, 6};
    System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(A));
  }

  public String largestTimeFromDigits(int[] A) {
    int max = -1;
    String result = "";
    for (int i = 0; i < A.length; i++) {
      if (A[i] > 2) continue;
      for (int j = 0; j < A.length; j++) {
        if (j == i) continue;
        if (A[i] == 2 && A[j] > 3) continue;
        for (int k = 0; k < A.length; k++) {
          if (k == i || k == j) continue;
          if (A[k] > 5) continue;
          for (int l = 0; l < A.length; l++) {
            if (l == i || l == j || l == k) continue;
            int value = ((A[i] * 10 + A[j]) * 60) + A[k] * 10 + A[l];
            if (value > max) {
              max = value;
              result = A[i] + "" + A[j] + ":" + A[k] + "" + A[l];
            }
          }
        }
      }
    }
    return result;
  }
}
