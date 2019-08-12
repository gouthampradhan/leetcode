package tree;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 01/08/2019 You need to find the largest value in each row of a
 * binary tree.
 *
 * <p>Example: Input:
 *
 * <p>1 / \ 3 2 / \ \ 5 3 9
 *
 * <p>Output: [1, 3, 9] Solution: O(N) do a bfs to check largest in each row.
 */
public class FindLargestValueInEachTreeRow {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  Map<Integer, Integer> maxRow = new HashMap<>();

  class Node {
    int row;
    TreeNode val;

    Node(int row, TreeNode val) {
      this.row = row;
      this.val = val;
    }
  }

  public List<Integer> largestValues(TreeNode root) {
    if (root == null) return new ArrayList<>();
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(new Node(0, root));
    while (!queue.isEmpty()) {
      Node top = queue.poll();
      maxRow.putIfAbsent(top.row, top.val.val);
      maxRow.put(top.row, Math.max(maxRow.get(top.row), top.val.val));
      if (top.val.left != null) {
        queue.offer(new Node(top.row + 1, top.val.left));
      }
      if (top.val.right != null) {
        queue.offer(new Node(top.row + 1, top.val.right));
      }
    }
    List<Integer> answer = new ArrayList<>();
    List<Integer> keyList = new ArrayList<>(maxRow.keySet());
    keyList.sort(Integer::compareTo);
    for (int k : keyList) {
      answer.add(maxRow.get(k));
    }
    return answer;
  }

  public static void main(String[] args) {
    //
  }
}
