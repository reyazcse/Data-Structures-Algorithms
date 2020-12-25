/*
The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. The problem is to find shortest distances between every pair of vertices in a given edge weighted directed Graph.
Example:

Input:
       graph[][] = { {0,   5,  INF, 10},
                    {INF,  0,  3,  INF},
                    {INF, INF, 0,   1},
                    {INF, INF, INF, 0} }
which represents the following graph
             10
       (0)------->(3)
        |         /|\
      5 |          |
        |          | 1
       \|/         |
       (1)------->(2)
            3       
Note that the value of graph[i][j] is 0 if i is equal to j 
And graph[i][j] is INF (infinite) if there is no edge from vertex i to j.

Output:
Shortest distance matrix
      0      5      8      9
    INF      0      3      4
    INF    INF      0      1
    INF    INF    INF      0 
    
References: 
https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
https://www.youtube.com/watch?v=LwJdNfdLF9s&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j

 * */

/*
Solution: O(V^3)

NOte:
	1. Negative edges are allowed (also in belman ford but not in dijkstra)
	2. Negative cycles are not allowed (like in dijkstra and bellman ford)
	
	3. For more info on negative cycles, go to https://cp-algorithms.com/graph/all-pair-shortest-path-floyd-warshall.html
 * */
package misc;

import java.util.Stack;

public class FloydWarshallAlgorithm {
	public static int INF = 9999;		//infinity value
	public static int V = 4;			//number of vertices
	
	public void allShortestPaths(int[][]graph) {
		int[][] dist = new int[V][V];
		int[][] path = new int[V][V];
		
		//initialize all distances and path
		//path[i][j] = -1 means no path between i and j
		for(int i=0; i<V; i++) {
			for(int j=0; j<V; j++) {
				dist[i][j] = graph[i][j];
				
				if(graph[i][j] == INF || i == j) {
					path[i][j] = -1;
				}else {
					path[i][j] = i;							
				}
			}
		}
		
		for(int k=0; k<V; k++) {
			for(int i=0; i<V; i++) {
				for(int j=0; j<V; j++) {
					if(dist[i][k] == INF || dist[k][j] == INF) {		//no edge between i and k;  k and j
						continue;
					}
					
					if(dist[i][k] + dist[k][j] < dist[i][j]) {			//we reach from i to j using vertex k at a lesser distance
						dist[i][j] = dist[i][k] + dist[k][j];
						
						path[i][j] = path[k][j];						//We reach j with k as intermediate node
					}
				}
			}
		}
		
		print(dist);
		
		//check for negative weight cycle: if any diagonal value in dist[][] is negative, then we have
		//negative weight in the graph
		boolean isNegativeCycle = false;
		for(int i=0; i<dist.length; i++) {
			if(dist[i][i] < 0) {
				isNegativeCycle = true;
				break;
			}
		}
		if(isNegativeCycle) {
			System.out.println("Graph has negative weight cycle");
		}
		
		//print path for a particular i and j
		printPath(0, 3, path);
	}
	
	private void printPath(int i, int j, int[][] path) {
		Stack<Integer> stack = new Stack<>();
		stack.add(j);
		while(true) {
			j = path[i][j];
			if(j == -1) {				//no path between i and j
				return;
			}
			stack.add(j);
			if(j == i) {
				break;
			}
			
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	private void print(int[][] dist) {
		for(int i=0; i<V; i++) {
			for(int j=0; j<V; j++) {
				if(dist[i][j] == INF) {
					System.out.print("INF  ");
				}else System.out.print(dist[i][j] + "    ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int [][] graph = {  {0,   5,  INF, 10}, 
                			{INF, 0,   3, INF}, 
                			{INF, INF, 0,   1}, 
                			{INF, INF, INF, 0} 
              			};
		
		FloydWarshallAlgorithm ob = new FloydWarshallAlgorithm();
		ob.allShortestPaths(graph);
		
	}

}
