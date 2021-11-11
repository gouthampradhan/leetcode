package string;

/**
 * Created by gouthamvidyapradhan on 10/05/2019 Given a list of strings, you could concatenate these
 * strings together into a loop, where for each string you could choose to reverse it or not. Among
 * all the possible loops, you need to find the lexicographically biggest string after cutting the
 * loop, which will make the looped string into a regular one.
 *
 * <p>Specifically, to find the lexicographically biggest string, you need to experience two phases:
 *
 * <p>Concatenate all the strings into a loop, where you can reverse some strings or not and connect
 * them in the same order as given. Cut and make one breakpoint in any place of the loop, which will
 * make the looped string into a regular one starting from the character at the cutpoint. And your
 * job is to find the lexicographically biggest one among all the possible regular strings.
 *
 * <p>Example: Input: "abc", "xyz" Output: "zyxcba" Explanation: You can get the looped string
 * "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", where '-' represents the looped status. The
 * answer string came from the fourth looped one, where you could cut from the middle character 'a'
 * and get "zyxcba". Note: The input strings will only contain lowercase letters. The total length
 * of all the strings will not over 1,000.
 */
public class SplitConcatenatedStrings {
  public static void main(String[] args) {
    String[] A = {"abc"};
    System.out.println(new SplitConcatenatedStrings().splitLoopedString(A));
  }

  public String splitLoopedString(String[] strs) {
    String max = "";
    for (int i = 0; i < strs.length; i++) {
      String s = strs[i];
      String result = findMax(strs, (i + 1) % strs.length);

      String ans;
      for (int k = 0, l = s.length(); k < l; k++) {
        StringBuilder sb = new StringBuilder();
        String start = s.substring(k);
        String end = s.substring(0, k);
        ans = sb.append(start).append(result).append(end).toString();
        max = max.compareTo(ans) > 0 ? max : ans;
      }

      s = new StringBuilder(s).reverse().toString();
      for (int k = 0, l = s.length(); k < l; k++) {
        StringBuilder sb = new StringBuilder();
        String start = s.substring(k);
        String end = s.substring(0, k);
        ans = sb.append(start).append(result).append(end).toString();
        max = max.compareTo(ans) > 0 ? max : ans;
      }
    }
    return max;
  }

  private String findMax(String[] strs, int i) {
    int c = 1;
    StringBuilder sb = new StringBuilder();
    for (int j = i, l = strs.length; c < l; j = (j + 1) % l, c++) {
      String nextStr = strs[j];
      String reverse = new StringBuilder(nextStr).reverse().toString();
      String result = nextStr.compareTo(reverse) > 0 ? nextStr : reverse;
      sb.append(result);
    }
    return sb.toString();
  }
}
