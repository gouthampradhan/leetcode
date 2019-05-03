package string;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given a string, find the first non-repeating
 * character in it and return it's index. If it doesn't exist, return -1.
 *
 * <p>Examples:
 *
 * <p>s = "leetcode" return 0.
 *
 * <p>s = "loveleetcode", return 2. Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {
  int[] CHAR = new int[256];

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new FirstUniqueCharacterInAString().firstUniqChar("loveleetcode"));
  }

  public int firstUniqChar(String s) {
    if (s == null || s.isEmpty()) return -1;

    for (int i = 0, l = s.length(); i < l; i++) CHAR[s.charAt(i)]++;

    for (int i = 0, l = s.length(); i < l; i++) {
      if (CHAR[s.charAt(i)] == 1) return i;
    }

    return -1;
  }
}
