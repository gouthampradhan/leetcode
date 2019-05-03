package breadth_first_search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by gouthamvidyapradhan on 13/03/2017. Given a binary tree, return the level order
 * traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * <p>For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7 return its level
 * order traversal as: [ [3], [9,20], [15,7] ]
 */
public class BinarayTreeLevelOrderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private class LevelNode {
    TreeNode node;
    int level;

    LevelNode(TreeNode node, int level) {
      this.node = node;
      this.level = level;
    }
  }

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
    root.right.left.left.left = new TreeNode(9);

    List<List<Integer>> result = new BinarayTreeLevelOrderTraversal().levelOrder(root);
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<LevelNode> queue = new ArrayDeque<>();
    queue.offer(new LevelNode(root, 0));
    int currentLevel = 0;
    List<Integer> row = new ArrayList<>();
    while (!queue.isEmpty()) {
      LevelNode levelNode = queue.poll();
      if (levelNode.node != null) {
        if (levelNode.level != currentLevel) {
          result.add(row);
          row = new ArrayList<>();
          currentLevel++;
        }
        row.add(levelNode.node.val);
        TreeNode left = levelNode.node.left;
        TreeNode right = levelNode.node.right;
        LevelNode lNodeL = new LevelNode(left, levelNode.level + 1);
        LevelNode lNodeR = new LevelNode(right, levelNode.level + 1);
        queue.offer(lNodeL);
        queue.offer(lNodeR);
      }
    }
    result.add(row);
    return result;
  }
}
