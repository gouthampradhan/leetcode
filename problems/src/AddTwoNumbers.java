/**
 * Created by gouthamvidyapradhan on 13/03/2017.
 * Accepted
 */
public class AddTwoNumbers
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x)
        {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args)
    {
        ListNode node = new ListNode(2);
        node.next = new ListNode(4);
        node.next.next = new ListNode(4);

        ListNode node1 = new ListNode(5);
        node1.next = new ListNode(6);
        node1.next.next = new ListNode(6);

        ListNode result = new AddTwoNumbers().addTwoNumbers(node, node1);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        if(l1 == null && l2 == null) return null;
        ListNode first = l1;
        ListNode second = l2;
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode prev = head;
        while(first != null && second != null)
        {
            int q = (first.val + second.val + carry) / 10;
            int r = (first.val + second.val + carry) % 10;
            carry = q;
            ListNode node = new ListNode(r);
            prev.next = node;
            prev = node;
            first = first.next;
            second = second.next;
        }

        while(first != null)
        {
            int q = (first.val + carry) / 10;
            int r = (first.val + carry) % 10;
            carry = q;
            ListNode node = new ListNode(r);
            prev.next = node;
            prev = node;
            first = first.next;
        }

        while(second != null)
        {
            int q = (second.val + carry) / 10;
            int r = (second.val + carry) % 10;
            carry = q;
            ListNode node = new ListNode(r);
            prev.next = node;
            prev = node;
            second = second.next;
        }

        if(carry != 0)
            prev.next = new ListNode(carry);

        return head.next;
    }
}
