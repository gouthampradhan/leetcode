package two_pointers;
/**
 * Created by gouthamvidyapradhan on 25/07/2019 Given an array A of positive integers, call a
 * (contiguous, not necessarily distinct) subarray of A good if the number of different integers in
 * that subarray is exactly K.
 *
 * <p>(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * <p>Return the number of good subarrays of A.
 *
 * <p>Example 1:
 *
 * <p>Input: A = [1,2,1,2,3], K = 2 Output: 7 Explanation: Subarrays formed with exactly 2 different
 * integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]. Example 2:
 *
 * <p>Input: A = [1,2,1,3,4], K = 3 Output: 3 Explanation: Subarrays formed with exactly 3 different
 * integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 20000 1 <= A[i] <= A.length 1 <= K <= A.length Solution: O(N) General idea is
 * to find subarraysWithKDistinct(A, atMost(K)) - subarraysWithKDistinct(A, atMost(K - 1)).
 */
public class SubarraysWithKDifferentIntegers {
  public static void main(String[] args) {
    int[] A = {1, 2, 1, 2, 3};
    SubarraysWithKDifferentIntegers task = new SubarraysWithKDifferentIntegers();
    System.out.println(task.subarraysWithKDistinct(A, 2));
  }

  public int subarraysWithKDistinct(int[] A, int K) {
    return calculate(A, K) - calculate(A, K - 1);
  }

  private int calculate(int[] A, int K) {
    int count = 0;
    int[] frequency = new int[A.length + 1];
    int currCount = 0;
    for (int i = 0, j = 0; i < A.length; i++) {
      frequency[A[i]]++;
      if (frequency[A[i]] == 1) {
        currCount++;
      }
      while (currCount > K) {
        frequency[A[j]]--;
        if (frequency[A[j]] == 0) {
          currCount--;
        }
        j++;
      }
      count += (i - j + 1);
    }
    return count;
  }
}
