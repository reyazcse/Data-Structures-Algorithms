/*

Question:
	Given a tetrahedron and its four vertices A, B, C and D. Top vertex is D and base vertices are A,B and C.
	We are given N steps and we start at vertex D. We have move over the edges and come back to D in N steps. How many such ways are there?
For example, if N=2, then we have 4 ways: 
										D to A and back D
										D to B and back D
										D to C and back D
Solution:
	We assume A=0, B=1, C=2 and D=3.
	When we reach D and N=0, then it means a tour has been found.
	From each vertex, we move to OTHER vertices, with steps decreased by 1.

 * */

package misc;

public class Tetrahedron {
	//recursive
	public static int findTours(int N, int[] vertices) {
		//we start from vertex D, i.e. 3
		return findToursUtl(N, vertices, 3);
	}
	
	public static int findToursUtl(int N, int[] vertices, int index) {
		//all steps exhausted
		if(N == 0) {
			//we reach the destination vertex D
			if (index == 3)
				return 1;
			//if we reach any other node, we do not have a solution
			return 0;
		}
		
		//now visit all other vertices except current vertex
		int totalSteps = 0;
		for(int i=0; i<vertices.length; i++) {
			if (i == index)
				continue;
		totalSteps += findToursUtl(N-1, vertices, i);
			
		}
		return totalSteps;	
	}
	
	//dp: bottom up
	public static int findToursBottomUp (int N, int []vertices) {
		int [][]table = new int [N+1][vertices.length];
		//System.out.println("table length" + table[0].length);
		table[0][3] = 1;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<table[0].length; j++) {
				for(int k=0; k<table[0].length; k++) {
					if (k == j)
						continue;
					table[i][j] += table[i-1][k];
					
					
				}
			}
		}
		return table[N][vertices.length-1];
		
	}
	
	public static void main(String [] args) {
		int []vertices = {0,1,2,3};
		int N = 2;
		System.out.println(findTours(N, vertices));
		System.out.println(findToursBottomUp(N, vertices));
	}

}
