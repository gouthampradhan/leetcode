package math;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 01/08/2019 Given an integer, return its base 7 string
 * representation.
 *
 * <p>Example 1: Input: 100 Output: "202" Example 2: Input: -7 Output: "-10" Note: The input will be
 * in range of [-1e7, 1e7].
 */
public class Base7 {
  public static void main(String[] args) {
    //
  }

  public String convertToBase7(int num) {
    Integer.toString(7, 7);
    if (num == 0) return "0";
    int q = Math.abs(num), r;
    StringBuilder sb = new StringBuilder();
    while (q != 0) {
      r = q % 7;
      sb.append(r);
      q /= 7;
    }
    if (num < 0) {
      return "-" + sb.reverse().toString();
    } else return sb.reverse().toString();
  }
}
