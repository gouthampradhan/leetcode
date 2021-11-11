package linked_list;

/**
 * Created by gouthamvidyapradhan on 06/07/2017. Given a linked list, reverse the nodes of a linked
 * list k at a time and return its modified list.
 *
 * <p>k is a positive integer and is less than or equal to the length of the linked list. If the
 * number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * <p>You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * <p>Only constant memory is allowed.
 *
 * <p>For example, Given this linked list: 1->2->3->4->5
 *
 * <p>For k = 2, you should return: 2->1->4->3->5
 *
 * <p>For k = 3, you should return: 3->2->1->4->5
 *
 * <p>
 *
 * <p>Solution: O(N) solution with constant space. Recursively reverse a group of K nodes and for
 * each group join the tail of the prev group to the head of the next group.
 */
public class ReverseNodesKGroup {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  private ListNode newHead, newStart, prev;

  public static void main(String[] args) throws Exception {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    ListNode result = new ReverseNodesKGroup().reverseKGroup(node, 2);
    while (result != null) {
      System.out.println(result.val + " ");
      result = result.next;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) return null;
    ListNode node = head;
    int count = 0;
    while (node != null) {
      node = node.next;
      count++;
    }
    if (k > count) return head;
    int N = count / k;
    newStart = head;
    ListNode tail = null;
    ListNode result = null;
    while (N-- > 0) {
      tail = reverse(newStart, 1, k);
      tail.next = null;
      if (result == null) result = newHead; // save the head only once
      if (prev != null) {
        prev.next = newHead;
      }
      prev = tail;
    }
    if (tail != null) tail.next = newStart;
    return result;
  }

  /**
   * Reverse k nodes
   *
   * @param node head node
   * @param count count
   * @param k K
   * @return
   */
  private ListNode reverse(ListNode node, int count, int k) {
    if (count == k) {
      newStart = node.next; // new start node
      newHead = node; // mark this as the new head
    } else {
      ListNode nNode = reverse(node.next, count + 1, k);
      nNode.next = node;
    }
    return node;
  }
}
