/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * WA
 */
public class LowestCommonAncestor
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root != null)
        {
            if(root.val == p.val || root.val == q.val)
                return root;
            TreeNode rlca = lowestCommonAncestor(root.right, p, q);
            TreeNode llca = lowestCommonAncestor(root.left, p, q);
            //TreeNode rlca = lowestCommonAncestor(root.right, p, q);
            if(llca != null && rlca != null) return root;
            return (llca != null) ? llca : rlca;
        }
        return null;
    }
}
