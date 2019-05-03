package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 08/04/2017. You are given a binary tree in which each node
 * contains an integer value.
 *
 * <p>Find the number of paths that sum to a given value.
 *
 * <p>The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 *
 * <p>The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * <p>Example:
 *
 * <p>root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 * <p>10 / \ 5 -3 / \ \ 3 2 11 / \ \ 3 -2 1
 *
 * <p>Return 3. The paths that sum to 8 are:
 *
 * <p>1. 5 -> 3 2. 5 -> 2 -> 1 3. -3 -> 11
 */
public class PathSumIII {
  /** */
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private Map<Integer, Integer> pathCount = new HashMap<>();
  private int totalCount;

  public static void main(String[] args) throws Exception {
    TreeNode node = new TreeNode(1);
    System.out.println(new PathSumIII().pathSum(node, 0));
  }

  public int pathSum(TreeNode root, int sum) {
    if (root == null) return 0;
    dfs(root, sum, 0);
    return totalCount;
  }

  private void dfs(TreeNode root, int target, int pSum) {
    if (root != null) {
      pSum += root.val;
      if (pSum == target) totalCount++;
      totalCount += pathCount.getOrDefault(pSum - target, 0);
      pathCount.put(pSum, pathCount.getOrDefault(pSum, 0) + 1);
      dfs(root.left, target, pSum);
      dfs(root.right, target, pSum);
      pathCount.put(pSum, pathCount.get(pSum) - 1);
    }
  }
}
