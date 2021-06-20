//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/

/*
Here we do optimization while checking if it is safe to place the queen in current cell.
The idea is not to check every element in right and left diagonal instead use property of diagonals:
	1.The sum of i and j is constant and unique for each right diagonal where i is the row of element and j is the
	column of element.
	2.The difference of i and j is constant and unique for each left diagonal where i and j are row and column of element respectively.


So we store the sum and difference for each cell.
Also when we are at a cell, we need to check if this column is safe (if cells above do not have a queen).
We use 'upper' for that

We add n-1 so as to avoid negative indices: row - col + n-1 in upperLeftD array

NOte:
1.	We can use bitset array instead of integer arrays for  upperLeftD, upperRightD and upper
	or we can use boolean arrays also which is less space efficient than bitset but more than
	integer arrays.
	

 * */
package misc;

public class NQueens2 {
	public void NQueens(int n) {
		int[][] board = new int[n][n];
		int[] upperLeftD = new int[2 * n];
		int[] upperRightD = new int[2 * n];
		int[] upper = new int[n];
		if(NQueensUtl(board, 0, n, upperLeftD, upperRightD, upper)) {
			printBoard(board, n);
		}
	}
	
	private boolean NQueensUtl(int[][] board, int row, int n, int[]upperLeftD, int[]upperRightD, int[]upper) {
		if(row == n) {
			return true;
		}
		for(int col=0; col<n; col++) {
			if(upperLeftD[row - col + n-1] != 1 && upperRightD[row + col] != 1 && upper[col] != 1) {	//if safe
				//place queen
				board[row][col] = 1;			//board is required only for printing. No need of this for problem
				upperLeftD[row - col + n-1] = upperRightD[row + col] = upper[col] = 1;
				
				//recurse
				if(NQueensUtl(board, row+1, n, upperLeftD, upperRightD, upper) == true) {
					return true;
				}
				
				//backtrack
				board[row][col] = 0;		//board is required only for printing. No need of this for problem
				upperLeftD[row - col + n-1] = upperRightD[row + col] = upper[col] = 0;
			}
			
		}
		return false;
	}
	
	private void printBoard(int[][] board, int n) {
		for(int i=0; i<n; i++) {
			System.out.println();
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j] + "  ");
			}
		}
	}
	
	public static void main(String[] args) {
		NQueens2 ob = new NQueens2();
		ob.NQueens(4);
	}

}
