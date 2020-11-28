/*
 * 
Solution using NAIVE approach: 
	After all vertices are colored, check if this is valid coloring
	In the backtracking approach, we first check if current vertex can be colored and then move to other vertex. But in 
	naive we first color all the vertices and at the end we check if it is valid coloring

*/
package misc;

import java.util.ArrayList;
import java.util.List;

public class MColoringProblem2 {
	/*
	 * G = adjacency list of vertices
	 * color = array to store color for each vertex. color[0] = 1 means vertex 0 is colored with color 1
	 * i = current vertex. i starts from 0
	 * C = total number of colors
	 * */
	
	//naive approach : give TLE
	public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C) {
		
		return utl(G, color, i, C, color.length);
	}
	
	private static boolean utl(List<Integer>[] G, int[] color, int currVertex, int C, int V) {
		if(currVertex == V) {						//all vertices are colored so check the coloring
			if(isValidColoring(G, color)) {
				return true;
			}
			return false; 						//no valid coloring
		}
		for(int c=1; c<=C; c++) {
			color[currVertex] = c;
			if(utl(G, color, currVertex + 1, C, V)) {
				return true;
			}
			//backtrack since we try with next color
			color[currVertex] = 0;
		}
		return false;						
	}
	
	private static boolean isValidColoring(List<Integer> [] G, int[] color ) {
		int V = color.length;
		for(int i=0; i<V; i++) {
			for(int neighbour : G[i]) {
				if(i != neighbour && color[i] == color[neighbour]) {	//check if adjacent two vertices are of same color then return false
					return false;
				}
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
