package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradhang on 3/8/2017. Given two integers n and k, return all possible combinations of
 * k numbers out of 1 ... n.
 *
 * <p>For example, If n = 4 and k = 2, a solution is:
 *
 * <p>[ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 */
public class Combinations {

  public static void main(String[] args) throws Exception {
    List<List<Integer>> result = new Combinations().combine(3, 3);
  }

  public List<List<Integer>> combine(int n, int k) {
    int[] subArr = new int[k];
    List<List<Integer>> result = new ArrayList<>();
    getNext(0, 0, n, k, subArr, result);
    return result;
  }

  private void getNext(int i, int count, int n, int k, int[] subArr, List<List<Integer>> result) {
    if (k == 0) {
      List<Integer> subList = new ArrayList<>();
      for (int a : subArr) subList.add(a);
      result.add(subList);
    } else {
      for (int j = i + 1; j <= n; j++) {
        subArr[count] = j;
        getNext(j, count + 1, n, k - 1, subArr, result);
      }
    }
  }
}
