//https://leetcode.com/problems/sudoku-solver/
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

	Each of the digits 1-9 must occur exactly once in each row.
	Each of the digits 1-9 must occur exactly once in each column.
	Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
	
The '.' character indicates empty cells.
 * */

//Solution: Here we use sets for each row, column and box to store values which are present on the sudoku so far.
//Idea to use sets based on https://leetcode.com/problems/valid-sudoku/
package leetcode;

import java.util.HashSet;

public class SudokuSolver2 {
	private HashSet<Character> [] rowSets = new HashSet[9];
	private HashSet<Character> [] colSets = new HashSet[9];
	private HashSet<Character> [] boxSets = new HashSet[9];
	
	public void solveSudoku(char[][] board) {
		populateInitialValues(board);
		utl(board);
	}
	
	/*
	 * Populate the values which are given for the sudoku.
	 * Here in question, the sudoku if valid. Else we would be returning false ( to denote that given sudoku is invalid)
	 * when we found that a value already existed in either of the three sets 
	 * 
	 * */
	private void populateInitialValues(char[][] board) {
		//create the sets
		for(int i=0; i<9; i++) {
			rowSets[i] = new HashSet<>();
			colSets[i] = new HashSet<>();
			boxSets[i] = new HashSet<>();
		}
		
		//populate the values in the sets
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				char curr_val = board[i][j];
				if(curr_val != '.') {
					rowSets[i].add(curr_val);
					colSets[j].add(curr_val);
					int boxNumber = i/3 * 3 + j/3;
					boxSets[boxNumber].add(curr_val);
				}
				
			}
		}
	}
	
	private boolean utl(char[][] board) {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(board[i][j] == '.') {
					for(char k = '1'; k<= '9'; k++) {
						if(isValid(board, i, j, k)) {
							board[i][j] = k;
							rowSets[i].add(k);
							colSets[j].add(k);
							int boxNumber = i/3 * 3 + j/3;
							boxSets[boxNumber].add(k);
							
							//recurse
							if(utl(board)) {
								return true;
							}
							
							//backtrack
							board[i][j] = '.';
							rowSets[i].remove(k);
							colSets[j].remove(k);
							boxSets[boxNumber].remove(k);
						}
					}
					return false;		//no value from 1 till 9 can be placed on current cell.
				}
			}
		}
		return true;
	}
	
	//valid if the row , column or the box do not have a value equal to target value
	private boolean isValid(char[][] board, int row, int col, char target) {
		int boxNumber = row/3 * 3 + col/3;
		if(rowSets[row].contains(target) || colSets[col].contains(target) || boxSets[boxNumber].contains(target)) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		char [][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		SudokuSolver2 ob = new SudokuSolver2();
		ob.solveSudoku(board);

	}

}
