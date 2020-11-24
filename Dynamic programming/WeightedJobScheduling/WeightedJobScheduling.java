//https://www.geeksforgeeks.org/weighted-job-scheduling/

/*
Given N jobs where every job is represented by following three elements of it:
	Start Time
	Finish Time
	Profit or Value Associated (>= 0)
	
Find the maximum profit subset of jobs such that no two jobs in the subset overlap.

Example:

Input: Number of Jobs n = 4
       Job Details {Start Time, Finish Time, Profit}
       Job 1:  {1, 2, 50} 
       Job 2:  {3, 5, 20}
       Job 3:  {6, 19, 100}
       Job 4:  {2, 100, 200}
Output: The maximum profit is 250.
 * */

//Solution: O(n^2) space and O(n^2) space dp
package misc;

import java.util.Arrays;

public class WeightedJobScheduling {
	public int getMaxProfitRecursive(Job[] jobs) {
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.end - job2.end;
		});
		Job lastJobIncluded = null;
		return utlRecursive(jobs, 0, lastJobIncluded);
	}
	
	private int utlRecursive(Job[] jobs, int i, Job lastJobIncluded) {
		if(i == jobs.length) {
			return 0;
		}
		int exclProfit = utlRecursive(jobs, i+1, lastJobIncluded);
		int inclProfit = 0;
		// lastJobIncluded is null when we have not considered any job so far
		if(lastJobIncluded == null || jobs[i].start >= lastJobIncluded.end) {
			lastJobIncluded = jobs[i];
			inclProfit = jobs[i].profit + utlRecursive(jobs, i+1, lastJobIncluded);
		}
		return Math.max(exclProfit, inclProfit);
		
	}
	
	/****************************************TOP DOWN Approach***********************************************************************/
	
	public int getMaxProfitTopDown(Job[] jobs) {
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.end - job2.end;
		});
		int lastJobIncluded = -1;
		int[][] dp = new int[jobs.length][jobs.length];
		initialize(dp);
		return utlTopDown(jobs, 0, lastJobIncluded, dp);
	}
	
	private int utlTopDown(Job[] jobs, int i, int lastJobIncluded, int[][] dp) {
		if(i == jobs.length) {
			return 0;
		}
		
		//precomputed value
		if(lastJobIncluded != -1 && dp[lastJobIncluded][i] != -1) {
			return dp[lastJobIncluded][i];
		}
		int exclProfit = utlTopDown(jobs, i+1, lastJobIncluded, dp);
		int inclProfit = 0;
		
		// lastJobIncluded is -1 when we have not considered any job so far
		if(lastJobIncluded == -1 || jobs[i].start >= jobs[lastJobIncluded].end) {
			inclProfit = jobs[i].profit + utlTopDown(jobs, i+1, i, dp);
		}
		int currProfit = Math.max(exclProfit, inclProfit);
		
		//if not the first time
		if(lastJobIncluded != -1) {
			dp[lastJobIncluded][i] = currProfit;
		}
		 
		return currProfit;
		
	}
	private void initialize(int[][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
	}

	public static void main(String[] args) {
		WeightedJobScheduling ob = new WeightedJobScheduling();
		 Job[] jobs  = new Job[4];
		 jobs[0] = ob.new Job(3,5,20);
		 jobs[1] = ob.new Job(1,2,50);
		 jobs[2] = ob.new Job(6,19,100);
		 jobs[3] = ob.new Job(2,100,200);
		System.out.println(ob.getMaxProfitRecursive(jobs));
		System.out.println(ob.getMaxProfitTopDown(jobs));

	}
	
	private class Job {
		public int start;
		public int end;
		public int profit;
		public Job(int start, int end , int profit) {
			this.start = start;
			this.end = end;
			this.profit = profit;
		}
	}
	
	//BELOW TOP DOWN will not work since we do not store lastJobIncluded in dp!!! 
	
//	private int utlRecursiveTopDown(Job[] jobs, int i, Job lastJobIncluded, int[] dp) {
//		if(i == jobs.length) {
//			return 0;
//		}
//		if(dp[i] != -1) {
//			return dp[i];
//		}
//		int exclProfit = utlRecursiveTopDown(jobs, i+1, lastJobIncluded, dp);
//		int inclProfit = 0;
//		if(lastJobIncluded == null || jobs[i].start >= lastJobIncluded.end) {
//			lastJobIncluded = jobs[i];
//			inclProfit = jobs[i].profit + utlRecursiveTopDown(jobs, i+1, lastJobIncluded, dp);
//		}
//		dp[i] = Math.max(exclProfit, inclProfit); 
//		return dp[i];
//		
//	}

}
