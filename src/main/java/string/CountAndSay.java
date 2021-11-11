package string;

/**
 * Created by gouthamvidyapradhan on 20/01/2018.
 *
 * <p>The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * <p>1. 1 2. 11 3. 21 4. 1211 5. 111221 1 is read off as "one 1" or 11. 11 is read off as "two 1s"
 * or 21. 21 is read off as "one 2, then one 1" or 1211. Given an integer n, generate the nth term
 * of the count-and-say sequence.
 *
 * <p>Note: Each term of the sequence of integers will be represented as a string.
 *
 * <p>Example 1:
 *
 * <p>Input: 1 Output: "1" Example 2:
 *
 * <p>Input: 4 Output: "1211"
 */
public class CountAndSay {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new CountAndSay().countAndSay(4));
  }

  public String countAndSay(int n) {
    String result = "1";
    for (int i = 1; i < n; i++) {
      int count = 1;
      char num = result.charAt(0);
      StringBuilder temp = new StringBuilder();
      for (int j = 1, l = result.length(); j < l; j++) {
        if (result.charAt(j) == num) {
          count++;
        } else {
          temp = temp.append(String.valueOf(count)).append(String.valueOf(num));
          num = result.charAt(j);
          count = 1;
        }
      }
      temp = temp.append(String.valueOf(count)).append(String.valueOf(num));
      result = temp.toString();
    }
    return result;
  }
}
