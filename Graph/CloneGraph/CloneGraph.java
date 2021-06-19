//https://leetcode.com/problems/clone-graph/
/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Constraints:

	1 <= Node.val <= 100
	Node.val is unique for each node.
	Number of Nodes will not exceed 100.
	There is no repeated edges and no self-loops in the graph.
	The Graph is connected and all nodes can be visited starting from the given node.
 * */

/*
 * Solution: Using BFS
 * See this for explanation: https://www.geeksforgeeks.org/clone-an-undirected-graph/
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {

	public Node cloneGraph(Node node) {
		if(node == null) {
			return null;
		}
		if(node.neighbors == null || node.neighbors.isEmpty()) {		//single node in source graph
			return new Node(node.val);
		}
		
		Queue<Node> q = new LinkedList<>();
		HashMap<Node, Node> orgToCloneMap = new HashMap<>();			// reference to node visited and copied
		
		q.add(node);
		Node clonedRoot = new Node(node.val);
		orgToCloneMap.put(node, clonedRoot);
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			Node clonedCurr = orgToCloneMap.get(curr);
			
			for(Node neigh : curr.neighbors) {
				Node cloneNeigh = orgToCloneMap.get(neigh);
				if(cloneNeigh == null) {
					cloneNeigh = new Node(neigh.val);
					orgToCloneMap.put(neigh, cloneNeigh);
					q.add(neigh);								//do bfs over the neighbor
				}
				clonedCurr.neighbors.add(cloneNeigh);
				
			}
			
		}
		return clonedRoot;
	}
	
	
	//Another way of doing: initially do not put any cloned node in the map.
	public Node cloneGraph_2(Node node) {
		if(node == null) {
			return null;
		}
		if(node.neighbors == null || node.neighbors.isEmpty()) {		//single node in source graph
			return new Node(node.val);
		}
		
		Queue<Node> q = new LinkedList<>();
		HashMap<Node, Node> clonedMap = new HashMap<>();
		
		q.add(node);
		Node clonedRoot = null;
		
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			Node clonedCurr = clonedMap.get(curr);
			if(clonedCurr == null) {
				clonedCurr = new Node(curr.val);
				clonedMap.put(curr, clonedCurr);
			}
			
			if(clonedRoot == null) {								//start of cloned. It is set only once
				clonedRoot = clonedCurr;
			}
			
			for(Node v : curr.neighbors) {
				Node clonedV = clonedMap.get(v);
				if(clonedV == null) {
					clonedV = new Node(v.val);
					clonedMap.put(v, clonedV);
					q.add(v);
				}
				clonedCurr.neighbors.add(clonedV);
				
			}
			
		}
		return clonedRoot;
	}
	
	
	
	
	
	
	
	//graph node
	private static class Node {
	    public int val;
	    public List<Node> neighbors;
	    public Node() {
	        val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val) {
	        val = _val;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val, ArrayList<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}
}

/*
 * Note: This solution is for a single connected component. If there are multiple components, then call the method for each
 * component. Also we need to pass the map from the main method if the node on which we are calling the method is not present in the 
 * map. The second method is more apt here.
 * See below for reference:
 * https://www.geeksforgeeks.org/clone-an-undirected-graph-with-multiple-connected-components/
 * */
 
