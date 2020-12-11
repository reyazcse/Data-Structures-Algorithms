//https://leetcode.com/problems/balanced-binary-tree/
/*
Solution:
	O(n)  solution with early return if left subtree is not balanced but it runs slower than the other solution on leetcode
 * */
package misc;


public class BalancedBinaryTree2 {
	
	public boolean isBalanced(TreeNode root) {
		Height height = new Height();
		return isBalancedUtl(root, height);
	}
	
	private boolean isBalancedUtl(TreeNode root, Height height) {
		if(root == null) {
			height.height = 0;
			return true;
		}
		Height lheight = new Height();
		Height rheight = new Height();
		boolean leftBalanced = isBalancedUtl(root.left, lheight);
		if(!leftBalanced) {
			return false;												//early return if left subtree not balanced
		}
		boolean rightBalanced = isBalancedUtl(root.right, rheight);		
		
		//set height for current node:
		height.height = Math.max(lheight.height, rheight.height) + 1;
		
		//return true if left and right subtrees balanced and height b/w two subtrees is at most 1
		return leftBalanced && rightBalanced && Math.abs(lheight.height - rheight.height) <= 1;
		
	}


	
	private class Height {
		public int height = 0;
	}
	
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

}
