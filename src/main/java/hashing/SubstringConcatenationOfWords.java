package hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 02/03/2019 You are given a string, s, and a list of words,
 * words, that are all of the same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening characters.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "barfoothefoobarman", words = ["foo","bar"] Output: [0,9] Explanation: Substrings
 * starting at index 0 and 9 are "barfoor" and "foobar" respectively. The output order does not
 * matter, returning [9,0] is fine too. Example 2:
 *
 * <p>Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] Output: []
 *
 * <p>Solution: General idea is to do the following 1. Calculate the word count for the given array
 * of words and store this in a HashMap. 2. For every substring (substring of s) of length
 * (words[0].length() * words.length) split this into words of length words[0].length and calculate
 * the word frequency for the split words. If the word frequency matches the word frequency of the
 * given original word list then add the starting index of this substring into the result array.
 *
 * <p>A small optimization is to break the substring match as soon as you find out that the word
 * formed from the substring is not part of the original given word list or if the frequency of the
 * word exceeds the frequency of the original word count.
 */
public class SubstringConcatenationOfWords {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    String[] words = {"word", "good", "best", "word"};
    System.out.println(
        new SubstringConcatenationOfWords().findSubstring("wordgoodgoodgoodbestword", words));
  }

  public List<Integer> findSubstring(String s, String[] words) {
    if (words.length == 0) return new ArrayList<>();
    int wLen = words[0].length();
    int sLen = wLen * words.length;
    List<Integer> result = new ArrayList<>();
    if (sLen > s.length()) return result;
    Map<String, Integer> countMap = new HashMap<>();
    for (String w : words) {
      countMap.putIfAbsent(w, 0);
      countMap.put(w, countMap.get(w) + 1);
    }
    for (int k = 0; (s.length() - k) >= sLen; k++) {
      Map<String, Integer> subSMap = new HashMap<>();
      int i = k;
      for (int j = i + wLen; (i - k) < sLen; i = j, j += wLen) {
        String subS = s.substring(i, j);
        subSMap.putIfAbsent(subS, 0);
        subSMap.put(subS, subSMap.get(subS) + 1);
        if (!countMap.containsKey(subS) || subSMap.get(subS) > countMap.get(subS)) {
          break;
        }
      }
      if ((i - k) >= sLen) {
        result.add(k);
      }
    }
    return result;
  }
}
