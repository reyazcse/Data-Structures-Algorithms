//https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
/*
Given arrival and departure times of all trains that reach a railway station, the task is to find the minimum number of platforms required for the railway station so that no train waits.
We are given two arrays which represent arrival and departure times of trains that stop.

Examples:

Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
Output: 3
Explantion: There are at-most three trains at a time (time between 11:00 to 11:20)

Input: arr[] = {9:00, 9:40}
dep[] = {9:10, 12:00}
Output: 1
Explantion: Only one platform is needed.

 * */

/*
Solution:
	Brute force: O(n^2)
	
	The idea is to find the number of overlapping intervals
	So take two pointers. 
	i=0 and j = i+1
	Then move j till end and find the number of overlapping intervals.
	Update totalPlatforms count after j loop finishes
	Finally return answer
	
	Efficient: O(n log n) time and O(n) space
	Similar to meeting rooms II but one test case is failing for below code on geeksforgeeks
	
	Efficient solution 2 : O(n log n) time but O(1) complexity
	See geeks page
	
 * */
package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class MinimumNumberOfPlatforms {
	static int findPlatform(int arr[], int dep[], int n)
    {
		Interval[] intervals = new Interval[n];
		ArrayList<Integer> activities = new ArrayList<>();
		for(int i=0; i<n; i++) {
			intervals[i] = new Interval(arr[i], dep[i]);
			
		}
		Arrays.sort(intervals, (interval1, interval2)-> {
			return interval1.start - interval2.start;
		});
        
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		minHeap.add(intervals[0].end);
		for(int i=1; i<intervals.length; i++) {
			int prevDeparture = minHeap.peek();
			if(intervals[i].start >= prevDeparture) {
				minHeap.remove();
			}
			minHeap.add(intervals[i].end);
		}
		return minHeap.size();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static class Interval {
		int start;
		int end;
		public Interval() {start=0; end=0;}
		public Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

}
