//https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
//https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/

package leetcode;

public class MinimumInsertionPalindrome {
	//Recursive approach:
	public int minInsertions(String s) {
		if(s == null || s.length() == 0) {		//base case
			return 0;
		}
		return minInsertionsUtl(s, 0, s.length()-1);
	}
	
	private int minInsertionsUtl(String s, int start, int end) {

		if(start == end) {
			return 0;
		}
		if(start == end-1) {			//length of string = 2
			return s.charAt(start) == s.charAt(end) ? 0 : 1;	//if both equal then 0 insertions else 1 insertion
		}
		
		//length of string is more than 2
		if(s.charAt(start) == s.charAt(end)) {
			return minInsertionsUtl(s, start+1, end-1);
		}
		
		return 1 + Math.min(minInsertionsUtl(s, start+1, end), minInsertionsUtl(s, start, end-1));
	}
	
	
	/*
		Top down approach:
		O(n^2) time and O(n^2) space where n = length of string
		
	 * */
	public int minInsertionsTopDown(String s) {
		if(s == null || s.length() == 0) {		//base case
			return 0;
		}
		int n = s.length();
		int[][] dp = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				dp[i][j] = -1;
			}
		}
		return topDownUtl(s, 0, n-1, dp);
	}
	
	private int topDownUtl(String s, int start, int end, int[][]dp) {
		
		int ans = 0;
		if(dp[start][end] != -1) {		//if already computed
			return dp[start][end];
		}
		else if(start == end) {
			ans = 0;
		}
		else if(start == end-1) {
			ans =  s.charAt(start) == s.charAt(end) ? 0 : 1;
		}
		
		else if(s.charAt(start) == s.charAt(end)) {
			ans =  topDownUtl(s, start+1, end-1, dp);
		}
		else {
			ans = 1 + Math.min(topDownUtl(s, start+1, end, dp), topDownUtl(s, start, end-1, dp));
		}
		
		dp[start][end] = ans;
		return ans;
	}
	
	
	/*
	 	Bottom up approach.
	 	O(n^2) time and O(n^2) space where n = length of string
	 	
	 	Suppose we have a string of length = 4.
	 	Then the table is filled as follows:
	 	(0,0) (1,1) (2,2) (3,3)     : gap = 0
	 	(0,1) (1,2) (2,3)			: gap = 1 
	 	(0,2) (1,3)					: gap = 2
	 	(0,3)						: gap = 3
		
	 * */
	public int minInsertionsBottomUp(String s) {
		if(s == null || s.length() == 0) {		//base case
			return 0;
		}
		int n = s.length();
		int[][] dp = new int[n][n];
		for(int i=0; i<n; i++) {
			dp[i][i] = 0;
		}
		for(int gap=1; gap < n; gap++) {
			for(int i=0; i+gap < n; i++) {
				int j = i+gap;
				if(s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i+1][j-1];			//for gap=1, dp[0][1] = dp[1][0] = 0, since all initial values in dp are 0
				}else {								
					dp[i][j] = 1 + Math.min(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
		return dp[0][n-1];
	}
	public static void main(String[] args) {
		String s = "abcd";
		MinimumInsertionPalindrome ob = new MinimumInsertionPalindrome();
		System.out.println(ob.minInsertionsBottomUp(s));
				

	}

}
//Note another solution is : find the LCS of string and its reverse. This will be answer.
