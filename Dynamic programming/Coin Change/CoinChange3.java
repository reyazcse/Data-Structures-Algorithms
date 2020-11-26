
package misc;

public class CoinChange3 {
	public long count(int S[], int m, int n) 
    { 
        long [] dp = new long[n+1];
        dp[0] = 1;
        
        //for each coin value, process the value which is less than or equal 
        for(int coin : S) {
        	for(int j=1; j<=n; j++) {		//can also do like this: for(int j=coin; j<=n; j++) then no need of if condition
        		if(coin <= j) {
        			dp[j] += dp[j - coin];
        		}
        		
        	}
        }
        return dp[n];
        
    }
	
	public static void main(String[] args) {
		int []S = {1,2,3};
		int m = S.length;
		int n = 4;
		CoinChange3 ob = new CoinChange3();
		System.out.println(ob.count(S, m, n));

	}

}



//NOTE:

/*
Input:
	S = {1,2,3}, m = 3
	n = 4

If we use below code, then we will get the answer as 7


1,1,1,1
1,1,2
1,2,1
2,1,1
1,3
3,1
2,2

 * */

//public long count(int S[], int m, int n) 
//{ 
//    long [] dp = new long[n+1];
//    dp[0] = 1;
//    for(int i=1; i<=n; i++) {
//    	for(int j=0; j<m; j++) {
//    		if(S[j] <= i) {
//    			dp[i] += dp[i - S[j]];
//    		}
//    	}
//    }
//    return dp[n];
//    
//}
