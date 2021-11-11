package array;

/**
 * Created by gouthamvidyapradhan on 04/07/2017. Given an array containing n distinct numbers taken
 * from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * <p>For example, Given nums = [0, 1, 3] return 2.
 *
 * <p>Note: Your algorithm should run in linear runtime complexity. Could you implement it using
 * only constant extra space complexity?
 */
public class MissingNumber {

  public static void main(String[] args) throws Exception {
    int[] nums = {0};
    System.out.println(new MissingNumber().missingNumber(nums));
  }

  public int missingNumber(int[] nums) {
    int sum = 0;
    int n = nums.length;
    for (int num : nums) {
      sum += num;
    }
    int arrSum = (((n + 1)) * n) / 2;
    if (arrSum == sum) return 0;
    else return arrSum - sum;
  }
}
