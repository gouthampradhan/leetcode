package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 15/08/2019 Given an array A of non-negative integers, return
 * the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L
 * and M. (For clarification, the L-length subarray could occur before or after the M-length
 * subarray.)
 *
 * <p>Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1]
 * + ... + A[j+M-1]) and either:
 *
 * <p>0 <= i < i + L - 1 < j < j + M - 1 < A.length, or 0 <= j < j + M - 1 < i < i + L - 1 <
 * A.length.
 *
 * <p>Example 1:
 *
 * <p>Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2 Output: 20 Explanation: One choice of subarrays
 * is [9] with length 1, and [6,5] with length 2. Example 2:
 *
 * <p>Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2 Output: 29 Explanation: One choice of subarrays
 * is [3,8,1] with length 3, and [8,9] with length 2. Example 3:
 *
 * <p>Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3 Output: 31 Explanation: One choice of subarrays
 * is [5,6,0,9] with length 4, and [3,8] with length 3.
 *
 * <p>Note:
 *
 * <p>L >= 1 M >= 1 L + M <= A.length <= 1000 0 <= A[i] <= 1000 Solution O(N ^ 2) Find prefix sum of
 * array of length L and array of length M and keep track of their begin and end indices. Now,
 * brute-force compare pairs of prefix array sum where their indices don't overlap and return the
 * max possible answer.
 */
public class MaximumSumofTwoNonOverlappingSubarrays {
  public static void main(String[] args) {
    int[] A = {2, 1, 5, 6, 0, 9, 5, 0, 3, 8};
    System.out.println(new MaximumSumofTwoNonOverlappingSubarrays().maxSumTwoNoOverlap(A, 4, 3));
  }

  class MaxWithIndex {
    int max, i, j;

    MaxWithIndex(int max, int i, int j) {
      this.max = max;
      this.i = i;
      this.j = j;
    }
  }

  public int maxSumTwoNoOverlap(int[] A, int L, int M) {
    List<MaxWithIndex> first = getMax(A, L);
    List<MaxWithIndex> second = getMax(A, M);
    int max = 0;
    for (MaxWithIndex f : first) {
      for (MaxWithIndex s : second) {
        if (f.j < s.i || s.j < f.i) {
          max = Math.max(max, f.max + s.max);
        }
      }
    }
    return max;
  }

  private List<MaxWithIndex> getMax(int[] A, int L) {
    List<MaxWithIndex> list = new ArrayList<>();
    int i = 0, j = L;
    int sum = 0;
    for (; i < L; i++) {
      sum += A[i];
    }
    list.add(new MaxWithIndex(sum, 0, j - 1));
    for (i = 1; j < A.length; i++, j++) {
      sum -= A[i - 1];
      sum += A[j];
      list.add(new MaxWithIndex(sum, i, j));
    }
    return list;
  }
}
