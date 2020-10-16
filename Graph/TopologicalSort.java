/*
Solution:
	We do dfs of a graph. The graph may have connected components and hence
	we iterate and do dfs for each vertex if it is unvisited.
	For a vertex, if we are done doing dfs then before exiting we assign a number to it.
	
 * */
package misc;

import java.util.List;

import mylibrary.Graph;

public class TopologicalSort {
	public int[] topologicalSort(Graph g) {
		int n = g.totalVertices;
		boolean [] visited = new boolean[n];
		int[]result = new int[n];			//stores topological order
		int rank = n-1;
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				rank = dfs(g, i, visited, result, rank); //do dfs over each component in the graph
			}
		}
		return result;
	}
	
	
	private int dfs(Graph g, int i, boolean[] visited, int[]result, int rank) {
		visited[i] = true;
		List<Integer> neighbours = g.adjList[i];
		for(int neighbour : neighbours) {
			if(!visited[neighbour]) {
				rank = dfs(g, neighbour, visited, result, rank);
			}
		}
		result[i] = rank;  //assign the topological number just before exit
		return rank-1;     //now for other vertex, we get 1 less rank
	}
	public static void main(String[] args) {
		int v = 5;
		Graph g = new Graph(v);
		g.addEdgeDirected(0, 1);
		g.addEdgeDirected(0, 2);
		g.addEdgeDirected(1, 3);
		g.addEdgeDirected(2, 3);
		g.addEdgeDirected(3, 4);
		
		TopologicalSort obj = new TopologicalSort();
		int [] result = obj.topologicalSort(g);
		for(int i=0; i<result.length; i++) {
			System.out.println("vertex: " + i + " topological rank: " + result[i]);
		}
	}

}



