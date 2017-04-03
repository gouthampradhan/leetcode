/**
 * Created by pradhang on 4/3/2017.
 * Accepted
 */
public class HouseRobber
{
    private int[] max;
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

    }

    public int rob(int[] nums)
    {
        if(nums.length == 0) return 0;
        max = new int[nums.length];
        if(nums.length == 1) return nums[0];
        max[nums.length - 1] = nums[nums.length - 1];
        max[nums.length - 2] = Math.max(nums[nums.length - 1], nums[nums.length - 2]);
        for(int i = nums.length - 3; i >= 0; i --)
        {
            max[i] = Math.max(max[i + 1], nums[i] + max[i + 2]);
        }
        return max[0];
    }
}
