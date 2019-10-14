class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int count = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=val)
                count++;
        }
       int i = 0;
        int j = nums.length - 1;
        while(i<=j){
            if(nums[i]==val){
                int t = nums[i];
                nums[i] = nums[j];
                nums[j]  = t;
                j--;
            }else{
                i++;
            }
        }
        return count;
    }
}