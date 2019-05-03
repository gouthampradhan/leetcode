package hashing;

/**
 * Created by gouthamvidyapradhan on 10/03/2017. Given two strings s and t, write a function to
 * determine if t is an anagram of s.
 *
 * <p>For example, s = "anagram", t = "nagaram", return true. s = "rat", t = "car", return false.
 *
 * <p>Note: You may assume the string contains only lowercase alphabets.
 *
 * <p>Follow up: What if the inputs contain unicode characters? How would you adapt your solution to
 * such case?
 */
public class ValidAnagram {
  private int[] S = new int[256];
  private int[] T = new int[256];

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new ValidAnagram().isAnagram("anagram", "nagaram"));
  }

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    for (int i = 0, l = s.length(); i < l; i++) {
      S[s.charAt(i)]++;
    }

    for (int i = 0, l = t.length(); i < l; i++) {
      T[t.charAt(i)]++;
    }

    for (int i = 0; i < 256; i++) {
      if (S[i] != T[i]) return false;
    }

    return true;
  }
}
