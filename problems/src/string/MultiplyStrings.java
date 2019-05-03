package string;

/**
 * Created by gouthamvidyapradhan on 01/02/2018. Given two non-negative integers num1 and num2
 * represented as strings, return the product of num1 and num2.
 *
 * <p>Note:
 *
 * <p>The length of both num1 and num2 is < 110. Both num1 and num2 contains only digits 0-9. Both
 * num1 and num2 does not contain any leading zero. You must not use any built-in BigInteger library
 * or convert the inputs to integer directly.
 *
 * <p>Solution: O(N x M) where N and M are length of strings
 */
public class MultiplyStrings {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new MultiplyStrings().multiply("00", "0000"));
  }

  public String multiply(String num1, String num2) {
    if ((num1.length() == 1 && num1.equals("0")) || (num2.length() == 1 && num2.equals("0")))
      return "0";
    if (num1.length() < num2.length()) return multiply(num2, num1);
    String temp2 = "", trail = "";
    int carry = 0;
    for (int i = 0; i < num1.length(); i++) {
      temp2 += "0";
    }
    for (int i = num1.length() - 1; i >= 0; i--) {
      String temp1 = "";
      for (int j = num2.length() - 1; j >= 0; j--) {
        int prod =
            Integer.parseInt(String.valueOf(num2.charAt(j)))
                * Integer.parseInt(String.valueOf(num1.charAt(i)));
        prod += carry;
        temp1 = (prod % 10) + temp1;
        carry = (prod / 10);
      }
      if (carry > 0) {
        temp1 = String.valueOf(carry) + temp1;
        carry = 0;
      }
      String temp3 = add(temp1, temp2);
      temp2 = temp3.substring(0, temp3.length() - 1);
      trail = temp3.substring(temp3.length() - 1, temp3.length()) + trail;
    }
    return temp2 + trail;
  }

  private String add(String s1, String s2) {
    String result = "";
    int carry = 0;
    int i = s1.length() - 1, j = s2.length() - 1;
    for (; i >= 0 || j >= 0; i--, j--) {
      int l = (i >= 0) ? Integer.parseInt(String.valueOf(s1.charAt(i))) : 0;
      int r = (j >= 0) ? Integer.parseInt(String.valueOf(s2.charAt(j))) : 0;
      int sum = l + r + carry;
      carry = sum / 10;
      result = sum % 10 + result;
    }
    if (carry > 0) {
      result = carry + result;
    }
    return result;
  }
}
