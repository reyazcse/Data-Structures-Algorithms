//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
/*
Solution: Iterative solution using stack 
 * */
package misc;

import java.util.Stack;

public class FlattenBinaryTree3 {
	public void flatten(TreeNode root) {
		if(root == null) {
			return;
		}
		TreeNode prev = new TreeNode();
		
		Stack<TreeNode> stack = new Stack<>();
		
		stack.add(root);
		
		while(!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			
			prev.right = curr;
			prev.left = null;
			
			if(curr.right != null) {
				stack.add(curr.right);
			}
			
			if(curr.left != null) {
				stack.add(curr.left);
			}
			
			prev = curr;
		}

	}
	
	
	private static class TreeNode {
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
