//https://leetcode.com/problems/sudoku-solver/
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

	Each of the digits 1-9 must occur exactly once in each row.
	Each of the digits 1-9 must occur exactly once in each column.
	Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
	
The '.' character indicates empty cells.
 * */


/*
Solution:
	We solve it similar to NQueen problem. There are 81 cells in the matrix.
	So we start from 0 and when we get 81, that means we have a valid answer.
	Here from an index of a cell, we get the row and column number of the cell:
	row = index / 9
	column = index % 9
	
	
	//Idea to use sets based on https://leetcode.com/problems/valid-sudoku/
 * */
package leetcode;

import java.util.HashSet;

public class SudokuSolver3 {
	
	private HashSet<Character> [] rowSets = new HashSet[9];
	private HashSet<Character> [] colSets = new HashSet[9];
	private HashSet<Character> [] boxSets = new HashSet[9];
	
	public void solveSudoku(char[][] board) {
		populateInitialValues(board);
		utl(board, 0);
	}
	
	private boolean utl(char[][] board, int index) {
		if(index == 81) {
			return true;
		}
		
		//get row and column index for current index number
		int r = index / 9;
		int c = index % 9;
		if(board[r][c] == '.') {
			for(char k ='1'; k <= '9'; k++) {
				if(isValid(board, r, c, k)) {
					board[r][c] = k;
					rowSets[r].add(k);
					colSets[c].add(k);
					int boxNumber = r/3 * 3 + c/3;
					boxSets[boxNumber].add(k);
					
					//recurse
					if(utl(board, index+1)) {
						return true;
					}
					
					//backtrack
					board[r][c] = '.';
					rowSets[r].remove(k);
					colSets[c].remove(k);
					boxSets[boxNumber].remove(k);
				}
			}
			return false;
		}else {
			return utl(board, index+1);
		}
	}
	
	//valid if the row , column or the box do not have a value equal to target value
	private boolean isValid(char[][] board, int row, int col, char target) {
		int boxNumber = row/3 * 3 + col/3;
		if(rowSets[row].contains(target) || colSets[col].contains(target) || boxSets[boxNumber].contains(target)) {
			return false;
		}
		return true;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
