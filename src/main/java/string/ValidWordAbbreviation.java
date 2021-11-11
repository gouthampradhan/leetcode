package string;
/**
 * Created by gouthamvidyapradhan on 20/03/2019 Given a non-empty string s and an abbreviation abbr,
 * return whether the string matches with the given abbreviation.
 *
 * <p>A string such as "word" contains only the following valid abbreviations:
 *
 * <p>["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2",
 * "2r1", "3d", "w3", "4"] Notice that only the above abbreviations are valid abbreviations of the
 * string "word". Any other string is not a valid abbreviation of "word".
 *
 * <p>Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and
 * digits.
 *
 * <p>Example 1: Given s = "internationalization", abbr = "i12iz4n":
 *
 * <p>Return true. Example 2: Given s = "apple", abbr = "a2e":
 *
 * <p>Return false.
 */
public class ValidWordAbbreviation {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new ValidWordAbbreviation().validWordAbbreviation("abbreviation", "a10n"));
  }

  public boolean validWordAbbreviation(String word, String abbr) {
    if (abbr.length() > word.length()) return false;
    StringBuilder num = new StringBuilder();
    int j = 0;
    for (int i = 0; i < abbr.length() && j < word.length(); i++) {
      char curr = abbr.charAt(i);
      if (curr == '0' && num.toString().isEmpty()) return false;
      if (curr >= '0' && curr <= '9') {
        num.append(curr);
      } else {
        if (num.toString().isEmpty()) {
          if (word.charAt(j) != abbr.charAt(i)) {
            return false;
          }
          j++;
        } else {
          int next = Integer.parseInt(num.toString());
          j += next;
          if (word.length() <= j) {
            return false;
          }
          if (word.charAt(j) != abbr.charAt(i)) {
            return false;
          }
          num = new StringBuilder();
          j++;
        }
      }
    }
    if (!num.toString().isEmpty()) {
      int next = Integer.parseInt(num.toString());
      j += next;
      if (j > word.length() || j < word.length()) {
        return false;
      }
    }
    return true;
  }
}
