//Question: Travelling salesman problem src: https://www.youtube.com/watch?v=MTsvJUwZdS4
/*
	Here we use mask for the vertices. 
	We have the vertices as ABCD and we start from A and have to come back to A. Mask is "0000" for "DCBA"
	If say A is visited, then the mask becomes "0001".
	From each vertex, we visit all the unvisited vertices and get the value.
	We return the minimum cost.

 * */

package misc;

public class TravellingSalesmanProblem {
	private int n = 4; 								 //no of vertices	
	private int visited_all = (1 << n) - 1;          // when we have '1111', it means all vertices are visited
	
	int[][] adjMatrix = {{0,20,42,25},
						 {20,0,30,34},
						 {42,30,0,10},
						 {25,34,10,0}};

	public int findMinPath() {
		return utl(0, adjMatrix, 1);		//initially mask is '0001' as we visit A so pos is 0.
	}
	
	public int utl(int pos, int[][]adjMatrix, int mask) {
		if (mask == visited_all) {
			return adjMatrix[pos][0];	//return cost of going back to A from this vertex if we have visited all the vertices
		}
		int res = Integer.MAX_VALUE;
		//iterate over all vertices and see which one is not visited yet
		for(int i=0; i< adjMatrix.length; i++) {
			if((mask & (1 << i)) == 0) {			//if this city i is unvisited
				int ans = adjMatrix[pos][i] + utl(i, adjMatrix, mask|(1 << i)); 
				res = Math.min(res, ans);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		TravellingSalesmanProblem obj = new TravellingSalesmanProblem();
		System.out.println(obj.findMinPath());
	}


}
