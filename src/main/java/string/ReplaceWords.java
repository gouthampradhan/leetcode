package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by gouthamvidyapradhan on 04/04/2019 In English, we have a concept called root, which can
 * be followed by some other words to form another longer word - let's call this word successor. For
 * example, the root an, followed by other, which can form another word another.
 *
 * <p>Now, given a dictionary consisting of many roots and a sentence. You need to replace all the
 * successor in the sentence with the root forming it. If a successor has many roots can form it,
 * replace it with the root with the shortest length.
 *
 * <p>You need to output the sentence after the replacement.
 *
 * <p>Example 1:
 *
 * <p>Input: dict = ["cat", "bat", "rat"] sentence = "the cattle was rattled by the battery" Output:
 * "the cat was rat by the bat"
 *
 * <p>Note:
 *
 * <p>The input will only have lower-case letters. 1 <= dict words number <= 1000 1 <= sentence
 * words number <= 1000 1 <= root length <= 100 1 <= sentence words length <= 1000
 *
 * <p>Solution: O(w + S) where w is the max length of each word in the dictionary and S is the
 * length of the string. Add all the words in the dictionary to a trie and evaluate each word in the
 * string to check if it matches any path in the trie. Terminate the search as soon as a leaf node
 * in the trie has been reached.
 */
public class ReplaceWords {
  class Trie {
    private Map<Character, Trie> map;

    /** Initialize your data structure here. */
    public Trie() {
      map = new HashMap<>();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
      if (word != null) {
        add(0, word, word.length());
      }
    }

    public String find(String s) {
      return search(this, s, 0, new StringBuilder());
    }

    private void add(int i, String word, int length) {
      if (i < length) {
        char c = word.charAt(i);
        Trie subTrie = map.get(c);
        if (subTrie == null) {
          subTrie = new Trie();
          map.put(c, subTrie);
        }
        subTrie.add(i + 1, word, length);
      } else map.put(null, new Trie()); // use null to indicate end of string
    }

    private String search(Trie curr, String s, int i, StringBuilder sb) {
      if (s.length() == i) return sb.toString();
      else {
        Trie subTrie = curr.map.get(s.charAt(i));
        if (subTrie == null) {
          return curr.map.containsKey(null) ? sb.toString() : "";
        } else {
          sb.append(s.charAt(i));
          if (subTrie.map.containsKey(null)) return sb.toString();
          return search(subTrie, s, i + 1, sb);
        }
      }
    }
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    List<String> words = Arrays.asList("a", "aa", "aaa");
    String sentence = "aa aa";
    System.out.println(new ReplaceWords().replaceWords(words, sentence));
  }

  public String replaceWords(List<String> dict, String sentence) {
    Trie root = new Trie();
    dict.forEach(root::insert);
    String[] words = sentence.split(" ");
    StringBuilder result = new StringBuilder();
    Stream.of(words)
        .map(
            w -> {
              String s = root.find(w);
              return s.isEmpty() ? w.concat(" ") : s.concat(" ");
            })
        .forEach(result::append);
    return result.toString().trim();
  }
}
