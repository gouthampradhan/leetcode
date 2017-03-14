import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 22/02/2017.
 * Accepted
 */
public class Test
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] n = {1, 1, 3, 3};
        new Test().subsetsWithDup(n);
        //System.out.println(summation(n));
    }

    static void StairCase(int n)
    {
        for(int i = 1; i <= n; i ++)
        {
            for(int k = 0; k < (n - i); k++)
            {
                System.out.print(" ");
            }
            for(int j = 0; j < i; j++)
            {
                System.out.print("#");
            }
            System.out.println();
        }

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        int begin = 0;
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || nums[i] != nums[i - 1])
                begin = 0;
            int size = result.size();
            for(int j = begin; j < size; j++){
                List<Integer> cur = new ArrayList<Integer>(result.get(j));
                cur.add(nums[i]);
                result.add(cur);
            }
            begin = size;
        }
        return result;
    }

    static int summation(int[] numbers)
    {
        int n = numbers[0];
        int sum = 0;
        for(int i = 1; i <= n; i ++)
        {
            sum += numbers[i];
        }
        return sum;
    }
}
