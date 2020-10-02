/*
src: https://www.youtube.com/watch?v=6Q7OrMi0Vvc&list=PLfBJlB6T2eOsET4tlfcLs7oXR7kCyt1xc&index=3  || Rachit Jain youtube
Question: Find LCA of a binary tree. Tree representation in adjacency list.
Input:
	11		//no of nodes
	1 2 	//edge between node 1 and node 2
	1 3
	2 4
	3 5
	4 6
	6 7
	7 8
	8 9
	9 10
	10 11
Output:
<LCS node value>
 * */

package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryLiftingLCA {
	static int[] lvl;
	static List<Integer> [] adjList;
	static int[][] P;
	public static final int max = 20;
	
	//computes level of node u, as well as parent of node u
	public static void dfs(int u, int parent) {
		lvl[u] = 1 + lvl[parent];
		P[u][0] = parent;
		for(int v: adjList[u]) {
			if (v == parent)
				continue;
			dfs(v, u);
		}
	}
	
	//lca
	public static int lca(int u, int v) {
		//swap u and v if level of u is less than level of v
		if (lvl[u] < lvl[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		int i,lg;
		
		//let's say level of v is 51, then lg = 5 after this loop so that 2^6 > 51
		for(lg=0; (1 << lg)<= lvl[u]; lg++);   //stop when we find lg such that 2^lg > lvl[u]
		
		lg--;                              //adjust lg
		
		//now we keep moving upwards from node u till we reach the same level as node v
		for(i=lg; i>=0 ; i--) {
			if (lvl[u] - (1 << i) >= lvl[v]) {
				u = P[u][i];
			}
		}
		//u and v are at same level. check if u and v are same
		if(u == v) {
			return u;
		}
		
		//now we move upwards and in logarithmic time.
		for(i=lg; i>=0; i--) {
			if (P[u][i] != -1 && P[u][i] != P[v][i]) {
				u = P[u][i];
				v = P[v][i];
			}
		}
		return P[u][0];	
	}
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		lvl = new int[n+1];
		lvl[0] = -1;                              //level of parent of root is non-existent, hence -1. root at level 0;
		adjList = new ArrayList[n+1];
		for (int i=0; i<=n; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		//populating adjacency list
		String [] edges = null;
		for (int i=1; i<n ;i++) {
			edges = sc.nextLine().split(" ");
			int u = Integer.parseInt(edges[0]);
			int v = Integer.parseInt(edges[1]);
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		P = new int[n+1][max];
		//initializing p[][]. p[u][0] means parent of u. 
		for(int i=0; i<=n; i++) {
			for (int j=0; j<max; j++) {
				P[i][j] = -1;
			}
		}
		dfs(1,0);
		
		//filling values in P. P[u][0] values are already filled in dfs
		for(int i=1; i<=n; i++) {
			for (int j=1; j<max; j++) {
				if (P[i][j-1] != -1)
					P[i][j] = P[P[i][j-1]][j-1];
			}
		}
		System.out.println(lca(1,11));		
		
	}

}

/*
Notes:
	The algorithm is first we move up from the node which is at a higher level
	When level of both the nodes is same, we start moving up.
	Both of these movements is logarithmic. We move in 2^i steps.

Complexity: O(log n) for finding lca
--------------------------------------------------------------------------------

For a node u, P[u][i] means the node which is at 2^i level above u
So P[u][0] is the parent node of u, since 2^0 = 1 and node above is parent node.

To understand above problem, consider below tree:
						    1
						  /  \
						 2    3
						/      \
					   4        5
					  /
					 6
					/
				   7
				  /
				 8
				/
			   9
			  /
			 10
			/
		   11 
		   
Level of node 10 is 7 and level of node 5 is 2.
We want to find lca of node 10 and node 5

--------------------------------------------------------------------------------
Applications of Binary lifting:

1. Finding LCA
2. Finding distance between two nodes say u and v.
Solution:
	Find lca of u and v, say w.
	Then distance between u and v = lvl[u] + lvl[v] - 2*lvl[w]
	
	int dis(int u, int v){
    	if (lvl[u] < lvl[v]) swap(u, v);
    	int w = lca(u, v);
    	return lvl[u] + lvl[v] - 2*lvl[w];
	}

3. Finding ancestor of a node at x distance from it.

//Get the ancestor of node "u"
//which is "dis" distance above.

int getAncestor(int u, int dis){
    dis = lvl[u] - dis;
    int i, lg = 0;
    for(; (1<<lg) <= lvl[u]; lg++); lg--;

    for(i=lg; i>=0; i--){
        if (lvl[u] - (1<<i) >= dis)
            u = P[u][i];
    }

    return u;
}

 * */
