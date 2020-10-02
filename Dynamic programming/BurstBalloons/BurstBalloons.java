//https://leetcode.com/problems/burst-balloons/

package leetcode;

//In this solution, we find the max when we burst a balloon at each index. We return the max
//Exponential complexity. Not accepted
public class BurstBalloons {
	public int maxCoins(int[] nums) {
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<nums.length; i++) {
			max = Math.max(max, utl(nums,i));
		}
		return max;
    }
	
	//get max amount by bursting each balloon
	private int utl(int[] nums, int index) {
		if(nums.length == 1)
			return nums[index];
		int currVal = (index == 0 ? 1 : nums[index-1]) * nums[index] *(index == nums.length-1 ? 1 : nums[index+1]);
		//new array to be passed to the recursive call
		int []numsDup = new int[nums.length-1];
		for(int i=0; i < index; i++) {
			numsDup[i] = nums[i];
		}
		for(int i=index+1; i<nums.length; i++) {
			numsDup[i-1] = nums[i];
		}
		int max = Integer.MIN_VALUE;
		//get max by bursting balloon from each index
		for(int i=0; i<numsDup.length; i++) {
			max = Math.max(max, utl(numsDup,i));
		}
		if(max != Integer.MIN_VALUE) {
			return currVal + max;
		}else {
			return currVal;
		}
	}
	public static void main(String[] args) {
		BurstBalloons obj = new BurstBalloons();
		int []nums = {3,1,5,8};
		System.out.println(obj.maxCoins(nums));

	}

}
