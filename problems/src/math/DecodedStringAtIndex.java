package math;

/**
 * Created by gouthamvidyapradhan on 21/07/2019 An encoded string S is given. To find and write the
 * decoded string to a tape, the encoded string is read one character at a time and the following
 * steps are taken:
 *
 * <p>If the character read is a letter, that letter is written onto the tape. If the character read
 * is a digit (say d), the entire current tape is repeatedly written d-1 more times in total. Now
 * for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the
 * decoded string.
 *
 * <p>Example 1:
 *
 * <p>Input: S = "leet2code3", K = 10 Output: "o" Explanation: The decoded string is
 * "leetleetcodeleetleetcodeleetleetcode". The 10th letter in the string is "o". Example 2:
 *
 * <p>Input: S = "ha22", K = 5 Output: "h" Explanation: The decoded string is "hahahaha". The 5th
 * letter is "h". Example 3:
 *
 * <p>Input: S = "a2345678999999999999999", K = 1 Output: "a" Explanation: The decoded string is "a"
 * repeated 8301530446056247680 times. The 1st letter is "a".
 *
 * <p>Note:
 *
 * <p>2 <= S.length <= 100 S will only contain lowercase letters and digits 2 through 9. S starts
 * with a letter. 1 <= K <= 10^9 The decoded string is guaranteed to have less than 2^63 letters.
 *
 * <p>Solution: General idea is as shown below example: If S = "leet2" and K = 6 the answer is "e"
 * which is same as finding answer for K = 2. As soon as the product exceeds the total value of K as
 * in this case the product of 4 (leet) x 2 is 8 and 8 clearly exceeds 6 therefore we can reduce K
 * to 8 - 6 = 2 and start from the beginning once again. Repeat the same process until we reach the
 * answer.
 */
public class DecodedStringAtIndex {
  public static void main(String[] args) {
    System.out.println(
        new DecodedStringAtIndex().decodeAtIndex("a2345678999999999999999", 1000000000));
  }

  public String decodeAtIndex(String S, int K) {
    long product = 0;
    char lastC = S.charAt(0);
    for (int i = 0, l = S.length(); i < l; ) {
      char c = S.charAt(i);
      if (Character.isLetter(c)) {
        lastC = c;
        product++;
        i++;
        if (K == product) break;
      } else {
        long temp = (product * Integer.parseInt(String.valueOf(c)));
        if (temp == K) break;
        else {
          if (temp > K) {
            long x = (K / product);
            if ((product * x) == K) break;
            K -= (product * x);
            i = 0;
            product = 0;
          } else {
            product = temp;
            i++;
          }
        }
      }
    }
    return String.valueOf(lastC);
  }
}
