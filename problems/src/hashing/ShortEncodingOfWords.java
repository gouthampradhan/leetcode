package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 04/05/2018. Given a list of words, we may encode it by writing
 * a reference string S and a list of indexes A.
 *
 * <p>For example, if the list of words is ["time", "me", "bell"], we can write it as S =
 * "time#bell#" and indexes = [0, 2, 5].
 *
 * <p>Then for each index, we will recover the word by reading from the reference string from that
 * index until we reach a "#" character.
 *
 * <p>What is the length of the shortest reference string S possible that encodes the given words?
 *
 * <p>Example:
 *
 * <p>Input: words = ["time", "me", "bell"] Output: 10 Explanation: S = "time#bell#" and indexes =
 * [0, 2, 5]. Note:
 *
 * <p>1 <= words.length <= 2000. 1 <= words[i].length <= 7. Each word has only lowercase letters.
 *
 * <p>Solution: Sort the words by length and then use a hashmap to map each substring of a string
 * with its position.
 */
public class ShortEncodingOfWords {
  class Node {
    String s;
    int l;

    Node(String s, int l) {
      this.s = s;
      this.l = l;
    }
  }

  public static void main(String[] args) {
    String[] A = {"memo", "me", "mo"};
    System.out.println(new ShortEncodingOfWords().minimumLengthEncoding(A));
  }

  public int minimumLengthEncoding(String[] words) {
    List<Node> list = new ArrayList<>();
    for (String w : words) {
      list.add(new Node(w, w.length()));
    }
    Collections.sort(list, (o1, o2) -> Integer.compare(o2.l, o1.l));
    Map<String, Integer> map = new HashMap<>();
    int count = 0;
    for (Node node : list) {
      String str = node.s;
      if (!map.containsKey(str)) {
        for (int i = 0, l = str.length(); i < l; i++) {
          map.put(str.substring(i, l), count + i);
        }
        count += (str.length() + 1);
      }
    }
    return count;
  }
}
