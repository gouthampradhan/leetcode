package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 01/04/2017. You are climbing a stair case. It takes n steps to
 * reach to the top.
 *
 * <p>Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the
 * top?
 *
 * <p>Note: Given n will be a positive integer.
 */
public class ClimbingStairs {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public int climbStairs(int n) {
    if (n == 0 || n == 1) return 1;
    int[] A = new int[n + 1];
    A[n] = 1;
    A[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) A[i] = A[i + 1] + A[i + 2];
    return A[0];
  }
}
