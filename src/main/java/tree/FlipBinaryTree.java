package tree;

import java.util.*;

/** Created by gouthamvidyapradhan on 26/01/2020 */
public class FlipBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    //
  }

  private int i, count;
  private List<Integer> result;

  public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
    i = 0;
    count = 0;
    result = new ArrayList<>();
    inorderCount(root);
    if (count != voyage.length) return Arrays.asList(-1);
    preorder(root, voyage);
    if (i == voyage.length) return result;
    return Arrays.asList(-1);
  }

  private void inorderCount(TreeNode node) {
    if (node != null) {
      count++;
      inorderCount(node.left);
      inorderCount(node.right);
    }
  }

  private void preorder(TreeNode node, int[] voyage) {
    if (node != null) {
      if (voyage[i] == node.val) {
        i++;
      }
      if (node.left != null && node.right != null) {
        if (voyage[i] == node.right.val) {
          TreeNode temp = node.left;
          node.left = node.right;
          node.right = temp;
          result.add(node.val);
        }
      }
      preorder(node.left, voyage);
      preorder(node.right, voyage);
    }
  }
}
