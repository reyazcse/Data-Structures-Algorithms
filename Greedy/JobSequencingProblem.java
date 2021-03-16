//https://www.geeksforgeeks.org/job-sequencing-problem/
/*
Given an array of jobs where every job has a deadline and associated profit if the job is finished before the deadline. 
It is also given that every job takes the single unit of time, so the minimum possible deadline for any job is 1. 
How to maximize total profit if only one job can be scheduled at a time.

Input:  Five Jobs with following
deadlines and profits
JobID   Deadline  Profit
  a       2        100
  b       1        19
  c       2        27
  d       1        25
  e       3        15
Output: Following is maximum 
profit sequence of jobs
        c, a, e
        
 * */

/*
Solution:
	In the problem, n is total number of jobs
	We solve it using greedy method.
	So sort the jobs in decreasing order of profit.
	And then simply pick the job and put it in slot if slot is free.
	
	Here one thing to note is that we put the job in slot as late as possible
	Let's say we pick a job and its deadline is 4.
	So we can put it in 0th, 1st, 2nd or 3rd slot. 
	Let's say we put it in 0th slot. Then let's say we have another job whose deadline is 1.
	This job can only be put in the 0th slot, but 0th slot is already occupied! 
	We could have put the first job in slot no 1,2,3.
	So the idea is: PUT THE JOB IN SLOT AS LATE AS POSSIBLE
	So we put the job in slot no 3 and then in slot no 0 we can put the second job!
	
	Another thing to note is when calculating slot, we take min of (no of jobs-1, deadline-1).
	Let's say we have job1 with deadline,profit = (5,50) and job2 is (2,20). Here n=2
	So if we keep job1 at 4th slot (first free slot) and job2 at 1st slot, then slot 0, 2 and 3 are empty
	So using min calculation, we put job1 in slot 1 and job2 in slot 0. 
	
	Complexity: O(n log n) for sorting and O(n^2) to put the job in a slot
				So O(n^2) total time
				
	
	Above has been implemented in Method 1.

 * */
package misc;

import java.util.Arrays;

public class JobSequencingProblem {
	
	//Method 1: Here we take initial slot as Math.min(n-1, jobs[i].deadline-1); and we have
	//created slots size as n which is no of jobs.
	//This works good for case when we have for e.g n=2 jobs and their deadline is very big say 1000000.  We save space
	int[] JobScheduling(Job jobs[], int n){
		int [] result = new int[2];
		int[] resultJob = new int[n];
		boolean[]slots = new boolean[n];
		
		//sort in desc order of profits
		Arrays.sort(jobs, (job1, job2) -> {
			return job2.profit - job1.profit;
		});
		
		//select a job
		for(int i=0; i<jobs.length; i++) {
			//get last slot for the job
			int slot = Math.min(n-1, jobs[i].deadline-1);		//put the job as late as possible.
			while(slot >= 0) {					//keep decreasing till a free is not found
				if(slots[slot] == false) {
					slots[slot] = true;
					resultJob[slot] = jobs[i].id;		//storing actual job id
					result[0] ++;						//increment count of jobs
					result[1] += jobs[i].profit;		//increment total profit so far
					break;
				}
				slot--;									//find another slot if not free
			}
		}
		return result;
	}
	
	/*
	 * Method 2: Here we create the array of length equal to max deadline.
	 * This works well when we have many jobs but deadline is low. Say for eg 100000 jobs but all have deadlines of 1
	 * References for code: https://www.youtube.com/watch?v=LjPx4wQaRIs&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma
	 * 
	 * */
	
	int[] JobScheduling2(Job jobs[], int n){
		int [] result = new int[2];
		
		//sort in desc order of profits
		Arrays.sort(jobs, (job1, job2) -> {
			return job2.profit - job1.profit;
		});
		
		int maxDeadline = 0;
		for(int i=0; i<jobs.length; i++) {
			if(jobs[i].deadline > maxDeadline) {
				maxDeadline = jobs[i].deadline;
			}
		}
		
		int[] slots = new int[maxDeadline + 1];			//we use 1 based: job with deadline 4 can be placed in 1,2,3 or 4 th slot
		
		for(int i=1; i<=maxDeadline; i++) {				//init
			slots[i] = -1;
		}
		
		
		//select a job
		for(int i=0; i<jobs.length; i++) {
			
			for(int j = jobs[i].deadline; j > 0; j--) {
				if(slots[j] == -1) {					//free slot
					slots[j] = i;							//put job in slot
					result[0] ++;						//increment count of jobs
					result[1] += jobs[i].profit;		//increment total profit so far
					break;
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private class Job {
		public int id;
		public int profit;
		public int deadline;
		public Job(int id, int profit, int deadline) {
			this.id = id;
			this.profit = profit;
			this.deadline = deadline;
		}
	}
}
