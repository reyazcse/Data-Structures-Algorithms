//Longest Increasing Subsequence (a[i] < a[j] for j > i | strictly increasing)

package misc;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
	
	/**************************************RECURSIVE****************************************************************/
	
	static int longestSubsequence(int size, int a[]){
		int lis = Integer.MIN_VALUE;
		for(int i=0; i<size; i++) {
			lis = Math.max(lis, utl(a,i));
		}
		return lis;
	}
	
	private static int utl(int[]a, int idx) {
		if(idx == a.length) {
			return 0;
		}
		int currMax = 0;
		for(int i=idx+1; i<a.length; i++) {
			if(a[i] > a[idx]) {
				currMax = Math.max(currMax, utl(a, i));
			}
		}
		return 1 + currMax;
	}
	
	/******************************************TOP DOWN *************************************************************/
	
	//O(n^2) time and O(n) space
	static int longestSubsequenceTopDown(int size, int a[]) {
		int lis = Integer.MIN_VALUE;
		int []dp = new int[size];
		for(int i=0; i<size; i++) {
			dp[i] = -1;
		}
		for(int i=0; i<size; i++) {
			lis = Math.max(lis, utlTopDown(a,i, dp));
		}
		return lis;
	}
	
	private static int utlTopDown(int[]a, int idx, int[]dp) {
		if(idx == a.length) {
			return 0;
		}
		
		if(dp[idx] != -1) {
			return dp[idx];
		}
		int currMax = 0;
		for(int i=idx+1; i<a.length; i++) {
			if(a[i] > a[idx]) {
				currMax = Math.max(currMax, utlTopDown(a, i, dp));
			}
		}
		dp[idx] = 1 + currMax;
		return dp[idx];
	}
	
	
	/*********************************** BOTTOM UP ***********************************************************************/
	//O(n^2) time and O(n) space
	
	static int longestSubsequenceBottomUp(int size, int a[]) {
		int lis = Integer.MIN_VALUE;
		int []dp = new int[size];
		for(int i=0; i<size; i++) {
			dp[i] = 1;
		}
		for(int i=1; i<size; i++) {
			for(int j=0; j<i; j++) {
				if(a[j] < a[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
		}
		for(int i=0; i<size; i++) {
			lis = Math.max(lis, dp[i]);
		}
		return lis;
	}
	
	/**************************************** PRINTING THE LIS ************************************************************/
	static void printLIS(int []a, int size) {
		int lis = Integer.MIN_VALUE;
		int pos = size;
		int []dp = new int[size];
		for(int i=0; i<size; i++) {
			dp[i] = 1;
		}
		for(int i=1; i<size; i++) {
			for(int j=0; j<i; j++) {
				if(a[j] < a[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
		}
		for(int i=0; i<size; i++) {
			lis = Math.max(lis, dp[i]);
			pos = i;
		}
		List<Integer> result = new ArrayList<>();
		result.add(a[pos]);
		
		for(int i = pos-1; i>=0; i--) {
			if(dp[i]+1 == dp[pos] && a[i] < a[pos]) {
				result.add(a[i]);
				pos = i;
			}
		}
		
		//below also works
		
//		while(lis > 0) {
//			
//			for(int i=pos-1; i>=0; i--) {
//				if(a[i] < a[pos] && dp[i]+1 == dp[pos]) {
//					result.add(a[i]);
//					pos = i;
//					lis--;
//					break;
//				}
//			}
//		}
		for(int elt : result) {
			System.out.println(elt);
		}
	} 
	
	public static void main(String[] args) {
		int a [] = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		//System.out.println(longestSubsequence(a.length, a));
		printLIS(a, a.length);

	}

}
