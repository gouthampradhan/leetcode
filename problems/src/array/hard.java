class Solution {
    public int maxSubArray(int[] nums) {
        
        int n= nums.length;
        int i=0,sum=0,max=Integer.MIN_VALUE;
        int cur,next=-1;
        
        i = nextpos(nums,0,n); 
        cur =i;
        
        while(i<n)
        {
            //System.out.println(sum+" "+max);
            sum+=nums[i];
                        
            if(sum>max)
            {
                max=sum;
            }
            
            if(nums[i]>0 && cur>=next)
            {
                next=i;                
            }
            
            if(sum < 0)
            {
                sum=0;
                if(cur<next)
                {
                    cur=next;
                    next=-1;
                    i=cur;
                    i--;
                }
            }   
            i++;
           
        }
        
        int lar = Integer.MIN_VALUE;
        
         for(int j=0;j<n;j++)
            {
                if(lar<nums[j])
                {
                    lar=nums[j];
                }
            }
              
        return Math.max(max,lar);       
        
    }
    
    public int nextpos (int arr[], int ind , int n)
    {
        for(int i=ind;i<n;i++)
        {
            if(arr[i]>0)
                return i;
        }
        
        return n;
    }
}
