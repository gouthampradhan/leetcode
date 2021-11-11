package stack;

import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 12/04/2018. Given an encoded string, return it's decoded
 * string.
 *
 * <p>The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
 * is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * <p>You may assume that the input string is always valid; No extra white spaces, square brackets
 * are well-formed, etc.
 *
 * <p>Furthermore, you may assume that the original data does not contain any digits and that digits
 * are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * <p>Examples:
 *
 * <p>s = "3[a]2[bc]", return "aaabcbc". s = "3[a2[c]]", return "accaccacc". s = "2[abc]3[cd]ef",
 * return "abcabccdcdcdef".
 *
 * <p>Solution: Maintain a stack and push items when a character other than ] is encountered. When a
 * character ] is encountered pop elements, build string and duplicate it.
 */
public class DecodeString {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new DecodeString().decodeString("100[leetcode]"));
  }

  public String decodeString(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ']') {
        StringBuilder stackBuff = new StringBuilder();
        while (stack.peek() != '[') {
          stackBuff.append(stack.pop());
        }
        stack.pop(); // pop '['
        String num = "";
        while (!stack.isEmpty() && !Character.isAlphabetic(stack.peek()) && stack.peek() != '[') {
          num = stack.pop() + num;
        }
        String str = stackBuff.reverse().toString();
        StringBuilder stringMultiple = new StringBuilder();
        int N = Integer.parseInt(num);
        while (N-- > 0) {
          stringMultiple.append(str);
        }
        for (int j = 0; j < stringMultiple.length(); j++) {
          stack.push(stringMultiple.charAt(j));
        }
      } else stack.push(s.charAt(i));
    }
    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
      result.append(stack.pop());
    }
    return result.reverse().toString();
  }
}
