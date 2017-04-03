/**
 * Created by gouthamvidyapradhan on 02/04/2017.
 * Accepted
 */
public class LongestIncreasingSubsequence
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] nums = {9, 8, 7, 6};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums)
    {
        if(nums.length == 0) return 0;
        int[] A = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for(int i = 0, l = nums.length; i < l; i ++)
        {
            int lis = 1;
            for(int j = 0; j < i; j ++)
            {
                if(nums[i] > nums[j])
                    lis = Math.max(lis, A[j] + 1);
            }
            A[i] = lis;
            max = Math.max(max, A[i]);
        }
        return max;
    }
}
