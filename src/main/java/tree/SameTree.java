package tree;

/**
 * Created by gouthamvidyapradhan on 23/01/2018. Given two binary trees, write a function to check
 * if they are the same or not.
 *
 * <p>Two binary trees are considered the same if they are structurally identical and the nodes have
 * the same value.
 *
 * <p>Example 1:
 *
 * <p>Input: 1 1 / \ / \ 2 3 2 3
 *
 * <p>[1,2,3], [1,2,3]
 *
 * <p>Output: true Example 2:
 *
 * <p>Input: 1 1 / \ 2 2
 *
 * <p>[1,2], [1,null,2]
 *
 * <p>Output: false Example 3:
 *
 * <p>Input: 1 1 / \ / \ 2 1 1 2
 *
 * <p>[1,2,1], [1,1,2]
 *
 * <p>Output: false
 *
 * <p>Solution: Do a pre-order traversal of both the trees in parallel and compare each node
 */
public class SameTree {

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

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if ((p == null && q != null) || (p != null && q == null)) return false;
    if (p == null && q == null) return true;
    else {
      boolean status = isSameTree(p.left, q.left);
      if (!status || p.val != q.val) {
        return false;
      }
      return isSameTree(p.right, q.right);
    }
  }
}
