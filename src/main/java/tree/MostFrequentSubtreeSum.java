package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 27/03/2017. Given the root of a tree, you are asked to find the
 * most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself). So what is the most
 * frequent subtree sum value? If there is a tie, return all the values with the highest frequency
 * in any order.
 *
 * <p>Examples 1 Input:
 *
 * <p>5 / \ 2 -3 return [2, -3, 4], since all the values happen only once, return all of them in any
 * order. Examples 2 Input:
 *
 * <p>5 / \ 2 -5 return [2], since 2 happens twice, however -5 only occur once. Note: You may assume
 * the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private Map<Integer, List<Integer>> fList = new HashMap<>();
  private Map<Integer, Integer> fCount = new HashMap<>();

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    MostFrequentSubtreeSum mfs = new MostFrequentSubtreeSum();
    TreeNode node = new TreeNode(5);
    // node.left = new TreeNode(2);
    // node.right = new TreeNode(-5);
    int[] result = mfs.findFrequentTreeSum(node);
  }

  public int[] findFrequentTreeSum(TreeNode root) {
    int[] resArr = new int[0];
    if (root == null) return resArr;
    postOrder(root);
    for (Map.Entry<Integer, Integer> entry : fCount.entrySet()) {
      int frequency = entry.getValue();
      List<Integer> list = fList.get(frequency);
      if (list == null) list = new ArrayList<>();
      list.add(entry.getKey());
      fList.put(frequency, list);
    }
    int max = Integer.MIN_VALUE;
    List<Integer> result;
    for (int key : fList.keySet()) max = Math.max(max, key);
    result = fList.get(max);
    resArr = new int[result.size()];
    for (int i = 0, l = resArr.length; i < l; i++) resArr[i] = result.get(i);
    return resArr;
  }

  private int postOrder(TreeNode root) {
    if (root == null) return 0;
    int sum = postOrder(root.left) + postOrder(root.right) + root.val;
    Integer fSum = fCount.get(sum);
    if (fSum == null) fCount.put(sum, 1);
    else fCount.put(sum, fSum + 1);
    return sum;
  }
}
