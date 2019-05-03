package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/04/2019 We are given a binary tree (with root node root), a
 * target node, and an integer value K.
 *
 * <p>Return a list of the values of all nodes that have a distance K from the target node. The
 * answer can be returned in any order.
 *
 * <p>Example 1:
 *
 * <p>Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * <p>Output: [7,4,1]
 *
 * <p>3 / \ 5 1 / \ / \ 6 2 0 8 / \ 7 4 Explanation: The nodes that are a distance 2 from the target
 * node (with value 5) have values 7, 4, and 1.
 *
 * <p>Note that the inputs "root" and "target" are actually TreeNodes. The descriptions of the
 * inputs above are just serializations of these objects.
 *
 * <p>Note:
 *
 * <p>The given tree is non-empty. Each node in the tree has unique values 0 <= node.val <= 500. The
 * target node is a node in the tree. 0 <= K <= 1000.
 *
 * <p>Solution: O(N) The general intuition is as below. All the nodes from which a target nodes can
 * be reached (including the target node) can have child nodes at a distance of K from target node.
 * All the nodes from which a target node cannot be reached definitely cannot have a child node at a
 * distance of K from target node. Do a dfs from root to find the target node. As soon as a target
 * node is found, all the nodes in the recursion stack are the ancestors of target node i.e the
 * target node can be reached from each of these nodes. Now do another dfs from each of the nodes
 * starting from target node and all its ancestors up to the root to find nodes at a distance of (K
 * - dk) where dk is the distance to the target node. Keep track of visited nodes in order not to
 * revisit the same node once again.
 */
public class AllNodesDistanceKInBinaryTree {

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
  public static void main(String[] args) {}

  class Node {
    int v, d;

    Node(int v, int d) {
      this.d = d;
      this.v = v;
    }
  }

  private Set<Integer> done;

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    done = new HashSet<>();
    List<Integer> result = new ArrayList<>();
    findAndProcess(root, result, K, target);
    return result;
  }

  private int findAndProcess(TreeNode node, List<Integer> result, int K, TreeNode target) {
    if (node != null) {
      if (target == node) {
        fillResult(node, result, 0, K);
        return 1;
      } else {
        int status = findAndProcess(node.left, result, K, target);
        if (status > 0) {
          if (K - status >= 0) {
            fillResult(node, result, 0, K - status);
          }
          return status + 1;
        } else {
          status = findAndProcess(node.right, result, K, target);
          if (status > 0) {
            if (K - status >= 0) {
              fillResult(node, result, 0, K - status);
            }
            return status + 1;
          }
        }
        return -1;
      }
    } else return -1;
  }

  private void fillResult(TreeNode node, List<Integer> result, int d, int K) {
    done.add(node.val);
    if (d == K) {
      result.add(node.val);
      return;
    }
    if (node.left != null && !done.contains(node.left.val)) {
      fillResult(node.left, result, d + 1, K);
    }
    if (node.right != null && !done.contains(node.right.val)) {
      fillResult(node.right, result, d + 1, K);
    }
  }
}
