package array;

/**
 * Created by gouthamvidyapradhan on 29/07/2017. Given two sorted integer arrays nums1 and nums2,
 * merge nums2 into nums1 as one sorted array.
 *
 * <p>Note: You may assume that nums1 has enough space (size that is greater or equal to m + n) to
 * hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m
 * and n respectively.
 */
public class MergeSortedArray {
  public static void main(String[] args) throws Exception {
    int[] A = {0};
    int[] B = {1};
    new MergeSortedArray().merge(A, 0, B, 1);
    for (int i : A) System.out.println(i);
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m + n - 1, j = m - 1, k = n - 1;
    while (j >= 0 && k >= 0) nums1[i--] = (nums1[j] > nums2[k]) ? nums1[j--] : nums2[k--];
    while (k >= 0) nums1[i--] = nums2[k--];
  }
}
