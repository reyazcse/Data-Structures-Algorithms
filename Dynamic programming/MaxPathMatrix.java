/*
Question:
	Given a N by M matrix of integers, find the max sum path to move from (0,0) to (N-1, M-1). You can only move
	right or down.
	
Solution:
	We have solved it using bottom up DP.
	First fill the last row and last col.
	Then for inner cells, 
		mat [i][j] = mat [i][j] + maximum of (value in right cell, value in down cell)
	Finally return mat[0][0] as the answer.
 * */
package misc;

public class MaxPathMatrix {
	public static int maxPathSum(int[][] mat) {
		int N  = mat.length;
		int M = mat[0].length;
		//filling last row
		for (int i=N-2; i>=0; i-- ) {
			mat[i][M-1] += mat[i+1][M-1];
		}
		//filling last column
		for (int i=M-2; i>=0; i--) {
			mat[N-1][i] += mat[N-1][i+1];
		}
		//value in current cell is max of value in right or down cell + current cell value
		for(int i= N-2; i>=0; i--) {
			for (int j= M-2; j>=0; j--) {
				mat[i][j] += Math.max(mat[i][j+1], mat[i+1][j]);
			}
		}
		return mat[0][0];
	}
	public static void main (String [] args) {
		int [][] mat = {{1,2,3},
						{4,5,6},
						{7,8,9}};
		System.out.println(maxPathSum(mat));
	}

}
