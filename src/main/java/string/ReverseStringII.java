package string;

/**
 * Created by gouthamvidyapradhan on 30/07/2019 Given a string and an integer k, you need to reverse
 * the first k characters for every 2k characters counting from the start of the string. If there
 * are less than k characters left, reverse all of them. If there are less than 2k but greater than
 * or equal to k characters, then reverse the first k characters and left the other as original.
 * Example: Input: s = "abcdefg", k = 2 Output: "bacdfeg" Restrictions: The string consists of lower
 * English letters only. Length of the given string and k will in the range [1, 10000]
 *
 * <p>Solution O(N)
 */
public class ReverseStringII {
  public static void main(String[] args) {
    System.out.println(new ReverseStringII().reverseStr("abcdefg", 2));
  }

  public String reverseStr(String s, int k) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0, l = s.length(); i < l; i++) {
      if (i % (2 * k) == 0) {
        int count = 0;
        StringBuilder temp = new StringBuilder();
        while (count < k && i < l) {
          temp.append(s.charAt(i));
          count++;
          i++;
        }
        sb.append(temp.reverse());
      }
      if (i < l) {
        sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }
}
