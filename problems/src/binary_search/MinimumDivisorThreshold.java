import java.util.*;
class abc
{
    public static void main(String[] args) {
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++)
        {
            a[i]=sc.nextInt();
        }
        int thresh=sc.nextInt();
        int l=1,r=0;
        for(int i:a)
        {
            r=Math.max(r,i);
        }
        while(l<r)
        {
            int mid=l+(r-l)/2;
            int s=0;
            for(int i:a)
            {
                s+=(int)(Math.ceil(i*1.0/mid*1.0));
            }
            if(s<=thresh)
            r=mid;
            else l=mid+1;
        }
        System.out.println(r);
    }
}