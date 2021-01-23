/*
You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of PERMUTATIONS that make up that amount. 
You may assume that you have infinite number of each kind of coin.

Example 1:

Input: amount = 7, coins = [2,3,5]
Output: 5
Explanation: there are four ways to make up the amount:
7=2+2+3
7=2+3+2
7=3+2+2
7=2+5
7=5+2
 * */

/*
Solution:
	
	Create dp array of length amount+1.
	For each dp cell, we would consider all the coins. 
 * */
package misc;

public class CoinChangePermutations {
	
	public static int change(int S, int[] coins) {
        int[]dp = new int[S+1];
        dp[0] = 1;
        
        //at each position, try all the coins for permutations
        for(int i=1; i<dp.length; i++) {
        	for(int c=0; c<coins.length; c++) {
        		if(coins[c] <= i) {
        			dp[i] += dp[i-coins[c]];
        		}
        	}
        }
        return dp[S];
      
    }

	public static void main(String[] args) {
		int S = 7;
		int [] coins = {2,3,5};
		System.out.println(change(S, coins));

	}

}
