package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/10/2019 Given two strings str1 and str2 of the same length,
 * determine whether you can transform str1 into str2 by doing zero or more conversions.
 *
 * <p>In one conversion you can convert all occurrences of one character in str1 to any other
 * lowercase English character.
 *
 * <p>Return true if and only if you can transform str1 into str2.
 *
 * <p>Example 1:
 *
 * <p>Input: str1 = "aabcc", str2 = "ccdee" Output: true Explanation: Convert 'c' to 'e' then 'b' to
 * 'd' then 'a' to 'c'. Note that the order of conversions matter. Example 2:
 *
 * <p>Input: str1 = "leetcode", str2 = "codeleet" Output: false Explanation: There is no way to
 * transform str1 to str2.
 *
 * <p>Note:
 *
 * <p>1 <= str1.length == str2.length <= 10^4 Both str1 and str2 contain only lowercase English
 * letters.
 */
public class StringTransformsIntoAnotherString {
  public static void main(String[] args) {
    System.out.println(new StringTransformsIntoAnotherString().canConvert("ab", "ba"));
  }

  public boolean canConvert(String str1, String str2) {
    if (str1.length() != str2.length()) return false;
    if (str1.equals(str2)) return true;
    Map<Character, Character> mapping = new HashMap<>();
    for (int i = 0; i < str1.length(); i++) {
      char c1 = str1.charAt(i);
      char c2 = str2.charAt(i);
      if (mapping.containsKey(c1)) {
        if (mapping.get(c1) != c2) return false;
      } else {
        mapping.put(c1, c2);
      }
    }
    Set<Character> set = new HashSet<>();
    for (char k : mapping.keySet()) {
      set.add(mapping.get(k));
    }
    return (set.size() < 26);
  }
}
