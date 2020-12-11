//https://leetcode.com/problems/same-tree/
/*
Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * */

//Solution: recursie and iterative
package misc;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
	
	//recursive
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null) {
			return true;
		}
		
		if(p != null && q != null && p.val == q.val) {
			if(isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
				return true;
			}
		}
		return false;
	}
	
	//iterative
	public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
		if(p == null && q == null) {						//if both null
			return true;
		}
		if(p == null || q == null) {						//if one of them is null
			return false;
		}
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();
		q1.add(p);
		q2.add(q);
		while(!q1.isEmpty() && !q2.isEmpty()) {
			TreeNode t1 = q1.poll();
			TreeNode t2  = q2.poll();
			
			if(t1.val != t2.val) {							//node value not equal
				return false;
			}
			
			if(t1.left != null && t2.left != null) {		//if both not null
				q1.add(t1.left);
				q2.add(t2.left);
			}else if (t1.left != null || t2.left != null) {	//if one of them not null
				return false;
			}
			
			if(t1.right != null && t2.right != null) {		//if both not null
				q1.add(t1.right);
				q2.add(t2.right);
			}else if (t1.right != null || t2.right != null) {	//if one of them not null
				return false;
			}
		}
		return true;
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
