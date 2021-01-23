//https://leetcode.com/problems/coin-change-2/
/*
Question: 
	Given a sum S and an array of coins A, find the number of ways we can make change of S using coins in A.
	Also note that we can use a coin any no of times.
For example:
	S=4, A = {1,2}
	Then output is 3: (1,1,1,1), (1,1,2), (2,2) 
 * */
package misc;

public class CoinChangeCombinations2DLoop {

	//Gives TLE
	public static int findTotalChanges(int S, int [] coins) {
		return findTotalChangesUtl(S, coins, 0);
	}


	public static int findTotalChangesUtl(int S, int []coins, int index) {
		//base case when sum is 0
		if (S == 0) return 1;
		if (S < 0 || index == coins.length) return 0;

		int ways=0;
		//we start from index to ensure that we do not have duplicates: 1,1,2 and 2,1,1, are duplicates for example
		for (int i=index; i< coins.length; i++) {
			ways += findTotalChangesUtl(S-coins[i], coins, i);
		}
		return ways;
	}
	public static void printTable(int [][] table) {
		for (int i=0; i< table.length; i++) {
			System.out.println();
			for (int j=0; j< table[0].length; j++) {
				System.out.print(table[i][j] + "  ");
			}
		}
	}



	/*
	 * Top Down approach
	 * Notes:
	 * 	index == coins.length check to pass base case when coins = []
	 * 	Using int table and checking table[S][index] != null for cached values give TLE. Hence Integer table is used as some entries may be 0.
	 * 	If you want to use int table, then initialize table with -1 and check the for precomputed value like this: table[S][index] != -1
	 * */

	public static int findTotalChangesTopDown(int S, int [] coins) {
		Integer [][] table = new Integer [S+1][coins.length];
		for (int i=0; i< table[0].length; i++) {
			table[0][i] = 1;										//For S=0, 1 way
		}
		return findTotalChangesTopDownUtl(S, coins, 0, table);
		//return table[S][0];                               give error when coins = []
	}
	public static int findTotalChangesTopDownUtl(int S, int[] coins, int index, Integer [][] table) {
		if (S == 0) return 1;
		if (S < 0 || index == coins.length ) return 0;
		if (table[S][index] != null ) return table[S][index];
		int ways = 0;

		//we start from index to ensure that we do not have duplicates: 1,1,2 and 2,1,1, are duplicates for example
		for(int i=index; i< coins.length; i++) {
			ways += findTotalChangesTopDownUtl(S-coins[i], coins, i, table);
		}
		table[S][index] = ways;
		return ways;
	}

	//bottom up
	public static int findTotalChangeBottomUp (int S, int [] coins) {
		int [][] table = new int [S+1][coins.length];

		for(int i=0; i< table[0].length; i++) {
			table[0][i] = 1;                         		//S=0, 1 way  
		}
		for(int i=1; i<=S; i++) {
			for (int j=0; j< table[0].length; j++) {
				//we start from j to ensure that we do not have permutations for example: 1,1,2 and 2,1,1
				for(int k=j; k < coins.length; k++) {
					if (i - coins[k] >= 0) {
						table[i][j] += table[i-coins[k]][k];
					}
				}

			}
		}
		
		//handle base cases
		if(S == 0) {
			return 1;
		}else if(coins.length == 0) {
			return 0;
		}
		else{
			return table[S][0];
		}
	}

	public static void main (String [] ags) {
		int [] coins = {1,2,3};
		int S = 4;
		System.out.println(findTotalChanges(S, coins));
		System.out.println(findTotalChangesTopDown(S, coins));
		System.out.println(findTotalChangeBottomUp(S, coins));
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