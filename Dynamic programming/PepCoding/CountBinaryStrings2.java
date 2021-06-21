/*
1. You are given a number n.
2. You are required to print the number of binary strings of length n with no consecutive 0's.

Example: n = 2
		output = 3  {00, 01, 10}
 * */

/*
Solution:
	-----0 : If we place 0 at end, then string of length n-1 can end with either 0 or 1 : f(n-1)
	-----1 : If we place 1 at end, then n-1 place should be 0 only : f(n-2)
 * */
package misc;

public class CountBinaryStrings2 {
	
	/*
	 * Method 1: 
	 * 
	 * */
	private static int utl1(int n, int[] dp) {
	     if(n < 0) {
	         return 0;
	     }
	     
	     if(n == 0) {
	         return 1;
	     }
	     
	     if(n == 1) {
	         return 2;
	     }
	     
	     if(dp[n] != 0) {
	         return dp[n];
	     }
	     
	     return dp[n] = utl1(n-1, dp) + utl1(n-2, dp);
	 }
	
	
	/*
	 * Method 2
	 * Similar to Method 1
	 * */
	
	private static int utl2(int n, int[] dp) {
	     
	     
	     if(n == 1) {
	         return 2;			//{"0", "1"}
	     }
	     
	     if(n == 2) {			//{"00", "01", "10"}
	         return 3;
	     }
	     
	     if(dp[n] != 0) {
	         return dp[n];
	     }
	     
	     return dp[n] = utl2(n-1, dp) + utl2(n-2, dp);
	 }
	
	public static void main(String[] args) {
		int n=3;
		int[] dp = new int[n+1];						//dp array
		System.out.println(utl1(n, dp));
		System.out.println(utl2(n, dp));
	}

}
