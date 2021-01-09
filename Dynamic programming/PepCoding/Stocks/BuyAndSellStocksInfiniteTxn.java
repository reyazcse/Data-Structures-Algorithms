//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/buy-and-sell-stocks-ita-official/ojquestion
/*
1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are required to print the maximum profit you can make if you are allowed infinite transactions.
Note - There can be no overlapping transaction. One transaction needs to be closed (a buy followed by a sell) before opening another transaction (another buy)
 * */
package misc;

import java.util.Scanner;

public class BuyAndSellStocksInfiniteTxn {

	
	
	public static void main(String[] args) {
		buyAndSellStocks_1();
		buyAndSellStocks_2();

	}
	
	private static void buyAndSellStocks_1() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[]arr = new int[n];

		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}

		int total_profit = 0;

		for(int i=1; i<n; i++) {
			if(arr[i] >= arr[i-1]) {						//sell each day if profit						
				total_profit += arr[i] - arr[i-1];
			}
		}

		//total profit after all transactions
		System.out.println(total_profit);
	}
	
	private static void buyAndSellStocks_2() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[]arr = new int[n];

		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}

		int buy_day = 0, sell_day = 0, total_profit = 0;

		for(int i=1; i<n; i++) {
			if(arr[i] >= arr[i-1]) {								
				sell_day++;											//keep postponing selling
			}else {													
				total_profit += arr[sell_day] - arr[buy_day];		//sell at one time to aggregate all profits
				buy_day = i;
				sell_day = i;
			}
		}

		//we have to sell on last also as it may lead to profit
		total_profit += arr[sell_day] - arr[buy_day];

		//total profit after all transactions
		System.out.println(total_profit);
	}

}
