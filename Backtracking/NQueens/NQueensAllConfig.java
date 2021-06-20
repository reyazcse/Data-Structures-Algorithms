//Print total number of configurations possible for N queens.

//

/*
Solution: O(n^n) time complexity

For each row, we can place the queen in nC1 time.
And there are n rows.
Hence nC1 * nC1 ......n time = n^n time

Note:
There is a more efficient solutio to this problem using bit masking but it's not intuitive
 * */

package misc;

public class NQueensAllConfig {

	public int NQueensAllConfig(int n) {
		int[][] board = new int[n][n];
		int totalConfigs = NQueensAllConfigUtl(board, 0, n);
		return totalConfigs;
	}

	private int NQueensAllConfigUtl(int[][] board, int row, int n) {
		//successful configuration
		if(row == n) {
			printBoard(board, n);
			return 1;
		}
		int cnt = 0;							//count of possible configurations with queen in current 'row'
		for(int col=0; col < n; col++) {
			if(canBePlaced(board, row, col)) {

				//place current queen
				board[row][col] = 1;

				//recurse
				cnt += NQueensAllConfigUtl(board, row+1, n);

				//backtrack
				board[row][col] = 0;
			}
		}
		return cnt;
	}


	/*
	 * Checks if upper cells in current col and upper left and upper right diagonal cells are safe
	 * Takes O(n) time
	 * Note: this can be optimised to O(1). Refer NQueens2. If we do that then new time  = 1/n of previour time 
	 * */
	private boolean canBePlaced(int[][] board, int row, int col) {
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

	private void printBoard(int[][] board, int n) {
		System.out.println();
		for(int i=0; i<n; i++) {
			System.out.println();
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j] + "  ");
			}
		}
	}

	public static void main(String[] args) {
		NQueensAllConfig ob = new NQueensAllConfig();
		System.out.println(ob.NQueensAllConfig(4));           //ans = 2
		System.out.println(ob.NQueensAllConfig(8));           //ans = 92
		
		
	}

}
