/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 28/05/2020
 *
 * <p>A program was supposed to print an array of integers. The program forgot to print whitespaces
 * and the array is printed as a string of digits and all we know is that all integers in the array
 * were in the range [1, k] and there are no leading zeros in the array.
 *
 * <p>Given the string s and the integer k. There can be multiple ways to restore the array.
 *
 * <p>Return the number of possible array that can be printed as a string s using the mentioned
 * program.
 *
 * <p>The number of ways could be very large so return it modulo 10^9 + 7
 *
 * <p>Example 1:
 *
 * <p>Input: s = "1000", k = 10000 Output: 1 Explanation: The only possible array is [1000] Example
 * 2:
 *
 * <p>Input: s = "1000", k = 10 Output: 0 Explanation: There cannot be an array that was printed
 * this way and has all integer >= 1 and <= 10. Example 3:
 *
 * <p>Input: s = "1317", k = 2000 Output: 8 Explanation: Possible arrays are
 * [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7] Example 4:
 *
 * <p>Input: s = "2020", k = 30 Output: 1 Explanation: The only possible array is [20,20]. [2020] is
 * invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros. Example 5:
 *
 * <p>Input: s = "1234567890", k = 90 Output: 34
 *
 * <p>Constraints:
 *
 * <p>1 <= s.length <= 10^5. s consists of only digits and doesn't contain leading zeros. 1 <= k <=
 * 10^9.
 */
public class RestoreTheArray {
  public static void main(String[] args) {
    System.out.println(new RestoreTheArray().numberOfArrays("19284738192", 90));
  }

  int[] DP;
  int MOD = (int) 1e9 + 7;

  public int numberOfArrays(String s, int k) {
    DP = new int[s.length() + 1];
    Arrays.fill(DP, -1);
    return dp(0, s, k);
  }

  private int dp(int i, String s, int k) {
    if (i == s.length()) return 1;
    else if (DP[i] != -1) return DP[i];
    else if (s.charAt(i) == '0') return 0;
    else {
      long sum = 0L;
      String num = "";
      for (int j = i; j < (i + 10) && j < s.length(); j++) {
        num = num + s.charAt(j);
        if (Long.parseLong(num) <= k) {
          sum = ((sum + dp(j + 1, s, k)) % MOD);
        }
      }
      DP[i] = (int) sum;
      return DP[i];
    }
  }
}
