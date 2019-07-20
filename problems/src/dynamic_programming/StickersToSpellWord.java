package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 14/07/2019 We are given N different types of stickers. Each
 * sticker has a lowercase English word on it.
 *
 * <p>You would like to spell out the given target string by cutting individual letters from your
 * collection of stickers and rearranging them.
 *
 * <p>You can use each sticker more than once if you want, and you have infinite quantities of each
 * sticker.
 *
 * <p>What is the minimum number of stickers that you need to spell out the target? If the task is
 * impossible, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input:
 *
 * <p>["with", "example", "science"], "thehat" Output:
 *
 * <p>3 Explanation:
 *
 * <p>We can use 2 "with" stickers, and 1 "example" sticker. After cutting and rearrange the letters
 * of those stickers, we can form the target "thehat". Also, this is the minimum number of stickers
 * necessary to form the target string. Example 2:
 *
 * <p>Input:
 *
 * <p>["notice", "possible"], "basicbasic" Output:
 *
 * <p>-1 Explanation:
 *
 * <p>We can't form the target "basicbasic" from cutting letters from the given stickers. Note:
 *
 * <p>stickers has length in the range [1, 50]. stickers consists of lowercase English words
 * (without apostrophes). target has length in the range [1, 15], and consists of lowercase English
 * letters. In all test cases, all words were chosen randomly from the 1000 most common US English
 * words, and the target was chosen as a concatenation of two random words. The time limit may be
 * more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms
 * on average.
 *
 * <p>Solution: O(2 ^ T x T x S) where T is the length of target and S is length of sticker array.
 * Each state is a combination of characters selected in the target sticker plus the total count of
 * stickers used. Cache the minimum count in each state and explore all the different possible
 * states.
 */
public class StickersToSpellWord {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    String[] stickers = {"bright", "neighbor", "capital"};

    System.out.println(new StickersToSpellWord().minStickers(stickers, "originalchair"));
  }

  private int destination = 0;
  private int min = Integer.MAX_VALUE;
  private int[][] DP;

  public int minStickers(String[] stickers, String target) {
    for (int i = 0; i < target.length(); i++) {
      destination |= (1 << i);
    }
    DP = new int[destination][target.length() + 1];
    int answer = dp(stickers, target, 0, 0);
    return answer == Integer.MAX_VALUE ? -1 : answer;
  }

  private int dp(String[] stickers, String target, int curr, int count) {
    if (curr == destination) {
      return count;
    } else {
      if (count > min) return Integer.MAX_VALUE;
      if (DP[curr][count] != 0) return DP[curr][count];
      DP[curr][count] = Integer.MAX_VALUE;
      for (String s : stickers) {
        int temp = 0;
        char[] arr = s.toCharArray();
        for (int i = 0, l = target.length(); i < l; i++) {
          if ((curr & (1 << i)) == 0) {
            char targetChar = target.charAt(i);
            for (int j = 0; j < arr.length; j++) {
              if (arr[j] == targetChar) {
                arr[j] = '0';
                temp |= (1 << i);
                break;
              }
            }
          }
        }
        if (temp > 0) {
          int child = (curr | temp);
          int retValue = dp(stickers, target, child, count + 1);
          DP[curr][count] = Math.min(DP[curr][count], retValue);
          min = Math.min(min, DP[curr][count]);
        }
      }
      return DP[curr][count];
    }
  }
}
