package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by gouthamvidyapradhan on 16/12/2017. Given a non-empty binary tree, return the average
 * value of the nodes on each level in the form of an array. Example 1: Input: 3 / \ 9 20 / \ 15 7
 * Output: [3, 14.5, 11] Explanation: The average value of nodes on level 0 is 3, on level 1 is
 * 14.5, and on level 2 is 11. Hence return [3, 14.5, 11]. Note: The range of node's value is in the
 * range of 32-bit signed integer.
 *
 * <p>Solution O(n) : Perform a BFS and calculate average for each level.
 */
public class AverageOfLevelsInBinaryTree {

  class LevelNode {
    int level;
    TreeNode node;

    LevelNode(int level, TreeNode node) {
      this.level = level;
      this.node = node;
    }
  }

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
  public static void main(String[] args) throws Exception {}

  public List<Double> averageOfLevels(TreeNode root) {
    Queue<LevelNode> queue = new ArrayDeque<>();
    LevelNode node = new LevelNode(0, root);
    queue.offer(node);
    int curLevel = 0, count = 0;
    long sum = 0L;
    List<Double> result = new ArrayList<>();
    while (!queue.isEmpty()) {
      LevelNode first = queue.poll();
      if (first.level == curLevel) {
        sum += first.node.val;
        count++;
      } else {
        result.add((double) sum / count);
        sum = first.node.val;
        count = 1;
        curLevel++;
      }
      if (first.node.left != null) {
        queue.offer(new LevelNode(curLevel + 1, first.node.left));
      }
      if (first.node.right != null) {
        queue.offer(new LevelNode(curLevel + 1, first.node.right));
      }
    }
    result.add((double) sum / count);
    return result;
  }
}
