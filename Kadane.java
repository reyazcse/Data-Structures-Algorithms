//src: https://leetcode.com/problems/maximum-subarray/
package misc;

public class Kadane {
	public int maxSubArray(int[] nums) {
        int local_max = 0;
        int global_max = Integer.MIN_VALUE;
        for(int i=0; i< nums.length; i++) {
            local_max = Math.max(nums[i], nums[i] + local_max);  //either take this element or this element+local_max
            global_max = Math.max(global_max, local_max);        //update answer
        }
        return global_max;
    }
	public static void main(String[] args) {
		int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(new Kadane().maxSubArray(nums));

	}

}
