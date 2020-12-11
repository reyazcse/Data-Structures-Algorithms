//https://leetcode.com/problems/symmetric-tree/
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 * */

/*
Solution:
	Both recursive and iterative approach are given below.
	The iterative approach is using two queues. 
	Another iterative approach using one queue is discussed here: The idea is the levels of a symmetric tree are palindromic
	https://www.geeksforgeeks.org/check-symmetric-binary-tree-iterative-approach/
 * */
package misc;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
	
	//RECURSIVE
	public boolean isSymmetric(TreeNode root) {
		if(root == null) {							//base case
            return true;
        }
        return utl(root.left, root.right);			//check if left and right subtrees are symmetric
	}
	
	private boolean utl(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if(p != null && q != null && p.val == q.val) {
            boolean outer = utl(p.left, q.right);
            boolean inner = utl(p.right, q.left);
            return outer && inner;
        }
        
        return false;
    }
	
	//Iterative
	public boolean isSymmetricIterative(TreeNode root) {
		if(root == null || (root.left == null && root.right == null)) {	//if root null or its children null then symmetric
			return true;
		}
		if(root.left == null || root.right == null) {	//only one child is null, hence not symmetric
			return false;
		}
		
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();
		
		q1.add(root.left);
		q2.add(root.right);
		while(!q1.isEmpty() && !q2.isEmpty()) {
			TreeNode n1 = q1.poll();
			TreeNode n2 = q2.poll();
			if(n1.val != n2.val) {							//value mismatch
				return false;
			}
			
			if(n1.left != null && n2.right != null) {   	//both child not null then insert in queue
				q1.add(n1.left);
				q2.add(n2.right);
			}else if(n1.left != null || n2.right != null) {  // only of one the children is not null
				return false;
			}
			
			if(n1.right != null && n2.left != null) {		//both child not null then insert in queue
				q1.add(n1.right);
				q2.add(n2.left);
			}else if(n1.right != null || n2.left != null) {	// only of one the children is not null
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
