//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/buy-sell-stocks-transaction-fee-ita-official/ojquestion
/*
1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are give a number fee, representing the transaction fee for every transaction.
4. You are required to print the maximum profit you can make if you are allowed infinite transactions, but has to pay "fee" for every closed transaction.
Note - There can be no overlapping transaction. One transaction needs to be closed (a buy followed by a sell) before opening another transaction (another buy).
 * */

/*
Solution:
	Let's assume we have two profits: bought_state_profit and sold_state_profit
	bought_state_profit means we have a stock in hand. It is of type: b, bsb, bsbbsb, etc which means we have an extra stock in our hand
	sold_state_profit means we do not have any stock in hand. It is of type: bs, bsbs, bsbsbs etc which means we have sold what we bought
	
	For each day, we have to find these values. Finally return the maximum of these two values after calculating for last day.
	
 * */
package misc;

import java.util.Scanner;

public class BuyAndSellStocksTxnFee {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[]arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		int txn_fee = sc.nextInt();
		
		//for 1st day
		int bought_state_profit = -arr[0];
		int sold_state_profit = 0;
		
		//calculate for other days:
		for(int i=1; i<n; i++) {
			int new_bought_state_profit = Math.max(bought_state_profit, sold_state_profit - arr[i]);			//max of {old value, buy today}
			int new_sold_state_profit = Math.max(sold_state_profit, arr[i] - txn_fee + bought_state_profit);    //max of {old value, sell the stock in hand}
			
			bought_state_profit = new_bought_state_profit;
			sold_state_profit = new_sold_state_profit;
		}
		
		//answer is maximum of two values
		System.out.println(Math.max(bought_state_profit, sold_state_profit));

	}

}
