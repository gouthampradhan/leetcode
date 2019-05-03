package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 16/03/2017. Given a non-empty string s and a dictionary
 * wordDict containing a list of non-empty words, determine if s can be segmented into a
 * space-separated sequence of one or more dictionary words. You may assume the dictionary does not
 * contain duplicate words.
 *
 * <p>For example, given s = "leetcode", dict = ["leet", "code"].
 *
 * <p>Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<String> dic = new ArrayList<>();
    String[] arr = {
      "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"
    };
    for (String s : arr) dic.add(s);
    System.out.println(
        new WordBreak()
            .wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                dic));
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> dictionary = new HashSet<>();
    dictionary.addAll(wordDict);
    Map<Integer, Boolean> dic = new HashMap<>();
    for (int i = s.length() - 1; i >= 0; i--) dp(i, s, dic, dictionary);
    return dic.get(0);
  }

  private boolean dp(int i, String s, Map<Integer, Boolean> dic, Set<String> dictionary) {
    if (i == s.length()) return true;
    else if (dic.containsKey(i)) return dic.get(i);
    else {
      for (int j = i, l = s.length(); j < l; j++) {
        String subStr = s.substring(i, j + 1);
        if (dictionary.contains(subStr)) {
          if (dp(j + 1, s, dic, dictionary)) {
            dic.put(i, true);
            break;
          }
        }
      }
    }
    if (!dic.containsKey(i)) dic.put(i, false);
    return dic.get(i);
  }
}
