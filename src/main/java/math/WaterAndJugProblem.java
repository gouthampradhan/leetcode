package math;

import java.math.BigInteger;

/**
 * Created by gouthamvidyapradhan on 29/07/2017. You are given two jugs with capacities x and y
 * litres. There is an infinite amount of water supply available. You need to determine whether it
 * is possible to measure exactly z litres using these two jugs.
 *
 * <p>If z liters of water is measurable, you must have z liters of water contained within one or
 * both buckets by the end.
 *
 * <p>Operations allowed:
 *
 * <p>Fill any of the jugs completely with water. Empty any of the jugs. Pour water from one jug
 * into another till the other jug is completely full or the first jug itself is empty. Example 1:
 * (From the famous "Die Hard" example)
 *
 * <p>Input: x = 3, y = 5, z = 4 Output: True Example 2:
 *
 * <p>Input: x = 2, y = 6, z = 5 Output: False
 */
public class WaterAndJugProblem {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new WaterAndJugProblem().canMeasureWater(0, 0, 1));
  }

  public boolean canMeasureWater(int x, int y, int z) {
    if (x == y && y == z) return true;
    if (z > (x + y)) return false;
    BigInteger b1 = new BigInteger(String.valueOf(x));
    BigInteger b2 = new BigInteger(String.valueOf(y));
    BigInteger b3 = b1.gcd(b2);
    return b3.intValue() != 0 && (z % b3.intValue()) == 0;
  }
}
