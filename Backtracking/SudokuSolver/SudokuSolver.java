//https://leetcode.com/problems/sudoku-solver/
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

	Each of the digits 1-9 must occur exactly once in each row.
	Each of the digits 1-9 must occur exactly once in each column.
	Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
	
The '.' character indicates empty cells.
 * */

//Solution: backtracking method
package leetcode;

public class SudokuSolver {
	
	public void solveSudoku(char[][] board) {
		utl(board);
	}
	
	private boolean utl(char[][] board) {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(board[i][j] == '.') {
					for(char k = '1'; k<= '9'; k++) {
						if(isValid(board, i, j, k)) {
							board[i][j] = k;
							
							//recurse
							if(utl(board)) {
								return true;
							}
							
							//backtrack
							board[i][j] = '.';
						}
					}
					return false;		//no value from 1 to 9 can be placed on current cell.
				}
			}
		}
		return true;
	}
	
	//valid if the row , column or the box do not have a value equal to target value
	private boolean isValid(char[][] board, int row, int col, char target) {
		//check row
		for(int j=0; j<9; j++) {
			if(board[row][j] == target) {
				return false;
			}
		}
		
		//check col
		for(int i=0; i<9; i++) {
			if(board[i][col] == target) {
				return false;
			}
		}
		
		//check box
		int boxStartRow = row/3 * 3; 		// nth block * block_size
		int boxStartCol = col/3 * 3;
		
		for(int rowOffset = 0; rowOffset<3; rowOffset++) {
			for(int colOffset = 0; colOffset<3; colOffset++) {
				if(board[boxStartRow + rowOffset][boxStartCol + colOffset] == target) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		char [][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		SudokuSolver ob = new SudokuSolver();
		ob.solveSudoku(board);

	}

}
