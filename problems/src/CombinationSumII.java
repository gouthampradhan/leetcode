import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 14/03/2017.
 * Accepted
 */

public class CombinationSumII
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] candidates = {1, 1, 2, 2};
        List<List<Integer>> result = new CombinationSumII().combinationSum2(candidates, 4);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target)
    {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combination(0, target, candidates, new ArrayList<>(), result);
        return result;
    }

    private void combination(int i, int target, int[] candidates, List<Integer> row, List<List<Integer>> result)
    {
        if(target == 0)
        {
            result.add(new ArrayList<>(row));
        }
        else if(target > 0)
        {
            for(int j = i, l = candidates.length; j < l; j ++)
            {
                if(j > i && candidates[j] == candidates[j - 1]) continue;
                row.add(candidates[j]);
                combination(j + 1, target - candidates[j], candidates, row, result);
                row.remove(row.size() - 1);
            }
        }
    }
}
