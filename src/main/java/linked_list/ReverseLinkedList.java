package linked_list;

/** Created by gouthamvidyapradhan on 24/02/2017. Reverse a singly linked list. */
public class ReverseLinkedList {
  private ListNode newHead;

  public static class ListNode {
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
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    ListNode node6 = new ListNode(6);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    // node5.next = node6;
    ListNode newNode = new ReverseLinkedList().reverseList(node1);
    System.out.println(newNode.val);
  }

  public ListNode reverseList(ListNode head) {
    if (head == null) return null;
    else if (head.next == null) return head;
    reverse(head).next = null;
    return newHead;
  }

  private ListNode reverse(ListNode head) {
    if (head.next == null) {
      newHead = head;
      return head;
    }
    ListNode node = reverse(head.next);
    node.next = head;
    return head;
  }
}
