package tree;

/**
 * Created by gouthamvidyapradhan on 19/08/2017. Given an integer array with no duplicates. A
 * maximum tree building on this array is defined as follow:
 *
 * <p>The root is the maximum number in the array. The left subtree is the maximum tree constructed
 * from left part subarray divided by the maximum number. The right subtree is the maximum tree
 * constructed from right part subarray divided by the maximum number. Construct the maximum tree by
 * the given array and output the root node of this tree.
 *
 * <p>Example 1: Input: [3,2,1,6,0,5] Output: return the tree root node representing the following
 * tree:
 *
 * <p>6 / \ 3 5 \ / 2 0 \ 1
 *
 * <p>Note: The size of the given array will be in the range [1,1000].
 */
public class MaximumBinaryTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private int[][] max;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {3, 2, 1, 6, 0, 5};
    TreeNode root = new MaximumBinaryTree().constructMaximumBinaryTree(nums);
    System.out.println(root.val); // print root
  }

  public TreeNode constructMaximumBinaryTree(int[] nums) {

    max = new int[nums.length][nums.length];

    // pre-fill with initial values
    for (int i = 0; i < nums.length; i++) {
      max[i][i] = i;
    }

    // pre-calculate max for range index
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        max[i][j] = nums[max[i][j - 1]] > nums[j] ? max[i][j - 1] : j;
      }
    }

    return build(0, nums.length - 1, nums);
  }

  private TreeNode build(int s, int e, int[] nums) {
    if (s <= e) {
      int val = nums[max[s][e]];
      TreeNode n = new TreeNode(val);
      n.left = build(s, max[s][e] - 1, nums);
      n.right = build(max[s][e] + 1, e, nums);
      return n;
    }
    return null;
  }
}
