import java.util.HashMap;

/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * Accepted
 */
public class TwoSum
{
    HashMap<Integer, Integer> map = new HashMap<>();

    public int[] twoSum(int[] nums, int target)
    {
        int[] result = new int[2];

        for(int i : nums)
        {
            if(map.keySet().contains(i))
            {
                int count = map.get(i);
                map.put(i, ++count);
            }
            else
            {
                map.put(i, 1);
            }
        }

        for(int i = 0, l = nums.length; i < l; i ++)
        {
            int ele = nums[i];
            int req = target - ele;
            if(map.keySet().contains(req))
            {
                result[0] = i;
                if(ele == req)
                {
                    int count = map.get(req);
                    if(count > 1)
                    {
                        for(int j = i + 1; j < l; j++)
                        {
                            if(nums[j] == req)
                            {
                                result[1] = j;
                                return result;
                            }
                        }
                    }
                }
                else
                {
                    for(int j = i + 1; j < l; j++)
                    {
                        if(nums[j] == req)
                        {
                            result[1] = j;
                            return result;
                        }
                    }
                }
            }
        }
        return result;
    }
}
