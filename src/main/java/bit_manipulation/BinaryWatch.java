package bit_manipulation;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 05/11/2019 A binary watch has 4 LEDs on the top which represent
 * the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 *
 * <p>Each LED represents a zero or one, with the least significant bit on the right.
 *
 * <p>For example, the above binary watch reads "3:25".
 *
 * <p>Given a non-negative integer n which represents the number of LEDs that are currently on,
 * return all possible times the watch could represent.
 *
 * <p>Example:
 *
 * <p>Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16",
 * "0:32"] Note: The order of output does not matter. The hour must not contain a leading zero, for
 * example "01:00" is not valid, it should be "1:00". The minute must be consist of two digits and
 * may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
  public static void main(String[] args) {
    System.out.println(new BinaryWatch().readBinaryWatch(1));
  }

  public List<String> readBinaryWatch(int num) {
    int H = 11, M = 59;
    List<String> result = new ArrayList<>();
    if (num == 0) {
      result.add("0:00");
      return result;
    }
    for (int i = 0; i <= H; i++) {
      for (int j = 0; j <= M; j++) {
        int count = 0;
        for (int k = 0; k < 4; k++) {
          if (((1 << k) & i) > 0) {
            count++;
          }
        }
        for (int k = 0; k < 6; k++) {
          if (((1 << k) & j) > 0) {
            count++;
          }
        }
        if (count == num) {
          result.add(i + ":" + ((String.valueOf(j).length() == 1) ? ("0" + j) : j));
        }
      }
    }
    return result;
  }
}
