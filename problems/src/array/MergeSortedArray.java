package array;

/**
 * Created by gouthamvidyapradhan on 29/07/2017.
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {
    public static void main(String[] args) throws Exception{
        int[] A = {0};
        int[] B = {1};
        new MergeSortedArray().merge(A, 0, B, 1);
        for(int i : A)
            System.out.println(i);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int s = m + n;
        int l = s - 1;
        for(int i = m - 1; i >= 0; i --, l --){
            nums1[l] = nums1[i];
        }
        l++; //last index of nums1
        int i = 0, j = 0;
        for(; i < s && l < s && j < n; i ++){
            if(nums1[l] <= nums2[j]){
                nums1[i] = nums1[l];
                l++;
            }
            else{
                nums1[i] = nums2[j];
                j++;
            }
        }
        while(l < s){
            nums1[i++] = nums1[l++];
        }
        while(j < n){
            nums1[i++] = nums2[j++];
        }
    }

}
