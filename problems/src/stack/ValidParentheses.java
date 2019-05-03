package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 25/02/2017. Given a string containing just the characters '(',
 * ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * <p>The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and
 * "([)]" are not.
 */
public class ValidParentheses {
  public static void main(String[] args) {
    System.out.println(hasBalancedBrackets("(h[e"));
  }

  private static Map<Character, Character> MAP = new HashMap<>();

  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public static int hasBalancedBrackets(String str) {
    if (str == null) return 1;

    MAP.put(')', '(');
    MAP.put('}', '{');
    MAP.put('>', '<');
    MAP.put(']', '[');

    Stack<Character> stack = new Stack<>();
    for (int i = 0, l = str.length(); i < l; i++) {
      switch (str.charAt(i)) {
        case '(':
        case '{':
        case '[':
        case '<':
          stack.push(str.charAt(i));
          break;

        case ')':
        case '}':
        case ']':
        case '>':
          if (stack.isEmpty()) return 0;
          char top = stack.pop();
          if (top != MAP.get(str.charAt(i))) return 0;
          break;

        default: // ignore
      }
    }
    return stack.isEmpty() ? 1 : 0;
  }
}
