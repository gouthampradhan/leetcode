package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/08/2019 Consider all the leaves of a binary tree. From left
 * to right order, the values of those leaves form a leaf value sequence.
 *
 * <p>For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * <p>Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * <p>Return true if and only if the two given trees with head nodes root1 and root2 are
 * leaf-similar.
 *
 * <p>Solution: Do a inorder traversal for each trree and keep track of all the leaf nodes of the
 * tree in a list. Compare the list and return the answer.
 */
public class LeafSimilarTrees {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {}

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    inorder(root1, list1);
    inorder(root2, list2);
    if (list1.size() != list2.size()) return false;
    else {
      for (int i = 0, l = list1.size(); i < l; i++) {
        if (list1.get(i).intValue() != list2.get(i).intValue()) {
          return false;
        }
      }
    }
    return true;
  }

  private void inorder(TreeNode node, List<Integer> list) {
    if (node != null) {
      if (node.left == null && node.right == null) {
        list.add(node.val);
      }
      inorder(node.left, list);
      inorder(node.right, list);
    }
  }
}
