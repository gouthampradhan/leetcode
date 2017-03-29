import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 29/03/2017.
 * Accepted
 */
public class ThreeSum
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] nums = {-1,0,1,2,-1,-4,-1,0,1,2,-1,-4,-1,0,1,2,-1,-4,-1,0,1,2,-1,-4,-1,0,1,2,-1,-4,-1,0,1,2,-1,-4,-1,0,1,2,-1,-4,-1,0,1,2,-1,-4};
        System.out.println(new ThreeSum().threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;
        Arrays.sort(nums);
        for(int i = 0, l = nums.length; i < l - 2; i ++)
        {
            if(i == 0 || nums[i] != nums[i - 1])
            {
                int j = i + 1, k = l - 1;
                while(k > j)
                {
                    if(j != i + 1 && nums[j] == nums[j - 1])
                    {
                        j ++;
                        continue;
                    }
                    int sum = nums[i] + nums[j] + nums[k];
                    if(sum == 0)
                    {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        k--; j++;
                    }
                    else if(sum > 0) k--;
                    else j++;
                }
            }
        }
        return result;
    }
}
