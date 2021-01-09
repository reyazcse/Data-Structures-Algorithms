//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/paint-fence-official/ojquestion
/*
1. You are given a number n and a number k in separate lines, representing the number of fences and number of colors.
2. You are required to calculate and print the number of ways in which the fences could be painted so that not more than two consecutive fences have same colors.
 * */

/*
Solution:

Let's create a 2D matrix: dp[2][n]
dp[0][j] is the number of ways of painting fence so that not more than two consecutive fences have same color but last two fences have SAME color
dp[1][j] is the number of ways of painting fence so that not more than two consecutive fences have same color but last two fences have DIFFERENT color

We do not have values in the table for j=0 and j=1.
For j = 2:
	dp[0][j] = k 
	dp[1][j] = k.(k-1)
	total1  = dp[0][j] + dp [1][j]


For j = 3:
	dp[0][j] = dp[1][j]    		//since we add the same color which is at the end 
	dp[1][j] = total1 * k-1	    //we take each group of fences colored so far and can place k-1 colors so that last two fences have different colors	
	total2  = dp[0][j] + dp [1][j]

and so on...
Check the link for video explanation.

Instead of using table, we can use two variables.
 * */
package misc;

import java.util.Scanner;

public class PaintFence {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		//for n = 2
		long last_two_same  = k;						//number of ways to paint with given condition if last two fences have same color
		long last_two_diff = k * (k-1);
		long total = last_two_same + last_two_diff;		//number of ways to paint with given condition if last two fences have different color
		
		//start for 3 fences since for two fences, already calculated above
		for(int i=3; i<=n; i++) {
			last_two_same = last_two_diff;
			last_two_diff = total * (k-1);
			total = last_two_same + last_two_diff;
		}
		
		System.out.println(total);

	}

}






















