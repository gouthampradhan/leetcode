package array;

/**
 * Created by gouthamvidyapradhan on 10/12/2017. Given a non-negative integer, you could swap two
 * digits at most once to get the maximum valued number. Return the maximum valued number you could
 * get.
 *
 * <p>Example 1: Input: 2736 Output: 7236 Explanation: Swap the number 2 and the number 7. Example
 * 2: Input: 9973 Output: 9973 Explanation: No swap. Note: The given number is in the range [0, 108]
 *
 * <p>Solution O(n): Create a array of digit index. Iterate through the digits starting from left
 * and in each iteration check if there is any digit which is greater than the current digit and
 * appearing after the current index, if found then swap and return the new integer.
 */
public class MaximumSwap {

  public static void main(String[] args) throws Exception {
    System.out.println(new MaximumSwap().maximumSwap(2736));
  }

  public int maximumSwap(int num) {
    int[] D = new int[10];
    char[] A = String.valueOf(num).toCharArray();
    for (int i = 0; i < A.length; i++) {
      D[A[i] - '0'] = i;
    }

    boolean found = false;

    for (int i = 0; i < A.length; i++) {
      int digit = A[i] - '0';
      for (int j = 9; j > digit; j--) {
        if (D[j] > i) {
          char temp = A[i];
          A[i] = (char) (j + '0');
          A[D[j]] = temp;
          found = true;
          break;
        }
      }
      if (found) break;
    }

    return Integer.parseInt(String.valueOf(A));
  }
}
