package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 11/02/2020 There is a strange printer with the following two
 * special requirements:
 *
 * <p>The printer can only print a sequence of the same character each time. At each turn, the
 * printer can print new characters starting from and ending at any places, and will cover the
 * original existing characters. Given a string consists of lower English letters only, your job is
 * to count the minimum number of turns the printer needed in order to print it.
 *
 * <p>Example 1: Input: "aaabbb" Output: 2 Explanation: Print "aaa" first and then print "bbb".
 * Example 2: Input: "aba" Output: 2 Explanation: Print "aaa" first and then print "b" from the
 * second place of the string, which will cover the existing character 'a'. Hint: Length of the
 * given string will not exceed 100.
 */
public class StrangePrinter {
  public static void main(String[] args) {
    String A = "aaaaaaa";
    System.out.println(new StrangePrinter().strangePrinter(A));
  }

  int DP[][];

  public int strangePrinter(String s) {
    DP = new int[s.length() + 1][s.length() + 1];
    return calculate(0, s.length() - 1, s);
  }

  private int calculate(int i, int j, String s) {
    if (i > j) return 0;
    else if (DP[i][j] != 0) return DP[i][j];
    else {
      DP[i][j] = calculate(i, j - 1, s) + 1;
      int min = DP[i][j];
      for (int m = i; m < j; m++) {
        if (s.charAt(m) == s.charAt(j)) {
          min = Math.min(min, calculate(i, m, s) + calculate(m + 1, j - 1, s));
        }
      }
      DP[i][j] = min;
      return DP[i][j];
    }
  }
}
