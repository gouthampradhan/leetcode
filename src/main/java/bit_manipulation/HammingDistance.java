package bit_manipulation;

/**
 * Created by gouthamvidyapradhan on 16/12/2017. The Hamming distance between two integers is the
 * number of positions at which the corresponding bits are different.
 *
 * <p>Given two integers x and y, calculate the Hamming distance.
 *
 * <p>Note: 0 ≤ x, y < 231.
 *
 * <p>Example:
 *
 * <p>Input: x = 1, y = 4
 *
 * <p>Output: 2
 *
 * <p>Explanation: 1 (0 0 0 1) 4 (0 1 0 0) ↑ ↑
 *
 * <p>The above arrows point to positions where the corresponding bits are different.
 *
 * <p>Solution O(1): XOR (x, y) and count the number of bits set
 */
public class HammingDistance {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public int hammingDistance(int x, int y) {
    int z = (x ^ y);
    int count = 0;
    for (int i = 0; i < 31; i++) {
      if ((z & (1 << i)) > 0) {
        count++;
      }
    }
    return count;
  }
}
