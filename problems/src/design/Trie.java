package design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Goutham Vidya Pradhan on 7/3/2017. Implement a trie with insert, search, and
 * startsWith methods.
 *
 * <p>Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {

  private Map<Character, Trie> map;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Trie trie = new Trie();
    trie.insert("boxing");
    trie.insert("box");
    System.out.println(trie.search("boxing"));
    System.out.println(trie.startsWith("box"));
    System.out.println(trie.search("box"));
  }

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

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    if (word != null) {
      return search(0, word, word.length());
    }
    return false;
  }

  private boolean search(int i, String word, int length) {
    if (i < length) {
      char c = word.charAt(i);
      Trie subTrie = map.get(c);
      if (subTrie == null) return false;
      return subTrie.search(i + 1, word, length);
    }
    return map.containsKey(null);
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    if (prefix != null) {
      return startsWith(0, prefix, prefix.length());
    }
    return false;
  }

  private boolean startsWith(int i, String prefix, int length) {
    if (i < length) {
      char c = prefix.charAt(i);
      Trie subTrie = map.get(c);
      if (subTrie == null) return false;
      return subTrie.startsWith(i + 1, prefix, length);
    } else return true;
  }
}
