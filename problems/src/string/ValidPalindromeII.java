package string;

/**
 * Created by gouthamvidyapradhan on 09/12/2017. Given a non-empty string s, you may delete at most
 * one character. Judge whether you can make it a palindrome.
 *
 * <p>Example 1: Input: "aba" Output: True Example 2: Input: "abca" Output: True Explanation: You
 * could delete the character 'c'. Note: The string will only contain lowercase characters a-z. The
 * maximum length of the string is 50000.
 */
public class ValidPalindromeII {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new ValidPalindromeII().validPalindrome("aaaaaab"));
  }

  public boolean validPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ) {
      if (s.charAt(i) == s.charAt(j)) {
        i++;
        j--;
      } else {
        return isPaliandrome(s.substring(i, j)) || isPaliandrome(s.substring(i + 1, j + 1));
      }
    }
    return true;
  }

  private boolean isPaliandrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ) {
      if (s.charAt(i) == s.charAt(j)) {
        i++;
        j--;
      } else return false;
    }
    return true;
  }
}
