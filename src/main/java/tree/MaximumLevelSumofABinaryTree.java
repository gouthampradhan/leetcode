package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 28/08/2019 Given the root of a binary tree, the level of its
 * root is 1, the level of its children is 2, and so on.
 *
 * <p>Return the smallest level X such that the sum of all the values of nodes at level X is
 * maximal.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,7,0,7,-8,null,null] Output: 2 Explanation: Level 1 sum = 1. Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1. So we return the level with the maximum sum which is level 2.
 *
 * <p>Note:
 *
 * <p>The number of nodes in the given tree is between 1 and 10^4. -10^5 <= node.val <= 10^5
 *
 * <p>Solution: Keep a hashmap key-value pairs where key is the level and value is the sum of values
 * at that level, do a inorder search in the tree and sum up the values at each level.
 */
public class MaximumLevelSumofABinaryTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  Map<Integer, Integer> levelMap;

  public static void main(String[] args) {
    //
  }

  public int maxLevelSum(TreeNode root) {
    levelMap = new HashMap<>();
    inorder(root, 1);
    int max = Integer.MIN_VALUE;
    int ans = 0;
    for (int k : levelMap.keySet()) {
      if (levelMap.get(k) > max) {
        max = levelMap.get(k);
        ans = k;
      }
    }
    return ans;
  }

  private void inorder(TreeNode root, int level) {
    if (root != null) {
      levelMap.putIfAbsent(level, 0);
      levelMap.put(level, levelMap.get(level) + root.val);
      inorder(root.left, level + 1);
      inorder(root.right, level + 1);
    }
  }
}
