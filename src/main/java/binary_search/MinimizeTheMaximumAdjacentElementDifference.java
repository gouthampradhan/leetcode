/* (C) 2024 YourCompanyName */
package binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimizeTheMaximumAdjacentElementDifference {
    record Interval(int s, int e, boolean hasMoreThanOne){
        static boolean check(int a, int mid, int b, int range){
            return Math.abs(mid - a) <= range && Math.abs(mid - b) <= range;
        }
        static boolean check(int a, int mid1, int mid2, int b, int range){
            return Math.abs(mid1 - a) <= range && Math.abs(mid1 - mid2) <= range && Math.abs(mid2 - b) <= range;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,10,-1,8};
        int res = new MinimizeTheMaximumAdjacentElementDifference().minDifference(nums);
        System.out.println(res);
    }

    public int minDifference(int[] nums) {
        boolean noPositiveNum = Arrays.stream(nums).filter(i -> i != -1).findAny().isEmpty();
        if(noPositiveNum){
            return 0;
        }
        int currentMax = getCurrentMax(nums);
        List<Interval> intervals = buildIntervals(nums);
        int minStart = Integer.MAX_VALUE, maxEnd = Integer.MIN_VALUE;
        for (Interval interval : intervals) {
            minStart = Math.min(minStart, Math.min(interval.e, interval.s));
            maxEnd = Math.max(maxEnd, Math.max(interval.e, interval.s));
        }
        int l = 0, h = maxEnd, m;
        int ans = -1;
        while(l <= h){
            m = l + (h - l) / 2;
            boolean result = checkIfThisNumberSatisfiesAllIntervals(intervals, minStart + m, maxEnd - m, m);
            if(result){
                ans = m;
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return Math.max(ans, currentMax);
    }

    private int getCurrentMax(int[] nums){
        int currMax = Integer.MIN_VALUE;
        int previous = nums[0];
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] != -1){
                if(previous != -1){
                    currMax = Math.max(currMax, Math.abs(previous - nums[i]));
                }
                previous = nums[i];
            } else {
                previous = -1;
            }
        }
        return currMax;
    }

    private List<Interval> buildIntervals(int[] nums) {
        int previous = -1;
        int minusOneCount = 0;
        List<Interval> intervals = new ArrayList<>();
        for (int num : nums) {
            if (num == -1) {
                minusOneCount ++;
            } else {
                if (minusOneCount > 0) {
                    intervals.add(new Interval(previous != -1 ? previous : num, num, minusOneCount > 1));
                    minusOneCount = 0;
                }
                previous = num;
            }
        }
        if(nums[nums.length - 1] == -1){
            intervals.add(new Interval(previous, previous, minusOneCount > 1));
        }
        return intervals;
    }

    boolean checkIfThisNumberSatisfiesAllIntervals(List<Interval> intervals, int minStart, int maxEnd, int maxDiff){
        for (Interval interval : intervals) {
            if (interval.hasMoreThanOne) {
                boolean res1 = Interval.check(interval.s, minStart, minStart, interval.e, maxDiff);
                boolean res2 = Interval.check(interval.s, minStart, maxEnd, interval.e, maxDiff);
                boolean res3 = Interval.check(interval.s, maxEnd, minStart, interval.e, maxDiff);
                boolean res4 = Interval.check(interval.s, maxEnd, maxEnd, interval.e, maxDiff);
                if (!res1 && !res2 && !res3 && !res4) {
                    return false;
                }
            } else {
                boolean res1 = Interval.check(interval.s, minStart, interval.e, maxDiff);
                boolean res2 = Interval.check(interval.s, maxEnd, interval.e, maxDiff);
                if (!res1 && !res2) {
                    return false;
                }
            }
        }
        return true;
    }
}
