package math;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 09/10/2019 Given an m * n matrix M initialized with all 0's and
 * several update operations.
 *
 * <p>Operations are represented by a 2D array, and each operation is represented by an array with
 * two positive integers a and b, which means M[i][j] should be added by one for all 0 <= i < a and
 * 0 <= j < b.
 *
 * <p>You need to count and return the number of maximum integers in the matrix after performing all
 * the operations.
 *
 * <p>Example 1: Input: m = 3, n = 3 operations = [[2,2],[3,3]] Output: 4 Explanation: Initially, M
 * = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
 *
 * <p>After performing [2,2], M = [[1, 1, 0], [1, 1, 0], [0, 0, 0]]
 *
 * <p>After performing [3,3], M = [[2, 2, 1], [2, 2, 1], [1, 1, 1]]
 *
 * <p>So the maximum integer in M is 2, and there are four of it in M. So return 4. Note: The range
 * of m and n is [1,40000]. The range of a is [1,m], and the range of b is [1,n]. The range of
 * operations size won't exceed 10,000.
 *
 * <p>Solution: O(N) where N is the number of operations. For every operation, keep track of minimum
 * of each row and column and return the product of minR x minC
 */
public class RangeAdditionII {
  public static void main(String[] args) {
    //
  }

  public int maxCount(int m, int n, int[][] ops) {
    int minR = m;
    int minC = n;
    for (int[] v : ops) {
      minR = Math.min(minR, v[0]);
      minC = Math.min(minC, v[1]);
    }
    return minR * minC;
  }
}
