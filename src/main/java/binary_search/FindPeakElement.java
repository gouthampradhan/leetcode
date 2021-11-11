package binary_search;

/**
 * Created by gouthamvidyapradhan on 10/07/2017. A peak element is an element that is greater than
 * its neighbors.
 *
 * <p>Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 *
 * <p>The array may contain multiple peaks, in that case return the index to any one of the peaks is
 * fine.
 *
 * <p>You may imagine that num[-1] = num[n] = -∞.
 *
 * <p>For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the
 * index number 2.
 *
 * <p>Note: Your solution should be in logarithmic complexity.
 *
 * <p>Solution: O(log N) check if the first or the last element is the peak element, if yes then
 * return this index. Else binary search for the answer - check mid element if this is a peak
 * element return this index, else if the left element is greater than current element search left
 * else search right.
 */
public class FindPeakElement {
  public static void main(String[] args) throws Exception {
    int[] nums = {3, 4, 3, 2, 1};
    System.out.println(new FindPeakElement().findPeakElement(nums));
  }

  public int findPeakElement(int[] nums) {
    if (nums.length == 1) return 0;
    if (nums[0] > nums[1]) return 0;
    else if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;

    int l = 0, h = nums.length - 1;
    int ans = 0;
    while (l <= h) {
      int m = l + (h - l) / 2;
      if (m - 1 >= 0 && m + 1 < nums.length) {
        if (nums[m] > nums[m - 1] && nums[m] > nums[m + 1]) {
          return m;
        }
      }
      if (m - 1 >= 0 && nums[m - 1] > nums[m]) { // search left
        h = m - 1;
      } else {
        ans = l; // mark this as the answer and search right
        l = m + 1;
      }
    }
    return ans;
  }
}
