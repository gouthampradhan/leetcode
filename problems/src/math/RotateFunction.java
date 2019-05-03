package math;

/**
 * Created by gouthamvidyapradhan on 18/03/2017. Given an array of integers A and let n to be its
 * length.
 *
 * <p>Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a
 * "rotation function" F on A as follow:
 *
 * <p>F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 *
 * <p>Calculate the maximum value of F(0), F(1), ..., F(n-1).
 *
 * <p>Note: n is guaranteed to be less than 105.
 *
 * <p>Example:
 *
 * <p>A = [4, 3, 2, 6]
 *
 * <p>F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25 F(1) = (0 * 6) + (1 * 4) +
 * (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 +
 * 9 = 23 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 *
 * <p>So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 */
public class RotateFunction {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] a = {4, 3, 2, 6};
    System.out.println(new RotateFunction().maxRotateFunction(a));
  }

  public int maxRotateFunction(int[] A) {
    if (A.length == 0 || A.length == 1) return 0;
    int max = Integer.MIN_VALUE;
    int l = A.length;
    int sum = 0, prodSum = 0;
    for (int i = 0; i < l; i++) {
      prodSum += (A[i] * i);
      sum += A[i];
    }
    max = Math.max(max, prodSum);
    for (int i = 0; i < l - 1; i++) {
      prodSum = (prodSum - sum + A[i] + ((l - 1) * A[i]));
      max = Math.max(max, prodSum);
    }
    return max;
  }
}
