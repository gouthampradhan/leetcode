package array;

/**
 * Created by gouthamvidyapradhan on 18/03/2017. Given an array of integers that is already sorted
 * in ascending order, find two numbers such that they add up to a specific target number.
 *
 * <p>The function twoSum should return indices of the two numbers such that they add up to the
 * target, where index1 must be less than index2. Please note that your returned answers (both
 * index1 and index2) are not zero-based.
 *
 * <p>You may assume that each input would have exactly one solution and you may not use the same
 * element twice.
 *
 * <p>Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 */
public class TwoSumII {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {2, 7, 11, 15};
    int[] result = new TwoSumII().twoSum(nums, 23);
    for (int i : result) System.out.println(i);
  }

  public int[] twoSum(int[] numbers, int target) {
    int i = 0, j = numbers.length - 1;
    while (i < j) {
      int x = (numbers[i] + numbers[j]);
      if (x == target) {
        int[] result = new int[2];
        result[0] = i + 1;
        result[1] = j + 1;
        return result;
      } else if (x < target) i++;
      else j--;
    }
    return new int[2];
  }
}
