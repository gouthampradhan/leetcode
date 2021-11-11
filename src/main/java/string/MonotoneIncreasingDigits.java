package string;

/**
 * Created by gouthamvidyapradhan on 01/05/2018. Given a non-negative integer N, find the largest
 * number that is less than or equal to N with monotone increasing digits.
 *
 * <p>(Recall that an integer has monotone increasing digits if and only if each pair of adjacent
 * digits x and y satisfy x <= y.)
 *
 * <p>Example 1: Input: N = 10 Output: 9 Example 2: Input: N = 1234 Output: 1234 Example 3: Input: N
 * = 332 Output: 299 Note: N is an integer in the range [0, 10^9].
 *
 * <p>Solution O(N): Convert to string for easier manipulation. Start from N.length - 1 and iterate
 * through until index 0.Mark the index where the violation occurs. Decrement the value of the
 * latest index where the violation occurs and append '9' to rest of the trailing digits. Convert
 * the string to integer before returning.
 */
public class MonotoneIncreasingDigits {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new MonotoneIncreasingDigits().monotoneIncreasingDigits(100001));
  }

  public int monotoneIncreasingDigits(int N) {
    String s = String.valueOf(N);
    if (s.length() == 1) return N;
    int p = -1;
    int prev = N % 10;
    for (int i = s.length() - 2; i >= 0; i--) {
      int curr = Integer.parseInt(String.valueOf(s.charAt(i)));
      if (curr > prev) {
        p = i;
        prev = curr - 1;
      } else {
        prev = curr;
      }
    }
    if (p == -1) return N;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (i == p) {
        int pV = Integer.parseInt(String.valueOf(s.charAt(i)));
        result.append(pV - 1);
        break;
      }
      result.append(String.valueOf(s.charAt(i)));
    }
    for (int i = p + 1; i < s.length(); i++) {
      result.append("9");
    }
    return Integer.parseInt(result.toString());
  }
}
