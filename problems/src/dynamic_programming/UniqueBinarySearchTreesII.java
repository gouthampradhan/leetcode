package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 31/03/2017. Given an integer n, generate all structurally
 * unique BST's (binary search trees) that store values 1...n.
 *
 * <p>For example, Given n = 3, your program should return all 5 unique BST's shown below.
 *
 * <p>1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
 */
public class UniqueBinarySearchTreesII {
  public static class TreeNode {
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
    List<TreeNode> list = new UniqueBinarySearchTreesII().generateTrees(3);
  }

  class Pair {
    int l, r;

    Pair(int l, int r) {
      this.l = l;
      this.r = r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      Pair pair = (Pair) o;
      return l == pair.l && r == pair.r;
    }

    @Override
    public int hashCode() {
      return Objects.hash(l, r);
    }
  }

  Map<Pair, List<TreeNode>> dp;

  public List<TreeNode> generateTrees(int n) {
    dp = new HashMap<>();
    if (n == 0) return new ArrayList<>();
    return generate(new Pair(1, n));
  }

  private List<TreeNode> generate(Pair p) {
    if (dp.containsKey(p)) {
      return dp.get(p);
    } else if (p.l > p.r) return Arrays.asList(new TreeNode(-1));
    else if (p.l == p.r) return Arrays.asList(new TreeNode(p.l));
    List<TreeNode> list = new ArrayList<>();
    for (int i = p.l; i <= p.r; i++) {
      Pair left = new Pair(p.l, i - 1);
      Pair right = new Pair(i + 1, p.r);
      List<TreeNode> leftList = generate(left);
      List<TreeNode> rightList = generate(right);
      for (TreeNode lNode : leftList) {
        for (TreeNode rNode : rightList) {
          TreeNode root = new TreeNode(i);
          root.left = lNode.val == -1 ? null : lNode;
          root.right = rNode.val == -1 ? null : rNode;
          list.add(root);
        }
      }
    }
    dp.put(p, list);
    return list;
  }
}
