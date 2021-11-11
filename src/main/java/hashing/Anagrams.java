package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 25/02/2017. Given a string s and a non-empty string p, find all
 * the start indices of p's anagrams in s.
 *
 * <p>Strings consists of lowercase English letters only and the length of both strings s and p will
 * not be larger than 20,100.
 *
 * <p>The order of output does not matter.
 *
 * <p>Example 1:
 *
 * <p>Input: s: "cbaebabacd" p: "abc"
 *
 * <p>Output: [0, 6]
 *
 * <p>Explanation: The substring with start index = 0 is "cba", which is an anagram of "abc". The
 * substring with start index = 6 is "bac", which is an anagram of "abc". Example 2:
 *
 * <p>Input: s: "abab" p: "ab"
 *
 * <p>Output: [0, 1, 2]
 *
 * <p>Explanation: The substring with start index = 0 is "ab", which is an anagram of "ab". The
 * substring with start index = 1 is "ba", which is an anagram of "ab". The substring with start
 * index = 2 is "ab", which is an anagram of "ab".
 */
public class Anagrams {
  int[] TC = new int[256];
  int[] PC = new int[256];

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<Integer> result = new Anagrams().findAnagrams("abab", "ab");
    result.forEach(System.out::print);
  }

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    int pLen = p.length();
    if (pLen > s.length()) return result;
    Arrays.fill(TC, 0);
    Arrays.fill(PC, 0);
    for (int i = 0; i < pLen; i++) {
      TC[s.charAt(i)]++;
      PC[p.charAt(i)]++;
    }

    int i = pLen;
    for (int l = s.length(); i < l; i++) {
      if (compare()) result.add(i - pLen);

      TC[s.charAt(i)]++;
      TC[s.charAt(i - pLen)]--;
    }
    if (compare()) result.add(i - pLen);

    return result;
  }

  private boolean compare() {
    for (int i = 0; i < 256; i++) {
      if (TC[i] != PC[i]) return false;
    }
    return true;
  }
}
