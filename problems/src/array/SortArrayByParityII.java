package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 20/08/2019 Given an array A of non-negative integers, half of
 * the integers in A are odd, and half of the integers are even.
 *
 * <p>Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 *
 * <p>You may return any answer array that satisfies this condition.
 *
 * <p>Example 1:
 *
 * <p>Input: [4,2,5,7] Output: [4,5,2,7] Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also
 * have been accepted.
 *
 * <p>Note:
 *
 * <p>2 <= A.length <= 20000 A.length % 2 == 0 0 <= A[i] <= 1000 Solution: O(N) straight forward
 * linear solution, keep track of odd and even indices and increment by 2 every time a value is
 * added at the index.
 */
public class SortArrayByParityII {
  public static void main(String[] args) {
    //
  }

  public int[] sortArrayByParityII(int[] A) {
    int[] R = new int[A.length];
    int i = 0, j = 1;
    for (int a : A) {
      if (a % 2 == 0) {
        R[i] = a;
        i += 2;
      } else {
        R[j] = a;
        j += 2;
      }
    }
    return R;
  }
}
