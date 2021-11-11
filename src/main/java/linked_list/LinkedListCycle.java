package linked_list;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 23/02/2017. Given a linked list, determine if it has a cycle in
 * it.
 *
 * <p>Follow up: Can you solve it without using extra space?
 */
public class LinkedListCycle {
  private static Set<Integer> hashCode = new HashSet<>();

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    node1.next = node2;
    node2.next = node3;
    node3.next = node1;
    System.out.println(new LinkedListCycle().hasCycle(node1));
  }

  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (slow != null && fast != null) {
      slow = slow.next;
      fast = fast.next;
      if (fast != null) fast = fast.next;
      else break;
      if (fast != null && slow != null) if (fast.equals(slow)) return true;
    }
    return false;
  }
}
