//This question is same as asked in CoinChange.java. Here we solved using different technique
package misc;

//Solution: At each step, we either use current coin OR we do not use it
public class CoinChange2 {
	public static int totalWaysToChange(int S, int [] coins) {
		return totalWaysToChangeUtl(S, coins, 0);
	}
	public static int totalWaysToChangeUtl (int S, int []coins, int index) {
		if (index == coins.length) {
			if (S == 0) return 1;
			return 0;
		}
		//this is needed as we are not doing (index + 1) in one of the calls
		if (S < 0) return 0;
		
		int ways = 0;
		ways += totalWaysToChangeUtl(S-coins[index], coins, index) + totalWaysToChangeUtl(S, coins, index+1);
		return ways;
	}
	
	//topdown
	public static int totalWaysToChangeTopDown(int S, int[] coins) {
		int table[][] = new int [S+1][coins.length];
		//when S=0, we found a change
		for(int i=0; i<table[0].length; i++)
			table[0][i] = 1;
		totalWaysToChangeTopDownUtl(S, coins, table, 0);
		//printTable(table);
		return table[S][0];
	}
	
	/*
	 * public static void printTable(int [][] table) { for (int i=0; i<
	 * table.length; i++) { System.out.println(); for (int j=0; j< table[0].length;
	 * j++) { System.out.print(table[i][j] + "  "); } } }
	 */
	public static int totalWaysToChangeTopDownUtl(int S, int [] coins, int [][] table, int index) {
		if (index == coins.length) {
			if (S == 0) return 1;
			return 0;
		}
		if (S <  0) return 0;
		if (table[S][index] != 0) return table[S][index];
		
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
		//printTable(table);
		return table[S][coins.length-1];
	}
	
	public static void main (String [] ags) {
		int [] coins = {1,2};
		int S = 10;
		System.out.println(totalWaysToChange(S, coins));
		System.out.println(totalWaysToChangeTopDown(S, coins));
		System.out.println(totalWaysToChangeBottomUp(S, coins));
	}

}
