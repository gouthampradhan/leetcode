package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 04/12/2019 In a warehouse, there is a row of barcodes, where
 * the i-th barcode is barcodes[i].
 *
 * <p>Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer,
 * and it is guaranteed an answer exists.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,1,1,2,2,2] Output: [2,1,2,1,2,1] Example 2:
 *
 * <p>Input: [1,1,1,1,2,2,3,3] Output: [1,3,1,3,2,1,2,1]
 *
 * <p>Note:
 *
 * <p>1 <= barcodes.length <= 10000 1 <= barcodes[i] <= 10000
 */
public class DistantBarcodes {
  public static void main(String[] args) {
    int[] barcode = {1, 1, 1, 2, 2, 2};
    int[] result = new DistantBarcodes().rearrangeBarcodes(barcode);
    for (int i : result) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  class Node {
    int value, count, rank;

    Node(int value, int count, int rank) {
      this.value = value;
      this.count = count;
      this.rank = rank;
    }
  }

  public int[] rearrangeBarcodes(int[] barcodes) {
    PriorityQueue<Node> pq =
        new PriorityQueue<>(
            (o1, o2) -> {
              int r = Integer.compare(o2.count, o1.count);
              return r == 0 ? Integer.compare(o1.rank, o2.rank) : r;
            });
    Map<Integer, Integer> map = new HashMap<>();
    for (int b : barcodes) {
      map.putIfAbsent(b, 0);
      map.put(b, map.get(b) + 1);
    }
    for (int k : map.keySet()) {
      pq.offer(new Node(k, map.get(k), -1));
    }
    int[] result = new int[barcodes.length];
    int i = 0;
    int rank = 0;
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      result[i++] = node.value;
      node.count -= 1;
      node.rank = rank++;
      if (!pq.isEmpty()) {
        Node next = pq.poll();
        result[i++] = next.value;
        next.count -= 1;
        next.rank = rank++;
        if (next.count > 0) {
          pq.offer(next);
        }
      }
      if (node.count > 0) {
        pq.offer(node);
      }
    }
    return result;
  }
}
