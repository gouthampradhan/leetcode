import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pradhang on 3/14/2017.
 * Accepted
 */
public class CombinationSum
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] candidates = {2,3,6,7};

        List<List<Integer>> result = new CombinationSum().combinationSum(candidates, 7);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target)
    {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        doNext(0, result, 0, candidates, target, subList);
        return result;
    }

    private void doNext(int i, List<List<Integer>> result, int count, int[] candidates, int target, List<Integer> subArr)
    {
        if(target == 0)
        {
            List<Integer> subList = new ArrayList<>();
            for(int k = 0; k < count; k ++)
                subList.add(subArr.get(k));
            result.add(subList);
        }
        else if(target > 0)
        {
            for(int j = i, l = candidates.length; j < l; j ++)
            {
                subArr.add(candidates[j]);
                doNext(j, result, count + 1, candidates, target - candidates[j], subArr);
                subArr.remove(subArr.size() - 1);
            }
        }
    }
}
