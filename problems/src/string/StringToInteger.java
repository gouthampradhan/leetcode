package string;

/**
 * Created by gouthamvidyapradhan on 21/03/2017. Implement atoi to convert a string to an integer.
 *
 * <p>Hint: Carefully consider all possible input cases. If you want a challenge, please do not see
 * below and ask yourself what are the possible input cases.
 *
 * <p>Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You
 * are responsible to gather all the input requirements up front.
 */
public class StringToInteger {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new StringToInteger().myAtoi("    2147483649a sdfasdf"));
  }

  public int myAtoi(String str) {
    boolean isPositive = true;
    if (str == null || str.trim().isEmpty()) return 0;
    str = str.trim();
    if (str.charAt(0) == '+') {
      isPositive = true;
      str = str.substring(1, str.length());
    } else if (str.charAt(0) == '-') {
      isPositive = false;
      str = str.substring(1, str.length());
    } else if (str.charAt(0) > '9' || str.charAt(0) < '0') return 0;
    int i = 0;
    for (int l = str.length(); i < l; i++) {
      if (str.charAt(i) > '9' || str.charAt(i) < '0') break;
    }
    str = str.substring(0, i);
    long num = 0;
    for (int j = 0, l = str.length(); j < l; j++) {
      int n = (str.charAt(j) - '0');
      num *= 10;
      num += n;
      if (isPositive) {
        if (num > Integer.MAX_VALUE) return Integer.MAX_VALUE;
      } else {
        if ((num * -1) < Integer.MIN_VALUE) return Integer.MIN_VALUE;
      }
    }
    if (isPositive) return (int) num;
    else return (int) (num * -1);
  }
}
