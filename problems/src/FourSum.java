import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 29/03/2017.
 * Accepted
 */
public class FourSum
{

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] nums = {1, 0, -1, 0, -2, 2};
        System.out.println(new FourSum().fourSum(nums, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target)
    {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 4) return result;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++)
        {
            if(i == 0 || nums[i] != nums[i - 1])
            {
                for(int j = i + 1; j < nums.length - 2; j ++)
                {
                    if(j == i + 1 || nums[j] != nums[j - 1])
                    {
                        int k = j + 1, l = nums.length - 1;
                        while(k < l)
                        {
                            if(k != j + 1 && nums[k] == nums[k + 1])
                            {
                                k++; continue;
                            }
                            int sum = nums[i] + nums[j] + nums[k] + nums[l];
                            if(sum == target)
                            {
                                result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                                k++; l --;
                            }
                            else if(sum < target)
                            {
                                k ++;
                            }
                            else
                            {
                                l --;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
