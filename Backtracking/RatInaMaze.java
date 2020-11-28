/*
Consider a rat placed at (0, 0) in a square matrix of order N*N. It has to reach the destination at (N-1, N-1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and cannot be crossed while value 1 at a cell in the matrix represents that it can be traveled through.

Example 1:

Input: N = 4, m[][] = {{1, 0, 0, 0},
                       {1, 1, 0, 1}, 
                       {1, 1, 0, 0},
                       {0, 1, 1, 1}}
Output: DDRDRR DRDDRR
 * */

/*
Solution: 
	Backtracking method
	
	
	References:
		https://www.geeksforgeeks.org/rat-in-a-maze-problem-when-movement-in-all-possible-directions-is-allowed/
		https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
 * */
package misc;

import java.util.ArrayList;
import java.util.Collections;

public class RatInaMaze {
	public static ArrayList<String> printPath(int[][] m, int n) {
		
		ArrayList<String> paths = new ArrayList<>();
		StringBuilder pathSoFar = new StringBuilder("");
		boolean [][] visited = new boolean[n][n];
		printPathUtl(m, n, 0, 0, pathSoFar, paths, visited);
		Collections.sort(paths);
		return paths;
	}
	
	private static void printPathUtl(int[][] m, int n, int r, int c, StringBuilder pathSoFar, ArrayList<String> paths, boolean[][] visited) {
		//if out of boundary or cell value is 0 or it is already visited
		
		if(r < 0 || r >= n || c < 0 || c >= n || m[r][c] == 0 || visited[r][c]) {
			return;
		}
		
		//reached target
		if(r == n-1 && c == n-1) {
			paths.add(new String(pathSoFar));
			return;
		}
		
		visited[r][c] = true;						//mark as visited
		
		int [] x = {0,-1,0,1};						//coordinates to move adjacent four cells
		int [] y = {-1,0,1,0};
		String[] directions = {"L", "U", "R", "D"};
		for(int i=0; i<4; i++) {
			pathSoFar.append(directions[i]);									//add direction for current iteration
			printPathUtl(m, n, r+x[i], c+y[i], pathSoFar, paths, visited);
			pathSoFar.deleteCharAt(pathSoFar.length()-1);						//remove direction for current iteration
		}
		visited[r][c] = false;						//backtrack
				
	}
	
	public static void main(String[] args) {
		int m[][] = {{1, 0, 0, 0},
					{1, 1, 0, 1}, 
					{1, 1, 0, 0},
					{0, 1, 1, 1}};
		int n  = 4;
		ArrayList<String> paths = printPath(m, n);
		for(String path : paths) {
			System.out.println(path);
		}
		
	}

}
