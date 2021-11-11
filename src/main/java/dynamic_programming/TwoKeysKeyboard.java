package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 19/08/2017. Initially on a notepad only one character 'A' is
 * present. You can perform two operations on this notepad for each step:
 *
 * <p>Copy All: You can copy all the characters present on the notepad (partial copy is not
 * allowed). Paste: You can paste the characters which are copied last time. Given a number n. You
 * have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
 * Output the minimum number of steps to get n 'A'.
 *
 * <p>Example 1: Input: 3 Output: 3 Explanation: Intitally, we have one character 'A'. In step 1, we
 * use Copy All operation. In step 2, we use Paste operation to get 'AA'. In step 3, we use Paste
 * operation to get 'AAA'.
 *
 * <p>Note: The n will be in the range [1, 1000].
 */
public class TwoKeysKeyboard {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new TwoKeysKeyboard().minSteps(8));
  }

  public int minSteps(int n) {
    int[] DP = new int[n + 1];
    for (int i = 2; i <= n; i++) {
      DP[i] = i;
      for (int j = 2; j < i; j++) {
        if ((i % j) == 0) {
          DP[i] = Math.min(DP[i], DP[j] + (i / j));
        }
      }
    }
    return DP[n];
  }
}
