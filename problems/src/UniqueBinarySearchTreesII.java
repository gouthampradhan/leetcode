import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 31/03/2017.
 * Accepted
 */
public class UniqueBinarySearchTreesII
{
    public static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private List<TreeNode>[][] dp;

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        List<TreeNode> list = new UniqueBinarySearchTreesII().generateTrees(3);
    }

    public List<TreeNode> generateTrees(int n)
    {
        if(n == 0) return new ArrayList<>();
        dp = new List[n + 1][n + 1];
        dp[0][0] = new ArrayList<>();
        for(int i = 1, j = 1; i <= n; i ++, j ++)
        {
            dp[i][j] = new ArrayList<>();
            dp[i][j].add(new TreeNode(i));
        }
        return dp(1, n, n);
    }

    private List<TreeNode> dp(int s, int e, int n)
    {
        if(e < s) return null;
        if(dp[s][e] != null) return dp[s][e];
        List<TreeNode> result = new ArrayList<>();
        for(int i = s; i <= e; i ++)
        {
            List<TreeNode> left = dp(s, i - 1, n);
            List<TreeNode> right = dp(i + 1, e, n);
            List<TreeNode> temp = new ArrayList<>();
            if(left != null)
            {
                for(TreeNode node : left)
                {
                    TreeNode root = new TreeNode(i);
                    root.left = node;
                    temp.add(root);
                }
            }
            if(right != null)
            {
                if(!temp.isEmpty())
                {
                    for(TreeNode root : temp)
                    {
                        for(TreeNode node : right)
                        {
                            TreeNode newRoot = clone(root);
                            newRoot.right = node;
                            result.add(newRoot);
                        }
                    }
                }
                else
                {
                    for(TreeNode node : right)
                    {
                        TreeNode root = new TreeNode(i);
                        root.right = node;
                        result.add(root);
                    }
                }
            }
            else if(!temp.isEmpty())
            {
                result.addAll(temp);
            }
        }
        dp[s][e] = result;
        return result;
    }

    /**
     * Clone treeNode
     * @param root rootnode
     * @return cloned root
     */
    private TreeNode clone(TreeNode root)
    {
        if(root == null) return null;
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = clone(root.left);
        newNode.right = clone(root.right);
        return newNode;
    }
}
