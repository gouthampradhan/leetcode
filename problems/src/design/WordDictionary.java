package design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 09/12/2017.
 *
 * <p>Design a data structure that supports the following two operations:
 *
 * <p>void addWord(word) bool search(word) search(word) can search a literal word or a regular
 * expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * <p>For example:
 *
 * <p>addWord("bad") addWord("dad") addWord("mad") search("pad") -> false search("bad") -> true
 * search(".ad") -> true search("b..") -> true Note: You may assume that all words are consist of
 * lowercase letters a-z.
 *
 * <p>Solution: Implement a simple Trie and perform a search.
 */
public class WordDictionary {

  private Trie trie;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    WordDictionary wd = new WordDictionary();
    wd.addWord("bad");
    wd.addWord("dad");
    wd.addWord("mad");
    System.out.println(wd.search("pad"));
    System.out.println(wd.search("bad"));
    System.out.println(wd.search(".ad"));
    System.out.println(wd.search("..."));
  }

  /** Initialize your data structure here. */
  public WordDictionary() {
    this.trie = new Trie();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    this.trie.insert(word);
  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to
   * represent any one letter.
   */
  public boolean search(String word) {
    return this.trie.search(word);
  }

  private class Trie {

    private Map<Character, Trie> map;

    /** Initialize your data structure here. */
    private Trie() {
      map = new HashMap<>();
    }

    /** Inserts a word into the trie. */
    private void insert(String word) {
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
    private boolean search(String word) {
      if (word != null) {
        return search(0, word, word.length());
      }
      return false;
    }

    private boolean search(int i, String word, int length) {
      if (i < length) {
        char c = word.charAt(i);
        if (c == '.') {
          for (Character child : map.keySet()) {
            if (child != null) {
              Trie subTrie = map.get(child);
              if (subTrie.search(i + 1, word, length)) return true;
            }
          }
          return false;
        } else {
          Trie subTrie = map.get(c);
          if (subTrie == null) return false;
          return subTrie.search(i + 1, word, length);
        }
      }
      return map.containsKey(null);
    }
  }
}
