package binary_search;

/**
 * Created by gouthamvidyapradhan on 10/04/2017. Suppose an array sorted in ascending order is
 * rotated at some pivot unknown to you beforehand.
 *
 * <p>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * <p>You are given a target value to search. If found in the array return its index, otherwise
 * return -1.
 *
 * <p>You may assume no duplicate exists in the array.
 */
public class SearchRotatedSortedArray {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {5, 4, 3, 2, 1};
    System.out.println(new SearchRotatedSortedArray().search(A, 4));
  }

  public int search(int[] nums, int target) {
    if (nums.length == 0) return -1;
    if (nums.length == 1) {
      return (nums[0] == target) ? 0 : -1;
    }
    int low = 0, high = nums.length - 1;
    while (low < high) {
      int mid = (low + high) >>> 1;
      if (nums[mid] == target) return mid;
      if ((nums[mid] <= nums[low]) && (target > nums[mid] && target <= nums[high])
          || (nums[low] <= nums[mid] && (target < nums[low] || target > nums[mid]))) low = mid + 1;
      else high = mid - 1;
    }
    return (nums[low] == target) ? low : -1;
  }
}
