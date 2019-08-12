package binary_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 06/08/2019 Given strings S and T, find the minimum (contiguous)
 * substring W of S, so that T is a subsequence of W.
 *
 * <p>If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting
 * index.
 *
 * <p>Example 1:
 *
 * <p>Input: S = "abcdebdde", T = "bde" Output: "bcde" Explanation: "bcde" is the answer because it
 * occurs before "bdde" which has the same length. "deb" is not a smaller window because the
 * elements of T in the window must occur in order.
 *
 * <p>Note:
 *
 * <p>All the strings in the input will only contain lowercase letters. The length of S will be in
 * the range [1, 20000]. The length of T will be in the range [1, 100].
 *
 * <p>Solution O(S x T x log S) General idea is to first find the left-most left (l) and right (r)
 * index where r - l is minimum and the minimum window contains the sub-sequence and iteratively
 * check the next left-most indices and continue for the entire string S. A naive implementation
 * would result in O(S ^ 2) therefore to speed up we have to maintain a hashtable of character as
 * key and all its index of occurrence in a sorted list. Now, since this list is sorted we can
 * easily find the next left-most by binarySearch or even better by using a TreeSet higher or ceil
 * function.
 */
public class MinimumWindowSubsequence {
  public static void main(String[] args) {
    System.out.println(new MinimumWindowSubsequence().minWindow("abcdebdde", "x"));
  }

  public String minWindow(String S, String T) {
    if (T.isEmpty() || S.isEmpty()) return "";
    Map<Character, TreeSet<Integer>> charMap = new HashMap<>();
    for (int i = 0, l = S.length(); i < l; i++) {
      char c = S.charAt(i);
      charMap.putIfAbsent(c, new TreeSet<>());
      charMap.get(c).add(i);
    }
    int min = Integer.MAX_VALUE;
    int start = -1, end;
    int ansStart = -1, ansEnd = -1;
    boolean finished = false;
    while (true) {
      int index = start;
      end = -1;
      for (int i = 0, l = T.length(); i < l; i++) {
        char c = T.charAt(i);
        if (!charMap.containsKey(c)) {
          return "";
        } else {
          TreeSet<Integer> indicies = charMap.get(c);
          Integer found = indicies.higher(index);
          if (found == null) {
            finished = true;
            break;
          } else {
            index = found;
            if (i == 0) {
              start = index;
            }
            if (i == l - 1) {
              end = index;
            }
          }
        }
      }
      if (start != -1 && end != -1) {
        if ((end - start) < min) {
          min = end - start;
          ansStart = start;
          ansEnd = end;
        }
      }
      if (finished) return ansStart == -1 ? "" : S.substring(ansStart, ansEnd + 1);
    }
  }
}
