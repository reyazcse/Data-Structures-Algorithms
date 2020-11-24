//https://www.geeksforgeeks.org/weighted-job-scheduling-log-n-time/

//O(n logn) time and O(n) space for table
package misc;

import java.util.Arrays;



public class WeightedJobScheduling3 {
	/********************************************BOTTOM UP *********************************************************/
	
		
	public int getMaxProfitBottomUp(Job[]jobs) {
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.end - job2.end;
		});
		int n = jobs.length;
		int[]dp = new int[jobs.length];
		dp[0] = jobs[0].profit;
		for(int i=1; i<n; i++) {
			int exclProfit = dp[i-1];
			int inclProfit = jobs[i].profit;
			int next = findNextNonConflicting(jobs, i);
			if(next != -1) {
				inclProfit += dp[next];
			}
			dp[i] = Math.max(inclProfit, exclProfit);
		}
		return dp[n-1];
		
	}
	
	//binary search
	private int findNextNonConflicting(Job[] jobs, int n) {
		int lo = 0, hi = n-1;
		
		while(lo <= hi) {
			int mid = lo + (hi-lo)/2;
			if(jobs[mid].end <= jobs[n].start) {
				if(jobs[mid+1].end <= jobs[n].start) {		//on right of mid, still we get a valid job, so move right
					lo = mid+1;
				}else {
					return mid;
				}
			}else {
				hi = mid-1;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		WeightedJobScheduling3 ob = new WeightedJobScheduling3();
		 Job[] jobs  = new Job[4];
		 jobs[0] = ob.new Job(3,5,20);
		 jobs[1] = ob.new Job(1,2,50);
		 jobs[2] = ob.new Job(6,19,100);
		 jobs[3] = ob.new Job(2,100,200);
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
