package greedy;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/07/2019 On a broken calculator that has a number showing on
 * its display, we can perform two operations:
 *
 * <p>Double: Multiply the number on the display by 2, or; Decrement: Subtract 1 from the number on
 * the display. Initially, the calculator is displaying the number X.
 *
 * <p>Return the minimum number of operations needed to display the number Y.
 *
 * <p>Example 1:
 *
 * <p>Input: X = 2, Y = 3 Output: 2 Explanation: Use double operation and then decrement operation
 * {2 -> 4 -> 3}. Example 2:
 *
 * <p>Input: X = 5, Y = 8 Output: 2 Explanation: Use decrement and then double {5 -> 4 -> 8}.
 * Example 3:
 *
 * <p>Input: X = 3, Y = 10 Output: 3 Explanation: Use double, decrement and double {3 -> 6 -> 5 ->
 * 10}. Example 4:
 *
 * <p>Input: X = 1024, Y = 1 Output: 1023 Explanation: Use decrement operations 1023 times.
 *
 * <p>Note:
 *
 * <p>1 <= X <= 10^9 1 <= Y <= 10^9
 *
 * <p>Solution: O(log Y) Arrive at the solution by working backwards starting from Y. General idea
 * is as follows. If Y is even then find the minimum steps required to arrive at Y by finding the
 * quotient after dividing by 2. If Y is odd then find the minimum steps required to arrive at Y + 1
 * (even number) + 1 (to move backwards)
 */
public class BrokenCalculator {
  public static void main(String[] args) {
    //
  }

  public int brokenCalc(int X, int Y) {
    if (X == Y) return 0;
    else if (Y < X) return X - Y;
    else {
      int count = 0;
      while (Y > X) {
        if (Y % 2 == 0) {
          Y /= 2;
          count++;
        } else {
          Y += 1;
          Y /= 2;
          count += 2;
        }
      }
      if (X == Y) return count;
      else return count + (X - Y);
    }
  }
}
