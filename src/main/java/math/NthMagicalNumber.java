package math;

import java.math.BigInteger;

/**
 * Created by gouthamvidyapradhan on 23/03/2019 A positive integer is magical if it is divisible by
 * either A or B.
 *
 * <p>Return the N-th magical number. Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * <p>Example 1:
 *
 * <p>Input: N = 1, A = 2, B = 3 Output: 2 Example 2:
 *
 * <p>Input: N = 4, A = 2, B = 3 Output: 6 Example 3:
 *
 * <p>Input: N = 5, A = 2, B = 4 Output: 10 Example 4:
 *
 * <p>Input: N = 3, A = 6, B = 4 Output: 8
 *
 * <p>Note:
 *
 * <p>1 <= N <= 10^9 2 <= A <= 40000 2 <= B <= 40000
 *
 * <p>Solution: O(log((2 ^ 64) - 1)) Lets take example of N = 5, A = 4 and B = 6 The multiple of A
 * are 4, 8, 12, 16, 20, 24 . . . The multiple of B are 6, 12, 18, 24 . . .
 *
 * <p>Lets take a arbitrary number E = 21 and see if this fits the correct answer E / A = 5 E / B =
 * 3 This means there are 5 + 3 = 8 numbers which are divisible by either A or B such as 4, 6, 8,
 * 12, 12, 16, 18, 20 but we have double counted number 12 so we have to reduce 8 by 1 therefore
 * there are 7 numbers. But, 7 is greater than required number N = 5 that means we have to search
 * between 0 and E - 1. Thus we can binary search to arrive at the answer.
 *
 * <p>The number of common multiples such as 12 in the above example can be found by E / LCM(4, 6)
 */
public class NthMagicalNumber {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new NthMagicalNumber().nthMagicalNumber(3, 2, 4));
  }

  public int nthMagicalNumber(int N, int A, int B) {
    final int CONST = 1000000007;
    BigInteger bigInteger = new BigInteger(String.valueOf(A));
    long aL = (long) A * B;
    long lcm = aL / bigInteger.gcd(new BigInteger(String.valueOf(B))).longValue();
    long l = 0, h = Long.MAX_VALUE;
    while (l <= h) {
      long m = l + (h - l) / 2;
      int status = check(N, m, A, B, lcm);
      if (status == 0) {
        long modA = m % A;
        long modB = m % B;
        if (modA == 0 || modB == 0) return (int) (m % CONST);
        else if (modA < modB) return (int) ((m - modA) % CONST);
        else return (int) ((m - modB) % CONST);
      } else if (status == -1) {
        l = m + 1;
      } else {
        h = m - 1;
      }
    }
    return 0;
  }

  private int check(int N, long num, int A, int B, long lcm) {
    long sum = (num / A) + (num / B);
    long common = num / lcm;
    sum -= common;
    if (sum == N) return 0;
    else if (sum > N) return 1;
    else return -1;
  }
}
