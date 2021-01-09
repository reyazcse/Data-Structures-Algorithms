//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/buy-and-sell-stocks-cooldown-ita-official/ojquestion
/*
1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are required to print the maximum profit you can make if you are allowed infinite transactions, but have to cooldown for 1 day after 1 transaction
i.e. you cannot buy on the next day after you sell, you have to cooldown for a day at-least before buying again.
Note - There can be no overlapping transaction. One transaction needs to be closed (a buy followed by a sell) before opening another transaction (another buy).
 * */

/*
Solution:
	Let's assume we have three variables to store profits for a day: 
		bought_state_profit, sold_state_profit, cool_state_profit
	bought_state_profit means we have a stock in hand. It is of type: b, bsb, bsbbsb, etc which means we have an extra stock in our hand
	sold_state_profit means we do not have any stock in hand. It is of type: bs, bsbs, bsbsbs etc which means we have sold what we bought
	cool_state_profit means we are in cooldown state after selling our stock previous day. It is of type: bsc, bsbsc, bsbsbsc, etc which means we bought, then sold and are now are in cooldown state.

	For each day, we have to find these values. Finally return the maximum of these values after calculating for last day.

	Transitions:
	From bought state, we can go to sold state
	From sold state, we can go to cooldown state
	From cooldown state, we can go to bought state.


 * */
package misc;

import java.util.Scanner;

public class BuyAndSellStocksCooldown {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[]arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}

		//for 1st day
		int bought_state_profit = -arr[0];
		int sold_state_profit = 0;
		int coold_state_profit = 0;
		
		for(int i=1; i<n; i++) {
			//transition from cooldown state
			int new_bought_state_profit = Math.max(bought_state_profit, coold_state_profit - arr[i]);
			
			//transition possible from bought state
			int new_sold_state_profit = Math.max(sold_state_profit, arr[i] + bought_state_profit);
			
			//transition possible from sold state
			int new_coold_state_profit = Math.max(coold_state_profit, sold_state_profit);
			
			//update old values:
			bought_state_profit = new_bought_state_profit;
			sold_state_profit = new_sold_state_profit;
			coold_state_profit = new_coold_state_profit;
					
		}
		
		//answer is the sold_state_profit
		System.out.println(sold_state_profit);

	}

}
