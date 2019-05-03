package linked_list;

/**
 * Created by gouthamvidyapradhan on 22/03/2019 Given a non-empty, singly linked list with head node
 * head, return a middle node of linked list.
 *
 * <p>If there are two middle nodes, return the second middle node.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,2,3,4,5] Output: Node 3 from this list (Serialization: [3,4,5]) The returned node
 * has value 3. (The judge's serialization of this node is [3,4,5]). Note that we returned a
 * ListNode object ans, such that: ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and
 * ans.next.next.next = NULL. Example 2:
 *
 * <p>Input: [1,2,3,4,5,6] Output: Node 4 from this list (Serialization: [4,5,6]) Since the list has
 * two middle nodes with values 3 and 4, we return the second one.
 *
 * <p>Solution: O(N) Return the middle node. middle = count / 2
 */
public class MiddleOfLinkedList {

  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {}

  public ListNode middleNode(ListNode head) {
    int count = 0;
    ListNode temp = head;
    while (temp != null) {
      temp = temp.next;
      count++;
    }
    int mid = count / 2;
    int c = 0;
    while (head != null && c < mid) {
      head = head.next;
      c++;
    }
    return head;
  }
}
