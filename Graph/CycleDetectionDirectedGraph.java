//Cycle detection in directed graph.

/*
	Cycle in a graph can be due to a back edge.
	A back edge is an edge that is from a node to itself (self-loop) or one of its ancestors in the tree produced by DFS
	References: https://www.geeksforgeeks.org/detect-cycle-in-a-graph/ 
 * */
package misc;

import java.util.List;

import mylibrary.Graph;

public class CycleDetectionDirectedGraph {
	boolean detectCycle( Graph g) {
		if(g == null || g.totalVertices == 0) return false;
		int n = g.totalVertices;
		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		for(int i=0; i<n; i++) {
			if(detectCycleUtl(g, i, visited, recStack)) {	//do dfs over each connected component
				return true;
			}
		}
		return false;
	}
	
	//dfs
	boolean detectCycleUtl(Graph g, int curr, boolean[] visited, boolean[] recStack) {
		visited[curr] = true;
		recStack[curr] = true;
		List<Integer> neighbours = g.adjList[curr];
		for(int i=0; i<neighbours.size(); i++) {
			int neighbour = neighbours.get(i);
			if(!visited[neighbour] && detectCycleUtl(g, neighbour, visited, recStack)) {
				return true;
			}else if(recStack[neighbour]) {  //visited but still on recStack
				return true;
			}
		}
		recStack[curr] = false;
		return false;
	}
	public static void main(String[] args) {

		int v = 5;
		Graph g = new Graph(v);
		g.addEdgeDirected(0, 1);
		g.addEdgeDirected(0, 2);
		g.addEdgeDirected(1, 3);
		g.addEdgeDirected(1, 2);
		//g.addEdgeDirected(2, 2);  //loop to itself
		g.addEdgeDirected(2,0);		//back edge
		g.addEdgeDirected(2, 3);
		g.addEdgeDirected(3, 4);
		
		CycleDetectionDirectedGraph obj = new CycleDetectionDirectedGraph();
		if(obj.detectCycle(g)) {
			System.out.println("graph has a  cycle");
		}else {
			System.out.println("graph does not have a   cycle");
		}

	

	}

}
