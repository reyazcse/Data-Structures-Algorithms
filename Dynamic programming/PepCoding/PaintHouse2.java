package misc;

import java.util.Scanner;

public class PaintHouse2 {

	public static void main(String[] args) {
		paintHouse();
		paintHouse_Optimized();
	}

	//O(n * k) time and O(n * k) space
	/*
	 * When we calculate for ith house, we need minimum value in the i-1 th row and this minimum value should not be in same column.
	 * So we keep track of least and second least element in the i-1th row. If least element lies in same column for ith house, we take second least element
	 * else take least element.
	 * 
	 * */
	
	private static void paintHouse_Optimized() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] arr = new int[n][k];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<k; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int least = Integer.MAX_VALUE, second_least = Integer.MAX_VALUE;
		int [][]dp = new int[n][k];
		
		//color first house and also calculate the least and second_least values
		for(int j=0; j<k; j++) {
			dp[0][j] = arr[0][j];
			
			if(arr[0][j] < least) {
				second_least = least;
				least = arr[0][j];
			}else if(arr[0][j] < second_least) {
				second_least = arr[0][j];
			}
		}
		
		for(int i=1; i<n; i++) {
			int curr_second_least = Integer.MAX_VALUE, curr_least = Integer.MAX_VALUE;	//least and second least elements for current row i
			for(int j=0; j<k; j++) {
				
				//color ith house with jth color. Then i-1 th house should not have jth color. 
				int min_cost = 0;
				if(least == dp[i-1][j]) {
					min_cost = second_least;
				}else {
					min_cost = least;
				}
				
				dp[i][j] = arr[i][j] + min_cost;
				
				//update new least and second least elements for this ith row
				if(dp[i][j] < curr_least) {
					curr_second_least = curr_least;
					curr_least = dp[i][j];
				}else if(dp[i][j] < curr_second_least) {
					curr_second_least = dp[i][j];
				}
			}
			//update least and second least values for next iteration
			least = curr_least;
			second_least = curr_second_least;
		}
		
		//answer is the minimum of all values of last row i.e. 'least'
		System.out.println(least);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//O(n * k^2) time and O(n * k) space
	private static void paintHouse() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] arr = new int[n][k];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<k; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int [][]dp = new int[n][k];
		
		//color first house
		for(int j=0; j<k; j++) {
			dp[0][j] = arr[0][j];
		}
		
		for(int i=1; i<n; i++) {
			for(int j=0; j<k; j++) {
				//color ith house with jth color. Then i-1 th house should not have jth color. 
				//Take the minimum cost of coloring i-1 th house
				int min_cost = Integer.MAX_VALUE;
				for(int c = 0; c < k ; c++) {
					if(c != j) {
						min_cost = Math.min(min_cost, dp[i-1][c]);
					}
					
				}
				
				dp[i][j] = arr[i][j] + min_cost;
			}
		}
		
		//answer is minimum of all the costs for n-1 th house. So take minimum of all values in the n-1 th row
		int answer = dp[n-1][0];
		for(int i=1; i<k; i++) {
			answer = Math.min(answer, dp[n-1][i]);
		}
		System.out.println(answer);
	}

}
