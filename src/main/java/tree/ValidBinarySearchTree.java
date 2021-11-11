package tree;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given a binary tree, determine if it is a valid
 * binary search tree (BST).
 *
 * <p>Assume a BST is defined as follows:
 *
 * <p>The left subtree of a node contains only nodes with keys less than the node's key. The right
 * subtree of a node contains only nodes with keys greater than the node's key. Both the left and
 * right subtrees must also be binary search trees. Example 1: 2 / \ 1 3 Binary tree [2,1,3], return
 * true. Example 2: 1 / \ 2 3 Binary tree [1,2,3], return false.
 */
public class ValidBinarySearchTree {
  class Range {
    long low, high;
  }

  static class TreeNode {
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
    TreeNode root = new TreeNode(Integer.MIN_VALUE);
    root.right = new TreeNode(Integer.MAX_VALUE);
    System.out.println(new ValidBinarySearchTree().isValidBST(root));
  }

  private boolean isValidBST(TreeNode root) {
    if (root == null || (root.right == null && root.left == null)) return true;
    Range range = new Range();
    range.high = Long.MAX_VALUE;
    range.low = Long.MIN_VALUE;
    return validate(root, range);
  }

  private boolean validate(TreeNode root, Range range) {
    if ((root.val > range.low) && (root.val < range.high)) {
      long temp = range.high;
      if (root.left != null) {
        range.high = root.val;
        if (!validate(root.left, range)) return false;
      }
      if (root.right != null) {
        range.high = temp;
        range.low = root.val;
        if (!validate(root.right, range)) return false;
      }
      return true;
    } else return false;
  }
}
