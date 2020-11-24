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

//Solution: O(n^2) space and O(n) space dp
//References: geeksforgeeks solution
package misc;

import java.util.Arrays;



public class WeightedJobScheduling2 {
	//RECURSIVE SOLUTION:
	//O(n * 2^n) time
	public int getMaxProfitRecursive(Job[] jobs) {
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.end - job2.end;
		});
		return utlRecursive(jobs, jobs.length);
	}
	
	private int utlRecursive(Job[] jobs, int n) {
		//we reach here if we have not included any jobs before this OR included a job which does not conflict with this job
		if(n == 1) {
			return jobs[n-1].profit;
		}
		//profit without including current job
		int exclProfit = utlRecursive(jobs, n-1);
		
		////profit including current job
		int inclProfit = jobs[n-1].profit;
		int i = findNextNonConflicting(jobs, n);
		if(i != -1) {
			inclProfit += utlRecursive(jobs, i+1);
		}
		return Math.max(inclProfit, exclProfit);
	}
	
	private int findNextNonConflicting(Job[] jobs, int j) {
		for(int i = j-2; i>=0; i--) {
			if(jobs[i].end <= jobs[j-1].start) {
				return i;
			}
		}
		return -1;
	}
	
	/*************************************************TOP DOWN*********************************************************/
	//O(n^2) time and O(n) space for table
	public int getMaxProfitTopDown(Job[] jobs) {
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.end - job2.end;
		});
		int[]dp = new int[jobs.length+1];
		return utlTopDown(jobs, jobs.length, dp);
		
	}
	
	private int utlTopDown(Job[]jobs, int n, int []dp) {
		if(n == 1) {
			return jobs[0].profit;
		}
		if(dp[n] != 0) {
			return dp[n-1];
		}
		//profit without including current job
		int exclProfit = utlRecursive(jobs, n-1);
		//profit including current job
		int inclProfit = jobs[n-1].profit;
		int i = findNextNonConflicting(jobs, n);
		if(i != -1) {
			inclProfit += utlRecursive(jobs, i+1);
		}
		dp[n] = Math.max(inclProfit, exclProfit);  
		return dp[n];
		
	}
	
	/********************************************BOTTOM UP *********************************************************/
	//O(n^2) time and O(n) space for table
	
	//NOte for this approach, we can also do with dp = new int[jobs.length]. I used dp = new int[jobs.length+1] to map with 
	//the recursive solution
	
	public int getMaxProfitBottomUp(Job[]jobs) {
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.end - job2.end;
		});
		int n = jobs.length;
		int[]dp = new int[jobs.length+1];
		dp[1] = jobs[0].profit;
		for(int i=2; i<=n; i++) {
			int exclProfit = dp[i-1];
			int inclProfit = jobs[i-1].profit;
			int next = findNextNonConflicting(jobs, i);
			if(next != -1) {
				inclProfit += dp[next+1];
			}
			dp[i] = Math.max(inclProfit, exclProfit);
		}
		return dp[n];
		
	}
	
	public static void main(String[] args) {
		WeightedJobScheduling2 ob = new WeightedJobScheduling2();
		 Job[] jobs  = new Job[4];
		 jobs[0] = ob.new Job(3,5,20);
		 jobs[1] = ob.new Job(1,2,50);
		 jobs[2] = ob.new Job(6,19,100);
		 jobs[3] = ob.new Job(2,100,200);
		//System.out.println(ob.getMaxProfitRecursive(jobs));
		System.out.println(ob.getMaxProfitTopDown(jobs));
		System.out.println(ob.getMaxProfitBottomUp(jobs));
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

}
