/*
Question: 
	Given a sum S and an array of coins A, find the number of ways we can make change of S using coins in A.
	Also note that we can use a coin any no of times.
For example:
	S=4, A = {1,2}
	Then output is 3: (1,1,1), (1,1,2), (2,2) 
 * */
package misc;

public class CoinChange {
	
	public static int findTotalChanges(int S, int [] coins) {
		return findTotalChangesUtl(S, coins, 0);
	}

	
	 public static int findTotalChangesUtl(int S, int []coins, int index) {
		//base case when sum is 0
		if (S == 0) return 1;
		if (S < 0) return 0;
		
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
	 //topdown
	 public static int findTotalChangesTopDown(int S, int [] coins) {
		 	int [][] table = new int [S+1][coins.length];
		 	for (int i=0; i< table[0].length; i++) {
		 		table[0][i] = 1;
		 	}
			findTotalChangesTopDownUtl(S, coins, 0, table);
			//printTable(table);
			return table[S][0];
		}
	 public static int findTotalChangesTopDownUtl(int S, int[] coins, int index, int [][] table) {
		 if (S == 0) return 1;
		 if (S < 0 ) return 0;
		 if (table[S][index] != 0 ) return table[S][index];
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
			 table[0][i] = 1;
		 }
		 for(int i=1; i<=S; i++) {
			 for (int j=0; j< table[0].length; j++) {
				//we start from j to ensure that we do not have duplicates: 1,1,2 and 2,1,1, are duplicates for example
				 for(int k=j; k < coins.length; k++) {
					 if (i - coins[k] >= 0) {
						 table[i][j] += table[i-coins[k]][k];
					 }
				 }
				 
			 }
		 }
		 //printTable(table);
		 return table[S][0];
	 }
	 
	public static void main (String [] ags) {
		int [] coins = {1,2};
		int S = 2;
		System.out.println(findTotalChanges(S, coins));
		System.out.println(findTotalChangesTopDown(S, coins));
		System.out.println(findTotalChangeBottomUp(S, coins));
	}

}
