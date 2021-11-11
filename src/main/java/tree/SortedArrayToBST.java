package tree;

/**
 * Created by gouthamvidyapradhan on 25/02/2017. Given an array where elements are sorted in
 * ascending order, convert it to a height balanced BST.
 */
public class SortedArrayToBST {
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
  public static void main(String[] args) throws Exception {
    int[] A = {1, 2, 3, 4, 5, 6};
    new SortedArrayToBST().sortedArrayToBST(A);
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) return null;

    TreeNode root = new SortedArrayToBST().build(0, nums.length - 1, nums);
    preorder(root);
    return root;
  }

  private void preorder(TreeNode node) {
    if (node != null) {
      preorder(node.left);
      System.out.println(node.val);
      preorder(node.right);
    }
  }

  private TreeNode build(int s, int e, int[] nums) {
    if (s > e) return null;

    int m = (e - s) / 2;
    int node = nums[s + m];
    TreeNode root = new TreeNode(node);
    if (s == e) return root;

    root.left = build(s, s + m - 1, nums);
    root.right = build(s + m + 1, e, nums);

    return root;
  }
}
