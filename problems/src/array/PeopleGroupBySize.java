/*
Submitted by: Akash Vartak https://github.com/akash-vartak
Problem: https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
Difficulty: Medium
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PeopleGroupBySize
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

		int a[] = {2,1,3,3,3,2};
		
		List<List<Integer>> ret = groupThePeople(a);
		
		for(int i=0;i<ret.size();i++)
		{
			List<Integer> list = ret.get(i);
			for(int j=0;j<list.size();j++)
			{
				System.out.print(list.get(j) + " ");
			}
			System.out.println();
		}
	}
	
	public static List<List<Integer>> groupThePeople(int[] groupSizes)
	{
		List<List<Integer>> combined = new ArrayList<List<Integer>>();
		
		boolean done[] = new boolean[groupSizes.length];
		for(int i=0;i<groupSizes.length;i++)
		{
			done[i] = false;
		}

		List<Integer> list1 = new ArrayList<Integer>();
		for(int i=0;i<done.length;i++)
		{
			if(!done[i])
			{
//				System.out.println("i:"+i+" groupSizes:"+groupSizes[i]+" done:"+done[i]);
				list1.add(i);
				int maxSize = groupSizes[i]-1;
				for(int j=(i+1);j<done.length;j++)
				{
//					System.out.println("\tj:"+j);
					if(maxSize<=0)
						break;
					if(!done[i] && groupSizes[j]==groupSizes[i])
					{
//						System.out.println("\tj:"+j+" groupSizes:"+groupSizes[j]+" done:"+done[j]+" maxSize:"+maxSize);
						list1.add(j);
						maxSize -= 1;
						done[j] = true;
					}
				}
				done[i] = true;
				combined.add(list1);
				list1 = new ArrayList<Integer>();
			}
		}
		
		return combined;
    }
}
