package tree;

/**
 * Created by gouthamvidyapradhan on 01/05/2018. Given a Binary Search Tree (BST) with root node
 * root, and a target value V, split the tree into two subtrees where one subtree has nodes that are
 * all smaller or equal to the target value, while the other subtree has all nodes that are greater
 * than the target value. It's not necessarily the case that the tree contains a node with value V.
 *
 * <p>Additionally, most of the structure of the original tree should remain. Formally, for any
 * child C with parent P in the original tree, if they are both in the same subtree after the split,
 * then node C should still have the parent P.
 *
 * <p>You should output the root TreeNode of both subtrees after splitting, in any order.
 *
 * <p>Example 1:
 *
 * <p>Input: root = [4,2,6,1,3,5,7], V = 2 Output: [[2,1],[4,3,6,null,null,5,7]] Explanation: Note
 * that root, output[0], and output[1] are TreeNode objects, not arrays.
 *
 * <p>The given tree [4,2,6,1,3,5,7] is represented by the following diagram:
 *
 * <p>4 / \ 2 6 / \ / \ 1 3 5 7
 *
 * <p>while the diagrams for the outputs are:
 *
 * <p>4 / \ 3 6 and 2 / \ / 5 7 1 Note:
 *
 * <p>The size of the BST will not exceed 50. The BST is always valid and each node's value is
 * different.
 *
 * <p>Solution: O(N) if a current node is <= to key then the current node and its child nodes form
 * the left sub-tree. Split the right node further recursively
 */
public class SplitBST {

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
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right = new TreeNode(6);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(7);
    root.right.right.right = new TreeNode(9);
    TreeNode[] result = new SplitBST().splitBST(root, 3);
  }

  public TreeNode[] splitBST(TreeNode root, int V) {
    if (root == null) {
      return new TreeNode[] {null, null};
    } else {
      TreeNode[] result = new TreeNode[2];
      if (root.val <= V) {
        result[0] = root;
        TreeNode[] right = splitBST(root.right, V);
        root.right = right[0];
        result[1] = right[1];
        return result;
      } else {
        TreeNode[] left = splitBST(root.left, V);
        root.left = left[1];
        result[0] = left[0];
        result[1] = root;
        return result;
      }
    }
  }
}
