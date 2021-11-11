package string;

/**
 * Created by gouthamvidyapradhan on 21/04/2018. Given an integer n, find the closest integer (not
 * including itself), which is a palindrome.
 *
 * <p>The 'closest' is defined as absolute difference minimized between two integers.
 *
 * <p>Example 1: Input: "123" Output: "121" Note: The input n is a positive integer represented by
 * string, whose length will not exceed 18. If there is a tie, return the smaller one as answer.
 *
 * <p>Solution O(N): General idea is to consider the first half of the string and make a new string
 * by reversing the first half of string. Concatenate the first_half and the new_string and return
 * this as an answer. There are also a lot of edge cases to be considered using this approach.
 */
public class FindTheClosestPalindrome {
  public static void main(String[] args) throws Exception {
    System.out.println(new FindTheClosestPalindrome().nearestPalindromic("1837722381"));
  }

  public String nearestPalindromic(String n) {
    if (n.length() == 1) return String.valueOf(Integer.parseInt(n) - 1);
    String p1, p2;
    String palindrome;
    if (isPaliandrome(n)) {
      String newFirstHalf;
      if (n.length() % 2 == 0) {
        String firstHalf = n.substring(0, n.length() / 2);
        newFirstHalf = String.valueOf(Long.parseLong(firstHalf) - 1);
        p1 = newFirstHalf + new StringBuilder(newFirstHalf).reverse();
        newFirstHalf = String.valueOf(Long.parseLong(firstHalf) + 1);
        p2 = newFirstHalf + new StringBuilder(newFirstHalf).reverse();
      } else {
        String firstHalf = n.substring(0, n.length() / 2);
        char middle = n.charAt(n.length() / 2);
        if (middle == '0') {
          p1 = firstHalf + "1" + new StringBuilder(firstHalf).reverse();
        } else {
          p1 =
              firstHalf
                  + (Integer.parseInt(String.valueOf(middle)) - 1)
                  + new StringBuilder(firstHalf).reverse();
        }
        newFirstHalf = String.valueOf(Long.parseLong(firstHalf) + 1);
        p2 = newFirstHalf + "0" + new StringBuilder(newFirstHalf).reverse();
      }
    } else {
      String firstHalf = n.substring(0, n.length() / 2);
      if (n.length() % 2 == 0) {
        p1 = firstHalf + new StringBuilder(firstHalf).reverse();
        String temp = String.valueOf(Long.parseLong(firstHalf) + 1);
        p2 = temp + new StringBuilder(temp).reverse();
        temp = String.valueOf(Long.parseLong(firstHalf) - 1);
        String p3 = temp + new StringBuilder(temp).reverse();
        p1 =
            Math.abs(Long.parseLong(p3) - Long.parseLong(n))
                    <= Math.abs(Long.parseLong(p1) - Long.parseLong(n))
                ? p3
                : p1;
      } else {
        char middle = n.charAt(n.length() / 2);
        p1 = firstHalf + middle + new StringBuilder(firstHalf).reverse();
        String temp = String.valueOf(Long.parseLong(firstHalf) + 1);
        p2 = temp + "0" + new StringBuilder(temp).reverse();
        String p3 =
            firstHalf
                + (Integer.parseInt(String.valueOf(middle)) + 1)
                + new StringBuilder(firstHalf).reverse();
        String p4 = null;
        if (middle != '0') {
          p4 =
              firstHalf
                  + (Integer.parseInt(String.valueOf(middle)) - 1)
                  + new StringBuilder(firstHalf).reverse();
        }
        p1 =
            Math.abs(Long.parseLong(p1) - Long.parseLong(n))
                    <= Math.abs(Long.parseLong(p3) - Long.parseLong(n))
                ? p1
                : p3;
        if (p4 != null) {
          p1 =
              Math.abs(Long.parseLong(p4) - Long.parseLong(n))
                      <= Math.abs(Long.parseLong(p1) - Long.parseLong(n))
                  ? p4
                  : p1;
        }
      }
    }
    long l1 = Math.abs(Long.parseLong(n) - Long.parseLong(p1));
    long l2 = Math.abs(Long.parseLong(n) - Long.parseLong(p2));
    if (l1 <= l2) {
      palindrome = p1;
    } else palindrome = p2;
    long m1 = Math.abs(Long.parseLong(getLow(n)) - Long.parseLong(n));
    long m2 = Math.abs(Long.parseLong(palindrome) - Long.parseLong(n));
    long m3 = Math.abs(Long.parseLong(getHigh(n)) - Long.parseLong(n));
    long min = Math.min(Math.min(m1, m2), m3);
    if (min == m1) return getLow(n);
    else if (min == m2) return palindrome;
    else return getHigh(n);
  }

  private String getLow(String s) {
    int n = s.length() - 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append("9");
    }
    return sb.toString();
  }

  private String getHigh(String s) {
    int n = s.length() - 1;
    StringBuilder sb = new StringBuilder();
    sb.append("1");
    for (int i = 0; i < n; i++) {
      sb.append("0");
    }
    sb.append("1");
    return sb.toString();
  }

  private boolean isPaliandrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }
}
