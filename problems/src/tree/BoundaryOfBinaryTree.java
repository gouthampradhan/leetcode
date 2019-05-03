package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 27/03/2017. Given a binary tree, return the values of its
 * boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves,
 * and right boundary in order without duplicate nodes.
 *
 * <p>Left boundary is defined as the path from root to the left-most node. Right boundary is
 * defined as the path from root to the right-most node. If the root doesn't have left subtree or
 * right subtree, then the root itself is left boundary or right boundary. Note this definition only
 * applies to the input binary tree, and not applies to any subtrees.
 *
 * <p>The left-most node is defined as a leaf node you could reach when you always firstly travel to
 * the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf
 * node.
 *
 * <p>The right-most node is also defined by the same way with left and right exchanged.
 *
 * <p>Example 1 Input: 1 \ 2 / \ 3 4
 *
 * <p>Ouput: [1, 3, 4, 2]
 *
 * <p>Explanation: The root doesn't have left subtree, so the root itself is left boundary. The
 * leaves are node 3 and 4. The right boundary are node 1,2,4. Note the anti-clockwise direction
 * means you should output reversed right boundary. So order them in anti-clockwise without
 * duplicates and we have [1,3,4,2]. Example 2 Input: ____1_____ / \ 2 3 / \ / 4 5 6 / \ / \ 7 8 9
 * 10
 *
 * <p>Ouput: [1,2,4,7,8,9,10,6,3]
 *
 * <p>Explanation: The left boundary are node 1,2,4. (4 is the left-most node according to
 * definition) The leaves are node 4,7,8,9,10. The right boundary are node 1,3,6,10. (10 is the
 * right-most node). So order them in anti-clockwise without duplicate nodes we have
 * [1,2,4,7,8,9,10,6,3].
 */
public class BoundaryOfBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private Set<TreeNode> done = new HashSet<>();

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    /*root.right = new TreeNode(2);
    root.right.right = new TreeNode(3);
    root.right.right.right = new TreeNode(4);
    root.right.right.right.left = new TreeNode(5);
    root.right.right.right.left.right = new TreeNode(6);
    /*root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(8);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.left.left = new TreeNode(9);
    root.right.left.right = new TreeNode(10);*/
    System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(root));
  }

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    if (root == null) return new ArrayList<>();
    List<Integer> antiClockwiseCollection = new ArrayList<>();
    List<Integer> collection = new ArrayList<>();

    if (root.left != null) leftShoulder(root, collection);
    else {
      if (!done.contains(root)) {
        done.add(root);
        collection.add(root.val);
      }
    }

    antiClockwiseCollection.addAll(collection);
    collection.clear();
    leafNode(root, collection);
    antiClockwiseCollection.addAll(collection);
    collection.clear();

    if (root.right != null) rightShoulder(root, collection);
    else {
      if (!done.contains(root)) {
        done.add(root);
        collection.add(root.val);
      }
    }

    Stack<Integer> stack = new Stack<>();
    stack.addAll(collection);
    while (!stack.isEmpty()) antiClockwiseCollection.add(stack.pop());
    return new ArrayList<>(antiClockwiseCollection);
  }

  private void leftShoulder(TreeNode node, List<Integer> list) {
    if (node == null) return;
    if (!done.contains(node)) {
      list.add(node.val);
      done.add(node);
    }

    if (node.left != null) leftShoulder(node.left, list);
    else if (node.right != null) leftShoulder(node.right, list);
  }

  private void rightShoulder(TreeNode node, List<Integer> list) {
    if (node == null) return;
    if (!done.contains(node)) {
      list.add(node.val);
      done.add(node);
    }
    if (node.right != null) rightShoulder(node.right, list);
    else if (node.left != null) rightShoulder(node.left, list);
  }

  private void leafNode(TreeNode node, List<Integer> list) {
    if (node == null) return;
    if (node.left == null && node.right == null)
      if (!done.contains(node)) {
        list.add(node.val);
        done.add(node);
      }
    leafNode(node.left, list);
    leafNode(node.right, list);
  }
}
