/*
src: https://www.youtube.com/watch?v=gm4Ye0fESpU&list=PLfBJlB6T2eOsET4tlfcLs7oXR7kCyt1xc
Question:
	Find the maximum root to leaf sum.
	We are given a tree with n nodes. Nodes are starting from 1 ....n
	We are given an adjacency list of edges.
	We are also given value inside a tree node.

Sample Input:
	14					//number of nodes
	1 2					//edge from node 1 to node 2	
	1 3
	1 4
	2 5
	2 6
	3 7
	4 8
	4 9
	4 10
	5 11
	5 12
	7 13
	7 14
	3 2 1 10 1 3 9 1 5 3 4 5 9 8                //node values, for e.g node 1 has value 3, node 2 has value 2........
	
Solution	
	We solve this problem with DP.
	At each node, we first get the maximum value from its children. We then:
	dp[u]  = node_value + max_value
	Finally dp[1] will store the maximum value(value at root node)
* */
package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxRootToLeafSum {
	static int []nodeVal;
	
	//adjacency List for the tree graph
	static List<Integer> [] adjList;
	
	//dp[u] gives max value at graph rooted at node index u
	static int [] dp;
	
	
	public static void dfs (int u, int parent) {
		dp[u] = nodeVal[u];
		int mx = 0;                             //it is max val we can get from children from node u
		//iterate for each child of node u
		for (int child : adjList[u]) {
			if (child == parent) continue;
			dfs(child, u);
			mx = Math.max(mx, dp[child]);
		}
		dp[u] += mx;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//no of nodes of graph
		int n = Integer.parseInt(sc.nextLine());
		
		nodeVal = new int[n+1];  //n+1 as our first node is 1 and NOT 0
		
		
		dp = new int[n+1];
		adjList = new ArrayList[n+1];  //we create an extra row as in the graph we have first node starting from 1 and NOT 0
		for (int i=0; i<n+1; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		//preparing adjacency list. for n nodes we have n-1 edges
		String [] edges = null;
		for (int i=0; i<n-1; i++) {
			edges = sc.nextLine().split(" ");
			int u = Integer.parseInt(edges[0]);
			int v = Integer.parseInt(edges[1]);
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		//getting node values in array nodeVal
		String [] nodeValues = sc.nextLine().split(" ");
		int i=1;
		for (int j=0; j<nodeValues.length; j++) {
			nodeVal[i] = Integer.parseInt(nodeValues[j]);
			i++;
		}
		sc.close();
		dfs(1,0);
		System.out.println(dp[1]);
	}

}
