package two_pointers;
/**
 * Created by gouthamvidyapradhan on 30/08/2019 Given a string s, return the last substring of s in
 * lexicographical order.
 *
 * <p>Example 1:
 *
 * <p>Input: "abab" Output: "bab" Explanation: The substrings are ["a", "ab", "aba", "abab", "b",
 * "ba", "bab"]. The lexicographically maximum substring is "bab". Example 2:
 *
 * <p>Input: "leetcode" Output: "tcode"
 *
 * <p>Note:
 *
 * <p>1 <= s.length <= 4 * 10^5 s contains only lowercase English letters.
 *
 * <p>Solution O(N) General idea is as below. Fix the index 0 as the answer initially and start
 * iterating the string character by character, if a char is encountered with is greater than the
 * current answer then mark this as the answer, if it is same as the current answer then this new
 * char can be a potential candidate for a answer hence mark this as a candidate and start comparing
 * all the further characters of candidate char and all further chars of current answer if any point
 * the char further down the candidate is greater than the char further down the current answer then
 * mark the new candidate as the answer.
 */
public class LastSubstringInLexicographicalOrder {
  public static void main(String[] args) {
    System.out.println(new LastSubstringInLexicographicalOrder().lastSubstring("babcbd"));
  }

  public String lastSubstring(String s) {
    int currAns = 0;
    int candidate = -1;
    int prevIndex = 1;
    for (int i = 1, l = s.length(); i < l; i++) {
      if (candidate != -1) {
        if (s.charAt(i) == s.charAt(prevIndex)) {
          prevIndex++;
        } else if (s.charAt(i) > s.charAt(prevIndex)) {
          if (s.charAt(i) > s.charAt(candidate)) {
            currAns = i;
            candidate = -1;
            prevIndex = currAns + 1;
          } else if (s.charAt(i) == s.charAt(candidate)) {
            currAns = candidate;
            candidate = i;
            prevIndex = currAns + 1;
          } else {
            currAns = candidate;
            candidate = -1;
            prevIndex = currAns + 1;
          }
        } else {
          candidate = -1;
          prevIndex = currAns + 1;
        }
      } else {
        if (s.charAt(i) > s.charAt(currAns)) {
          currAns = i;
          candidate = -1;
          prevIndex = currAns + 1;
        } else if (s.charAt(i) == s.charAt(currAns)) {
          candidate = i;
        }
      }
    }
    return s.substring(currAns);
  }
}
