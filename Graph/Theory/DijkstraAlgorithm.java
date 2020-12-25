//DIJKSTRA ALGORITHM: Single source to all shortest path

/*

Solution:

Greedy approach. The idea is similar to doing BFS of a graph, but here we use min heap since we greedily choose the vertex
with the least weight so far.

Also we keep a map which we use to check if a vertex has been processed earlier or not. This map also has all the visited vertices after
the termination of the algorithm

Algorithm:
	Insert the source vertex in the heap.
	while the heap is not empty
		extract top of heap.
		if it is visited already, do nothing
		if it is not visited
			visit it
			for each of its neighbor
				if it is not visited
					put this in the heap after updating its path_so_far and weight so far values
			
	
Complexity:
	O(E log V) time, where E = number of edges and V = number of  vertices in the graph
	O(V) for current implementation. For some implementation it may be O(E + V)


Note:
	1. Does not take care of negative weights
	2. Negative weight cycle is not handled
	3. One idea to make this algorithm work for negative weights:
	   calculate the minimum weight value, add a positive value (equal to absolute value of minimum weight value) to all weights and run the Dijkstra’s algorithm for the modified graph. 
References:
https://www.youtube.com/watch?v=lAXZGERcDf4&t=26s
https://www.youtube.com/watch?v=sD0lLYlGCJE
 * */
package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
	
	public void disjkstra(Graph g, int source) {
		PriorityQueue<VertexInfo> pq = new PriorityQueue<>();
		HashMap<Integer, VertexInfo> visited = new HashMap<>();			//stores the visited vertices
		
		VertexInfo start = new VertexInfo(source, "", 0);
		pq.add(start);
		
		while(!pq.isEmpty()) {
			VertexInfo curr = pq.remove();
			if(visited.containsKey(curr.vertex)) {				//if already visited
				continue;
			}
			visited.put(curr.vertex, curr);						//visit current vertex
			
			//put the neighbors of current vertex in the heap
			for(EdgeInfo v : g.adjacency_list[curr.vertex]) {
				if(!visited.containsKey(v.destination)) {
					String path_so_far = curr.path_so_far + " " + curr.vertex;
					int wt_so_far = curr.wt_so_far + v.edge_wt;
					VertexInfo neighbour = new VertexInfo(v.destination, path_so_far, wt_so_far);
					
					pq.add(neighbour);
				}
			}
		}
		
		//print all the vertices to show the path and weight of each calculated by dijkstra
		for(VertexInfo v : visited.values()) {
			System.out.println(v.vertex + "  path " + v.path_so_far + "  wt " + v.wt_so_far);
		}
	}
	
	public static void main(String[] args) {
		DijkstraAlgorithm ob = new DijkstraAlgorithm();
		Graph g = ob.construcGraph();
		ob.disjkstra(g, 0);
	}
	
	public Graph construcGraph() {
		int V = 6;
		Graph graph = new Graph(V);
		graph.addUndirectedEdge(0, 1, 5);		//A--B with weight 5
		graph.addUndirectedEdge(0, 4, 2);		//A--D with weight 2
		graph.addUndirectedEdge(0, 3, 9);		//A--E with weight 9 
		graph.addUndirectedEdge(1, 2, 2);		//B--C with weight 2
		graph.addUndirectedEdge(2, 3, 3);		//C--D with weight 3
		graph.addUndirectedEdge(3, 5, 2);		//D--F with weight 2
		graph.addUndirectedEdge(4, 5, 3);		//E--F with weight 3
		
		return graph;
		
	}
	
	//Node for the priority queue(min heap)
	//node with minimum wt_so_far is at the top
	private static class VertexInfo implements Comparable<VertexInfo>{
		int vertex;																//graph vertex
		String path_so_far;														//path to reach this vertex
		int wt_so_far;															//total weight to reach this vertex
		
		public VertexInfo(int v, String psf, int wsf) {
			this.vertex = v;
			this.path_so_far = psf;
			this.wt_so_far = wsf;
		}

		@Override
		public int compareTo(VertexInfo other) {
			return this.wt_so_far - other.wt_so_far;
		}
	}
	
	//GRAPH with adjacency list representation
	private static class Graph{
		int V;									//number of vertices;
		List<EdgeInfo>[] adjacency_list;		//adjacency list
		
		public Graph(int v) {
			this.V = v;
			adjacency_list = new ArrayList[v];
			for(int i=0; i<v; i++) {
				adjacency_list[i] = new ArrayList<EdgeInfo>();
			}
		}
		public void addUndirectedEdge(int source, int destination, int weight) {
			//add source to destination edge
			EdgeInfo e1 = new EdgeInfo(destination, weight);
			adjacency_list[source].add(e1);
			
			//add destination to source edge
			EdgeInfo e2 = new EdgeInfo(source, weight);
			adjacency_list[destination].add(e2);
		}
		
		public void addDirectedEdge(int source, int destination, int weight) {
			EdgeInfo e = new EdgeInfo(destination, weight);
			adjacency_list[source].add(e);
			
		}
	}
	
	//source vertex is index of the adjacency list. Below class contains destination vertex and the edge weight between
	//source and destination
	private static class EdgeInfo{
		int destination;
		int edge_wt;
		public EdgeInfo(int dest, int wt) {
			destination = dest;
			edge_wt = wt;
		}
	}
	
}
