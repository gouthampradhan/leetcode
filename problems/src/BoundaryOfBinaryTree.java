import java.util.*;

/**
 * Created by gouthamvidyapradhan on 27/03/2017.
 * Accepted
 */
public class BoundaryOfBinaryTree
{
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private Set<TreeNode> done = new HashSet<>();
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        TreeNode root = new TreeNode(1);
        /*root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.left = new TreeNode(5);
        root.right.right.right.left.right = new TreeNode(6);
        /*root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(10);*/
        System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(root));
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root)
    {
        if(root == null) return new ArrayList<>();
        List<Integer> antiClockwiseCollection = new ArrayList<>();
        List<Integer> collection = new ArrayList<>();

        if(root.left != null)
            leftShoulder(root, collection);
        else
        {
            if(!done.contains(root))
            {
                done.add(root);
                collection.add(root.val);
            }
        }

        antiClockwiseCollection.addAll(collection);
        collection.clear();
        leafNode(root, collection);
        antiClockwiseCollection.addAll(collection);
        collection.clear();

        if(root.right != null)
            rightShoulder(root, collection);
        else
        {
            if(!done.contains(root))
            {
                done.add(root);
                collection.add(root.val);
            }
        }

        Stack<Integer> stack = new Stack<>();
        stack.addAll(collection);
        while(!stack.isEmpty())
            antiClockwiseCollection.add(stack.pop());
        return new ArrayList<>(antiClockwiseCollection);
    }

    private void leftShoulder(TreeNode node, List<Integer> list)
    {
        if(node == null) return;
        if(!done.contains(node))
        {
            list.add(node.val);
            done.add(node);
        }

        if(node.left != null) leftShoulder(node.left, list);
        else if(node.right != null) leftShoulder(node.right, list);
    }

    private void rightShoulder(TreeNode node, List<Integer> list)
    {
        if(node == null) return;
        if(!done.contains(node))
        {
            list.add(node.val);
            done.add(node);
        }
        if(node.right != null) rightShoulder(node.right, list);
        else if(node.left != null) rightShoulder(node.left, list);
    }

    private void leafNode(TreeNode node, List<Integer> list)
    {
        if(node == null) return;
        if(node.left == null && node.right == null)
            if(!done.contains(node))
            {
                list.add(node.val);
                done.add(node);
            }
        leafNode(node.left, list);
        leafNode(node.right, list);
    }
}
