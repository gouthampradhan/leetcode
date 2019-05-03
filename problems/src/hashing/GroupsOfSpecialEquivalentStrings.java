package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 30/04/2019 You are given an array A of strings.
 *
 * <p>Two strings S and T are special-equivalent if after any number of moves, S == T.
 *
 * <p>A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with
 * S[j].
 *
 * <p>Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any
 * string not in S is not special-equivalent with any string in S.
 *
 * <p>Return the number of groups of special-equivalent strings from A.
 *
 * <p>Example 1:
 *
 * <p>Input: ["a","b","c","a","c","c"] Output: 3 Explanation: 3 groups ["a","a"], ["b"],
 * ["c","c","c"] Example 2:
 *
 * <p>Input: ["aa","bb","ab","ba"] Output: 4 Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
 * Example 3:
 *
 * <p>Input: ["abc","acb","bac","bca","cab","cba"] Output: 3 Explanation: 3 groups ["abc","cba"],
 * ["acb","bca"], ["bac","cab"] Example 4:
 *
 * <p>Input: ["abcd","cdab","adcb","cbad"] Output: 1 Explanation: 1 group
 * ["abcd","cdab","adcb","cbad"]
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 1000 1 <= A[i].length <= 20 All A[i] have the same length. All A[i] consist
 * of only lowercase letters.
 *
 * <p>Solution: The character array of odd positions and even positions of two special-equivalent
 * strings should be exactly equal after sorting. Use a hashset to count number of such groups.
 */
public class GroupsOfSpecialEquivalentStrings {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {}

  public int numSpecialEquivGroups(String[] A) {
    Set<String> set = new HashSet<>();
    for (String s : A) {
      StringBuilder temp1 = new StringBuilder();
      for (int i = 0, l = s.length(); i < l; i += 2) {
        char c = s.charAt(i);
        temp1.append(c);
      }
      StringBuilder temp2 = new StringBuilder();
      if (s.length() > 1) {
        for (int i = 1, l = s.length(); i < l; i += 2) {
          char c = s.charAt(i);
          temp2.append(c);
        }
      }
      char[] temp1Chars = temp1.toString().toCharArray();
      char[] temp2Chars = temp2.toString().toCharArray();
      Arrays.sort(temp1Chars);
      Arrays.sort(temp2Chars);
      set.add(String.valueOf(temp1Chars) + "+" + String.valueOf(temp2Chars));
    }
    return set.size();
  }
}
