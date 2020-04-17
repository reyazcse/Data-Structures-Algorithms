/**
src: https://www.youtube.com/watch?v=Xng1Od_v6Ug&list=PLfBJlB6T2eOsET4tlfcLs7oXR7kCyt1xc&index=2

Question:
	Find the height of the tree considering each node at the root.
	We are given n nodes and adjacency list of the nodes. 
	The nodes start from 1.
Sample Input:
10               //no of nodes in the tree
1 2 			 //edge between node 1 and node 2
1 3
1 4
2 5
2 6
3 7
4 8
7 9
7 10

 **/
package misc;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiameterWithEachNodeAsRoot {
	static int in[];
	static int out[];
	static int dp[];
	static List<Integer> [] adjList = null;
	
	//calculates in[] value for a node.
	public static void dfs1 (int u, int par) {
		in[u] = 0;
		for (int v : adjList[u]) {
			if (v == par) continue;
			dfs1 (v, u);
			in[u] = Math.max(in[u], 1 + in[v]);
		}
	}
	
	
	//calculates the out[] value for a node
	//base case is: out[root] = out[1] = 0
	public static void dfs2 (int u, int par) {
		
		int mx1 = -1, mx2 = -1;
		//find the max two in[v] values
		for (int v : adjList[u]) {
			if (v == par) continue;
			if (in[v] > mx1) {
				mx2 = mx1;
				mx1 = in[v];
			} else if (in[v] > mx2) {
				mx2 = in[v];
			}
		}
		//now we calculate the out[] values for the child nodes.
		//'use' takes care that current in[v] is not same as mx value to be used
		for (int v : adjList[u]) {
			if (v == par)continue;
			int use  = mx1;
			if (in[v] == mx1)
				use = mx2;
			out[v] = Math.max(1 + out[u], 2 + use);
			dfs2(v,u);
		}
	}
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		
		//in[] values of a node u. we start from node 1
		in = new int[n+1];
		
		//out values of a node u. We start from node 1 which is the root. out[root] = 0;
		out = new int[n+1];
		
		dp = new int[n+1];
		adjList = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		//populating the adjacency list
		String[] edges = null;
		for (int i=0; i<n-1; i++) {
			edges = sc.nextLine().split(" ");
			int u = Integer.parseInt(edges[0]);
			int v = Integer.parseInt(edges[1]);
			adjList[u].add(v);
			adjList[v].add(u);
		}
		dfs1(1,0);
		dfs2(1,0);
		//height of a node is the max of in[v] and out[v].
		for (int i=1 ; i<=n ;i++) {
			dp[i] = Math.max(in[i], out[i]);
			System.out.println(dp[i]);
		}
	}
}
