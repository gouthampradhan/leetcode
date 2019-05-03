package tree;

/**
 * Created by gouthamvidyapradhan on 13/12/2017. Find the sum of all left leaves in a given binary
 * tree.
 *
 * <p>Example:
 *
 * <p>3 / \ 9 20 / \ 15 7
 *
 * <p>There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumofLeftLeaves {

  public class TreeNode {
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
  public static void main(String[] args) throws Exception {}

  public int sumOfLeftLeaves(TreeNode root) {
    return inorder(root, false);
  }

  private int inorder(TreeNode node, boolean isLeft) {
    if (node != null) {
      if (node.left == null && node.right == null) {
        if (isLeft) {
          return node.val;
        } else return 0;
      }
      return inorder(node.left, true) + inorder(node.right, false);
    }
    return 0;
  }
}
