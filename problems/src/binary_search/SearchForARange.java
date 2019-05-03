package binary_search;

/**
 * Created by gouthamvidyapradhan on 20/05/2017. Given an array of integers sorted in ascending
 * order, find the starting and ending position of a given target value.
 *
 * <p>Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * <p>If the target is not found in the array, return [-1, -1].
 *
 * <p>For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 *
 * <p>Solution: Works with worst case time complexity of O(log n). Recursively binary search to find
 * the target index.
 */
public class SearchForARange {
  public static void main(String[] args) throws Exception {
    int[] test = {
      5, 7, 7, 8, 8, 10, 10, 10, 10, 18, 19, 20, 21, 21, 21, 21, 22, 23, 28, 28, 90, 101, 101, 101,
      200, 200, 200, 200, 200, 200
    };
    int[] result = new SearchForARange().searchRange(test, 200);
    for (int i : result) System.out.print(i + " ");
  }

  public int[] searchRange(int[] nums, int target) {
    int low = findIndex(nums, target, true);
    int high = findIndex(nums, target, false);
    int[] result = new int[2];
    result[0] = low;
    result[1] = high;
    return result;
  }

  /**
   * Find index
   *
   * @param nums nums array
   * @param target target
   * @param isLowerIndex true if target is to find lower index, false otherwise
   * @return index
   */
  private int findIndex(int[] nums, int target, boolean isLowerIndex) {
    int result = -1;
    int s = 0, e = nums.length - 1;
    while (s <= e) {
      int m = s + (e - s) / 2;
      if (nums[m] == target) {
        result = m;
        if (isLowerIndex)
          e = m - 1; // if searching for the lower index then search the lower bound,
        // else search the upper bound
        else s = m + 1;
      } else if (nums[m] < target) {
        s = m + 1;
      } else e = m - 1;
    }
    return result;
  }
}
