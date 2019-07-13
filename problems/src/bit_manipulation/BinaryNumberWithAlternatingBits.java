package bit_manipulation;
/**
 * Created by gouthamvidyapradhan on 28/05/2019\ Given a positive integer, check whether it has
 * alternating bits: namely, if two adjacent bits will always have different values.
 *
 * <p>Example 1: Input: 5 Output: True Explanation: The binary representation of 5 is: 101 Example
 * 2: Input: 7 Output: False Explanation: The binary representation of 7 is: 111. Example 3: Input:
 * 11 Output: False Explanation: The binary representation of 11 is: 1011. Example 4: Input: 10
 * Output: True Explanation: The binary representation of 10 is: 1010.
 */
public class BinaryNumberWithAlternatingBits {
  public static void main(String[] args) {
    System.out.println(new BinaryNumberWithAlternatingBits().hasAlternatingBits(18));
  }

  public boolean hasAlternatingBits(int n) {
    int curr = n & 1;
    int pos = 0;
    for (int i = 0; i < 32; i++) {
      if ((n & (1 << i)) > 0) {
        pos = i;
      }
    }

    for (int i = 1; i <= pos; i++) {
      int temp = (1 << i) & n;
      if ((temp > 0 && curr > 0) || (temp == 0 && curr == 0)) return false;
      curr = temp;
    }
    return true;
  }
}
