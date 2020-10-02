//https://leetcode.com/problems/burst-balloons/
/*
Given n balloons, indexed from 0 to n-1. 
Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. 
If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. 
After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:
You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * */

/*
Solution:
	Let's say we are bursting balloon at ith index. Then we cannot have subproblems from 0..i-1 and i+1...n-1 
	since after bursting, the left and right balloons come adjacent.
	To have subproblems, we burst this balloon at the end after bursting the left part and the right part
	Then total value = value we get by bursting ith balloon at the end + value from the left sub part + value from the right sub part
					 = nums[i] * nums[L-1] * nums[R+1] + dp[L][i-1] + dp[i+1][R]
					 
	Note that we need to take care of boundary values in above equation.
	Reference: https://www.youtube.com/watch?v=8RIqJDDgtU8   [errichto]
 * */
package leetcode;

public class BurstBalloonsDP {
	
	public int maxCoins(int[] nums) {
		if(nums == null || nums.length == 0)return 0;
		int n = nums.length;
		int [][]dp =  new int[n][n];
		for(int L=n-1; L>=0; L--) {
			for(int R=L; R<n; R++) {
				
				for(int i=L; i<=R; i++) {
					dp[L][R] = Math.max(dp[L][R], 
							nums[i] * (L==0?1:nums[L-1]) * (R==n-1?1:nums[R+1]) + (L<=i-1?dp[L][i-1]:0) + (i+1<=R?dp[i+1][R]:0));
				}
			}
		}
		return dp[0][n-1];
    }

	public static void main(String[] args) {	
		int []nums = {3,1,5,8};
		BurstBalloonsDP obj = new BurstBalloonsDP();
		System.out.println(obj.maxCoins(nums));

	}

}
/*
Explanation:
	let's say we have below array:
	value: 3  1  5  2  9  8
	index: 0  1  2  3  4  5
	
	Suppose L=2 ; R=5 and i=4
	1. So we have nums[i] * nums[L-1] * nums[R+1]  which we get by bursting nums[i] at the last
	Also we have to take the amount we get from bursting the balloons on left and right of nums[4]:
	2. Left amount = dp[L][i-1]
	3. Right amount = dp[i+1][R]
	In 1,2 and 3 above we have to take care of boundary conditions.

NOTE:
	We need the loop like this, i.e. L starts from the end.
	for(int L=n-1; L>=0; L--) {
				for(int R=L; R<n; R++) {
	
	This is because:
	Let's say we want to calculate dp[2][5], i.e. L=2 and R=5; and we have i=3. Already we done with i=2 in prev iteration
	5  2  9  8
	
	Then we need dp[2][2] and dp[4][5]. We will have this dp[4][5] only when we have already calculated by starting L from the end of the array
	
	Instead if we would use loop like this: for(L=0; L<n; L++), we would not have calculated dp[2][5]



 * */
