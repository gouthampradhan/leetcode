package tree;

/** Created by gouthamvidyapradhan on 29/01/2020 */
public class InsufficientNodesinRoottoLeafPaths {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(-5);
    root.right = new TreeNode(-3);
    root.right.left = new TreeNode(4);
    System.out.println(new InsufficientNodesinRoottoLeafPaths().sufficientSubset(root, -1));
  }

  public TreeNode sufficientSubset(TreeNode root, int limit) {
    long result = dfs(root, 0, limit);
    if (result < limit) return null;
    else return root;
  }

  private long dfs(TreeNode node, long curr, int limit) {
    if (node == null) return Integer.MIN_VALUE;
    long sumLeft = dfs(node.left, curr + node.val, limit);
    long sumRight = dfs(node.right, curr + node.val, limit);
    if (sumLeft == Integer.MIN_VALUE && sumRight == Integer.MIN_VALUE) {
      return node.val;
    } else if (sumLeft == Integer.MIN_VALUE) {
      if ((sumRight + curr + node.val) < limit) {
        node.right = null;
      }
      return node.val + sumRight;
    } else if (sumRight == Integer.MIN_VALUE) {
      if ((sumLeft + curr + node.val) < limit) {
        node.left = null;
      }
      return node.val + sumLeft;
    } else {
      if ((sumLeft + curr + node.val) < limit) {
        node.left = null;
      }
      if ((sumRight + curr + node.val) < limit) {
        node.right = null;
      }
      return Math.max(node.val + sumLeft, node.val + sumRight);
    }
  }
}
