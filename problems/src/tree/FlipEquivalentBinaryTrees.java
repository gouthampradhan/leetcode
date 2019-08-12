package tree;

/**
 * Created by gouthamvidyapradhan on 06/08/2019 For a binary tree T, we can define a flip operation
 * as follows: choose any node, and swap the left and right child subtrees.
 *
 * <p>A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y
 * after some number of flip operations.
 *
 * <p>Write a function that determines whether two binary trees are flip equivalent. The trees are
 * given by root nodes root1 and root2.
 *
 * <p>Example 1:
 *
 * <p>Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 =
 * [1,3,2,null,6,4,5,null,null,null,null,8,7] Output: true Explanation: We flipped at nodes with
 * values 1, 3, and 5. Flipped Trees Diagram
 *
 * <p>Note:
 *
 * <p>Each tree will have at most 100 nodes. Each value in each tree will be a unique integer in the
 * range [0, 99]. Solution O(N ^ 2) Since the node values are unique general idea is to find the
 * node on right tree for every node on the left tree and check if the values need to be swapped, if
 * yes then swap the node's left and right child in the left tree. After this operation is complete
 * check if both the trees are equal
 */
public class FlipEquivalentBinaryTrees {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    TreeNode node = new TreeNode(1);
    node.left = new TreeNode(2);
    node.left.left = new TreeNode(4);
    node.left.right = new TreeNode(5);
    node.left.right.left = new TreeNode(7);
    node.left.right.right = new TreeNode(8);
    node.right = new TreeNode(3);
    node.right.left = new TreeNode(6);

    TreeNode node1 = new TreeNode(1);
    node1.left = new TreeNode(3);
    node1.left.right = new TreeNode(6);
    node1.right = new TreeNode(2);
    node1.right.left = new TreeNode(4);
    node1.right.right = new TreeNode(5);
    node1.right.right.left = new TreeNode(8);
    node1.right.right.right = new TreeNode(7);
    System.out.println(new FlipEquivalentBinaryTrees().flipEquiv(node, node1));
  }

  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    flip(root1, root2);
    return checkIfBothAreSame(root1, root2);
  }

  private boolean checkIfBothAreSame(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) return true;
    else if (root1 == null) return false;
    else if (root2 == null) return false;
    else {
      if (root1.val != root2.val) return false;
      if (!checkIfBothAreSame(root1.left, root2.left)) return false;
      return checkIfBothAreSame(root1.right, root2.right);
    }
  }

  private void flip(TreeNode root1, TreeNode root2) {
    if (root1 != null) {
      TreeNode result = find(root2, root1.val);
      boolean valid = true;
      if (result != null) {
        if (root1.left == null) {
          if (result.right != null) {
            valid = false;
          }
        }
        if (root1.right == null) {
          if (result.left != null) {
            valid = false;
          }
        }
        if (root1.left != null) {
          if (result.right == null) {
            valid = false;
          } else {
            if (root1.left.val != result.right.val) {
              valid = false;
            }
          }
        }
        if (root1.right != null) {
          if (result.left == null) {
            valid = false;
          } else {
            if (root1.right.val != result.left.val) {
              valid = false;
            }
          }
        }
        if (valid) {
          TreeNode temp = result.left;
          result.left = result.right;
          result.right = temp;
        }
      }
      flip(root1.left, root2);
      flip(root1.right, root2);
    }
  }

  private TreeNode find(TreeNode node, int value) {
    if (node != null) {
      if (node.val == value) return node;
      TreeNode left = find(node.left, value);
      if (left != null) return left;
      TreeNode right = find(node.right, value);
      if (right != null) return right;
    }
    return null;
  }
}
