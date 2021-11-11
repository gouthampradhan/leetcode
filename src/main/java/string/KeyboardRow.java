package string;
/**
 * Created by gouthamvidyapradhan on 09/04/2019
 *
 * <p>Given a List of words, return the words that can be typed using letters of alphabet on only
 * one row's of American keyboard like the image below.
 *
 * <p>Example:
 *
 * <p>Input: ["Hello", "Alaska", "Dad", "Peace"] Output: ["Alaska", "Dad"]
 *
 * <p>Note:
 *
 * <p>You may use one character in the keyboard more than once. You may assume the input string will
 * only contain letters of alphabet.
 */
import java.util.*;

public class KeyboardRow {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {}

  public String[] findWords(String[] words) {
    String R1 = "qwertyuiop";
    String R2 = "asdfghjkl";
    String R3 = "zxcvbnm";
    List<String> answer = new ArrayList<>();
    for (String s : words) {
      Set<Character> set = new HashSet<>();
      for (char c : s.toCharArray()) {
        if (R1.indexOf(c) != -1) {
          set.add('1');
        } else if (R2.indexOf(c) != -1) {
          set.add('2');
        } else if (R3.indexOf(c) != -1) {
          set.add('3');
        }
      }
      if (set.size() == 1) {
        answer.add(s);
      }
    }
    String[] ans = new String[answer.size()];
    int i = 0;
    for (String s : answer) {
      ans[i++] = s;
    }
    return ans;
  }
}
