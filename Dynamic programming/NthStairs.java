//We have to reach the Nth stairs and at each time we can make at most K steps. Find the total number of ways to do so 
//for eg if N = 3 and K=2, then we have (1+1+1), (1+2) and (2+1) = 3 ways
package misc;

public class NthStairs {
	//RECURSIVE
	
	public int totalWaysRecursive(int N, int K) {
		if(N == 0)			//base case
			return 1;
		if(N < 0)
			return 0;
		int res = 0;
		//we can reach Nth stair from N-1, N-2...N-K stair
		for(int i=1; i<=K; i++) {
			res += totalWaysRecursive(N-i, K);
		}
		return res;
	}
	
	//DP TOP DOWN
	public int totalWaysTopDown(int N, int K) {
		int [] table = new int[N+1];
		totalWaysTopDown(N, K, table);
		return table[N];
	}
	
	private int totalWaysTopDown(int N, int K, int [] table) {
		if(N == 0)return 1;
		if(N <= 0) return 0;
		if(table[N] != 0)return table[N];		//value is already computed
		int res = 0;
		//we can reach Nth stair from N-1, N-2...N-K stair
		for(int i=1; i<=K; i++) {
			res += totalWaysTopDown(N-i, K, table);
		}
		table[N] = res;
		return res;
		
	}
	
	
	// BOTTOM UP DP
	public int totalWaysBottomUp(int N, int K) {
		int [] table = new int[N+1];
		//base case
		table[0] = 1;
		table[1] = 1;
		
		//first we fill up till < K
		for(int i=2; i<K; i++) {
			for(int j=1; j<=i; j++) {
				table[i] += table[i-j];
			}
		}
		
		//start to fill from K till end
		for(int i=K; i <= N; i++) {
			for(int j=1; j<=K; j++) {
				table[i] += table[i-j];
			}
		}
		return table[N];
	}
	public static void main(String[] args) {
		NthStairs obj = new NthStairs();
		int N = 10;
		int K = 3;
		System.out.println(obj.totalWaysRecursive(N, K));
		System.out.println(obj.totalWaysTopDown(N, K));
		System.out.println(obj.totalWaysBottomUp(N, K));
	}

}

/*
Note:
This is what I have observed.
	Suppose we are 5 th stair and we can reach it from 4th and 3rd stair (K=2 for example).
	res[5] = res[4] + res[3]
	From 4th stair we can reach in 1 way to 5th
	From 3rd stair we can reach in 2 ways...
	So it should be like this?  res[5] = res[4] + res[3]  + 1 + 2 ??
	The answer is no since we already count it: We return 1 if N==0!!!
	
 * */
