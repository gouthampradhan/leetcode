package tree;

/**
 * Created by gouthamvidyapradhan on 03/10/2017.
 *
 * <p>Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * <p>What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * <p>Note:
 *
 * <p>You may only use constant extra space. For example, Given the following binary tree, 1 / \ 2 3
 * / \ \ 4 5 7 After calling your function, the tree should look like: 1 -> NULL / \ 2 -> 3 -> NULL
 * / \ \ 4-> 5 -> 7 -> NULL
 */
public class NextRightPointerII {

  public static void main(String[] args) throws Exception {
    TreeLinkNode root = new TreeLinkNode(1);
    root.left = new TreeLinkNode(2);
    root.right = new TreeLinkNode(3);
    root.right.right = new TreeLinkNode(4);
    root.right.right.left = new TreeLinkNode(5);
    root.right.right.right = new TreeLinkNode(6);
    new NextRightPointerII().connect(root);
  }

  public void connect(TreeLinkNode root) {
    TreeLinkNode prev = new TreeLinkNode(0);
    TreeLinkNode first = null;
    while (root != null) {
      if (root.left != null) {
        prev.next = root.left;
        prev = root.left;
        if (first == null) {
          first = root.left;
        }
      }
      if (root.right != null) {
        prev.next = root.right;
        prev = root.right;
        if (first == null) {
          first = root.right;
        }
      }
      root = root.next;
      if (root == null) {
        root = first;
        first = null;
        prev = new TreeLinkNode(0);
      }
    }
  }

  public static class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
      val = x;
    }
  }
}
