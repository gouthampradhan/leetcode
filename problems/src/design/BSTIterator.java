package design;

import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 13/08/2017. Implement an iterator over a binary search tree
 * (BST). Your iterator will be initialized with the root node of a BST.
 *
 * <p>Calling next() will return the next smallest number in the BST.
 *
 * <p>Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is
 * the height of the tree.
 *
 * <p>Solution: The below solution works in average O(1) time and worst case O(h) time using O(h)
 * memory. Use a stack to keep track of min value node.
 */
public class BSTIterator {

  private Stack<TreeNode> stack;

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
    root.left = new TreeNode(5);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(8);
    root.left.left.left = new TreeNode(1);
    root.left.right.left = new TreeNode(7);
    root.right = new TreeNode(12);
    root.right.left = new TreeNode(11);
    root.right.right = new TreeNode(15);
    BSTIterator ite = new BSTIterator(root);
    System.out.println("Hasnext: " + ite.hasNext());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("next: " + ite.next());
    System.out.println("Hasnext: " + ite.hasNext());
  }

  public BSTIterator(TreeNode root) {
    stack = new Stack<>();
    fillStack(root);
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
    if (!stack.isEmpty()) {
      TreeNode top = stack.pop();
      fillStack(top.right);
      return top.val;
    }
    return -1;
  }

  /**
   * Fill stack with min values
   *
   * @param node curr node to begin with
   */
  private void fillStack(TreeNode node) {
    TreeNode ite = node;
    while (ite != null) {
      stack.push(ite);
      ite = ite.left;
    }
  }
}
