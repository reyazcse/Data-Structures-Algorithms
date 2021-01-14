//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/highway-billboard-official/ojquestion
/*
Question:
1. You are given a number M representing length of highway(range).
2. You are given a number N representing number of bill boards.
3. You are given N space separated numbers representing (P)position of bill-boards.
4. You are given N space separated numbers representing (R)revenue corresponding to each (P)position.
5. You are given a number T such that bill-boards can only be placed after specific distance(T).
6. Find the maximum revenue that can be generated.

Input Format
A number M(length of highway)
A number N(number of bill boards)
P1 ,P2 ,P3 ,P4 ,P5 .... Pn (placement of N bill-boards)
R1 ,R2 ,R3 ,R4 ,R5 .... Rn (revenue from each bill-board)
A number T (neccessary distance b/w two bill-board)

Output Format
Find the maximum revenue that can be generated.
Check the sample output and question video.

Constraints
1 <= M <= 100000
1 <= N <= 50000
1 <= Pi <= M
1 <= Ri <= 100
1 <= T
 * */


/*
Solution:
	Approach:
		Place the billboards on the highway one by one. Calculate the maximum revenue at each billboard
		While placing current billboard, take the maximum revenue from the billboards placed so far before current billboard.
		The other billboards should be at a distance more than 't' as asked in the problem
		After taking maximum revenue, add it to revenue of current billboard and place this billboard.
		
		Finally return the overall maximum revenue.
		
	Complexity: O(n^2) time and O(n) time
		
 * */
package misc;

import java.util.Scanner;

public class HighwayBillboard1 {

	public static int solution(int m, int[] x, int[] rev, int t) {
        // write your code here
        int ans = Integer.MIN_VALUE;
        int n = rev.length;
        int[]dp = new int[n];
        dp[0] = rev[0];								//place the first billboard
        for(int i=1; i<n; i++) {					//place other billboards one by one
            int max = 0;							//Note that max has to be 0 and not Integer.MIN_VALUE since it may not change if we do not find any board
            for(int j=0; j < i; j++) {
                int dist = x[i]-x[j];
                if(dist > t) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + rev[i];					//revenue due to current billboard
            ans = Math.max(ans, dp[i]);				//overall maximum revenue
            
        }
        return ans;
    }
    public static void input(int[] arr, Scanner scn) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int m = scn.nextInt();							//length of highway
        int n = scn.nextInt();							//number of billboards

        int x[] = new int[n];							
        input(x, scn);

        int revenue[] = new int[n];
        input(revenue, scn);

        int t = scn.nextInt();

        System.out.println(solution(m, x, revenue, t));
        scn.close();
    }
    
    

}
