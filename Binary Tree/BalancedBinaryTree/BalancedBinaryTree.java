//https://leetcode.com/problems/balanced-binary-tree/
/*
Solution:
	A tree at current node is balanced if:
		left subtree is balance and
		right subtree is balanced and
		height of left subtree and height of right subtree differ by at most 1.
		
		
	Complexity: O(n) since we find height in the same call
	
Note: Here traverse all the nodes even though might find a subtree not balanced.
One idea for early return is to check if left subtree is not balanced, then return false. No need to check right subtree is 
balanced or not.
See BalancedBinaryTree2.java
 * */
package misc;

public class BalancedBinaryTree {
	boolean isBalanced = true;							//initially this has to be true.
	public boolean isBalanced(TreeNode root) {
		utl(root);
		return isBalanced;
	}
	
	private int utl(TreeNode root) {
		if(root == null) {
			return 0;
		}
		
		int lheight = utl(root.left);
		int rheight = utl(root.right);
		
		boolean currentBalanced = Math.abs(lheight - rheight) <= 1;
		isBalanced = isBalanced & currentBalanced;						//isBalanced = true denotes left and right subtrees are balanced
		
		return Math.max(lheight, rheight) + 1;									//return current  height
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
