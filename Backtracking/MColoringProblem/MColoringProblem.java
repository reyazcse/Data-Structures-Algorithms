
/*
Given an undirected graph and an integer M. The task is to determine if the graph can be colored with at most M colors such that no two adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of colors to all vertices. Print 1 if it is possible to colour vertices and 0 otherwise.

Example 1:

Input:
N = 4
M = 3
E = 5
Edges[] = {(1,2),(2,3),(3,4),(4,1),(1,3)}
Output: 1

References:
	https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
	https://practice.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
 * */

/*
Solution: backtracking

Complexity: O(M^N) time and O(N) space where M = no of colors and N = no of vertices 
			The upper bound time complexity remains the same as naive algorithm, but the average time taken will be less.
 * */
package misc;

import java.util.ArrayList;
import java.util.List;

public class MColoringProblem {
	/*
	 * G = adjacency list of vertices
	 * color = array to store color for each vertex. color[0] = 1 means vertex 0 is colored with color 1
	 * i = current vertex. i starts from 0
	 * C = total number of colors
	 * */
	public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C) {
		
		return utl(G, color, i, C, color.length);
	}
	
	private static boolean utl(List<Integer> [] G, int[] color, int currVertex, int C, int V) {
		//all vertices covered
		if(currVertex == V) {
			return true;
		}
		
		//start coloring current vertex from c=1 to C
		for(int c=1; c<=C; c++) {
			if(isSafe(G, color, currVertex, c)) {
				//color current vertex with color c
				color[currVertex] = c;
				
				//recurse
				if(utl(G, color, currVertex+1, C, V)) {
					return true;
				}
				
				//backtrack
				color[currVertex] = -1;
			}
		}
		return false;
	}

	private static boolean isSafe(List<Integer>[] G, int[] color, int currVertex, int c) {
		for(int neighbour : G[currVertex]) {
			if(color[neighbour] == c) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int V = 4;
		int M = 3;
		int [] color = new int[V];
		List<Integer> [] G = new ArrayList[V];
		for(int i=0; i<V; i++) {
			G[i] = new ArrayList<>();
		}
		G[0].add(1);
		G[0].add(2);
		G[0].add(3);
		G[1].add(0);
		G[2].add(0);
		G[3].add(0);
		
		G[1].add(2);
		G[2].add(1);
		
		G[2].add(3);
		G[3].add(2);
		
		System.out.println(graphColoring(G, color, 0, M));
	}

}

/*
Note:
 Here if use neighbours for current vertex for the recursive calls, then it can become complex,
 as we need to traverse the vertices separately to ensure we cover all components. 
 Also logic of returning true or false for current call becomes complex.
 So to avoid all those it is better to call with currentVertex+1 for the recursive call
 
 * */
