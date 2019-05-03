package tree;

/**
 * Created by gouthamvidyapradhan on 07/07/2017. Given two non-empty binary trees s and t, check
 * whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s
 * is a tree consists of a node in s and all of this node's descendants. The tree s could also be
 * considered as a subtree of itself.
 *
 * <p>Example 1: Given tree s:
 *
 * <p>3 / \ 4 5 / \ 1 2 Given tree t: 4 / \ 1 2 Return true, because t has the same structure and
 * node values with a subtree of s. Example 2: Given tree s:
 *
 * <p>3 / \ 4 5 / \ 1 2 / 0 Given tree t: 4 / \ 1 2 Return false.
 */
public class SubtreeOfAnotherTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {}

  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s != null) {
      if (s.val == t.val) {
        if (equal(s, t)) return true;
        else return (isSubtree(s.left, t) || isSubtree(s.right, t));
      } else return (isSubtree(s.left, t) || isSubtree(s.right, t));
    }
    return false;
  }

  private boolean equal(TreeNode s, TreeNode t) {
    if (s == null && t == null) return true;
    else if (s == null || t == null) return false;
    else if (s.val != t.val) return false;
    else return equal(s.left, t.left) && equal(s.right, t.right);
  }
}
