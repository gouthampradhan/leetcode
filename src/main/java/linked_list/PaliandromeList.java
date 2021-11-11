package linked_list;

/**
 * Created by gouthamvidyapradhan on 25/02/2017. Given a singly linked list, determine if it is a
 * palindrome.
 *
 * <p>Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PaliandromeList {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  ListNode headNode;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    ListNode node1 = new ListNode(1);
    // ListNode node2 = new ListNode(2);
    // ListNode node3 = new ListNode(3);
    // ListNode node4 = new ListNode(3);
    // ListNode node5 = new ListNode(2);
    // ListNode node6 = new ListNode(1);
    // node1.next = node2;
    // node2.next = node3;
    // node3.next = node5;
    // node4.next = node5;
    // node5.next = node6;
    System.out.println(new PaliandromeList().isPalindrome(node1));
  }

  public boolean isPalindrome(ListNode head) {
    int length = 0, count = 0;
    if (head == null) return true;
    ListNode temp = head;
    while (temp != null) {
      temp = temp.next;
      length++;
    }
    if (length == 1) return true;

    int halfLen = length / 2;

    temp = head;
    while (count < halfLen - 1) {
      temp = temp.next;
      count++;
    }

    ListNode newHead = temp.next;
    temp.next = null;
    reverse(newHead);
    ListNode first = head, second = headNode;
    for (int i = 0; i < halfLen; i++) {
      if (first.val != second.val) return false;
      first = first.next;
      second = second.next;
    }
    return true;
  }

  private ListNode reverse(ListNode node) {
    if (node.next == null) {
      headNode = node;
      return node;
    }
    ListNode prev = reverse(node.next);
    node.next = null;
    prev.next = node;
    return node;
  }
}
