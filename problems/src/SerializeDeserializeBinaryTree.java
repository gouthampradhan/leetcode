import java.util.*;

/**
 * Created by gouthamvidyapradhan on 11/03/2017.
 * Accepted
 */
public class SerializeDeserializeBinaryTree
{
    public static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private static final String NULL = "X";
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        TreeNode ten = new TreeNode(10);
        root.left = null;
        root.right = two;
        two.left = three;
        three.left = four;
        four.right = five;
        five.right = six;
        six.left = seven;
        seven.left = eight;
        eight.right = nine;
        nine.right = ten;
        String serializedStr = new SerializeDeserializeBinaryTree().serialize(root);

        TreeNode result = new SerializeDeserializeBinaryTree().deserialize(serializedStr);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root)
    {
        if(root == null) return null;
        List<String> list = new ArrayList<>();
        encode(root, list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for(int i = 1, l = list.size(); i < l; i++)
        {
            sb.append(",").append(list.get(i));
        }
        return sb.toString();
    }

    private void encode(TreeNode root, List<String> list)
    {
        if(root == null) list.add(NULL);
        else
        {
            list.add(String.valueOf(root.val));
            encode(root.left, list);
            encode(root.right, list);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data)
    {
        if(data == null || data.isEmpty()) return null;
        StringTokenizer st = new StringTokenizer(data, ",");
        Queue<String> queue = new ArrayDeque<>();
        while(st.hasMoreTokens())
            queue.offer(st.nextToken());
        return decode(queue);
    }

    private TreeNode decode(Queue<String> queue)
    {
        String node = queue.poll();
        if(node.equals(NULL))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = decode(queue);
        root.right = decode(queue);
        return root;
    }
}
