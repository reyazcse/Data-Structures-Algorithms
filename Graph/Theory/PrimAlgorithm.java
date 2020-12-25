//Prim's algorithm

/*
This algorithm is used to find the minimum spanning tree of a graph.
The algorithm is similar to dijkstra algorithm.
Only difference is here we take an edge and do not consider edge weight so far.

Algorithm:
	Put the first vertex in the heap. We assume we reach here from an imaginary vertex -1.
	Then we do below while the heap is not empty:
		Extract top element.
		If this is already visited, continue
		else
			Add it to result set if it is not the first vertex
			Add its neighbors if not already visited.
			For each neighbor, acquired vertex will be current vertex and edge weight will be weight of the edge
			

Complexity: 
			O(E * logV) time
			O(V) space for below implementation

References:
	https://www.youtube.com/watch?v=Vw-sktU1zmc
	For graph diagram: https://www.youtube.com/watch?v=oP2-8ysT3QQ
 * */


package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;


public class PrimAlgorithm {
	
	public void primAlgorithm(Graph g) {
		PriorityQueue<VertexInfo> pq = new PriorityQueue<>();
		HashSet<Integer> visited = new HashSet<>();			//keeps track of the visited vertices
		HashSet<VertexInfo> result = new HashSet<>();
		
		pq.add(new VertexInfo(0, -1, 0));			//we start with vertex 0. we can start from any vertex. we assume we come to vertex 0 from vertex -1 initially. -1 is imaginary vertex
		
		while(!pq.isEmpty()) {
			VertexInfo curr = pq.remove();
			if(visited.contains(curr.vertex)) {			//if visited
				continue;
			}	
			
			visited.add(curr.vertex);
			if(curr.acquired_vertex != -1) {			//do not add first vertex to result since -1 is an imaginary vertex connecting first vertex
				result.add(curr);
			}
			
			//add unvisited neighbors to the heap
			for(EdgeInfo v : g.adjacency_list[curr.vertex]) {
				if(!visited.contains(v.destination)) {
					pq.add(new VertexInfo(v.destination, curr.vertex, v.edge_wt));  		//for curr.vertex is the vertex taken when we put the neighbor vertex into the heap.
				}
			}
		}
		
		
		//printing the mst
		printMST(result);
	}
	
	private void printMST(HashSet<VertexInfo> result) {
		for(VertexInfo v : result) {
			System.out.println("Edge: " + v.acquired_vertex  + "-----" + v.vertex + "  Weight: " + v.edge_wt);
		}
	}
	

	//Node for the priority queue(min heap)
	//node with minimum edge_wt is at the top
	private static class VertexInfo implements Comparable<VertexInfo>{
		int vertex;																//vertex
		int acquired_vertex;													//other vertex of the edge, which is taken
		int edge_wt;															//weight of the edge between vertex and acquired_vertex

		public VertexInfo(int v, int av, int wt) {
			this.vertex = v;
			this.acquired_vertex = av;
			this.edge_wt = wt;
		}

		@Override
		public int compareTo(VertexInfo other) {
			return this.edge_wt - other.edge_wt;
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
	
	public static void main(String[] args) {
		Graph g =  new Graph(6);
		g.addUndirectedEdge(0, 1, 3);
		g.addUndirectedEdge(0, 3, 1);
		g.addUndirectedEdge(1, 2, 1);
		g.addUndirectedEdge(1, 3, 3);
		g.addUndirectedEdge(2, 3, 1);
		g.addUndirectedEdge(2, 4, 5);
		g.addUndirectedEdge(2, 5, 4);
		g.addUndirectedEdge(3, 4, 6);
		g.addUndirectedEdge(4, 5, 2);
		
		PrimAlgorithm ob = new PrimAlgorithm();
		//ob.primAlgorithm(g);
		
		//Another graph: https://bohr.wlu.ca/cp164/notes/20_pq_prims.php
		Graph g2 = new Graph(7);
		g2.addUndirectedEdge(0, 1, 2);
		g2.addUndirectedEdge(0, 2, 3);
		g2.addUndirectedEdge(0, 3, 7);
		g2.addUndirectedEdge(1, 2, 6);
		g2.addUndirectedEdge(1, 6, 4);
		g2.addUndirectedEdge(2, 4, 1);
		g2.addUndirectedEdge(2, 5, 8);
		g2.addUndirectedEdge(3, 4, 5);
		g2.addUndirectedEdge(4, 5, 4);
		g2.addUndirectedEdge(5, 6, 2);
		//ob.primAlgorithm(g2);
			
		//another graph test: https://www.youtube.com/watch?v=ZtZaR7EcI5Y
		Graph g3  = new Graph(6);
		g3.addUndirectedEdge(0, 1, 7);
		g3.addUndirectedEdge(0, 2, 8);
		g3.addUndirectedEdge(1, 2, 3);
		g3.addUndirectedEdge(1, 3, 6);
		g3.addUndirectedEdge(2, 3, 4);
		g3.addUndirectedEdge(2, 4, 3);
		g3.addUndirectedEdge(3, 4, 2);
		g3.addUndirectedEdge(3, 5, 5);
		g3.addUndirectedEdge(4, 5, 2);
		ob.primAlgorithm(g3);
		
	}

}
