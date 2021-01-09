//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/tiling2-official/ojquestion
/*
1. You are given a number n and a number m separated by line-break representing the length and breadth of a n * m floor.
2. You've an infinite supply of m * 1 tiles.
3. You are required to calculate and print the number of ways floor can be tiled using tiles.
 * */

/*
Solution: O(n) time and O(n) space
	
 * */
package misc;

import java.util.Scanner;

public class Tiling2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[]dp = new int[n+1];
		for(int i=1; i<=n; i++ ) {
			if(i < m) {
				dp[i] = 1;							//tiles can be placed vertically only on  m * n floor
			}else if(i == m) {
				dp[i] = 2;							//tiles can be all be placed vertically or all be placed horizontally. so 2 ways
			}else {
				dp[i] = dp[i-1] + dp[i-m];			//place first tile vertically and recurse for i-1 length floor OR place first tile horizontally and recurse for i-m length floor 
			}
		}
		
		System.out.println(dp[n]);
		
	}

}
