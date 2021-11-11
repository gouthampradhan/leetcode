package math;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 12/08/2017.
 *
 * <p>Given a roman numeral, convert it to an integer.
 *
 * <p>Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInteger {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new RomanToInteger().romanToInt("DXCIX"));
  }

  public int romanToInt(String s) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);

    String str = new StringBuilder(s).reverse().toString();
    int sum = 0, prev = -1;
    for (int i = 0, l = str.length(); i < l; i++) {
      int curr = map.get(str.charAt(i));
      if (curr < prev) {
        sum -= curr;
      } else {
        sum += curr;
      }
      prev = curr;
    }

    return sum;
  }
}
