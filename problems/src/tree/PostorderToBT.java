package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 23/02/2017. Given inorder and postorder traversal of a tree,
 * construct the binary tree.
 *
 * <p>Note: You may assume that duplicates do not exist in the tree.
 */
public class PostorderToBT {

  private Map<Integer, Integer> INDEX = new HashMap<>();
  private static int postIndex;

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
  public static void main(String[] args) throws Exception {
    int in[] = new int[] {1, 2};
    int post[] = new int[] {1, 2};
    TreeNode root = new PostorderToBT().buildTree(in, post);
    new PostorderToBT().preorderPrint(root);
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    int count = 0;
    for (int i : inorder) INDEX.put(i, count++);
    postIndex = postorder.length - 1;
    return build(0, inorder.length - 1, postorder);
  }

  private void preorderPrint(TreeNode root) {
    if (root != null) {
      System.out.print(root.val + " ");
      preorderPrint(root.left);
      preorderPrint(root.right);
    }
  }

  private TreeNode build(int s, int e, int[] postorder) {
    if (postIndex >= 0 && s <= e) {
      int poi = postorder[postIndex];

      int ini = INDEX.get(poi);

      TreeNode node = new TreeNode(poi);
      postIndex--;

      if (s == e) return node; // leaf node

      node.right = build(ini + 1, e, postorder);
      node.left = build(s, ini - 1, postorder);

      return node;
    }
    return null;
  }
}
