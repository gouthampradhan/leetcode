import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by gouthamvidyapradhan on 10/03/2017.
 * Accepted
 */
public class SlidingWindowMaximum
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] a = {1, 3, 1, 2, 0, 5};
        int[] result = new SlidingWindowMaximum().maxSlidingWindow(a, 3);
        for(int i : result)
            System.out.print(i + " ");
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k)
    {
        int[] result = new int[nums.length - (k - 1)];

        if(nums.length == 0) return new int[0];

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0, j = 0, l = nums.length; i < l; i ++)
        {
            int head = i - (k - 1);
            if(head >= 0)
            {
                //remove out of range
                if(queue.peek() != null && queue.peek() < head)
                    queue.poll();
            }
            while(queue.peekLast() != null && nums[queue.peekLast()] <= nums[i])
            {
                queue.pollLast();
            }
            queue.offer(i);
            if(i >= k - 1)
                result[j++] = nums[queue.peek()];
        }

        return result;
    }
}
