package binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 23/05/2017. There are two sorted arrays nums1 and nums2 of size
 * m and n respectively.
 *
 * <p>Find the median of the two sorted arrays. The overall run time complexity should be O(log
 * (m+n)).
 *
 * <p>Example 1: nums1 = [1, 3] nums2 = [2]
 *
 * <p>The median is 2.0 Example 2: nums1 = [1, 2] nums2 = [3, 4]
 *
 * <p>The median is (2 + 3)/2 = 2.5
 *
 * <p>Solution: Works in worst case time complexity of O(log min(m, n))
 *
 * <p>The basic idea is that if you are given two arrays A and B and know the length of each, you
 * can check whether an element A[i] is the median in constant time. Suppose that the median is
 * A[i]. Since the array is sorted, it is greater than exactly i − 1 values in array A. Then if it
 * is the median, it is also greater than exactly j = [n / 2] − (i − 1) elements in B. It requires
 * constant time to check if B[j] A[i] <= B[j + 1]. If A[i] is not the median, then depending on
 * whether A[i] is greater or less than B[j] and B[j + 1], you know that A[i] is either greater than
 * or less than the median. Thus you can binary search for A[i] in O(log N) worst-case time
 */
public class MedianOfTwoSortedArrays {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 2, 5, 8, 44, 45, 45};
    int[] B = {1, 2, 3, 4, 5, 6, 7, 23, 23, 23, 33, 44, 45, 45, 56, 77, 5555};
    System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(A, B));
  }

  /**
   * Find median
   *
   * @param nums1 array one
   * @param nums2 array two
   * @return
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length)
      return findMedianSortedArrays(nums2, nums1); // ensure always nums1 is the shortest array
    int T = nums1.length + nums2.length, low = -1, high = -1;
    int median = (T - 1) / 2;
    boolean isOdd = false;
    if ((T % 2) != 0) isOdd = true;

    int s = 0, e = nums1.length - 1;
    while (s <= e) {
      int m = s + (e - s) / 2;
      if ((median - m - 1) < 0 || nums1[m] >= nums2[median - m - 1]) {
        e = m - 1;
        low = m;
        high = median - m;
      } else s = m + 1;
    }

    if (low == -1) {
      if (isOdd) return nums2[median - nums1.length];
      else return (double) (nums2[median - nums1.length] + nums2[median - nums1.length + 1]) / 2.0D;
    } else {
      if (isOdd) return nums1[low] < nums2[high] ? nums1[low] : nums2[high];
      else {
        // Always sorts maximum of 4 elements hence works in O(1)
        List<Integer> list = new ArrayList<>();
        list.add(nums1[low]);
        if (low + 1 < nums1.length) list.add(nums1[low + 1]);
        list.add(nums2[high]);
        if (high + 1 < nums2.length) list.add(nums2[high + 1]);
        Collections.sort(list);
        return (double) (list.get(0) + list.get(1)) / 2.0;
      }
    }
  }
}
