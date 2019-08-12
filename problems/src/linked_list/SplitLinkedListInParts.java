package linked_list;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 06/08/2019 Given a (singly) linked list with head node root,
 * write a function to split the linked list into k consecutive linked list "parts".
 *
 * <p>The length of each part should be as equal as possible: no two parts should have a size
 * differing by more than 1. This may lead to some parts being null.
 *
 * <p>The parts should be in order of occurrence in the input list, and parts occurring earlier
 * should always have a size greater than or equal parts occurring later.
 *
 * <p>Return a List of ListNode's representing the linked list parts that are formed.
 *
 * <p>Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ] Example 1: Input:
 * root = [1, 2, 3], k = 5 Output: [[1],[2],[3],[],[]] Explanation: The input and each element of
 * the output are ListNodes, not arrays. For example, the input root has root.val = 1, root.next.val
 * = 2, \root.next.next.val = 3, and root.next.next.next = null. The first element output[0] has
 * output[0].val = 1, output[0].next = null. The last element output[4] is null, but it's string
 * representation as a ListNode is []. Example 2: Input: root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k =
 * 3 Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]] Explanation: The input has been split into
 * consecutive parts with size difference at most 1, and earlier parts are a larger size than the
 * later parts. Note:
 *
 * <p>The length of root will be in the range [0, 1000]. Each value of a node in the input will be
 * an integer in the range [0, 999]. k will be an integer in the range [1, 50].
 *
 * <p>Solution O(N) do a linear scan and split the array by required size.
 */
public class SplitLinkedListInParts {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    ListNode root = new ListNode(1);
    root.next = new ListNode(2);
    root.next.next = new ListNode(3);
    ListNode[] result = new SplitLinkedListInParts().splitListToParts(root, 5);
    System.out.println(result);
  }

  public ListNode[] splitListToParts(ListNode root, int k) {
    List<Integer> list = new ArrayList<>();
    while (root != null) {
      list.add(root.val);
      root = root.next;
    }
    int tempK = k;
    int N = list.size();
    List<ListNode> result = new ArrayList<>();
    ListNode head = new ListNode(-1);
    ListNode prev = head;
    int i = 0, j = 0;
    int count = 0;
    int P = N / tempK;
    if ((N % tempK) > 0) {
      P++;
    }
    for (; j < N; ) {
      if (j - i < P) {
        prev.next = new ListNode(list.get(j));
        prev = prev.next;
        j++;
        count++;
      } else {
        result.add(head.next);
        i = j;
        head = new ListNode(-1);
        prev = head;
        tempK--;
        P = (N - count) / tempK;
        if (((N - count) % tempK) > 0) {
          P++;
        }
      }
    }
    result.add(head.next);
    while (result.size() < k) {
      result.add(null);
    }
    ListNode[] nodes = new ListNode[result.size()];
    for (int l = 0; l < result.size(); l++) {
      nodes[l] = result.get(l);
    }
    return nodes;
  }
}
