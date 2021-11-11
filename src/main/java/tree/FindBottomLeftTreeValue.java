package tree;

/**
 * Created by gouthamvidyapradhan on 30/08/2017. Given a binary tree, find the leftmost value in the
 * last row of the tree.
 *
 * <p>Example 1: Input:
 *
 * <p>2 / \ 1 3
 *
 * <p>Output: 1 Example 2: Input:
 *
 * <p>1 / \ 2 3 / / \ 4 5 6 / 7
 *
 * <p>Output: 7 Note: You may assume the tree (i.e., the given root node) is not NULL. Solution:
 * O(N) do a inorder search to find the left most value. Keep a level counter to keep track of what
 * level you are at when you do a inorder search.
 */
public class FindBottomLeftTreeValue {
  private int max = 0, result;

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(5);
    root.right.left.left = new TreeNode(7);
    root.right.right = new TreeNode(6);
    System.out.println(new FindBottomLeftTreeValue().findBottomLeftValue(root));
  }

  public int findBottomLeftValue(TreeNode root) {
    inorder(root, 1);
    return result;
  }

  private void inorder(TreeNode node, int level) {
    if (node != null) {
      if (level > max) {
        result = node.val;
        max = level;
      }
      inorder(node.left, level + 1);
      inorder(node.right, level + 1);
    }
  }
}
