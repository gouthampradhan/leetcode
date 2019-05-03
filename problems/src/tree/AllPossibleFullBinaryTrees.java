package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 30/04/2019 A full binary tree is a binary tree where each node
 * has exactly 0 or 2 children.
 *
 * <p>Return a list of all possible full binary trees with N nodes. Each element of the answer is
 * the root node of one possible tree.
 *
 * <p>Each node of each tree in the answer must have node.val = 0.
 *
 * <p>You may return the final list of trees in any order.
 *
 * <p>Example 1:
 *
 * <p>Input: 7 Output:
 * [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],
 * [0,0,0,0,0,null,null,null, null,0,0],[0,0,0,0,0,null,null,0,0]]
 *
 * <p>Note:
 *
 * <p>1 <= N <= 20
 *
 * <p>Solution: O(2 ^ N) A full binary tree can only be generated for a case with odd number of
 * nodes. Start with a base case - when only one node is given a full binary tree can be formed with
 * only one node. Cache this value in a hashmap.
 *
 * <p>Now, iteratively generate a list of possible trees for 3, 5, 7 . . . N - ((N + 1) % 2) and
 * cache this in a hashmap.
 */
public class AllPossibleFullBinaryTrees {

  public static class TreeNode {
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
   */
  public static void main(String[] args) {
    List<TreeNode> result = new AllPossibleFullBinaryTrees().allPossibleFBT(7);
    System.out.println(result.size());
  }

  public List<TreeNode> allPossibleFBT(int N) {
    if (N % 2 == 0) return new ArrayList<>();
    Map<Integer, List<TreeNode>> map = new HashMap<>();
    map.put(1, Arrays.asList(new TreeNode(0)));
    for (int i = 3; i <= N; i += 2) {
      List<TreeNode> list = new ArrayList<>();
      for (int j = 1; j < i - 1; j += 2) {
        int l = j, r = i - 1 - j;
        List<TreeNode> leftList = map.get(l);
        List<TreeNode> rightList = map.get(r);
        for (TreeNode leftNode : leftList) {
          for (TreeNode rightNode : rightList) {
            TreeNode root = new TreeNode(0);
            root.left = leftNode;
            root.right = rightNode;
            list.add(root);
          }
        }
      }
      map.put(i, list);
    }
    return map.get(N);
  }
}
