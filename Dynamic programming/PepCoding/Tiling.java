//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/tiling1-official/ojquestion
/*
1. You are given a number n representing the length of a floor space which is 2m wide. It's a 2 * n board.
2. You've an infinite supply of 2 * 1 tiles.
3. You are required to calculate and print the number of ways floor can be tiled using tiles.
 * */

//Solution: O(n) time and O(1) space
//We can keep the tile vertically: n-1  OR we can keep the tile horizontally : n-2. Hence f(n) = f(n-1) + f(n-2)
package misc;

import java.util.Scanner;


public class Tiling {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = 1;
		int b = 2;
		int c = a+b;
		for(int i=3; i<=n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		System.out.println(c);
	}
	
	//Using O(n) space
	/*
	int n = sc.nextInt();
	
	int [] dp = new int[n+1];
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 2;
	
	for(int i=3; i<=n; i++) {
		dp[i] = dp[i-1] + dp[i-2];
	}
	System.out.println(dp[n]);
	
	*/

}
