package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 25/02/2017. Given preorder and inorder traversal of a tree,
 * construct the binary tree.
 *
 * <p>Note: You may assume that duplicates do not exist in the tree.
 */
public class PreorderToBT {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  Map<Integer, Integer> MAP = new HashMap<>();
  private int index = 0, totalLen = 0;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] perorder = {7, -10, -4, 3, -1, 2, -8, 11};
    int[] inorder = {-4, -10, 3, -1, 7, 11, -8, 2};
    new PreorderToBT().buildTree(perorder, inorder);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int i = 0, l = inorder.length; i < l; i++) MAP.put(inorder[i], i);
    totalLen = preorder.length;
    return build(preorder, 0, inorder.length - 1);
  }

  private TreeNode build(int[] preorder, int s, int e) {
    if (s > e || index >= totalLen) return null;

    int n = preorder[index++];
    int pos = MAP.get(n);

    TreeNode node = new TreeNode(n);
    if (s == e) return node;

    node.left = build(preorder, s, pos - 1);
    node.right = build(preorder, pos + 1, e);
    return node;
  }
}
