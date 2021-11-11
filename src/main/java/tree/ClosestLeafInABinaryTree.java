package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 02/05/2018. Given a binary tree where every node has a unique
 * value, and a target key k, find the value of the nearest leaf node to target k in the tree.
 *
 * <p>Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach
 * any leaf of the tree. Also, a node is called a leaf if it has no children.
 *
 * <p>In the following examples, the input tree is represented in flattened form row by row. The
 * actual root tree given will be a TreeNode object.
 *
 * <p>Example 1:
 *
 * <p>Input: root = [1, 3, 2], k = 1 Diagram of binary tree: 1 / \ 3 2
 *
 * <p>Output: 2 (or 3)
 *
 * <p>Explanation: Either 2 or 3 is the nearest leaf node to the target of 1. Example 2:
 *
 * <p>Input: root = [1], k = 1 Output: 1
 *
 * <p>Explanation: The nearest leaf node is the root node itself. Example 3:
 *
 * <p>Input: root = [1,2,3,4,null,null,null,5,null,6], k = 2 Diagram of binary tree: 1 / \ 2 3 / 4 /
 * 5 / 6
 *
 * <p>Output: 3 Explanation: The leaf node with value 3 (and not the leaf node with value 6) is
 * nearest to the node with value 2. Note: root represents a binary tree with at least 1 node and at
 * most 1000 nodes. Every node has a unique node.val in range [1, 1000]. There exists some node in
 * the given binary tree for which node.val == k.
 *
 * <p>Solution: O(N): Maintain a hashmap of distances from each node in the first iteration. In the
 * second iteration, find the key value node and then calculate distance from each node during
 * backtrack.
 */
public class ClosestLeafInABinaryTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private static class Pair {
    int n, d;

    Pair(int n, int d) {
      this.n = n;
      this.d = d;
    }
  }

  private Map<Integer, Pair> map;
  private Pair minNode;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.left.left = new TreeNode(5);
    root.left.left.left.left = new TreeNode(6);
    // root.right = new TreeNode(3);
    System.out.println(new ClosestLeafInABinaryTree().findClosestLeaf(root, 2));
  }

  public int findClosestLeaf(TreeNode root, int k) {
    map = new HashMap<>();
    minNode = new Pair(-1, Integer.MAX_VALUE);
    findDistanceToLeaf(root);
    findMin(root, k);
    return minNode.n;
  }

  private Pair findDistanceToLeaf(TreeNode node) {
    if (node != null) {
      if (node.left == null && node.right == null) {
        map.put(node.val, new Pair(node.val, 0));
        return new Pair(node.val, 1);
      } else {
        Pair left = findDistanceToLeaf(node.left);
        Pair right = findDistanceToLeaf(node.right);
        if (left.d < right.d) {
          map.put(node.val, left);
          return new Pair(left.n, left.d + 1);
        } else {
          map.put(node.val, right);
          return new Pair(right.n, right.d + 1);
        }
      }
    }
    return new Pair(-1, Integer.MAX_VALUE);
  }

  private int findMin(TreeNode node, int k) {
    if (node != null) {
      if (node.val == k) {
        if (map.get(node.val).d < minNode.d) {
          minNode = map.get(node.val);
        }
        return 1;
      } else {
        int left = findMin(node.left, k);
        int right = findMin(node.right, k);
        if (left != -1) {
          if ((left + map.get(node.val).d) < minNode.d) {
            minNode = new Pair(map.get(node.val).n, (left + map.get(node.val).d));
          }
          return left + 1;
        } else if (right != -1) {
          if ((right + map.get(node.val).d) < minNode.d) {
            minNode = new Pair(map.get(node.val).n, (right + map.get(node.val).d));
          }
          return right + 1;
        }
      }
    }
    return -1;
  }
}
