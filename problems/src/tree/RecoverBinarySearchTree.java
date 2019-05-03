package tree;

/**
 * Created by gouthamvidyapradhan on 28/07/2018. Two elements of a binary search tree (BST) are
 * swapped by mistake.
 *
 * <p>Recover the tree without changing its structure.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,3,null,null,2]
 *
 * <p>1 / 3 \ 2
 *
 * <p>Output: [3,1,null,null,2]
 *
 * <p>3 / 1 \ 2 Example 2:
 *
 * <p>Input: [3,1,4,null,null,2]
 *
 * <p>3 / \ 1 4 / 2
 *
 * <p>Output: [2,1,4,null,null,3]
 *
 * <p>2 / \ 1 4 / 3 Follow up:
 *
 * <p>A solution using O(n) space is pretty straight forward. Could you devise a constant space
 * solution?
 *
 * <p>Solution: O(N) time and O(1) space. Step 1, perform a inorder traversal and mark left and
 * right pointer at the node where violation of BST occurs. Step2, find the next node which is
 * smaller or equal to right pointer node. Finally swap left and right node values.
 */
public class RecoverBinarySearchTree {
  private boolean violation;
  private TreeNode left, right, prev;

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(1);
    root.left.left = new TreeNode(3);
    root.left.left.left = new TreeNode(5);
    new RecoverBinarySearchTree().recoverTree(root);
  }

  public void recoverTree(TreeNode root) {
    inorder(root);
    if (left != null && right != null) {
      int temp = left.val;
      left.val = right.val;
      right.val = temp;
    }
  }

  private void inorder(TreeNode root) {
    if (root != null) {
      inorder(root.left);
      if (prev != null) {
        if (!violation) {
          if (prev.val > root.val) {
            violation = true;
            left = prev;
            right = root;
          } else {
            prev = root;
          }
        } else {
          if (root.val <= right.val) {
            right = root;
          }
        }
      } else {
        prev = root;
      }
      inorder(root.right);
    }
  }
}
