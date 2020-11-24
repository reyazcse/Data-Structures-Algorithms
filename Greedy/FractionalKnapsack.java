//https://www.geeksforgeeks.org/fractional-knapsack-problem/
/*
Given weights and values of n items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 * */

//Solution: Greedy approach
package misc;

import java.util.Arrays;

public class FractionalKnapsack {
	double fractionalKnapsack(int W, Item arr[], int n) {
		Arrays.sort(arr, (itm1, itm2) -> {
			return itm2.cost.compareTo(itm1.cost);
		});
		double totalProfit = 0;
		for(Item i : arr) {
			if(W - i.weight >= 0) {
				W = W - i.weight;
				totalProfit += i.value;
			}else {
				double fraction = (double)W / (double)i.weight;
				W = (int)(W - i.weight * fraction);
				totalProfit += fraction * i.value;
				break;
			}
		}
		
		return totalProfit;
    }
	double fractionalKnapsack(int[] values, int [] weight, int W) {
		int n = values.length;
		Item[] arr = new Item[n];
		for(int i=0; i<n; i++) {
			arr[i] = new Item(values[i], weight[i]);
		}
		return fractionalKnapsack(W, arr, n);
	}
	public static void main(String[] args) {
		int values[] = {60,100,120};
		int weight[] = {10,20,30};
		int W = 50;
		FractionalKnapsack ob = new FractionalKnapsack();
		System.out.println(ob.fractionalKnapsack(values, weight, W));
	}
	
	
	
	private class Item {
		public Double cost;
	    public int value, weight;
	    Item(int x, int y){
	        this.value = x;
	        this.weight = y;
	        cost =  new Double ((double)x / y);
	    }
	}

}
