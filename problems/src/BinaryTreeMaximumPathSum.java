/**
 * Created by gouthamvidyapradhan on 03/04/2017.
 * Accepted
 */
public class BinaryTreeMaximumPathSum
{
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int max = Integer.MIN_VALUE;
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(-1);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(9);
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(root));
    }
    public int maxPathSum(TreeNode root)
    {
        if(root == null) return 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root)
    {
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, root.val);
        max = Math.max(max, root.val + left);
        max = Math.max(max, root.val + right);
        max = Math.max(max, root.val + left + right);
        int leftVal = Math.max(root.val, root.val + left);
        int rightVal = Math.max(root.val, root.val + right);
        return Math.max(leftVal, rightVal);
    }
}
