package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 11/04/2018. Given two strings s and t, determine if they are
 * isomorphic.
 *
 * <p>Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * <p>All occurrences of a character must be replaced with another character while preserving the
 * order of characters. No two characters may map to the same character but a character may map to
 * itself.
 *
 * <p>For example, Given "egg", "add", return true.
 *
 * <p>Given "foo", "bar", return false.
 *
 * <p>Given "paper", "title", return true.
 *
 * <p>Note: You may assume both s and t have the same length. Solution O(N): Maintain two hashmaps
 * and compare character by character.
 */
public class IsomorphicStrings {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new IsomorphicStrings().isIsomorphic("abc", "dea"));
  }

  public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) return false;
    Map<Character, Character> first = new HashMap<>();
    Map<Character, Character> second = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (first.containsKey(c)) {
        char secondC = first.get(c);
        if (t.charAt(i) != secondC) return false;
      } else {
        first.put(c, t.charAt(i));
        if (second.containsKey(t.charAt(i))) return false;
        second.put(t.charAt(i), c);
      }
    }
    return true;
  }
}
