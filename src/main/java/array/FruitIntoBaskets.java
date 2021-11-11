package array;

import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 02/03/2019 In a row of trees, the i-th tree produces fruit with
 * type tree[i].
 *
 * <p>You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * <p>Add one piece of fruit from this tree to your baskets. If you cannot, stop. Move to the next
 * tree to the right of the current tree. If there is no tree to the right, stop. Note that you do
 * not have any choice after the initial choice of starting tree: you must perform step 1, then step
 * 2, then back to step 1, then step 2, and so on until you stop.
 *
 * <p>You have two baskets, and each basket can carry any quantity of fruit, but you want each
 * basket to only carry one type of fruit each.
 *
 * <p>What is the total amount of fruit you can collect with this procedure?
 *
 * <p>Example 1:
 *
 * <p>Input: [1,2,1] Output: 3 Explanation: We can collect [1,2,1]. Example 2:
 *
 * <p>Input: [0,1,2,2] Output: 3 Explanation: We can collect [1,2,2]. If we started at the first
 * tree, we would only collect [0, 1]. Example 3:
 *
 * <p>Input: [1,2,3,2,2] Output: 4 Explanation: We can collect [2,3,2,2]. If we started at the first
 * tree, we would only collect [1, 2]. Example 4:
 *
 * <p>Input: [3,3,3,1,2,1,1,2,3,3,4] Output: 5 Explanation: We can collect [1,2,1,1,2]. If we
 * started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 * <p>Note:
 *
 * <p>1 <= tree.length <= 40000 0 <= tree[i] < tree.length
 */
public class FruitIntoBaskets {

  private int count = 0;
  private int max = 0;
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] trees = {1, 0, 3, 4, 3};
    System.out.println(new FruitIntoBaskets().totalFruit(trees));
  }

  public int totalFruit(int[] tree) {
    int t1 = -1, t2 = -1;
    Stack<Integer> stack = new Stack<>();
    for (int i : tree) {
      if (i == t1 || i == t2) {
        countAndMax(stack, i);
      } else {
        if (t1 == -1) {
          t1 = i;
          countAndMax(stack, i);
        } else if (t2 == -1) {
          t2 = i;
          countAndMax(stack, i);
        } else {
          Stack<Integer> temp = new Stack<>();
          count = 0;
          t1 = stack.pop();
          countAndMax(temp, t1);
          while (!stack.isEmpty() && stack.peek() == t1) {
            countAndMax(temp, stack.pop());
          }
          t2 = i;
          stack = temp;
          countAndMax(stack, i);
        }
      }
    }
    return max;
  }

  private void countAndMax(Stack<Integer> stack, int i) {
    count++;
    stack.push(i);
    max = Math.max(max, count);
  }
}
