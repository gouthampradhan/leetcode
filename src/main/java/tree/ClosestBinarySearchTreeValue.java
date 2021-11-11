package tree;

/**
 * Created by gouthamvidyapradhan on 10/05/2017. Given a non-empty binary search tree and a target
 * value, find the value in the BST that is closest to the target.
 *
 * <p>Note: Given target value is a floating point. You are guaranteed to have only one unique value
 * in the BST that is closest to the target.
 *
 * <p>Solution: Simple dfs recursive algorithm to find the closest match. Worst case time complexity
 * is O(h) where h is height of the tree
 */
public class ClosestBinarySearchTreeValue {
  /** TreeNode */
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private double absDiff = Double.MAX_VALUE;
  private int closest;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(9);
    root.left.left = new TreeNode(8);
    System.out.println(new ClosestBinarySearchTreeValue().closestValue(root, 7.63354D));
  }

  /**
   * Find closest
   *
   * @param root Root node
   * @param target double target
   * @return closest value
   */
  public int closestValue(TreeNode root, double target) {
    if (root == null) return closest;
    if (Math.abs(target - root.val) < absDiff) {
      absDiff = Math.abs(target - root.val);
      closest = root.val;
    }
    if (root.val > target) return closestValue(root.left, target);
    else return closestValue(root.right, target);
  }
}
