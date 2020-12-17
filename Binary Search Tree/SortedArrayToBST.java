//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

//Solution: O(n) time using divide and conquer
package leetcode;

public class SortedArrayToBST {
	
	public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        
       return utl(nums, 0, nums.length-1); 
    }
    
    private TreeNode utl(int[] nums, int lo, int hi){
        if(lo > hi) {
            return null;
        }
        
        int mid = lo + (hi-lo)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = utl(nums, lo, mid-1);
        node.right = utl(nums, mid+1, hi);
        return node;
    }

}
