package greedy;

import java.util.*;

/** Created by gouthamvidyapradhan on 30/11/2019 */
public class PartitionLabels {
  public static void main(String[] args) {
    System.out.println(new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij"));
  }

  public List<Integer> partitionLabels(String S) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      char c = S.charAt(i);
      map.putIfAbsent(c, i);
      map.remove(c);
      map.put(c, i);
    }
    char start = S.charAt(0);
    int currMax = map.get(start);
    int startIndex = 0;
    List<Integer> list = new ArrayList<>();
    while (true) {
      int i = startIndex;
      for (; i <= currMax; i++) {
        char c = S.charAt(i);
        int pos = map.get(c);
        currMax = Math.max(currMax, pos);
      }
      if (i > currMax && i < S.length()) {
        list.add(i - startIndex);
        startIndex = i;
        currMax = map.get(S.charAt(i));
      } else {
        if (i == S.length()) {
          list.add(i - startIndex);
          break;
        }
      }
    }
    return list;
  }
}
