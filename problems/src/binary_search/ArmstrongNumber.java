package binary_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/11/2019 The k-digit number N is an Armstrong number if and
 * only if the k-th power of each digit sums to N.
 *
 * <p>Given a positive integer N, return true if and only if it is an Armstrong number.
 *
 * <p>Example 1:
 *
 * <p>Input: 153 Output: true Explanation: 153 is a 3-digit number, and 153 = 1^3 + 5^3 + 3^3.
 * Example 2:
 *
 * <p>Input: 123 Output: false Explanation: 123 is a 3-digit number, and 123 != 1^3 + 2^3 + 3^3 =
 * 36.
 *
 * <p>Note:
 *
 * <p>1 <= N <= 10^8
 */
public class ArmstrongNumber {
  public static void main(String[] args) {
    //
  }

  public boolean isArmstrong(int N) {
    int s = String.valueOf(N).length();
    long sum = 0;
    for (char c : String.valueOf(N).toCharArray()) {
      int i = Integer.parseInt(String.valueOf(c));
      sum += power(i, s);
    }
    return (sum == N);
  }

  private long power(int n, int p) {
    long res = 1L;
    for (int i = 0; i < p; i++) {
      res *= n;
    }
    return res;
  }
}
