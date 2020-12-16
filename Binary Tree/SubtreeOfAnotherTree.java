//https://leetcode.com/problems/subtree-of-another-tree/

/*
Solution:
	Two ways to solve this:
	1. Get the serialized strings for both the trees and then compare if serialized 't' is contained in serialized  's'
	
	2. At every node of 's', check if 't' is identical to subtree at current node. 
	
	Below is implementation of the 2nd method.
	Its complexity is O(m*n) time and O(n) space. n = number of nodes in tree s.
 * */
package misc;


public class SubtreeOfAnotherTree {
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if(t == null) {
			return true;
		}
		return preorder(s, t);
	}

	private boolean preorder(TreeNode s, TreeNode t) {
		if(s == null) {
			return false;
		}
		if(areEqual(s, t)) {
			return true;
		}
		if(preorder(s.left, t) || preorder(s.right, t)) {
			return true;
		}
		return false;
	}

	//returns true if tree s is identical to tree t
	private boolean areEqual(TreeNode s, TreeNode t) {
		if(s == null && t == null) {					//both null
			return true;	
		}
		if(s == null || t == null) {					//only one is null
			return false;
		}
		if(s.val == t.val && areEqual(s.left, t.left) && areEqual(s.right, t.right)) {
			return true;
		}

		return false;
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
