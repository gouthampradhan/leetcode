/**
 * Created by gouthamvidyapradhan on 18/03/2017.
 */
public class TwoSumII
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] nums = {2, 7, 11, 15};
        int[] result = new TwoSumII().twoSum(nums, 23);
        for (int i : result)
            System.out.println(i);
    }

    public int[] twoSum(int[] numbers, int target)
    {
        int i = 0, j = numbers.length - 1;
        while(i < j)
        {
            int x = (numbers[i] + numbers[j]);
            if(x == target)
            {
                int[] result = new int[2];
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            }
            else if(x < target)
                i++;
            else j--;
        }
        return new int[2];
    }


}
