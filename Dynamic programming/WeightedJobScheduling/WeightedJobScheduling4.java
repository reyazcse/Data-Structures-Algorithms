//https://www.geeksforgeeks.org/weighted-job-scheduling-set-2-using-lis/

//Solution: We solve it using LIS
/*

	The idea is for each job, we consider all jobs before it can be done.
	Sort the jobs based on start time.
	Then apply logic similar to bottom up LIS
	
	Complexity: O(n^2) time 
	
	Gives TLE Error!!! on leetcode: https://leetcode.com/problems/maximum-profit-in-job-scheduling/

*/ 
package misc;

import java.util.ArrayList;
import java.util.Arrays;


public class WeightedJobScheduling4 {
	public int getMaxProfitBottomUp(Job[]jobs) {
		//sort jobs on basis of start times asc
		Arrays.sort(jobs, (job1, job2) ->{
			return job1.start - job2.start;
		});
		
		ArrayList<ArrayList<Job>> L = new ArrayList<>();
		//add first row in L
		ArrayList<Job> jobItm = new ArrayList<>();
		jobItm.add(jobs[0]);
		L.add(jobItm);
		
		//iterate for all jobs 
		//L[i] stores the jobs which can be done including job[i]
		for(int i=1; i<jobs.length; i++) {
			for(int j=0; j<i; j++) {
				L.add(new ArrayList<>());													//add empty list to avoid exception
				//if job j and job i do not conflict and profit is more for  L[j]
				if(jobs[j].end <= jobs[i].start && findSum(L.get(j)) > findSum(L.get(i))) {
					L.remove(i);
					L.add(i, (ArrayList<Job>)L.get(j).clone());
				}
			}
			//add current job to the current list
			L.get(i).add(jobs[i]);
		}
		
		int maxProfit = 0;
		
		for(int i=0; i<L.size(); i++) {
			maxProfit = Math.max(maxProfit, findSum(L.get(i)));
		}
		return maxProfit;
	}
	
	//returns total profit for the list 
	private int findSum(ArrayList<Job> jobs) {
		int sum = 0;
		for(Job job : jobs) {
			sum += job.profit;
		}
		return sum;
	}
	public static void main(String[] args) {
		WeightedJobScheduling4 ob = new WeightedJobScheduling4();
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
