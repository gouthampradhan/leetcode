package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pradhang on 7/11/2017. Given a binary tree, return the zigzag level order traversal of
 * its nodes' values. (ie, from left to right, then right to left for the next level and alternate
 * between).
 *
 * <p>For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7 return its zigzag
 * level order traversal as: [ [3], [20,9], [15,7] ]
 */
public class ZigZagTraversal {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {}

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    dfs(root, 0, result);
    return result;
  }

  @SuppressWarnings("unchecked")
  private void dfs(TreeNode root, int level, List<List<Integer>> result) {
    if (root != null) {
      LinkedList<Integer> subList;
      if (level >= result.size()) {
        subList = new LinkedList<>();
        result.add(subList);
      } else subList = (LinkedList) result.get(level);
      if (level % 2 == 0) subList.addFirst(root.val); // add to right
      else subList.add(root.val); // add to left
      dfs(root.right, level + 1, result);
      dfs(root.left, level + 1, result);
    }
  }
}
