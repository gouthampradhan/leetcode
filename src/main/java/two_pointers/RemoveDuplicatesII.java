package two_pointers;

/**
 * Created by gouthamvidyapradhan on 20/01/2018. Follow up for "Remove Duplicates": What if
 * duplicates are allowed at most twice?
 *
 * <p>For example, Given sorted array nums = [1,1,1,2,2,3],
 *
 * <p>Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2
 * and 3. It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesII {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 1, 1, 2, 2, 2, 3, 4, 4};
    System.out.println(new RemoveDuplicatesII().removeDuplicates(A));
  }

  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int j = 0;
    int count = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i - 1] == nums[i]) {
        count++;
      } else {
        count = 1;
      }
      if (count == 1 || count == 2) {
        j++;
        nums[j] = nums[i];
      }
    }
    return j + 1;
  }
}
