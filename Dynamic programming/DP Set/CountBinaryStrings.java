//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/count-binary-strings-official/ojquestion
/*
1. You are given a number n.
2. You are required to print the number of binary strings of length n with no consecutive 0's.
 * */

/*
Solution:
	The idea is:

	Let's use a 2D array dp[][]
	dp[0][i] = number of strings ending with 0
	dp[1][i] = number of strings ending with 1

	dp[0][0] = dp[1][0] = 0
	dp[0][1] = dp[1][1] = 1  since number of strings ending with 0 or 1 is 1

	Now to find number of strings ending with 0 for k length, we can append 0 only to strings of length k-1 which end with 1
	To find number of strings ending with 1 for k length, we can append 1 to all strings of length k-1

	We will use above idea to solve the problem.
	We can do away with the 2D array.

 * */
package misc;

import java.util.Scanner;

public class CountBinaryStrings {
	//Withoug using extra space:
	public static void main(String[] args) throws Exception {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int [][]dp = new int[2][n+1];
		int end_with_0s = 1;
		int end_with_1s = 1;

		for(int i=2; i<=n; i++) {
			int new_end_with_0s = end_with_1s;					//add 0 to old strings that end with 1
			int new_end_with_1s = end_with_0s + end_with_1s;	//add 1 to all old strings
			end_with_0s = new_end_with_0s;
			end_with_1s = new_end_with_1s;
		}
		int total  = end_with_1s + end_with_0s;
		System.out.println(total);
	}

	/*
    //using a 2D array
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int [][]dp = new int[2][n+1];
        dp[0][0] = 0;
        dp[1][0] = 0;
        dp[0][1] = 1;
        dp[1][1] = 1;

        for(int i=2; i<=n; i++) {
            dp[0][i] = dp[1][i-1];
            dp[1][i] = dp[0][i-1] + dp[1][i-1];
        }
        int total  = dp[0][n] + dp[1][n];
        System.out.println(total);
    }
	 */

}
