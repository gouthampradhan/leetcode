package string;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 15/02/2018. Given a string and a string dictionary, find the
 * longest string in the dictionary that can be formed by deleting some characters of the given
 * string. If there are more than one possible results, return the longest word with the smallest
 * lexicographical order. If there is no possible result, return the empty string.
 *
 * <p>Example 1: Input: s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * <p>Output: "apple" Example 2: Input: s = "abpcplea", d = ["a","b","c"]
 *
 * <p>Output: "a" Note: All the strings in the input will only contain lower-case letters. The size
 * of the dictionary won't exceed 1,000. The length of all the strings in the input won't exceed
 * 1,000.
 *
 * <p>Solution: O((n x m x log n) + (m ^ 2 + m x n))) sort the dictionary based on the longest first
 * and then lexicographically and compare each sorted word with given word and do a two pointer
 * comparison to check for sub-sequence.
 */
public class LongestWordInDictonary {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    List<String> dict = Arrays.asList("ale", "apple", "monkey", "plea");
    System.out.println(new LongestWordInDictonary().findLongestWord("abpcplea", dict));
  }

  public String findLongestWord(String s, List<String> d) {
    Collections.sort(
        d, Comparator.comparing(String::length).reversed().thenComparing(String::compareTo));
    for (String str : d) {
      if (str.length() <= s.length()) {
        int i = 0, j = 0;
        for (int l1 = s.length(), l2 = str.length(); i < l1 && j < l2; ) {
          if (s.charAt(i) == str.charAt(j)) {
            i++;
            j++;
          } else {
            i++;
          }
        }
        if (j >= str.length()) return str;
      }
    }
    return "";
  }
}
