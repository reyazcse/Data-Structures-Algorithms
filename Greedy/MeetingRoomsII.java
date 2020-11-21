/*

	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] 
	find the minimum number of conference rooms required.
	Input:
		[[7,10], [2,4]]
	Output:
		1
Solution:
	We sort the intervals by their start time.
	We also use min heap which stores the end times of the intervals which has been accessed so far
	When we are at an interval, we check if the start time of current interval is >= root of minHeap(minimum end point)
	If it is so, it means no new room is needed. We will simple add end time of this interval to minHeap
	However if it is not so, then we need a room for current interval. We also add end time of current interval in this case

Complexity: O(NlogN) time where N is size of intervals
			O(N) space
	
 * */
package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {
	public int minMeetingRooms(Interval[] intervals) {
		if(intervals == null || intervals.length == 0)
			return 0;
		int rooms=0;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		//sort using start times
		Arrays.sort(intervals, (int1,int2) -> int1.start-int2.start);
		minHeap.add(intervals[0].end);
		rooms=1;
		for(int i=1; i< intervals.length; i++ ) {
			//no new room needed
			if(intervals[i].start >= minHeap.peek()) {
				minHeap.remove();   //remove as this room is now occupied by current interval
				
			} else {
				rooms++;
			}
			minHeap.add(intervals[i].end);
		}
		return rooms;
	}
	public static void main(String[] args) {
		Interval [] intervals = new Interval[4];
		intervals[0] = new Interval(0,30);
		intervals[1] = new Interval(5,10);
		intervals[2] = new Interval(15,40);
		intervals[3] = new Interval(30,35);
		MeetingRoomsII obj = new MeetingRoomsII();
		System.out.println(obj.minMeetingRooms(intervals));
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
