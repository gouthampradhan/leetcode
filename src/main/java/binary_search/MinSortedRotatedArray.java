package binary_search;

/**
 * Created by gouthamvidyapradhan on 10/04/2017. Suppose an array sorted in ascending order is
 * rotated at some pivot unknown to you beforehand.
 *
 * <p>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * <p>Find the minimum element.
 *
 * <p>You may assume no duplicate exists in the array.
 */
public class MinSortedRotatedArray {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {5, 1, 2, 3, 4};
    System.out.println(new MinSortedRotatedArray().findMin(A));
  }

  public int findMin(int[] nums) {
    if (nums.length == 0) return 0;
    else if (nums.length == 1) return nums[0];
    int low = 0, high = nums.length - 1;
    while (low < high) {
      int mid = (low + high) / 2;
      if (mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid];
      if (nums[low] > nums[mid]) high = mid - 1;
      else if (nums[high] < nums[mid]) low = mid + 1;
      else high = mid - 1;
    }
    return nums[low];
  }
}
