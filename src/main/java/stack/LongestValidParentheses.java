package stack;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 08/07/2018. Given a string containing just the characters '('
 * and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * <p>Example 1:
 *
 * <p>Input: "(()" Output: 2 Explanation: The longest valid parentheses substring is "()" Example 2:
 *
 * <p>Input: ")()())" Output: 4 Explanation: The longest valid parentheses substring is "()()"
 *
 * <p>Solution: O(N) Iterate through each of the parentheses and if '(' is encountered push it to
 * stack else check the top of the stack to see if there is a matching parentheses, if yes pop it
 * and then take the length (currIndex - index at top of the stack). Maintain a max length and
 * return this as the answer.
 */
public class LongestValidParentheses {

  private class Node {
    char c;
    int i;

    Node(char c, int i) {
      this.c = c;
      this.i = i;
    }
  }

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new LongestValidParentheses().longestValidParentheses("((()()(((())))))"));
  }

  public int longestValidParentheses(String s) {
    Stack<Node> stack = new Stack<>();
    int max = 0;
    for (int i = 0, l = s.length(); i < l; i++) {
      char c = s.charAt(i);
      switch (c) {
        case '(':
          stack.push(new Node(c, i));
          break;

        case ')':
          if (!stack.isEmpty()) {
            if (stack.peek().c == '(') {
              stack.pop();
              if (stack.isEmpty()) {
                max = Math.max(max, i + 1);
              } else {
                max = Math.max(max, i - stack.peek().i);
              }
            } else {
              stack.push(new Node(c, i));
            }
          } else {
            stack.push(new Node(c, i));
          }
      }
    }
    return max;
  }
}
