package linked_list;

/**
 * Created by gouthamvidyapradhan on 13/08/2017. Given a linked list, swap every two adjacent nodes
 * and return its head.
 *
 * <p>For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * <p>Your algorithm should use only constant space. You may not modify the values in the list, only
 * nodes itself can be changed.
 */
public class SwapNodesInPairs {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    node.next.next.next.next.next = new ListNode(6);
    ListNode head = new SwapNodesInPairs().swapPairs(node);
    while (head != null) {
      System.out.println(head.val);
      head = head.next;
    }
  }

  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode newHead = head.next;
    ListNode curr = head.next;
    ListNode prev = head;
    ListNode prevPrev = new ListNode(-1); // dummy node
    while (curr != null) {
      prev.next = curr.next;
      curr.next = prev;
      prevPrev.next = curr;
      if (prev.next != null) {
        curr = prev.next.next;
        prev = prev.next;
        prevPrev = prevPrev.next.next;
      } else {
        curr = null;
      }
    }
    return newHead;
  }
}
