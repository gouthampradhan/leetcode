package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradhang on 3/15/2017. Given a string s, partition s such that every substring of the
 * partition is a palindrome.
 *
 * <p>Return all possible palindrome partitioning of s.
 *
 * <p>For example, given s = "aab", Return
 *
 * <p>[ ["aa","b"], ["a","a","b"] ]
 */
public class PalindromePartitioning {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<List<String>> result = new PalindromePartitioning().partition("aaaaaaaaaaaaaaaaaa");
  }

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    doNext(0, new ArrayList<>(), s, result);
    return result;
  }

  private void doNext(int i, List<String> row, String s, List<List<String>> result) {
    if (i == s.length()) {
      List<String> list = new ArrayList<>(row);
      result.add(list);
    } else {
      for (int j = i, l = s.length(); j < l; j++) {
        String sbStr = s.substring(i, j + 1);
        if (isPalindrome(sbStr)) {
          row.add(sbStr);
          doNext(j + 1, row, s, result);
          row.remove(row.size() - 1);
        }
      }
    }
  }

  private boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i <= j) {
      if (s.charAt(i) != s.charAt(j)) return false;
      i++;
      j--;
    }
    return true;
  }
}
