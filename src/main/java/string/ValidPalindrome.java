package string;

/**
 * Created by gouthamvidyapradhan on 13/07/2017. Given a string, determine if it is a palindrome,
 * considering only alphanumeric characters and ignoring cases.
 *
 * <p>For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a
 * palindrome.
 *
 * <p>Note: Have you consider that the string might be empty? This is a good question to ask during
 * an interview.
 *
 * <p>For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {
  public static void main(String[] args) throws Exception {
    System.out.println(new ValidPalindrome().isPalindrome("989 "));
  }

  public boolean isPalindrome(String s) {
    if (s == null || s.isEmpty()) return true;
    s = s.toLowerCase();
    for (int i = 0, j = s.length() - 1; i < j; ) {
      char f = s.charAt(i);
      char l = s.charAt(j);
      if (!(f >= 'a' && f <= 'z') && !(f >= '0' && f <= '9')) {
        i++;
        continue;
      }
      if (!(l >= 'a' && l <= 'z') && !(l >= '0' && l <= '9')) {
        j--;
        continue;
      }
      if (f != l) return false;
      i++;
      j--;
    }
    return true;
  }
}
