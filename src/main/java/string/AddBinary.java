package string;

/**
 * Created by gouthamvidyapradhan on 25/11/2017.
 *
 * <p>Given two binary strings, return their sum (also a binary string).
 *
 * <p>For example, a = "11" b = "1" Return "100".
 */
public class AddBinary {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new AddBinary().addBinary("000001010000101001", "0"));
  }

  public String addBinary(String a, String b) {
    if (a.length() > b.length()) {
      return calculate(a, b);
    } else return calculate(b, a);
  }

  /**
   * Calculate sum
   *
   * @param a length of a should always be greater or equal to b
   * @param b length of b should always be smaller of equal to a
   * @return
   */
  private String calculate(String a, String b) {
    int carry = 0;
    int d = a.length() - b.length();
    StringBuilder sb = new StringBuilder();
    for (int i = a.length() - 1; i >= 0; i--) {
      int first = Integer.parseInt(String.valueOf(a.charAt(i)));
      int second = i - d >= 0 ? Integer.parseInt(String.valueOf(b.charAt(i - d))) : 0;
      int sum = (first + second + carry);
      carry = sum / 2;
      sb.append(sum % 2);
    }
    if (carry != 0) sb.append(carry);
    return sb.reverse().toString();
  }
}
