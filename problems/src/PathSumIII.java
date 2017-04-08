import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 08/04/2017.
 * Accepted
 */

public class PathSumIII
{
    /**
     *
     */
    public static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private Map<Integer, Integer> pathCount = new HashMap<>();
    private int totalCount;

    public static void main(String[] args) throws Exception
    {
        TreeNode node = new TreeNode(1);
        System.out.println(new PathSumIII().pathSum(node, 0));
    }

    public int pathSum(TreeNode root, int sum)
    {
        if(root == null) return 0;
        dfs(root, sum, 0);
        return totalCount;
    }

    private void dfs(TreeNode root, int target, int pSum)
    {
        if(root != null)
        {
            pSum += root.val;
            if(pSum == target) totalCount++;
            totalCount += pathCount.getOrDefault(pSum - target, 0);
            pathCount.put(pSum, pathCount.getOrDefault(pSum, 0) + 1);
            dfs(root.left, target, pSum);
            dfs(root.right, target, pSum);
            pathCount.put(pSum, pathCount.get(pSum) - 1);
        }
    }
}
