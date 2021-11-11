package hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 10/04/2018. A string S of lowercase letters is given. We want
 * to partition this string into as many parts as possible so that each letter appears in at most
 * one part, and return a list of integers representing the size of these parts.
 *
 * <p>Example 1: Input: S = "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation: The partition is
 * "ababcbaca", "defegde", "hijhklij". This is a partition so that each letter appears in at most
 * one part. A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into
 * less parts. Note:
 *
 * <p>S will have length in range [1, 500]. S will consist of lowercase letters ('a' to 'z') only.
 *
 * <p>Solution O(n): Maintain a hashmap index of last occurrence of a character and do a linear
 * check for max index, get the length and add it to the result set.
 */
public class PartitionLabels {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new PartitionLabels().partitionLabels("abc"));
  }

  public List<Integer> partitionLabels(String S) {
    if (S == null || S.trim().isEmpty()) return new ArrayList<>();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = S.length() - 1; i >= 0; i--) {
      char c = S.charAt(i);
      map.putIfAbsent(c, i);
    }
    List<Integer> result = new ArrayList<>();
    int start = 0;
    int max = map.get(S.charAt(0));
    for (int i = 0; i < S.length(); i++) {
      char c = S.charAt(i);
      if (map.get(c) > max) {
        max = map.get(c);
      } else if (i == max) {
        result.add(max - start + 1);
        if (i < S.length() - 1) {
          start = i + 1;
          max = map.get(S.charAt(i + 1));
        }
      }
    }
    return result;
  }
}
