/**
 * Created by gouthamvidyapradhan on 21/03/2017.
 */
public class LCA
{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root != null)
        {
            if(root.equals(p) || root.equals(q)) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(left != null && right != null) return root;
            else if(left != null) return left;
            else return right;
        }
        return null;
    }


}
