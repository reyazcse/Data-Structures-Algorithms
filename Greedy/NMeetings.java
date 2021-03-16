//https://www.geeksforgeeks.org/find-maximum-meetings-in-one-room/
/*
There is one meeting room in a firm. There are N meetings in the form of (S[i], F[i]) where S[i] is start time of meeting i and F[i] is finish time of meeting i.

What is the maximum number of meetings that can be accommodated in the meeting room?

Example 1:

Input:
N = 6
S[] = {1,3,0,5,8,5}
F[] = {2,4,6,7,9,9}
Output: 1 2 4 5
Explanation: Four meetings can held with
given start and end timings.

 * */

/*
Solution:
	Sort the intervals by end times
	Take the first interval.
	Take limit =  end of first interval
	Then for each interval from i=1 till last
		If start of current interval is >= limit 
		then include this meeting in result and take limit as end time of current interval
		
	Complexity: O(n log n) for sorting
 * */
package misc;

import java.util.ArrayList;
import java.util.Arrays;

public class NMeetings {
	static void maxMeetings(int start[], int end[], int n) {
		Interval[] intervals = new Interval[n];
		ArrayList<Integer> meetingNumbers = new ArrayList<>();
		for(int i=0; i<n; i++) {
			intervals[i] = new Interval(start[i], end[i], i);
			
		}
		
		Arrays.sort(intervals, (interval1, interval2) -> {
			
			return interval1.end - interval2.end;
		});
		
		meetingNumbers.add(intervals[0].pos);
		int limit = intervals[0].end;
		for(int i=1; i<n; i++) {
			if(intervals[i].start >= limit) {
				limit = intervals[i].end;
				meetingNumbers.add(intervals[i].pos);
			}
		}
		for(int i=0; i<meetingNumbers.size(); i++) {
			System.out.println(meetingNumbers.get(i) + 1 + " ");
		}
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
/*
NOte in the question it is not mentioned which meeting room to take if end times of both are same.
If we take based on position i.e. first meeting will be taken when end times of both are same irrespective of start times, then
we have to change the comparator function also. 
 * */
