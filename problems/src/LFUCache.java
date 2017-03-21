import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 20/03/2017.
 * WA
 */
public class LFUCache
{
    public static class DLinkList
    {
        int key, value, frequency;
        DLinkList left;
        DLinkList right;
        DLinkList(int key, int value, int frequency)
        {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            left = null;
            right = null;
        }
    }

    private static class HeadTail
    {
        DLinkList head, tail;
        HeadTail(DLinkList head, DLinkList tail)
        {
            this.head = head;
            this.tail = tail;
        }
    }

    private int capacity;
    private int currentSize;
    private Map<Integer, DLinkList> cache;
    private Map<Integer, HeadTail> frequencyMap;
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)
    {
        LFUCache cache1 = new LFUCache(3);
        cache1.put(2, 2);
        cache1.put(1, 1);
        System.out.println(cache1.get(2));
        System.out.println(cache1.get(1));
        System.out.println(cache1.get(2));
        cache1.put(3, 3);
        cache1.put(4, 4);
        System.out.println(cache1.get(3));
        System.out.println(cache1.get(2));
        System.out.println(cache1.get(1));
        System.out.println(cache1.get(4));

        //System.out.println(cache1.get(3));
        //System.out.println(cache1.get(4));
    }

    public LFUCache(int capacity)
    {
        currentSize = 0;
        this.capacity = capacity;
        cache = new HashMap<>();
        frequencyMap = new LinkedHashMap<>();
    }

    private DLinkList popNode(DLinkList node)
    {
        node.left.right = node.right;
        node.right.left = node.left;
        HeadTail headTail = frequencyMap.get(node.frequency);
        if(headTail.head.right.equals(headTail.tail))
            frequencyMap.remove(node.frequency);
        node.left = null;
        node.right = null;
        return node;
    }

    private DLinkList removeHead(int i)
    {
        HeadTail node = frequencyMap.get(i);
        return popNode(node.head.right);
    }

    private void offer(int i, DLinkList node)
    {
        HeadTail dList = frequencyMap.get(i);
        if(dList == null)
        {
            DLinkList pHead = new DLinkList(-1, -1, -1);
            DLinkList pTail = new DLinkList(-1, -1, -1);
            pHead.right = pTail;
            pTail.left = pHead;
            HeadTail newHeadTail = new HeadTail(pHead, pTail);
            frequencyMap.put(i, newHeadTail);
            dList = newHeadTail;
        }
        //add to the tail
        dList.tail.left.right = node;
        node.left = dList.tail.left;
        node.right = dList.tail;
        dList.tail.left = node;
    }

    public int get(int key)
    {
        if(!cache.containsKey(key)) return -1;
        DLinkList node = cache.get(key);
        popNode(node);
        node.frequency += 1;
        offer(node.frequency, node);
        return node.value;
    }

    public void put(int key, int value)
    {
        if(cache.containsKey(key))
        {
            DLinkList node = cache.get(key);
            popNode(node);
            node.value = value;
            node.frequency += 1;
            offer(node.frequency, node);
        }
        else if(currentSize == capacity)
        {
            if(frequencyMap.keySet().iterator().hasNext())
            {
                int f = frequencyMap.containsKey(1) ? 1 : frequencyMap.keySet().iterator().next();
                cache.remove(removeHead(f).key);
                DLinkList node = new DLinkList(key, value, 1);
                cache.put(key, node);
                offer(1, node);
            }
        }
        else
        {
            DLinkList node = new DLinkList(key, value, 1);
            cache.put(key, node);
            offer(1, node);
            ++currentSize;
        }
    }
}
