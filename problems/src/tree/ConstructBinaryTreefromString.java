package tree;

/**
 * Created by gouthamvidyapradhan on 30/07/2019 You need to construct a binary tree from a string
 * consisting of parenthesis and integers.
 *
 * <p>The whole input represents a binary tree. It contains an integer followed by zero, one or two
 * pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains
 * a child binary tree with the same structure.
 *
 * <p>You always start to construct the left child node of the parent first if it exists.
 *
 * <p>Example: Input: "4(2(3)(1))(6(5))" Output: return the tree root node representing the
 * following tree:
 *
 * <p>4 / \ 2 6 /\ / 3 1 5
 *
 * <p>Note: There will only be '(', ')', '-' and '0' ~ '9' in the input string. An empty tree is
 * represented by "" instead of "()".
 *
 * <p>Solution: O(N ^ 2) Form a node for every number and treat the first sub-string within round
 * braces as left node and the second sub-string within the round braces as the right node.
 * Recursively solve each substring within the round braces.
 */
public class ConstructBinaryTreefromString {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    System.out.println(new ConstructBinaryTreefromString().str2tree("4(2(3)(1))(6(5))"));
  }

  private TreeNode str2tree(String s) {
    if (s == null || s.isEmpty()) return null;
    TreeNode current;
    StringBuilder num = new StringBuilder();
    boolean isNeg = false;
    int i = 0;
    for (; i < s.length(); i++) {
      if (s.charAt(i) == '-') {
        isNeg = true;
      } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        num.append(s.charAt(i));
      } else break;
    }
    if (isNeg) {
      current = new TreeNode(Integer.parseInt(num.toString()) * -1);
    } else current = new TreeNode(Integer.parseInt(num.toString()));
    int count = 0;
    StringBuilder left = new StringBuilder();
    for (; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        count++;
      } else if (s.charAt(i) == ')') {
        count--;
      }
      left.append(s.charAt(i));
      if (count == 0) {
        break;
      }
    }
    i++;
    String leftStr = "";
    String rightStr = "";
    if (i < s.length()) {
      rightStr = s.substring(i);
    }
    if (left.length() > 0) {
      leftStr = left.subSequence(1, left.length() - 1).toString();
    }
    if (rightStr.length() > 0) {
      rightStr = rightStr.substring(1, rightStr.length() - 1);
    }
    TreeNode leftNode = str2tree(leftStr);
    TreeNode rightNode = str2tree(rightStr);
    current.left = leftNode;
    current.right = rightNode;
    return current;
  }
}
