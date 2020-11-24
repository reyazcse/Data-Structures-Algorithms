//https://www.geeksforgeeks.org/greedy-algorithm-to-find-minimum-number-of-coins/
/*
Given a value V, if we want to make a change for V Rs, and we have an infinite supply of each of the denominations in Indian currency, i.e., we have an infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes, what is the minimum number of coins and/or notes needed to make the change?
Examples:

Input: V = 70
Output: 2
We need a 50 Rs note and a 20 Rs note.

 * */

/*
Solution:
	We solve it using greedy approach.
	Idea is sort the denominations and start with the largest value coin.
	Take as many as possible for current largest denomination.
	Then move on the next smaller denominations.
	
	NOTE:
	This algorithm may FAIL. For example
	denoms[] = {9,6,5,1} and V = 11
	Then this algo gives 9,1,1 but CORRECT ANSWER IS 6,5!!!
 * */

package misc;

import java.util.ArrayList;

//This algorithm may give wrong answers depending upon denominations
public class MininumCoinsGreedy {
	int findMin(int[] denom, int V) {
		ArrayList<Integer> coins = new ArrayList<>();
		
		//denom is already sorted so no need to sort it
		for(int i=denom.length-1; i>=0; i--) {
			while(V - denom[i] >= 0) {
				coins.add(denom[i]);
				V -= denom[i];
			}
		}
		return coins.size();
	}
	public static void main(String[] args) {
		int[] denom = {1, 2, 5, 10, 20,  
			    50, 100, 500, 1000};
		int V = 93;
		
		MininumCoinsGreedy ob = new MininumCoinsGreedy();
		System.out.println(ob.findMin(denom, V));
		
	}

}
