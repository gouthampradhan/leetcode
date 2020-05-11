package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 26/04/2020
 *
 * <p>A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *
 * <p>You have a keyboard layout as shown above in the XY plane, where each English uppercase letter
 * is located at some coordinate, for example, the letter A is located at coordinate (0,0), the
 * letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the
 * letter Z is located at coordinate (4,1).
 *
 * <p>Given the string word, return the minimum total distance to type such string using only two
 * fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 *
 * <p>Note that the initial positions of your two fingers are considered free so don't count towards
 * your total distance, also your two fingers do not have to start at the first letter or the first
 * two letters.
 *
 * <p>Example 1:
 *
 * <p>Input: word = "CAKE" Output: 3 Explanation: Using two fingers, one optimal way to type "CAKE"
 * is: Finger 1 on letter 'C' -> cost = 0 Finger 1 on letter 'A' -> cost = Distance from letter 'C'
 * to letter 'A' = 2 Finger 2 on letter 'K' -> cost = 0 Finger 2 on letter 'E' -> cost = Distance
 * from letter 'K' to letter 'E' = 1 Total distance = 3 Example 2:
 *
 * <p>Input: word = "HAPPY" Output: 6 Explanation: Using two fingers, one optimal way to type
 * "HAPPY" is: Finger 1 on letter 'H' -> cost = 0 Finger 1 on letter 'A' -> cost = Distance from
 * letter 'H' to letter 'A' = 2 Finger 2 on letter 'P' -> cost = 0 Finger 2 on letter 'P' -> cost =
 * Distance from letter 'P' to letter 'P' = 0 Finger 1 on letter 'Y' -> cost = Distance from letter
 * 'A' to letter 'Y' = 4 Total distance = 6 Example 3:
 *
 * <p>Input: word = "NEW" Output: 3 Example 4:
 *
 * <p>Input: word = "YEAR" Output: 7
 *
 * <p>Constraints:
 *
 * <p>2 <= word.length <= 300 Each word[i] is an English uppercase letter.
 */
public class MinimumDistanceToTypeAWordUsingTwoFingers {
  int[][] DP;
  int[][] dist;

  public static void main(String[] args) {
    System.out.println(new MinimumDistanceToTypeAWordUsingTwoFingers().minimumDistance("YEAR"));
  }

  public int minimumDistance(String word) {
    DP = new int[word.length()][word.length()];
    dist = new int[26][26];
    char[][] chars = {
      {'A', 'B', 'C', 'D', 'E', 'F'},
      {'G', 'H', 'I', 'J', 'K', 'L'},
      {'M', 'N', 'O', 'P', 'Q', 'R'},
      {'S', 'T', 'U', 'V', 'W', 'X'},
      {'Y', 'Z', ' ', ' ', ' ', ' '}
    };
    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[0].length; j++) {
        char from = chars[i][j];
        if (from == ' ') break;
        for (int k = 0; k < chars.length; k++) {
          for (int l = 0; l < chars[0].length; l++) {
            char to = chars[k][l];
            if (to == ' ') break;
            dist[from - 'A'][to - 'A'] = Math.abs(k - i) + Math.abs(l - j);
          }
        }
      }
    }
    for (int i = 0; i < word.length(); i++) {
      for (int j = 0; j < word.length(); j++) {
        DP[i][j] = -1;
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < word.length(); i++) {
      min = Math.min(min, dp(0, i, word));
    }
    return min;
  }

  private int dp(int p, int i, String S) {
    if (DP[p][i] != -1) return DP[p][i];
    else {
      int left = Integer.MAX_VALUE, right;
      int min = Integer.MAX_VALUE;
      if (p + 1 == S.length()) return 0;
      if (p + 1 != i) {
        left = dp(p + 1, i, S) + dist[S.charAt(p) - 'A'][S.charAt(p + 1) - 'A'];
      }
      right = dp(p + 1, p, S) + dist[S.charAt(i) - 'A'][S.charAt(p + 1) - 'A'];
      DP[p][i] = Math.min(min, Math.min(left, right));
      return DP[p][i];
    }
  }
}
