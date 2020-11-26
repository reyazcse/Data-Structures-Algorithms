//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
/*
The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.

 * */


package misc;

public class NQueens {
	public void NQueens(int n) {
		int[][] board = new int[n][n];
		if(NQueensUtl(board, 0, n)) {
			printBoard(board, n);
		}
	}
	
	private boolean NQueensUtl(int[][] board, int row, int n) {
		//all queens placed
		if(row == n) {
			return true;
		}
		//check in each col for this queen
		for(int col = 0; col<n; col++) {
			if(isSafe(board, row, col)) {
				//place queen
				board[row][col] = 1;
				
				//recurse
				if(NQueensUtl(board, row+1, n) == true) {	//if a solution is found with this queen at current cell
					return true;
				}
				
				//backtrack
				board[row][col] = 0;			
			}
		}
		return false;			//this queen cannot be placed on this 'row'
	}
	
	private void printBoard(int[][] board, int n) {
		for(int i=0; i<n; i++) {
			System.out.println();
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j] + "  ");
			}
		}
	}
	
	//check if upper cells in current col and upper left and upper right diagonal cells are safe
	private boolean isSafe(int[][] board, int row, int col) {
		int r = row-1, c = col;
		//upper cells
		while(r >=0) {
			if(board[r][c] == 1) {
				return false;
			}
			r--;
		}
		r = row-1;
		c = col-1;
		
		//upper left diagonal
		while(r >=0 && c>=0) {
			if(board[r][c] == 1) {
				return false;
			}
			r--;
			c--;
		}
		
		//upper right diagonal
		r = row-1;
		c = col+1;
		while(r >=0 && c < board[0].length) {
			if(board[r][c] == 1) {
				return false;
			}
			r--;
			c++;
		}
		return true;
	}
	public static void main(String[] args) {
		NQueens ob = new NQueens();
		ob.NQueens(4);

	}

}
