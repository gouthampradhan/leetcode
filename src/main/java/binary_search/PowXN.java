package binary_search;

/**
 * Created by gouthamvidyapradhan on 23/05/2017.
 *
 * <p>Implement pow(x, n).
 *
 * <p>Solution: Works with O(log n)
 */
public class PowXN {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(1 / new PowXN().myPow(2.00000, -2147483648));
  }

  public double myPow(double x, int n) {
    if (n == 0) return 1D;
    long N = n; // use long to avoid overflow.
    return solve(n < 0 ? (1 / x) : x, N < 0 ? (N * -1) : N);
  }

  public double solve(double x, long n) {
    if (n == 1) return x;
    double val = solve(x, n / 2);
    return val * val * ((n % 2) == 0 ? 1 : x);
  }
}
