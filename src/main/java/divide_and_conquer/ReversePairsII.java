package divide_and_conquer;

/**
 * Created by gouthamvidyapradhan on 01/08/2019 Given an array nums, we call (i, j) an important
 * reverse pair if i < j and nums[i] > 2*nums[j].
 *
 * <p>You need to return the number of important reverse pairs in the given array.
 *
 * <p>Example1:
 *
 * <p>Input: [1,3,2,3,1] Output: 2 Example2:
 *
 * <p>Input: [2,4,3,5,1] Output: 3 Note: The length of the given array will not exceed 50,000. All
 * the numbers in the input array are in the range of 32-bit integer.
 *
 * <p>Solution: O(N log N) Given two sorted arrays A[] and B[] it is quite easy to see for every
 * element i in A, how many elements in A have a value > 2 * B[j] using binary search (also possible
 * using two pointers) - using this idea we can implement standard merge sort algorithm and for
 * every sorted pairs A[] and B[] before we merge we can total number of elements in A which are > 2
 * x B[i]
 */
public class ReversePairsII {
  public static void main(String[] args) {
    int[] A = {2, 4, 3, 5, 1};
    System.out.println(new ReversePairsII().reversePairs(A));
  }

  int answer = 0;

  public int reversePairs(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return answer;
  }

  private int[] mergeSort(int[] num, int l, int h) {
    if (l < h) {
      int m = l + (h - l) / 2;
      int[] left = mergeSort(num, l, m);
      int[] right = mergeSort(num, m + 1, h);
      return merge(left, right);
    } else if (l == h) {
      return new int[] {num[l]};
    } else {
      return new int[] {};
    }
  }

  private int[] merge(int[] A, int[] B) {
    for (int i = 0; i < B.length; i++) {
      int num = B[i];
      int l = 0, h = A.length;
      int index = -1;
      while (l < h) {
        int m = l + (h - l) / 2;
        if ((long) A[m] > (2 * (long) num)) {
          index = m;
          h = m;
        } else {
          l = m + 1;
        }
      }
      if (index > -1) {
        answer += ((A.length - index));
      }
    }
    int[] C = new int[A.length + B.length];
    int k = 0;
    int i = 0, j = 0;
    for (; i < A.length && j < B.length; ) {
      if (A[i] < B[j]) {
        C[k++] = A[i];
        i++;
      } else {
        C[k++] = B[j];
        j++;
      }
    }
    while (i < A.length) {
      C[k++] = A[i++];
    }
    while (j < B.length) {
      C[k++] = B[j++];
    }
    return C;
  }
}
