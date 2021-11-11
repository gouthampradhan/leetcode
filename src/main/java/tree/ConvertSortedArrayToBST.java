package tree;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given an array where elements are sorted in
 * ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedArrayToBST {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) return null;
    return build(0, nums.length - 1, nums);
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
