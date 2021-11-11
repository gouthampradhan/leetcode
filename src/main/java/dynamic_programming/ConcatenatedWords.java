package dynamic_programming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 12/06/2017. Accepted Given a list of words (without
 * duplicates), please write a program that returns all concatenated words in the given list of
 * words.
 *
 * <p>A concatenated word is defined as a string that is comprised entirely of at least two shorter
 * words in the given array.
 *
 * <p>Example: Input:
 * ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * <p>Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * <p>Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; "dogcatsdog" can
 * be concatenated by "dog", "cats" and "dog"; "ratcatdogcat" can be concatenated by "rat", "cat",
 * "dog" and "cat".
 *
 * <p>Note: The number of elements of the given array will not exceed 10,000 The length sum of
 * elements in the given array will not exceed 600,000. All the input string will only include lower
 * case letters. The returned elements order does not matter.
 */
public class ConcatenatedWords {
  public static void main(String[] args) throws Exception {
    String[] words = {""};
    System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(words));
  }

  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    Set<String> dictionary = new HashSet<>();
    for (String w : words) dictionary.add(w);
    List<String> result = new ArrayList<>();
    for (String w : words) {
      if (!w.isEmpty() && concatenatedWordsPossible(w, dictionary)) result.add(w);
    }
    return result;
  }

  private boolean concatenatedWordsPossible(String word, Set<String> dictionary) {
    boolean[] D = new boolean[word.length() + 1];
    D[word.length()] = true;
    dictionary.remove(word); // remove current word from dictionary temporarily
    for (int i = word.length() - 1; i >= 0; i--) {
      for (int j = i, l = word.length(); j < l; j++) {
        String subStr = word.substring(i, j + 1);
        if (dictionary.contains(subStr) && D[j + 1]) {
          D[i] = true;
          break;
        }
      }
    }
    dictionary.add(word); // restore deleted word
    return D[0];
  }
}
