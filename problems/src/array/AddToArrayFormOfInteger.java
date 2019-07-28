package array;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/07/2019
 *
 * <p>For a non-negative integer X, the array-form of X is an array of its digits in left to right
 * order. For example, if X = 1231, then the array form is [1,2,3,1].
 *
 * <p>Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 *
 * <p>Example 1:
 *
 * <p>Input: A = [1,2,0,0], K = 34 Output: [1,2,3,4] Explanation: 1200 + 34 = 1234 Example 2:
 *
 * <p>Input: A = [2,7,4], K = 181 Output: [4,5,5] Explanation: 274 + 181 = 455 Example 3:
 *
 * <p>Input: A = [2,1,5], K = 806 Output: [1,0,2,1] Explanation: 215 + 806 = 1021 Example 4:
 *
 * <p>Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1 Output: [1,0,0,0,0,0,0,0,0,0,0] Explanation:
 * 9999999999 + 1 = 10000000000
 *
 * <p>Note：
 *
 * <p>1 <= A.length <= 10000 0 <= A[i] <= 9 0 <= K <= 10000 If A.length > 1, then A[0] != 0
 *
 * <p>Solution: O(N) use BigInteger to add long numbers
 */
public class AddToArrayFormOfInteger {
  public static void main(String[] args) {
    //
  }

  public List<Integer> addToArrayForm(int[] A, int K) {
    StringBuilder sb = new StringBuilder();
    for (int a : A) {
      sb.append(a);
    }
    BigInteger big = new BigInteger(sb.toString());
    BigInteger result = big.add(BigInteger.valueOf(K));
    String resultStr = result.toString();
    List<Integer> list = new ArrayList<>();
    for (char a : resultStr.toCharArray()) {
      list.add(Integer.parseInt(String.valueOf(a)));
    }
    return list;
  }
}
