//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/paint-house-official/ojquestion
/*
1. You are given a number n, representing the number of houses.
2. In the next n rows, you are given 3 space separated numbers representing the cost of painting nth house with red or blue or green color.
3. You are required to calculate and print the minimum cost of painting all houses without painting any consecutive house with same color.

Input format:
A number n
n1red n1blue n1green
n2red n2blue n2green
.. n number of elements
 * */

//Solution: Using dp
package misc;

import java.util.Scanner;

public class PaintHouse {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int  n = sc.nextInt();
		int [][] arr = new int[n][3];
		
		for(int i=0; i<n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
			arr[i][2] = sc.nextInt();
		}
		
		int [][] dp = new int[n][3];
		
		//coloring first house:
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];

		for(int i=1; i<n; i++) {
			//cost of coloring ith house with red color and then i-1 th house should either be colored with green or blue
			dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			
			//similarly for other colors
			dp[i][1] = arr[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			
			dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		//finally minimum of the values of last row is the answer
		int answer = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
		System.out.println(answer);
		
	}

}
