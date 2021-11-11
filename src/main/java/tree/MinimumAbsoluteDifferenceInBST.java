package tree;

/**
 * Created by gouthamvidyapradhan on 15/02/2018. Given a binary search tree with non-negative
 * values, find the minimum absolute difference between values of any two nodes.
 *
 * <p>Example:
 *
 * <p>Input:
 *
 * <p>1 \ 3 / 2
 *
 * <p>Output: 1
 *
 * <p>Explanation: The minimum absolute difference is 1, which is the difference between 2 and 1 (or
 * between 2 and 3). Note: There are at least two nodes in this BST.
 */
public class MinimumAbsoluteDifferenceInBST {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  int min = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(2);
    root.right.right = new TreeNode(3);
    new MinimumAbsoluteDifferenceInBST().getMinimumDifference(root);
  }

  public int getMinimumDifference(TreeNode root) {
    getMin(root, null);
    return min;
  }

  private Integer getMin(TreeNode node, Integer prev) {
    if (node == null) return prev;
    Integer left = getMin(node.left, prev);
    if (left != null) {
      min = Math.min(min, Math.abs(node.val - left));
    }
    return getMin(node.right, node.val);
  }
}
