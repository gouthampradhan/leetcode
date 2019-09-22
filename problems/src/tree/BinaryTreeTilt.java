package tree;
/**
 * Created by gouthamvidyapradhan on 14/08/2019 Given a binary tree, return the tilt of the whole
 * tree.
 *
 * <p>The tilt of a tree node is defined as the absolute difference between the sum of all left
 * subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 *
 * <p>The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * <p>Example: Input: 1 / \ 2 3 Output: 1 Explanation: Tilt of node 2 : 0 Tilt of node 3 : 0 Tilt of
 * node 1 : |2-3| = 1 Tilt of binary tree : 0 + 0 + 1 = 1 Note:
 *
 * <p>The sum of node values in any subtree won't exceed the range of 32-bit integer. All the tilt
 * values won't exceed the range of 32-bit integer.
 *
 * <p>Solution: Find tilt of left node and find tilt of right node and return left + right + curr to
 * its parent.
 */
public class BinaryTreeTilt {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    TreeNode node = new TreeNode(1);
    node.left = new TreeNode(2);
    node.right = new TreeNode(3);
    System.out.println(new BinaryTreeTilt().findTilt(node));
  }

  int sum = 0;

  public int findTilt(TreeNode root) {
    if (root == null) return 0;
    tilt(root);
    return sum;
  }

  private int tilt(TreeNode node) {
    if (node == null) return 0;
    int left = tilt(node.left);
    int right = tilt(node.right);
    sum += Math.abs(left - right);
    return left + right + node.val;
  }
}
