package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/10/2019 Given a binary array data, return the minimum number
 * of swaps required to group all 1’s present in the array together in any place in the array.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,0,1,0,1] Output: 1 Explanation: There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap. [0,1,1,1,0] using 2 swaps. [0,0,1,1,1] using 1 swap. The minimum is 1.
 * Example 2:
 *
 * <p>Input: [0,0,0,1,0] Output: 0 Explanation: Since there is only one 1 in the array, no swaps
 * needed. Example 3:
 *
 * <p>Input: [1,0,1,0,1,0,0,1,1,0,1] Output: 3 Explanation: One possible solution that uses 3 swaps
 * is [0,0,0,0,0,1,1,1,1,1,1]. Solution: O(N) All the 1s to be grouped together would mean that all
 * 1s should occupy a small window in a array, this window could be in any part of the array - a
 * window with minimum number of 0s is the minimum number of swap required.
 */
public class MinimumSwapsToGroupAll1Together {
  public static void main(String[] args) {
    //
  }

  public int minSwaps(int[] data) {
    int one = 0;
    int zero = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] == 1) {
        one++;
      } else zero++;
    }
    if (one == 0) return 0;
    int window = one;
    one = 0;
    zero = 0;
    int i = 0, j = window - 1;
    for (int k = i; k <= j; k++) {
      if (data[k] == 1) {
        one++;
      } else zero++;
    }
    i++;
    j++;
    int min = zero;
    for (; j < data.length; i++, j++) {
      if (data[j] == 0) {
        zero++;
      } else one++;

      if (data[i - 1] == 0) {
        zero--;
      } else one--;
      min = Math.min(min, zero);
    }
    return min;
  }
}
