/**
 * Created by gouthamvidyapradhan on 02/04/2017.
 * Accepted
 */
public class JumpGameII
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

    }

    public int jump(int[] nums)
    {
        int step = 0;
        int e = 0, max = 0;
        for(int i = 0; i < nums.length - 1; i++)
        {
            max = Math.max(max, i + nums[i]);
            if(i == e)
            {
                step++;
                e = max;
            }
        }
        return step;
    }
}
