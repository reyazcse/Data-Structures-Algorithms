//This solution is based on below link
//https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
package misc;

public class MinimumCoins2 {
	//RECURSIVE
	
	/*
	 * M = length of coins array
	 * V = value
	 * */
	public int minCoinsRecursive(int coins[], int M, int V) 
	{ 
		int res = utlRecursive(coins, M, V);
		return res == Integer.MAX_VALUE ? -1 : res;
	}
	
	private int utlRecursive(int []coins, int M, int V) {
		if(V == 0) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		for(int i=0; i<M; i++) {
			if(coins[i] <= V) {
				int sub_res = minCoinsRecursive(coins, M, V - coins[i]);
				if(sub_res != Integer.MAX_VALUE) {
					res = Math.min(res, 1+sub_res);
				}
			}
		}
		return res;
	}
	
	/*********************************************TOP DOWN*******************************************************/
	/*
	 * m = length of coins array
	 * v = value
	 * 
	 * O(mv) time and O(v) space
	 * */
	public int minCoinsTopDown(int [] coins, int m, int v) {
		int []dp = new int[v+1];
		for(int i=1; i<dp.length; i++) {
			dp[i] = -1;
		}
		int res = utlTopDown(coins, v, dp);
		return res == Integer.MAX_VALUE ? -1 : res;
	}
	private int utlTopDown(int[] coins, int v, int[] dp) {
		if(v == 0) {
			return 0;
		}
		if(dp[v] != -1) {
			return dp[v];
		}
		int res  = Integer.MAX_VALUE;
		for(int i=0; i<coins.length; i++) {
			if(coins[i] <= v) {
				int sub_res = utlTopDown(coins, v-coins[i], dp);
				if(sub_res != Integer.MAX_VALUE) {
					res = Math.min(res, 1+sub_res);
				}
			}
		}
		dp[v] = res;
		return res;
	}
	
	/*********************************************BOTTOM UP*******************************************************/
	/*
	 * m = length of coins array
	 * v = value
	 * 
	 * O(mv) time and O(v) space
	 * */
	public int minCoinsBottomUp (int[] coins, int m, int v) {
		int []dp = new int[v+1];
		dp[0] = 0;
		for(int i=1; i<=v; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		for(int i=1; i<=v; i++) {
			for(int j=0; j<m; j++) {
				//coins[j] is small and previous result is not inf
				if(coins[j] <= i && dp[i-coins[j]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1 + dp[i-coins[j]]);
				}
			}
		}
		return dp[v] == Integer.MAX_VALUE ? -1 : dp[v];
	}
	public static void main(String[] args) {
//		int[] coins = {9,5,6,1};
//		int V = 11;
		
		int[] coins = {5,6,1,2};
		int V = 11;
		
//		int [] coins = {9, 2, 11, 5, 14, 17, 8, 18};
//		int V = 39;
		MinimumCoins2 ob = new MinimumCoins2();
		System.out.println(ob.minCoinsRecursive(coins, coins.length, V));
		System.out.println(ob.minCoinsTopDown(coins, coins.length, V));

	}

}
