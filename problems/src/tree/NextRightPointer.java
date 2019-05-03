package tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by gouthamvidyapradhan on 07/07/2017.
 *
 * <p>Given a binary tree
 *
 * <p>struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; } Populate
 * each next pointer to point to its next right node. If there is no next right node, the next
 * pointer should be set to NULL.
 *
 * <p>Initially, all next pointers are set to NULL.
 *
 * <p>Note:
 *
 * <p>You may only use constant extra space. You may assume that it is a perfect binary tree (ie,
 * all leaves are at the same level, and every parent has two children). For example, Given the
 * following perfect binary tree, 1 / \ 2 3 / \ / \ 4 5 6 7 After calling your function, the tree
 * should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ / \ 4->5->6->7 -> NULL
 *
 * <p>Solution: Perform a level order traversal using BFS, keep track of prev node at each level.
 * Link the prev node to current node if both the nodes are in the same level.
 */
public class NextRightPointer {
  private class LevelNode {
    int level;
    TreeLinkNode node;

    LevelNode(TreeLinkNode node, int level) {
      this.node = node;
      this.level = level;
    }
  }

  public static class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    TreeLinkNode node = new TreeLinkNode(2);
    node.left = new TreeLinkNode(1);
    node.right = new TreeLinkNode(3);
    new NextRightPointer().connect(node);
    System.out.println(node.next);
    System.out.println(node.left.next.val);
    System.out.println(node.right.next);
  }

  public void connect(TreeLinkNode root) {
    Queue<LevelNode> queue = new ArrayDeque<>();
    LevelNode zero = new LevelNode(root, 0);
    queue.offer(zero);
    LevelNode prev = null;
    while (!queue.isEmpty()) {
      LevelNode levelNode = queue.poll();
      if (levelNode.node == null) break;
      TreeLinkNode curr = levelNode.node;
      if (prev != null) {
        if (prev.level == levelNode.level) {
          prev.node.next = levelNode.node;
        }
      }
      prev = levelNode;
      queue.offer(new LevelNode(curr.left, levelNode.level + 1));
      queue.offer(new LevelNode(curr.right, levelNode.level + 1));
    }
  }
}
