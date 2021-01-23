//https://leetcode.com/problems/coin-change/
/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.
 * */

/*
Solution:
	Here repetition is allowed. Hence we can use 1D array!
	If it was not allowed, then we would have to go for 2D table

	Permutations or combinations?
	It does not matter whether we evaluate one or other out of below
		11 = 5+6 Or 11 = 6+5
	Only thing that matters is we have to find number of coins. So for both ways above, answer will be 2

	Different solutions:
	1. Using 2D array and loop: recursive, top down and bottom up possible. Here we show only top down 
	2. Using 2D array and include/exclude method: recursive, top down and bottom up. Here we show only top down.
	3. Using 1D array and combinations
	4. Using 1D array and permutations

 * */
package misc;

public class CoinChangeMinimumCoins {
	//Solution 1: 2D array and loop : top down
	public int coinChange(int[] coins, int S) {
		Integer[][] dp = new Integer[S+1][coins.length];

		for(int i=0; i<dp[0].length; i++) {
			dp[0][i] = 1;						//for S=0, 1 change as base case
		}

		int result = utl(S, coins, dp, 0);
		if(result == Integer.MAX_VALUE){
			return -1;
		}
		return result;
	}

	private int utl(int S, int [] coins, Integer[][] dp, int index) {
		if(S == 0) {
			return 0;
		}
		if(S < 0 || index == coins.length) {
			return Integer.MAX_VALUE;
		}

		if(dp[S][index] != null) {
			return dp[S][index];
		}

		int ways = Integer.MAX_VALUE;
		for(int i=index; i<coins.length; i++) {
			int temp = utl(S - coins[i], coins, dp, index);
			if(temp != Integer.MAX_VALUE){
				ways = Math.min(ways, 1+temp);
			}
		}
		return dp[S][index] = ways;
	}


	//Solution 2: 2D array and include/exclude : top down
	public int coinChange_2(int[] coins, int S) {
		Integer[][] dp = new Integer[S+1][coins.length];

		for(int i=0; i<dp[0].length; i++) {
			dp[0][i] = 1;						//for S=0, 1 change as base case
		}

		int result = utl_2(S, coins, dp, 0);
		if(result == Integer.MAX_VALUE){
			return -1;
		}
		return result;
	}

	private int utl_2(int S, int[] coins, Integer[][] dp, int index) {
		if(S == 0) {
			return 0;
		}
		if(S < 0 || index == coins.length) {
			return Integer.MAX_VALUE;
		}

		if(dp[S][index] != null) {
			return dp[S][index];
		}

		int ways = Integer.MAX_VALUE;

		for(int i=index; i<coins.length; i++) {
			int include = utl_2(S-coins[i], coins, dp, index);
			int exclude = utl_2(S, coins, dp, index+1);

			int min = Math.min(include, exclude);
			if(min != Integer.MAX_VALUE) {
				ways = Math.min(ways, 1+min);
			}
		}
		return dp[S][index]=ways;
	}


	//Solution 3: Using 1D array and combinations. For combinations we have to use each coin for all positions. This method is the fastest
	//Only one of 11=6+5 or 11=5+6 will be computed
	public int coinChange_3(int[] coins, int S) {
		int[] dp = new int[S+1];
		dp[0] = 0;										//base case

		//initialize with maximum
		for(int i=1; i<dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		for(int i=0; i<coins.length; i++) {
			for(int j=coins[i]; j<dp.length; j++) {			//for j < coins[i], no solution. Hence start from position=coins[i]
				int temp = dp[j-coins[i]];
				if(temp != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], 1 + temp);
				}
			}
		}

		if(dp[S] == Integer.MAX_VALUE) {
			return -1;
		}
		return dp[S];
	}


	//Solution 3: Using 1D array and permutations. For permutations we have all coins for each position 
	//Both of 11=6+5 or 11=5+6 will be computed
	
	public int coinChange_4(int[] coins, int S) {
		int[] dp = new int[S+1];
		dp[0] = 0;										//base case

		//initialize with maximum
		for(int i=1; i<dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		for(int i=1; i<dp.length; i++) {
			for (int j=0; j<coins.length; j++) {
				if(coins[j] <= i) {
					int temp = dp[i - coins[j]];
					if(temp != Integer.MAX_VALUE) {
						dp[i] = Math.min(dp[i], 1 + temp);
					}
				}
			}
		}

		if(dp[S] == Integer.MAX_VALUE) {
			return -1;
		}
		return dp[S];
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
