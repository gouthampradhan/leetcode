package linked_list;

/**
 * Created by gouthamvidyapradhan on 24/02/2017. Write a program to find the node at which the
 * intersection of two singly linked lists begins.
 *
 * <p>
 *
 * <p>For example, the following two linked lists:
 *
 * <p>A: a1 → a2 ↘ c1 → c2 → c3 ↗ B: b1 → b2 → b3 begin to intersect at node c1.
 *
 * <p>
 *
 * <p>Notes:
 *
 * <p>If the two linked lists have no intersection at all, return null. The linked lists must retain
 * their original structure after the function returns. You may assume there are no cycles anywhere
 * in the entire linked structure. Your code should preferably run in O(n) time and use only O(1)
 * memory.
 */
public class IntersectionOfTwoLists {

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
    ListNode node1 = new ListNode(2);
    ListNode node2 = new ListNode(3);
    node1.next = node2;
    System.out.println(new IntersectionOfTwoLists().getIntersectionNode(node1, node2));
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;

    int l1len = 0, l2len = 0;
    ListNode temp1 = headA;
    while (temp1 != null) {
      temp1 = temp1.next;
      l1len++;
    }
    temp1 = headB;

    while (temp1 != null) {
      temp1 = temp1.next;
      l2len++;
    }

    int diff = Math.abs(l1len - l2len);

    ListNode temp2;

    if (l1len > l2len) {
      temp1 = headA;
      temp2 = headB;
    } else {
      temp1 = headB;
      temp2 = headA;
    }

    while (diff != 0) {
      temp1 = temp1.next;
      diff--;
    }
    while (!temp1.equals(temp2)) {
      temp1 = temp1.next;
      temp2 = temp2.next;
      if (temp1 == null || temp2 == null) return null;
    }

    if (temp1.equals(temp2)) return temp1;
    return null;
  }
}
