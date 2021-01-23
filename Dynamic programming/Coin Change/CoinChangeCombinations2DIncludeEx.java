//This question is same as asked in CoinChange.java. Here we solved using different technique
package misc;

//Solution: At each step, we either use current coin OR we do not use it
//Use of 2D array
public class CoinChangeCombinations2DIncludeEx {

	//Recursive
	public static int totalWaysToChange(int S, int [] coins) {
		return totalWaysToChangeUtl(S, coins, 0);
	}
	public static int totalWaysToChangeUtl (int S, int []coins, int index) {

		if (S == 0) return 1;


		if (S < 0 || index == coins.length) return 0;

		int ways = 0;
		ways += totalWaysToChangeUtl(S-coins[index], coins, index) + totalWaysToChangeUtl(S, coins, index+1);
		return ways;
	}

	/*
	 * Top Down approach
	 * Notes:
	 * 	index == coins.length check to pass base case when coins = []
	 * 	Using int table and checking table[S][index] != null for cached values give TLE. Hence Integer table is used as some entries may be 0.
	 * 	If you want to use int table, then initialize table with -1 and check the for precomputed value like this: table[S][index] != -1
	 * */

	public static int totalWaysToChangeTopDown(int S, int[] coins) {
		Integer table[][] = new Integer [S+1][coins.length];

		//when S=0, we found 1 way to change
		for(int i=0; i<table[0].length; i++)
			table[0][i] = 1;
		return totalWaysToChangeTopDownUtl(S, coins, table, 0);

		//return table[S][0];       gives error when coins = []
	}

	public static int totalWaysToChangeTopDownUtl(int S, int [] coins, Integer [][] table, int index) {
		if(S == 0) {
			return 1;
		}
		if (S <  0 || index == coins.length) return 0;
		if (table[S][index] != null) return table[S][index];

		int ways = 0;
		//we include coins[j] and also do not include coins[j]
		ways += totalWaysToChangeTopDownUtl(S-coins[index], coins, table, index) + totalWaysToChangeTopDownUtl(S, coins, table, index+1);
		table[S][index] = ways;
		return ways;
	}


	//bottom up: implementation is slightly different from what we did above. j -> j-1 and NOT j -> j+1
	public static int totalWaysToChangeBottomUp(int S, int [] coins) {
		int table[][] = new int [S+1][coins.length];
		//when S=0, we found a change
		for(int i=0; i<table[0].length; i++)
			table[0][i] = 1;

		for (int i=1; i<=S; i++) {
			for(int j=0; j < coins.length; j++) {
				//includes coins[j]
				int x = (i-coins[j])>=0 ? table[i-coins[j]][j] : 0;

				//do not include coins[j]
				int y = (j >= 1) ? table[i][j - 1] : 0;                 //we calculate j using value of j-1
				table[i][j] = x + y;
			}
		}

		//handle base cases like S=0, coins = [] | S=0, coins = [2,3] | S=5, coins = []
		if(S == 0) {
			return 1;
		}else if(coins.length == 0) {
			return 0;
		}
		return table[S][coins.length-1];
	}

	public static void main (String [] ags) {
		int [] coins = {1,2,3};
		int S = 4;
		System.out.println(totalWaysToChange(S, coins));
		System.out.println(totalWaysToChangeTopDown(S, coins));
		System.out.println(totalWaysToChangeBottomUp(S, coins));
	}

}
