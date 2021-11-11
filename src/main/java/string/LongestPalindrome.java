package string;
/**
 * Created by gouthamvidyapradhan on 20/03/2019 Given a string which consists of lowercase or
 * uppercase letters, find the length of the longest palindromes that can be built with those
 * letters.
 *
 * <p>This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * <p>Note: Assume the length of given string will not exceed 1,010.
 *
 * <p>Example:
 *
 * <p>Input: "abccccdd"
 *
 * <p>Output: 7
 *
 * <p>Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
import java.util.*;

public class LongestPalindrome {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int result = new LongestPalindrome().longestPalindrome("asdfasdf");
    System.out.println(result);
  }

  public int longestPalindrome(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      map.putIfAbsent(c, 0);
      int count = map.get(c);
      map.put(c, count + 1);
    }
    int max = 0;
    boolean odd = false;
    for (char c : map.keySet()) {
      int count = map.get(c);
      max += count;
      if ((count % 2) != 0) {
        max--;
        odd = true;
      }
    }
    if (odd) max++;
    return max;
  }
}
