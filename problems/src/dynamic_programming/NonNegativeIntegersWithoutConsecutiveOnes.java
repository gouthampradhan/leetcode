package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 20/10/2019 Given a positive integer n, find the number of
 * non-negative integers less than or equal to n, whose binary representations do NOT contain
 * consecutive ones.
 *
 * <p>Example 1: Input: 5 Output: 5 Explanation: Here are the non-negative integers <= 5 with their
 * corresponding binary representations: 0 : 0 1 : 1 2 : 10 3 : 11 4 : 100 5 : 101 Among them, only
 * integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. Note: 1 <= n
 * <= 109
 *
 * <p>Solution: O(1) (30 ^ 2) For each bit we can set either '0' or '1' starting from index i to 0,
 * if we set 0 then the next bit i + 1 can be either set to 0 or 1 but, if we set it to 1 then the
 * next bit at position i + 1 can only be 0 because two consecutive 1s are invalid. This gives us a
 * general dp formula DP[0][i] = DP[0][i + 1] + DP[1][i + 1] for bit 0 and similarly DP[1][i] =
 * DP[0][i + 1].
 *
 * <p>Lets consider an example with number = 4 (binary representation is 100). Now, the above
 * approach would calculate all possible number ranging from 0 (000) -> 7 (111), lets say the count
 * is x. But, we actually want to restrict until only 100. Therefore we have to calculate all valid
 * states starting from 100 until 111 and lets say this is y. Now, the answer would be x - y + 1.
 * Adding 1 here because the state 100 (which is a valid state) would be counted twice in x and also
 * in y. For cases where a binary representation of given N is like 1100 we have to find a max
 * possible valid state which occurs just before 1100 which in this case is 1010 and now calculate y
 * starting from 1010 to 1111.
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {
  public static void main(String[] args) {
    System.out.println(new NonNegativeIntegersWithoutConsecutiveOnes().findIntegers(1000000000));
  }

  public int findIntegers(int num) {
    int msbIndex = 0;
    for (int i = 0; i < 31; i++) {
      if (((1 << i) & num) > 0) {
        msbIndex = i;
      }
    }
    int[][] DP1 = new int[2][msbIndex + 1]; // count from 0 until all possible value.
    int[][] DP2 = new int[2][msbIndex + 2]; // count from given N until max possible value
    for (int i = msbIndex; i >= 0; i--) {
      if (i == msbIndex) {
        DP1[0][msbIndex] = 1;
        DP1[1][msbIndex] = 1;
      } else {
        DP1[0][i] = DP1[0][i + 1] + DP1[1][i + 1];
        DP1[1][i] = DP1[0][i + 1];
      }
    }
    // find valid state just before given num
    int[] bits = new int[msbIndex + 1];
    boolean bitFlipped = false;
    for (int i = msbIndex, j = 0; i >= 0; i--, j++) {
      if (j == 0) {
        bits[j] = 1;
      } else {
        if (bitFlipped) {
          bits[j] = bits[j - 1] == 0 ? 1 : 0;
        } else {
          if (((1 << i) & num) > 0) {
            if (bits[j - 1] > 0) {
              bits[j] = 0;
              bitFlipped = true;
            } else bits[j] = 1;
          }
        }
      }
    }
    DP2[0][msbIndex + 1] = 1;
    for (int i = bits.length - 1; i >= 0; i--) {
      if (bits[i] == 0) {
        DP2[0][i] = DP2[0][i + 1] + DP2[1][i + 1];
        // if the curr bit is 0 then, we can make this 1 provided the previous bit was not 1
        if (bits[i - 1] == 0) {
          DP2[1][i] = (i == bits.length - 1) ? 1 : DP1[0][i + 1];
        }
      } else {
        DP2[1][i] = DP2[0][i + 1];
      }
    }
    return (DP1[0][0] + DP1[1][0]) - (DP2[0][0] + DP2[1][0]) + 1;
  }
}
