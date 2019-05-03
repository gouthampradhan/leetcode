package divide_and_conquer;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 30/06/2018. Given an array nums, we call (i, j) an important
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
 * <p>Solution: O(n log n): Example: 1,3,2,3,1 1. Sort the array in non-increasing order (if there
 * is a collision, sort by lower index). So the sorted array will be (3, 3, 2, 1, 1) having indexes
 * (1, 3, 2, 0, 4) 2. Maintain a prefix sum of index (starting from 1) for the sorted array. So,
 * prefix sum for the above sorted array is (1, 2, 3, 4, 5) Now, the basic idea is to iterate from
 * index n - 1 to 0 in the original array and for each element calculate the element p (num[i] x 2)
 * and find the upper bound of the element p in sorted array which is 3 at index 1 in this case and
 * add prefix sum of the index 1 to the result. So the result now becomes 2.
 *
 * <p>To maintain a prefix sum and update it efficiently we have to use a BIT or Fenwick tree.
 */
public class ReversePairs {

  class Pair {
    int i, n;

    Pair(int i, int n) {
      this.i = i;
      this.n = n;
    }

    int getN() {
      return n;
    }

    int getI() {
      return i;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {2, 4, 3, 5, 1};
    System.out.println(new ReversePairs().reversePairs(A));
  }

  public int reversePairs(int[] nums) {
    List<Pair> list = new ArrayList<>();
    Ftree ft = new Ftree(nums.length);
    for (int i = 0; i < nums.length; i++) {
      list.add(new Pair(i, nums[i]));
      ft.update(i, 1);
    }
    Collections.sort(list, (Comparator.comparing(Pair::getN).reversed().thenComparing(Pair::getI)));
    int[] indexMap = new int[nums.length];
    for (int i = 0, l = list.size(); i < l; i++) {
      indexMap[list.get(i).getI()] = i;
    }
    int ans = 0;
    for (int i = nums.length - 1; i >= 0; i--) {
      ft.update(indexMap[i], -1);
      int index = binarySearch(list, (long) nums[i] * 2);
      if (index > -1) {
        ans += ft.getRangeSum(index);
      }
    }
    return ans;
  }

  private int binarySearch(List<Pair> list, long n) {
    int l = 0, h = list.size() - 1;
    int ans = -1;
    while (l <= h) {
      int m = l + (h - l) / 2;
      if (list.get(m).n > n) {
        ans = m;
        l = m + 1;
      } else {
        h = m - 1;
      }
    }
    return ans;
  }

  private class Ftree {
    private int[] a;

    Ftree(int n) {
      a = new int[n + 1];
    }

    void update(int p, int v) {
      for (int i = p + 1; i < a.length; i += (i & (-i))) {
        a[i] += v;
      }
    }

    int getRangeSum(int p) {
      int sum = 0;
      for (int i = p + 1; i > 0; i -= (i & (-i))) {
        sum += a[i];
      }
      return sum;
    }
  }
}
