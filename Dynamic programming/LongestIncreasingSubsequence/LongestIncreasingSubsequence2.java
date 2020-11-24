//Longest Increasing Subsequence

/*
Solution: O(n log n) time
	Suppose int a [] = {2,5,3,7,11,8,10,13,6}
	We will build the lis sequence.
	When we are at element elt in the input array, 
		if it is greater than than previous element in sequence then place it in the sequence
		else find the position i in sequence such that sequence[i] is smaller than elt. If i==-1 then we place elt at
		i=0. 
		While placing element, we remove the old element at i+1 th position.
		
	References: take u forward youtube | https://www.youtube.com/watch?v=TocJOW6vx_I
	
 * */

package misc;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence2 {
	static int longestSubsequence(int size, int a[]){
		List<Integer> sequence = new ArrayList<>();
		sequence.add(a[0]);
		for(int i=1; i<size; i++) {
			if(a[i] > sequence.get(sequence.size()-1)) {
				sequence.add(a[i]);
			}else {
				int pos = getFirstLessElementIndex(sequence, 0, sequence.size()-1, a[i]);
				//if(pos != -1) {					//if pos == -1, then we delete 0th element and add there a[i]
					sequence.remove(pos+1);
					sequence.add(pos+1, a[i]);
					
				//}
			}
		}
		return sequence.size();
		
	}
	//if we have 2 5 and we want to search for position to place 3, then it will return position of 2 here
	//for target=2, it will return -1
	private static int getFirstLessElementIndex(List<Integer> seq, int lo, int hi, int target) {
		while(lo <= hi) {
			int mid = lo + (hi-lo)/2;
			if(seq.get(mid) >= target) {		//if mid element is greater or equal, then move to left half
				hi = mid-1;						// we move to right in case of equal also as we look for strictly increasing LIS
			}else {								//else mid element is less than target
				if(seq.get(mid+1) < target) {   //if next of mid also greater, then go to the right half
					lo = mid+1;
				}
				else {
					return mid;
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		//int a [] = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		//int a [] = {2,5,3,7,11,8,10,13,6};
		//int a[] = {2,2,2,2,2}
		int a [] = {2,2,3,2};
		System.out.println(longestSubsequence(a.length, a));

	}

}
