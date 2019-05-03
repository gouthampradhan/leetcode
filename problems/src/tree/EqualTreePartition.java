package tree;

/**
 * Created by gouthamvidyapradhan on 17/02/2018. Given a binary tree with n nodes, your task is to
 * check if it's possible to partition the tree to two trees which have the equal sum of values
 * after removing exactly one edge on the original tree.
 *
 * <p>Example 1: Input: 5 / \ 10 10 / \ 2 3
 *
 * <p>Output: True Explanation: 5 / 10
 *
 * <p>Sum: 15
 *
 * <p>10 / \ 2 3
 *
 * <p>Sum: 15 Example 2: Input: 1 / \ 2 10 / \ 2 20
 *
 * <p>Output: False Explanation: You can't split the tree into two trees with equal sum after
 * removing exactly one edge on the tree. Note: The range of tree node value is in the range of
 * [-100000, 100000]. 1 <= n <= 10000
 */
public class EqualTreePartition {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private long sum;
  private boolean possible = false;

  public static void main(String[] args) throws Exception {}

  public boolean checkEqualTree(TreeNode root) {
    sum = 0L;
    getSum(root);
    getDiff(root);
    return possible;
  }

  private void getSum(TreeNode node) {
    if (node != null) {
      sum += node.val;
      getSum(node.left);
      getSum(node.right);
    }
  }

  private Long getDiff(TreeNode node) {
    if (node == null) return null;
    Long left = getDiff(node.left);
    Long right = getDiff(node.right);
    if (left != null) {
      if ((sum - left) == left) {
        possible = true;
      }
    }
    if (right != null) {
      if ((sum - right) == right) {
        possible = true;
      }
    }
    Long curr = (long) node.val;
    if (left != null) {
      curr += left;
    }
    if (right != null) {
      curr += right;
    }
    return curr;
  }
}
