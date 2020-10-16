//Cycle detection in undirected graph.

/*
	Cycle in a graph can be due to a back edge.
	A back edge is an edge that is from a node to itself (self-loop) or one of its ancestors in the tree produced by DFS
	References: https://www.geeksforgeeks.org/detect-cycle-undirected-graph/ 
 * */
package misc;

import java.util.List;

import mylibrary.Graph;

public class CycleDetectionUnDirectedGraph {
	public boolean detectCycle(Graph g) {
		if(g == null || g.totalVertices == 0) { //base case
			return false;
		}
		int n = g.totalVertices;
		boolean[] visited = new boolean[n];
		for(int i=0; i<n; i++) {
			if(!visited[i] && detectCycleUtl(g, i, -1, visited)) {
				return true;
			}
				
		}
		return false;	
	}
	
	//dfs
	private boolean detectCycleUtl(Graph g, int curr, int parent, boolean[] visited) {
		visited[curr] = true;
		List<Integer> neighbours = g.adjList[curr];
		for(int i=0; i<neighbours.size(); i++) {
			int neighbour = neighbours.get(i);
			if(!visited[neighbour]) {
				if(detectCycleUtl(g, neighbour, curr, visited)) {
					return true;
				}
			}
			else if( neighbour != parent){	//neighbour should not be parent of current vertex
				return true;
			}
			
		}
		return false;
	}
	public static void main(String[] args) {
		
		int v = 5;
		Graph g = new Graph(v);
		g.addEdgeUndirected(0, 1);
		
		g.addEdgeUndirected(1, 3);
		g.addEdgeUndirected(1, 2);
		g.addEdgeDirected(2, 2);  //loop to itself
		
		//g.addEdgeUndirected(2, 3);
		g.addEdgeUndirected(3, 4);
		
		CycleDetectionUnDirectedGraph obj = new CycleDetectionUnDirectedGraph();
		if(obj.detectCycle(g)) {
			System.out.println("graph has a  cycle");
		}else {
			System.out.println("graph does not have a   cycle");
		}
	}

}
