package tree;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given a binary search tree (BST), find the lowest
 * common ancestor (LCA) of two given nodes in the BST.
 *
 * <p>According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
 * between two nodes v and w as the lowest node in T that has both v and w as descendants (where we
 * allow a node to be a descendant of itself).”
 *
 * <p>_______6______ / \ ___2__ ___8__ / \ / \ 0 _4 7 9 / \ 3 5 For example, the lowest common
 * ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node
 * can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorBST {
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {}

  private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;

    if (p.val == root.val || q.val == root.val) return root;
    else if ((p.val < root.val && q.val > root.val) || (q.val < root.val && p.val > root.val))
      return root;
    else if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
    else return lowestCommonAncestor(root.right, p, q);
  }
}
