package stack;

import java.util.Stack;

/** Created by gouthamvidyapradhan on 12/05/2019 */
public class DecodedStringAtIndex {

  public static void main(String[] args) {
    System.out.println(new DecodedStringAtIndex().decodeAtIndex("ha22", 5));
  }

  class Node {
    String S;
    long count;
    int multiple;

    Node(String S, long count, int multiple) {
      this.S = S;
      this.count = count;
      this.multiple = multiple;
    }
  }

  public String decodeAtIndex(String S, int K) {
    Stack<Node> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    char prev = ' ';
    for (char c : S.toCharArray()) {
      if (Character.isDigit(c)) {
        String currStr = sb.toString();
        long len = 0L;
        if (!stack.isEmpty()) {
          len = stack.peek().count * stack.peek().multiple;
        }
        stack.push(
            new Node(currStr, len + (currStr.length()), Integer.parseInt(String.valueOf(c))));
        if (((len + (currStr.length())) * Integer.parseInt(String.valueOf(c))) >= K) {
          break;
        }
        prev = c;
      } else {
        if (Character.isDigit(prev)) {
          sb = new StringBuilder();
        }
        sb.append(c);
        prev = c;
      }
    }
    while (!stack.isEmpty()) {
      Node top = stack.peek();
      long l = top.count;
      if (K <= l) {
        return String.valueOf(top.S.charAt((int) l - K - 1));
      }
      long mod = (K % l);
      if (mod == 0) {
        return String.valueOf(top.S.charAt(top.S.length() - 1));
      }
      if (l - top.S.length() < mod) {
        long i = l - mod;
        return String.valueOf(top.S.charAt(top.S.length() - (int) i - 1));
      } else {
        stack.pop();
      }
    }
    return "";
  }
}
