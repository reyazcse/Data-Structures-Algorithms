//https://leetcode.com/problems/coin-change-2/
/*
You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 * */

/*
Solution:
	Let's say amount = 7 and coins = [2,3,5].
	Then we have return count of below combinations i.e. 2
		2,2,3
		2,5
	
	So create dp array of length amount+1.
	First calculate for 2, then 3 and then 5 so that only combinations are there.
	Had we to calculate permutations, then for each dp cell, we would consider all the coins. See CoinChangePermutations.java for this
	
	In these problems, there are repetitions since a coin can be used multiple number of times. There is a similar problem Target Sum Problem where
	we have combinations but no repetitions are allowed. In that we need to use 2D array. But here in combinations 1D array solves the problem.
	See this for explanation: https://www.youtube.com/watch?v=JyyS9mlMcr4  (PepCoding)
	
	Note: 
		There are two other methods to solve this: using loop and using include/exclude method. See CoinChange.java and CoinChange2.java
 * */
package misc;

public class CoinChangeCombinations {
	
	public int change(int S, int[] coins) {
        int[]dp = new int[S+1];
        dp[0] = 1;
        
        //try each coin for all the positions for combinations
        for(int i=0; i<coins.length; i++) {
            for(int j=coins[i]; j<dp.length; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }
        return dp[S];
      
    }

}

/*
Some boundary test cases:
S=0
coins=[]
Output: 1

S=0
coins=[2,3]
Output: 1

S=5
coins=[]
Output: 0
 * */
