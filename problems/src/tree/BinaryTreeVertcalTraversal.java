import java.util.*;
class Node
{
    int data;
    Node right,left;
    Node(int d)
    {
        data=d;
    }
}
class Tree{
    class Pair
    {
        Node node;
        int y; // y denotes height of the Node
        Pair(Node node,int y)
        {
            this.node=node;
            this.y=y;
        }
    }

    void dfs(Node root,int x,int y,TreeMap<Integer,ArrayList<Pair>> tm)
    {
        if(root==null) return;
        if(tm.containsKey(x))
        {
            tm.get(x).add(new Pair(root,y));
        }
        else
        {
            ArrayList<Pair> al=new ArrayList<>();
            al.add(new Pair(root, y));
            tm.put(x,al);
        }
        dfs(root.left,x-1,y+1,tm);
        dfs(root.right,x+1,y+1,tm);
    }
    ArrayList<ArrayList<Integer>> vertical(Node root)
    {
        TreeMap<Integer,ArrayList<Pair>> tm=new TreeMap<>();
        dfs(root, 0, 0, tm);
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        for(Map.Entry<Integer,ArrayList<Pair>> m:tm.entrySet())
        {
            ArrayList<Pair> temp=m.getValue();
            Collections.sort(temp,new Comparator<Pair>() {
                public int compare(Pair p1,Pair p2)
                {
                    int k=Integer.compare(p1.y,p2.y); //First sort according to the height
                    return (k==0)?Integer.compare(p1.node.data,p2.node.data):k; //If height is equal then sort according to the node value
                }
            });
            ArrayList<Integer> temp1=new ArrayList<>();
            for(Pair i:temp)
            {
                temp1.add(i.node.data);
            }
            res.add(temp1);
        }
        return res;
    }
}
class abc
{
    public static void main(String[] args) {
        Node root=new Node(1);
        root.left=new Node(2);
        root.left.left=new Node(4);
        root.left.right=new Node(6);
        root.right=new Node(3);
        root.right.left=new Node(5);
        root.right.right=new Node(7);
        ArrayList<ArrayList<Integer>> al=new Tree().vertical(root);
        System.out.println(al);
    }
}