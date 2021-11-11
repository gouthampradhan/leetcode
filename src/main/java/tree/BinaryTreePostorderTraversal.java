package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 28/07/2018. Given a binary tree, return the postorder traversal
 * of its nodes' values.
 *
 * <p>Example:
 *
 * <p>Input: [1,null,2,3] 1 \ 2 / 3
 *
 * <p>Output: [3,2,1] Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * <p>Solution: O(N). Maintain a stack, for every node which you pop from stack add it to result
 * list, push left and right node to stack. Reverse the result list and return this as the answer.
 */
public class BinaryTreePostorderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(2);
    root.right.left = new TreeNode(3);
    List<Integer> result = new BinaryTreePostorderTraversal().postorderTraversal(root);
    result.forEach(System.out::println);
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      stack.push(root);
    }
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val);
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }
    Collections.reverse(result);
    return result;
  }
}
