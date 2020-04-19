/*
src: http://codeforces.com/contest/166/submission/29672806
	 http://codeforces.com/contest/166/submission/29672861      //more optimized with no memory exceeded

Question:
	Given a tetrahedron and its four vertices A, B, C and D. Top vertex is D and base vertices are A,B and C.
	We are given N steps and we start at vertex D. We have move over the edges and come back to D in N steps. How many such ways are there?
For example, if N=2, then we have 4 ways: 
										D to A and back D
										D to B and back D
										D to C and back D


Solution:
	Earlier we solved by using space : table[N][4]  //total 4 vertices
	Here we solve it in table[N][2] by considering the three base vertices as similar.
		
 * */

package misc;
//We consider vertex D as 1 and vertex A,B,C as 0 as they are symmetrical
public class Tetrahedron2 {
	public static int findWays(int N, int [] vertices) {
		int [][] table = new int [N+1][vertices.length/2];  //there are 4 vertices always in a tetrahedron
		//base case when we reach vertex D and no more steps left.
		table[0][1] = 1;
		utl(N, vertices, table, 1);
		return table[N][1];
	}
	

	public static int utl(int N, int []vertices, int [][] table, int same ) {
		if (N == 0) {
			if (same == 1)                  //we reach vertex D
				return 1;
			return 0;
		}
		if (table[N][same] != 0)
			return table[N][same];
		
		//if vertex is D, then we go to the three vertices of the base and they are symmetrical,
		//hence we multiply by 3
		if (same  == 1) {
			table[N][same] += 3 * utl(N-1, vertices, table, 0);
		//else for base vertices, we go to two other similar vertices plus we go the to top vertex D	
		}else  {
			table[N][same] += 2* utl(N-1, vertices, table, 0) + utl(N-1, vertices, table, 1); 
		}
		return table[N][same];
	}
	
	
	public static void main (String [] args) {
		int N = 4;
		int []vertices = {0,1,2,3};
		System.out.println(findWays(N, vertices));
	}
}
