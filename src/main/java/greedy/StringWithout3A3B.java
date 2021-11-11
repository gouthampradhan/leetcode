package greedy;

/**
 * Created by gouthamvidyapradhan on 07/05/2019
 *
 * <p>Given two integers A and B, return any string S such that:
 *
 * <p>S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters; The
 * substring 'aaa' does not occur in S; The substring 'bbb' does not occur in S.
 *
 * <p>Example 1:
 *
 * <p>Input: A = 1, B = 2 Output: "abb" Explanation: "abb", "bab" and "bba" are all correct answers.
 * Example 2:
 *
 * <p>Input: A = 4, B = 1 Output: "aabaa"
 *
 * <p>Solution O(N) idea is to greedily try to put two a's if number of a's is > b and similarly for
 * b
 */
public class StringWithout3A3B {

  public static void main(String[] args) {
    System.out.println(new StringWithout3A3B().strWithout3a3b(4, 1));
  }

  public String strWithout3a3b(int A, int B) {
    StringBuilder sb = new StringBuilder();
    while (A > 0 || B > 0) {
      if (A > B && A > 1 && B > 0) {
        sb.append("a").append("a");
        sb.append("b");
        A -= 2;
        B -= 1;
      } else if (B > A && B > 1 && A > 0) {
        sb.append("b").append("b");
        sb.append("a");
        B -= 2;
        A -= 1;
      } else {
        if (A > B && A > 1) {
          sb.append("a");
          sb.append("a");
          A -= 2;
        } else if (A > B && A > 0) {
          sb.append("a");
          A -= 1;
        } else if (B > A && B > 1) {
          sb.append("b");
          sb.append("b");
          B -= 2;
        } else {
          sb.append("b");
          B -= 1;
        }
      }
    }
    return sb.toString();
  }
}
