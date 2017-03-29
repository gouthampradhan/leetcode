import java.util.*;

/**
 * Created by gouthamvidyapradhan on 28/03/2017.
 */
public class KdiffPairsInanArray
{
    private Map<Integer, Integer> map = new HashMap<>();
    private int count = 0;
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] nums = {1,2,3,4,5};
        System.out.println(new KdiffPairsInanArray().findPairs(nums, -1));
    }

    public int findPairs(int[] nums, int k)
    {
        if(nums.length == 0 || k < 0) return 0;
        for(int i : nums)
        {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if(k == 0)
            {
                if(entry.getValue() > 1)
                    count ++;
            }
            else
            {
                if(map.containsKey(entry.getKey() + k))
                    count ++;
            }
        }
        return count;
    }

}
