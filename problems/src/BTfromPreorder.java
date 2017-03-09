import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * Accepted
 */
public class BTfromPreorder
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Map<Integer, Integer> MAP = new HashMap<>();
    private int index = 0, totalLen = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder)
    {
        for(int i = 0, l = inorder.length; i < l; i ++)
            MAP.put(inorder[i], i);
        totalLen = preorder.length;
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int s, int e)
    {
        if (s > e || index >= totalLen) return null;

        int n = preorder[index++];
        int pos = MAP.get(n);

        TreeNode node = new TreeNode(n);
        if(s == e) return node;

        node.left = build(preorder, s, pos - 1);
        node.right = build(preorder, pos + 1, e);
        return node;
    }
}
