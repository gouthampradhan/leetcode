package math;

/**
 * Created by gouthamvidyapradhan on 08/09/2017. There is a room with n lights which are turned on
 * initially and 4 buttons on the wall. After performing exactly m unknown operations towards
 * buttons, you need to return how many different kinds of status of the n lights could be.
 *
 * <p>Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given
 * below:
 *
 * <p>Flip all the lights. Flip lights with even numbers. Flip lights with odd numbers. Flip lights
 * with (3k + 1) numbers, k = 0, 1, 2, ...
 *
 * <p>Example 1: Input: n = 1, m = 1. Output: 2 Explanation: Status can be: [on], [off]
 *
 * <p>Example 2: Input: n = 2, m = 1. Output: 3 Explanation: Status can be: [on, off], [off, on],
 * [off, off]
 *
 * <p>Example 3: Input: n = 3, m = 1. Output: 4 Explanation: Status can be: [off, on, off], [on,
 * off, on], [off, off, off], [off, on, on].
 *
 * <p>Note: n and m both fit in range [0, 1000].
 */
public class BulbSwitcherII {

  public static void main(String[] args) throws Exception {
    System.out.println(new BulbSwitcherII().flipLights(1, 1000));
  }

  public int flipLights(int n, int m) {
    if (n == 0 || m == 0) return 1;
    if (n == 1 && m >= 1) return 2;
    if (n == 2) {
      if (m == 1) return 3;
      if (m >= 2) return 4;
    } else if (n >= 3) {
      if (m == 1) return 4;
      if (m == 2) return 7;
    }
    return 8;
  }
}
