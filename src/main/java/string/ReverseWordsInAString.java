package string;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by gouthamvidyapradhan on 04/07/2017. Given an input string, reverse the string word by
 * word.
 *
 * <p>For example, Given s = "the sky is blue", return "blue is sky the".
 *
 * <p>Clarification: What constitutes a word? A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces? Yes. However, your reversed string
 * should not contain leading or trailing spaces. How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 */
public class ReverseWordsInAString {
  public static void main(String[] args) throws Exception {
    System.out.println(new ReverseWordsInAString().reverseWords("  the     sky is blue"));
  }

  public String reverseWords(String s) {
    if (s == null || s.isEmpty()) return "";
    StringBuilder sb = new StringBuilder(s.trim());
    String reverse = sb.reverse().toString();
    StringTokenizer st = new StringTokenizer(reverse, " ");
    List<String> list = new ArrayList<>();
    while (st.hasMoreTokens()) {
      list.add(st.nextToken());
    }
    for (int i = 0, l = list.size(); i < l; i++) {
      String str = list.get(i);
      String newStr = new StringBuilder(str).reverse().toString();
      list.set(i, newStr);
    }
    StringBuilder result = new StringBuilder();
    for (String str : list) {
      result.append(str).append(" ");
    }
    return result.toString().trim();
  }
}
