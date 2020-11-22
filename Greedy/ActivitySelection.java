/*
Given N activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.

Note : The start time and end time of two activities may coincide.

Example 1:

Input:
N = 5
start[] = {1,3,2,5,8,5}
end[] = {2,4,6,7,9,9}
Output: 4
Explanation:Perform the activites 1,2,4,5
 * */

//Solution: This problem is same as N meetings in a room problem
package misc;

import java.util.ArrayList;
import java.util.Arrays;


public class ActivitySelection {
	static int activitySelection(int start[], int end[], int n){
		Interval[] intervals = new Interval[n];
		ArrayList<Integer> activities = new ArrayList<>();
		for(int i=0; i<n; i++) {
			intervals[i] = new Interval(start[i], end[i], i);
			
		}
		
		Arrays.sort(intervals, (interval1, interval2) -> {
			
			return interval1.end - interval2.end;
		});
		
		activities.add(intervals[0].pos);
		int limit = intervals[0].end;
		for(int i=1; i<n; i++) {
			if(intervals[i].start >= limit) {
				limit = intervals[i].end;
				activities.add(intervals[i].pos);
			}
		}
//		for(int i=0; i<activities.size(); i++) {
//			System.out.println(activities.get(i) + 1 + " ");
//		}
		
		return activities.size();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static class Interval {
		public  int start;
		public  int end;
		public int pos;
		public Interval(int s, int e, int p) {
			this.start = s;
			this.end = e;
			this.pos = p;
		}
	} 

}
