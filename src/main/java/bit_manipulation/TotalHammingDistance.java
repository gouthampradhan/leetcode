package bit_manipulation;

/**
 * Created by gouthamvidyapradhan on 01/12/2017. The Hamming distance between two integers is the
 * number of positions at which the corresponding bits are different.
 *
 * <p>Now your job is to find the total Hamming distance between all pairs of the given numbers.
 *
 * <p>Example: Input: 4, 14, 2
 *
 * <p>Output: 6
 *
 * <p>Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just showing
 * the four bits relevant in this case). So the answer will be: HammingDistance(4, 14) +
 * HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6. Note: Elements of the given array
 * are in the range of 0 to 10^9 Length of the array will not exceed 10^4.
 *
 * <p>Solution: O(N * 32): Count the number of set bits in each of 32 bit positions and then take
 * the sum of product of number of set bits x number of un-set bits
 */
public class TotalHammingDistance {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1000000000, 4, 14, 2};
    System.out.println(new TotalHammingDistance().totalHammingDistance(A));
  }

  public int totalHammingDistance(int[] nums) {
    int sum = 0;
    for (int i = 0; i < 32; i++) {
      int numOfOnes = 0;
      int p = (1 << i);
      for (int num : nums) {
        if ((num & p) > 0) {
          numOfOnes++;
        }
      }
      sum += ((nums.length - numOfOnes) * numOfOnes);
    }
    return sum;
  }
}
