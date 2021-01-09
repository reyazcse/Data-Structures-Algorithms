//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/maximum-sum-non-adjacent-elements-official/ojquestion
/*
1. You are given a number n, representing the count of elements.
2. You are given n numbers, representing n elements.
3. You are required to find the maximum sum of a subsequence with no adjacent elements.
 * */


package misc;

import java.util.Scanner;

public class MaximumSumNonAdjacent {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int  n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int include = arr[0];
		int exclude = 0;
		
		for(int i=1; i<n; i++) {
			int new_include = exclude + arr[i];             //maximum value obtained so far if current element is included     
			int new_exclude = Math.max(include, exclude);   //maximum value obtained so far if current element is excluded
			
			include = new_include;
			exclude = new_exclude;
		}
		System.out.println(Math.max(include, exclude));
	}

}
