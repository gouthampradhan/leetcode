package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 01/05/2018. S and T are strings composed of lowercase letters.
 * In S, no letter occurs more than once.
 *
 * <p>S was sorted in some custom order previously. We want to permute the characters of T so that
 * they match the order that S was sorted. More specifically, if x occurs before y in S, then x
 * should occur before y in the returned string.
 *
 * <p>Return any permutation of T (as a string) that satisfies this property.
 *
 * <p>Example : Input: S = "cba" T = "abcd" Output: "cbad" Explanation: "a", "b", "c" appear in S,
 * so the order of "a", "b", "c" should be "c", "b", and "a". Since "d" does not appear in S, it can
 * be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 *
 * <p>Note:
 *
 * <p>S has length at most 26, and no character is repeated in S. T has length at most 200. S and T
 * consist of lowercase letters only.
 *
 * <p>Solution: O(N) count occurrence of each character and write to the output string
 */
public class CustomSortString {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new CustomSortString().customSortString("cba", "abcd"));
  }

  public String customSortString(String S, String T) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < T.length(); i++) {
      if (!map.containsKey(T.charAt(i))) {
        map.put(T.charAt(i), 1);
      } else {
        map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
      }
    }
    StringBuilder result = new StringBuilder();
    for (char c : S.toCharArray()) {
      if (map.containsKey(c)) {
        int count = map.remove(c);
        for (int i = 0; i < count; i++) {
          result.append(c);
        }
      }
    }
    for (char c : map.keySet()) {
      int count = map.get(c);
      for (int i = 0; i < count; i++) {
        result.append(c);
      }
    }
    return result.toString();
  }
}
