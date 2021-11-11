package string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gouthamvidyapradhan on 12/10/2019 You want to form a target string of lowercase
 * letters.
 *
 * <p>At the beginning, your sequence is target.length '?' marks. You also have a stamp of lowercase
 * letters.
 *
 * <p>On each turn, you may place the stamp over the sequence, and replace every letter in the
 * sequence with the corresponding letter from the stamp. You can make up to 10 * target.length
 * turns.
 *
 * <p>For example, if the initial sequence is "?????", and your stamp is "abc", then you may make
 * "abc??", "?abc?", "??abc" in the first turn. (Note that the stamp must be fully contained in the
 * boundaries of the sequence in order to stamp.)
 *
 * <p>If the sequence is possible to stamp, then return an array of the index of the left-most
 * letter being stamped at each turn. If the sequence is not possible to stamp, return an empty
 * array.
 *
 * <p>For example, if the sequence is "ababc", and the stamp is "abc", then we could return the
 * answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".
 *
 * <p>Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10
 * * target.length moves. Any answers specifying more than this number of moves will not be
 * accepted.
 *
 * <p>Example 1:
 *
 * <p>Input: stamp = "abc", target = "ababc" Output: [0,2] ([1,0,2] would also be accepted as an
 * answer, as well as some other answers.) Example 2:
 *
 * <p>Input: stamp = "abca", target = "aabcaca" Output: [3,0,1]
 *
 * <p>Note:
 *
 * <p>1 <= stamp.length <= target.length <= 1000 stamp and target only contain lowercase letters.
 *
 * <p>Solution: O(N ^ 2) General idea is to work the answer in the reverse order. For example if the
 * target string is 'aaabb' and stamp is 'aabb' then first stamp would be at 0 resulting in aabb?
 * and the next stamp would be at 1 resulting in 'aaabb' Consider each window of size = stamp.size
 * from index 0 (call this window at index i). For every window keep track of matched indices and
 * unmatched indices. Also, additionally Maintain a general-matched-index set containing all the
 * indices that are already matched. For every window, if all the characters at each index of stamp
 * sequence and target sequence match then add the window index to the answer also additionally
 * revisit every widow index that have been previously visited in starting from i - 1 to 0 and
 * verify if any window contains all the matched indices this can be checked by verifying the
 * unmatched set at each widow to general-matched-index - if any of the window satisfy this
 * condition then add this window index to the answer. Return the answer in the reverse order.
 */
public class StampingTheSequence {
  public static void main(String[] args) {
    int[] ans = new StampingTheSequence().movesToStamp("abca", "aaaaaaaaabcaaca");
    for (int a : ans) System.out.print(a + " ");
  }

  private class Window {
    Set<Integer> matched, unmatched;

    Window(Set<Integer> matched, Set<Integer> unmatched) {
      this.matched = matched;
      this.unmatched = unmatched;
    }
  }

  public int[] movesToStamp(String stamp, String target) {
    List<Window> windows = new ArrayList<>();
    Set<Integer> matchedTarget = new HashSet<>();
    Stack<Integer> answer = new Stack<>();
    for (int i = 0; i <= target.length() - stamp.length(); i++) {
      Window current = new Window(new HashSet<>(), new HashSet<>());
      for (int j = i, s = 0; j < (i + stamp.length()); j++, s++) {
        if (stamp.charAt(s) == target.charAt(j) || matchedTarget.contains(j)) {
          current.matched.add(j);
        } else current.unmatched.add(j);
      }
      if (current.unmatched.isEmpty()) {
        answer.push(i);
        matchedTarget.addAll(current.matched);
        for (int k = windows.size() - 1; k >= 0; k--) {
          if (!windows.get(k).unmatched.isEmpty()) {
            Set<Integer> newUnmatched =
                windows
                    .get(k)
                    .unmatched
                    .stream()
                    .filter(u -> !matchedTarget.contains(u))
                    .collect(Collectors.toSet());
            windows.get(k).unmatched = newUnmatched;
            if (newUnmatched.isEmpty()) {
              Set<Integer> newMatched =
                  windows
                      .get(k)
                      .matched
                      .stream()
                      .filter(m -> !matchedTarget.contains(m))
                      .collect(Collectors.toSet());
              if (!newMatched.isEmpty()) {
                answer.push(k);
                matchedTarget.addAll(newMatched);
              }
            }
          } else break;
        }
      }
      windows.add(current);
    }
    if (matchedTarget.size() == target.length()) {
      int[] finalAns = new int[answer.size()];
      int i = 0;
      while (!answer.isEmpty()) {
        finalAns[i++] = answer.pop();
      }
      return finalAns;
    }
    return new int[] {};
  }
}
