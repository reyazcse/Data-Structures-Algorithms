//BellmanFordAlgorithm : To find single source all shortest paths.

/*
Algorithm:
	Do V-1 iterations.
	In each iteration, relax the edges. Edges can be picked up in any random order.
	Relaxation of edge means:
		Suppose we have an edge (u,v) and weight from u to v is 'wt'.
		relax(edge uv) {
			if(distance[v] > distance[u] + wt ) {
				distance[v] = distance[u] + wt;
				parent[v] = u; 
			}
		}
Complexity:
	O(VE) time and O(V) space

Note:
	1. This algorithm handles negative weights also.
	2. It does not handle negative weight cycle. We can use it to find the presence of a negative weight cycle:
	   Do one more iteration (Vth iteration) and if there is a relaxation, it means there is negative weight cycle.
	3. This algorithm can also be used to find all source shortest paths. The idea is use each vertex as source vertex.
		However the complexity will be more: 
		O(V^2 * E) time. In worst case if E = V^2, then time will be O(v^4). Use floyd warshall instead which has O(V^3) complexity.
	4. It is slower than dijkstra. However it works good in distributed systems. Unlike Dijkstra’s where we need to find the minimum value of all vertices, in Bellman-Ford, edges are considered one by one.

References:
	https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
	https://www.youtube.com/watch?v=-mOEd_3gTK0&t=3s
 * */

/*
Graph Representation:
	We have a graph which consists of number of vertices V, number of edges E and a list of edges. Each edge has source vertex,
	destination vertex and weight of the edge.
 * */

package misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BellmanFordAlgorithm {
	public static int INF = Integer.MAX_VALUE;

	public void bellmanFord(Graph g, int source) {
		HashMap<Integer, Integer> parent = new HashMap<>();		//stores the parent of node through which we reach the node
		HashMap<Integer, Integer> distance = new HashMap<>();	//shortest distance of node from the source node

		int V = g.V;
		for(int i=0; i<V; i++) {
			parent.put(i, -1);						//-1 means no parent
			distance.put(i, INF);					//initially all distances are infinity except that of source vertex
		}

		distance.put(source, 0);					//distance for source vertex is 0

		//relax all the edges V-1 times
		for(int i=0; i<V-1; i++) {
			for(Edge e : g.edges) {
				int u = e.src;
				int v = e.dest;
				if(distance.get(u) == INF) {
					continue;
				}
				if(distance.get(u) + e.wt < distance.get(v)) {
					distance.put(v, distance.get(u) + e.wt);
					parent.put(v, u);
				}
			}
		}

		
		printAllDistances(distance);
		printPathForDestVertex(3, source,  parent);
		
		//check of negative weight cycle: if we get lesser distance for any vertex while again relaxing all the edges, it 
		//means there is negative weight cycle
		boolean isNegativeWeighCycle = false;

		for(Edge e : g.edges) {
			int u = e.src;
			int v = e.dest;
			if(distance.get(u) == INF) {
				continue;
			}
			if(distance.get(u) + e.wt < distance.get(v)) {
				isNegativeWeighCycle = true;								//negative cycle is present
			}
		}

		if(isNegativeWeighCycle) {
			System.out.println("Negative cycle is present");
		}
	}

	//prints shortest distance of each vertex from source vertex
	private void  printAllDistances(HashMap<Integer, Integer> distance) {
		for(Map.Entry<Integer, Integer> entry : distance.entrySet()) {
			System.out.println("Vertex: " + entry.getKey() + " Shortest distance:" + entry.getValue());
		}
	}
	
	//prints path from src to dest vertex
	private void printPathForDestVertex(int dest, int src, HashMap<Integer, Integer> parent) {
		Stack<Integer> stack = new Stack<>();
		stack.add(dest);
		while(true) {
			dest = parent.get(dest);
			if(dest == -1) {					//no path possible since -1 means no parent of current vertex
				return;
			}
			stack.add(dest);
			if(dest == src) {
				break;
			}
			
		}
		
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
		
	}
	
	
	
	
	//graph: consists of number of vertices, edges and also the edges
	private static class Graph {
		int V, E; 							//no of edges and vertices
		Edge[] edges;

		public Graph(int v, int e) {
			this.V = v;
			this.E = e;
			edges = new Edge[e];
			for(int i=0; i<e; i++) {
				edges[i] = new Edge();
			}
		}
	}


	//edge of the graph
	private static class Edge {
		int src, dest, wt;			//source and destination vertices and edge weight

		public Edge() {

		}
		public Edge(int src, int dest, int wt) {
			this.src  = src;
			this.dest = dest;
			this.wt = wt;

		}
	}

	
	
	
	public static void main(String[] args) {
		int V = 5, E = 7;
		Graph g = new Graph(V, E);  			//5 vertices and 7 edges
		
		//0-->1
		g.edges[0].src = 0;
		g.edges[0].dest = 1;
		g.edges[0].wt = 4;

		//0-->2
		g.edges[1].src = 0;
		g.edges[1].dest = 2;
		g.edges[1].wt = 5;
		
		//0-->3
		g.edges[2].src = 0;
		g.edges[2].dest = 3;
		g.edges[2].wt = 8;
		
		//1-->2
		g.edges[3].src = 1;
		g.edges[3].dest = 2;
		g.edges[3].wt = -3;
		
		//2-->4
		g.edges[4].src = 2;
		g.edges[4].dest = 4;
		g.edges[4].wt = 4;
		
		//3-->4
		g.edges[5].src = 3;
		g.edges[5].dest = 4;
		g.edges[5].wt = 2;
		
		//4-->3
		g.edges[6].src = 4;
		g.edges[6].dest = 3;
		g.edges[6].wt = 1;

		BellmanFordAlgorithm ob = new BellmanFordAlgorithm();
		ob.bellmanFord(g, 0);
		

	}
}

//Note: We can do little bit optimization here for bellman ford 
//Rather than doing the iterations v-1 times, we can use a flag and if in any iteration we find no change in the distance of
//of any vertex, then no need to continue the iteration. 
