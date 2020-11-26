//https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
/*
Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change?
Examples:


Input: coins[] = {9, 6, 5, 1}, V = 11
Output: Minimum 2 coins required
We can use one coin of 6 cents and 1 coin of 5 cents
 * */

/*
Solution: 
	We had used a greedy algo to solve it but that algo did not give correct answer for certain coin denominations
	So here we use dynamic programming to solve this.
	At each coin, we can either include it or exclude it.
	So we take minimum of these two scenarios.
	
		
 * */
package misc;

public class MinimumCoins {
	
	//RECURSIVE SOLUTION
	
	public int minCoinsRecursive(int coins[], int M, int V) 
	{ 
		int minCoins =  utl(coins, M, V);
		if(minCoins == Integer.MAX_VALUE) {   //if no change is possible for V
			return -1;
		}
		return minCoins;
	}
	
	private int utl(int[] coins, int pos, int V) {
		//base case when we are able to get change
		if(V == 0) {
			return 0;
		}
		
		//no change available
		if(pos == 0 || V < 0) {
			return Integer.MAX_VALUE;
		}
		
		//including current coin
		int included = utl(coins, pos, V-coins[pos-1]);
		
		//excluding current coin
		int excluded = utl(coins, pos-1, V);
		
		//included and excluded both valid
		if(included != Integer.MAX_VALUE && excluded != Integer.MAX_VALUE) {
			return Math.min(1 + included, excluded);
		}
		//at least one is infinite
		if(included == Integer.MAX_VALUE) {
			return excluded;
		}else {
			return 1 + included;
		}
	}
	
	/**********************************************TOP DOWN**************************************************************/
	
	//O(MV) time and O(MV) space
	
	public int minCoinsTopDown(int coins[], int M, int V) 
	{ 
		int [][] dp = new int[M + 1][V + 1];
		init(dp);
		int minCoins =  utlTopDown(coins, M, V, dp);
		if(minCoins == Integer.MAX_VALUE) {			//if no change is possible for V
			return -1;
		}
		return minCoins;
	}
	
	private int utlTopDown(int[] coins, int pos, int V, int[][]dp) {
		if(V == 0) {
			return 0;
		}
		if(pos == 0 || V < 0) {
			return Integer.MAX_VALUE;
		}
		if(dp[pos][V] != -1) {
			return dp[pos][V];
		}
		int included = utlTopDown(coins, pos, V-coins[pos-1], dp);
		int excluded = utlTopDown(coins, pos-1, V, dp);
		
		int result = 0;
		if(included != Integer.MAX_VALUE && excluded != Integer.MAX_VALUE) {
			result = Math.min(1+included, excluded);
		}
		//at least one is inf
		else if(included != Integer.MAX_VALUE) {
			result = 1 +  included;
		}else {
			result = excluded;
		}
		dp[pos][V] = result;
		return result;
	}
	
	private void init(int [][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
	}
	
	private void print(int [][] dp) {
		for(int i=0; i<dp.length; i++) {
			System.out.println();
			for(int j=0; j<dp[0].length; j++) {
				System.out.print(dp[i][j] + "   ");
			}
		}
	}
	
	
	/**********************************************BOTTOM UP**************************************************************/
	
	//O(MV) time and O(MV) space
	
	public int minCoinsBottomUp(int coins[], int M, int V) 
	{ 
		int [][] dp = new int[M + 1][V + 1];
		
		dp[0][0] = 0;						// V = 0 and 0 coins	
		for(int i=1; i<dp.length; i++) {	// V = 0 hence 0 coins needed
			dp[i][0] = 0;
		}
		for(int i=1; i<dp[0].length; i++) { // V = 1 and 0 coins, then inf number of coins
			dp[0][i] = Integer.MAX_VALUE;
		}
		
		
		for(int i=1; i<dp.length; i++) {
			
			for(int j=1; j<dp[0].length; j++) {
				int excluded = dp[i-1][j];
				int x = (j-coins[i-1])>=0 ? dp[i][j - coins[i-1]] : Integer.MAX_VALUE;	//check boundary
				int included = x;
				int result = 0;
				if(included != Integer.MAX_VALUE && excluded != Integer.MAX_VALUE) {
					result = Math.min(1+included, excluded);
				}
				//at least one is inf
				else if(included != Integer.MAX_VALUE) {
					result = 1 +  included;
				}else {
					result = excluded;
				}
				dp[i][j] = result;
			}
		}
		
		return dp[M][V] == Integer.MAX_VALUE? -1 : dp[M][V];
	}
	
	public static void main(String[] args) {
//		int[] coins = {9,5,6,1};
//		int V = 11;
		
//		int[] coins = {5,3,1,2};
//		int V = 11;
		
		int [] coins = {9, 2, 11, 5, 14, 17, 8, 18};
		int V = 39;
		MinimumCoins ob = new MinimumCoins();
		System.out.println(ob.minCoinsRecursive(coins, coins.length, V));
		System.out.println(ob.minCoinsTopDown(coins, coins.length, V));
		System.out.println(ob.minCoinsBottomUp(coins, coins.length, V));
	}

}
