package array;

/**
 * Created by gouthamvidyapradhan on 25/03/2017. Given a non-empty array of integers, return the
 * third maximum number in this array. If it does not exist, return the maximum number. The time
 * complexity must be in O(n).
 *
 * <p>Example 1: Input: [3, 2, 1]
 *
 * <p>Output: 1
 *
 * <p>Explanation: The third maximum is 1. Example 2: Input: [1, 2]
 *
 * <p>Output: 2
 *
 * <p>Explanation: The third maximum does not exist, so the maximum (2) is returned instead. Example
 * 3: Input: [2, 2, 3, 1]
 *
 * <p>Output: 1
 *
 * <p>Explanation: Note that the third maximum here means the third maximum distinct number. Both
 * numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] a = {1, 2};
    System.out.println(new ThirdMaximumNumber().thirdMax(a));
  }

  public int thirdMax(int[] nums) {
    long[] max = {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
    int count = 0;
    for (int num : nums) {
      for (int j = 0; j < 3; j++) {
        if (max[j] > num) continue;
        else if (max[j] == num) break;
        int k = j;
        long temp1, temp2;
        temp1 = num;
        count++;
        while (k < 3) {
          temp2 = max[k];
          max[k] = temp1;
          temp1 = temp2;
          k++;
        }
        break;
      }
    }
    System.out.println(Integer.MIN_VALUE);
    return (count >= 3) ? (int) max[2] : (int) max[0];
  }
}
