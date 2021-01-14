/*
Solution:
	Try to find revenue at each mile length starting from length = 0 till m
	At each mile, we have two options:
		put a billboard if a billboard is present for this mile
		do not put billboard
	Take maximum of these above two.
	
Complexity:
	O(m) time and O(m) space
 * */
package misc;

import java.util.HashMap;
import java.util.Scanner;

public class HighwayBillboard2 {

	public static int solution(int m, int[] x, int[] rev, int t) {
		int n = x.length;
		HashMap<Integer, Integer> boardPositionToIndex = new HashMap<>();
		int[] dp = new int[m+1];
		
		for(int i=0; i<x.length; i++) {
			boardPositionToIndex.put(x[i], i);
		}
		dp[0] = 0;										//for 0 mile length
		
		for(int i=1; i<=m; i++) {
			//do not put billboard
			dp[i] = dp[i-1];					
			
			//put billboard if present
			if(boardPositionToIndex.containsKey(i)) {
				int revenue = rev[boardPositionToIndex.get(i)];
				int boardPlaced = 0;
				if(i-t-1 >= 0) {									
					boardPlaced = revenue + dp[i-t-1];				//distance between board should be greater than 't'
					
				}else {
					boardPlaced = revenue;
				}
				dp[i] = Math.max(dp[i], boardPlaced);
			}
		}
		
		return dp[m];
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
