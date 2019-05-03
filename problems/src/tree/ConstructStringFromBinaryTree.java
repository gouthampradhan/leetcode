package tree;

/**
 * Created by gouthamvidyapradhan on 10/06/2017. Accepted You need to construct a string consists of
 * parenthesis and integers from a binary tree with the preorder traversing way.
 *
 * <p>The null node needs to be represented by empty parenthesis pair "()". And you need to omit all
 * the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the
 * string and the original binary tree.
 *
 * <p>Example 1: Input: Binary tree: [1,2,3,4] 1 / \ 2 3 / 4
 *
 * <p>Output: "1(2(4))(3)"
 *
 * <p>Explanation: Originallay it needs to be "1(2(4)())(3()())", but you need to omit all the
 * unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)". Example 2: Input: Binary tree:
 * [1,2,3,null,4] 1 / \ 2 3 \ 4
 *
 * <p>Output: "1(2()(4))(3)"
 *
 * <p>Explanation: Almost the same as the first example, except we can't omit the first parenthesis
 * pair to break the one-to-one mapping relationship between the input and the output.
 */
public class ConstructStringFromBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode t = new TreeNode(1);
    t.left = new TreeNode(2);
    t.left.left = new TreeNode(4);
    t.right = new TreeNode(3);
    System.out.println(new ConstructStringFromBinaryTree().tree2str(t));
  }

  public String tree2str(TreeNode t) {
    if (t == null) return "";
    String left = tree2str(t.left);
    String right = tree2str(t.right);
    if (left.equals("") && right.equals("")) return String.valueOf(t.val);
    if (left.equals("")) left = "()";
    else left = "(" + left + ")";
    if (!right.equals("")) right = "(" + right + ")";
    return t.val + left + right;
  }
}
