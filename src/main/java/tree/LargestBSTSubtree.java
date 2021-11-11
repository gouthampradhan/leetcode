package tree;

/**
 * Created by gouthamvidyapradhan on 08/05/2017.
 *
 * <p>Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where
 * largest means subtree with largest number of nodes in it.
 *
 * <p>Note: A subtree must include all of its descendants. Here's an example: 10 / \ 5 15 / \ \ 1 8
 * 7 The Largest BST Subtree in this case is the highlighted one (5-1-8). The return value is the
 * subtree's size, which is 3.
 *
 * <p>Follow up: Can you figure out ways to solve it with O(n) time complexity?
 *
 * <p>Solution: The below solution works with O(n). Validate the BST property from the leaf node and
 * increment the count, as soon as a violation of BST property is found terminate the count.
 */
public class LargestBSTSubtree {
  /** Range class */
  private class Range {
    int min, max, count;

    Range(int min, int max, int count) {
      this.min = min;
      this.max = max;
      this.count = count;
    }
  }

  /** TreeNode */
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  /** Count */
  private static int count = 0;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(9);
    root.left.left = new TreeNode(8);
    System.out.println(new LargestBSTSubtree().largestBSTSubtree(root));
  }

  /**
   * Largest subtree count
   *
   * @param root root node
   * @return count
   */
  public int largestBSTSubtree(TreeNode root) {
    getCount(root);
    return count;
  }

  /**
   * Get count
   *
   * @param node root node
   * @return Range
   */
  private Range getCount(TreeNode node) {
    if (node == null) return null;
    Range left = getCount(node.left);
    Range right = getCount(node.right);
    if (left == null && right == null) {
      count = Math.max(count, 1);
      return new Range(node.val, node.val, 1);
    } else if (left == null) {
      if (node.val < right.min
          && right.count != -1) // check for -1 ensures that there is no violation of BST property
      return countMaxAndBuildNewRange(right.count + 1, node.val, right.max);
    } else if (right == null) {
      if (node.val > left.max && left.count != -1)
        return countMaxAndBuildNewRange(left.count + 1, left.min, node.val);
    } else if (node.val > left.max && node.val < right.min && right.count != -1 && left.count != -1)
      return countMaxAndBuildNewRange(left.count + right.count + 1, left.min, right.max);
    return new Range(0, 0, -1); // violation of BST property
  }

  /**
   * Record max and build new range
   *
   * @param sum total sum
   * @param min min
   * @param max max
   * @return new Range
   */
  private Range countMaxAndBuildNewRange(int sum, int min, int max) {
    count = Math.max(count, sum);
    return new Range(min, max, sum);
  }
}
