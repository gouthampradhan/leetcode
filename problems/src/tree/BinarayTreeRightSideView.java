package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 12/03/2017. Given a binary tree, imagine yourself standing on
 * the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * <p>For example: Given the following binary tree, 1 <--- / \ 2 3 <--- \ \ 5 4 <--- You should
 * return [1, 3, 4].
 */
public class BinarayTreeRightSideView {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private int maxHeigh = Integer.MIN_VALUE;
  List<Integer> list = new ArrayList<>();

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(3);
    root.right = new TreeNode(4);
    root.right.right = new TreeNode(5);
    root.right.left = new TreeNode(4);
    root.right.left.right = new TreeNode(8);
    root.right.left.left = new TreeNode(7);
    root.right.left.left.right = new TreeNode(10);
    root.right.left.left.left = new TreeNode(7);

    List<Integer> list = new BinarayTreeRightSideView().rightSideView(root);
  }

  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) return list;
    dfs(root, 0);
    return list;
  }

  private void dfs(TreeNode node, int height) {
    if (node != null) {
      if (height > maxHeigh) {
        list.add(node.val);
        maxHeigh = height;
      }
      dfs(node.right, height + 1);
      dfs(node.left, height + 1);
    }
  }
}
