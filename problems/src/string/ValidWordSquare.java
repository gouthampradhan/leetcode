package string;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 04/06/2019 Given a sequence of words, check whether it forms a
 * valid word square.
 *
 * <p>A sequence of words forms a valid word square if the kth row and column read the exact same
 * string, where 0 ≤ k < max(numRows, numColumns).
 *
 * <p>Note: The number of words given is at least 1 and does not exceed 500. Word length will be at
 * least 1 and does not exceed 500. Each word contains only lowercase English alphabet a-z. Example
 * 1:
 *
 * <p>Input: [ "abcd", "bnrt", "crmy", "dtye" ]
 *
 * <p>Output: true
 *
 * <p>Explanation: The first row and first column both read "abcd". The second row and second column
 * both read "bnrt". The third row and third column both read "crmy". The fourth row and fourth
 * column both read "dtye".
 *
 * <p>Therefore, it is a valid word square. Example 2:
 *
 * <p>Input: [ "abcd", "bnrt", "crm", "dt" ]
 *
 * <p>Output: true
 *
 * <p>Explanation: The first row and first column both read "abcd". The second row and second column
 * both read "bnrt". The third row and third column both read "crm". The fourth row and fourth
 * column both read "dt".
 *
 * <p>Therefore, it is a valid word square. Example 3:
 *
 * <p>Input: [ "ball", "area", "read", "lady" ]
 *
 * <p>Output: false
 *
 * <p>Explanation: The third row reads "read" while the third column reads "lead".
 *
 * <p>Therefore, it is NOT a valid word square.
 *
 * <p>Solution: O(N x M) where N is number of words and M is max length of a string. Save strings
 * for each column and each row in a array and compare them both.
 */
public class ValidWordSquare {
  public static void main(String[] args) {
    List<String> arr = Arrays.asList("abcd", "bnrt", "crmy", "dtye");
    System.out.println(new ValidWordSquare().validWordSquare(arr));
  }

  public boolean validWordSquare(List<String> words) {
    List<String> newList = new ArrayList<>();
    int max = 0;
    for (int i = 0; i < words.size(); i++) {
      max = Math.max(max, words.get(i).length());
    }

    for (int i = 0; i < max; i++) {
      StringBuilder sb = new StringBuilder();
      for (String w : words) {
        if (i < w.length()) {
          sb.append(w.charAt(i));
        } else break;
      }
      newList.add(sb.toString());
    }

    if (words.size() != newList.size()) return false;

    for (int i = 0, l = words.size(); i < l; i++) {
      if (!words.get(i).equals(newList.get(i))) return false;
    }
    return true;
  }
}
