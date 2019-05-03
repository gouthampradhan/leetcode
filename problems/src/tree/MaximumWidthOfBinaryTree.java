package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 03/05/2018. Given a binary tree, write a function to get the
 * maximum width of the given tree. The width of a tree is the maximum width among all levels. The
 * binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * <p>The width of one level is defined as the length between the end-nodes (the leftmost and right
 * most non-null nodes in the level, where the null nodes between the end-nodes are also counted
 * into the length calculation.
 *
 * <p>Example 1: Input:
 *
 * <p>1 / \ 3 2 / \ \ 5 3 9
 *
 * <p>Output: 4 Explanation: The maximum width existing in the third level with the length 4
 * (5,3,null,9). Example 2: Input:
 *
 * <p>1 / 3 / \ 5 3
 *
 * <p>Output: 2 Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3: Input:
 *
 * <p>1 / \ 3 2 / 5
 *
 * <p>Output: 2 Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4: Input:
 *
 * <p>1 / \ 3 2 / \ 5 9 / \ 6 7 Output: 8 Explanation:The maximum width existing in the fourth level
 * with the length 8 (6,null,null,null,null,null,null,7).
 *
 * <p>Solution: O(N): General idea is to give a position value to each node. On every left traversal
 * give the value curr_pos * 2 and on every right traversal give the value curr_pos * 2 + 1
 * Calculate maximum width for each level using right - left + 1
 */
public class MaximumWidthOfBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private Map<Integer, List<Integer>> map;

  public static void main(String[] args) {}

  public int widthOfBinaryTree(TreeNode root) {
    map = new HashMap<>();
    preorder(root, 1, 1);
    int max = 0;
    for (int k : map.keySet()) {
      List<Integer> list = map.get(k);
      if (list.size() == 1) {
        max = Math.max(max, 1);
      } else {
        max = Math.max(max, list.get(list.size() - 1) - list.get(0) + 1);
      }
    }
    return max;
  }

  private void preorder(TreeNode node, int level, int pos) {
    if (node != null) {
      preorder(node.left, level + 1, pos * 2);
      map.putIfAbsent(level, new ArrayList<>());
      map.get(level).add(pos);
      preorder(node.right, level + 1, pos * 2 + 1);
    }
  }
}
