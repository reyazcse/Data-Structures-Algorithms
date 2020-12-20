//https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/

/*
Solution:
	For a tree with 'root'  to be bst, its left subtree and right subtree should be bst and
	root value must be greater than max value in left subtree and smaller than min value in right subtree.
	
	For each node, we hold the info:
		whether the tree rooted at node is bst
		min value in this tree if it is bst
		max value in this tree if it is bst
		sum of all nodes in this tree if it is bst
		
	Also keep track of overall max sum for a bst.
	
	
	Complexity: O(n) time and O(h) space. h = n for skewed tree
	

Another clean solution:
	https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/discuss/684444/Java-Simple-DFS-use-Output-class
 * */
package leetcode;

public class MaximumSumBST {
	int overall_max = Integer.MIN_VALUE;
	public int maxSumBST(TreeNode root) {
		maxSumBSTUtl(root);
		return overall_max < 0 ? 0 : overall_max;		//for negative overall_max, need to return 0 as per leetcode
		
	}
	
	private Info maxSumBSTUtl(TreeNode node) {
		if(node == null) {
			return null;
		}
		
		Info left_info = maxSumBSTUtl(node.left);
		Info right_info = maxSumBSTUtl(node.right);
		
		
		Info current_info = new Info(node);
		
		if(left_info != null) {
			current_info.isBst = left_info.isBst && node.val > left_info.max;
			if(current_info.isBst) {																		//if bst then only calculate the values otherwise we do not use the below values ever
				current_info.min = Math.min(node.val, left_info.min);
				current_info.max = Math.max(node.val, left_info.max);
				current_info.sum += left_info.sum;
			}
			
		}
		
		if(right_info != null) {
			current_info.isBst = current_info.isBst && right_info.isBst &&  node.val < right_info.min;
			if(current_info.isBst) {																		//if bst then only calculate the values otherwise we do not use the below values ever
				current_info.min = Math.min(current_info.min, right_info.min);
				current_info.max = Math.max(current_info.max, right_info.max);
				current_info.sum += right_info.sum;
			}
			
		}
		if(current_info.isBst) {
			overall_max = Math.max(overall_max, current_info.sum);
		}
		
		
		return current_info;
		
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(10);
		root.right.left = new TreeNode(-5);
		root.right.right = new TreeNode(20);
		System.out.println(new MaximumSumBST().maxSumBST(root));
	}
	
	private static class Info {
		public boolean isBst;				//is tree bst
		public int min;						//min value in the tree
		public int max;						//max value in the tree	
		public int sum;						//sum of all nodes in the tree
		
		public Info(TreeNode node) {	
			this.isBst = true;				//single node is bst
			this.min = node.val;
			this.max = node.val;
			this.sum = node.val;
		}
	}

}
