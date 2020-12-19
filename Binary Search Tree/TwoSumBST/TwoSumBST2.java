//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/

/*
Solution:
	We do inorder and reverse inorder for the tree at the same time.
	So we have the minimum and maximum element in the start.
	Iterative inorder is used using stacks.
	
	Complexity:
		O(n) time and for a balanced bst, below approach takes O(logn) time
		
References: https://www.geeksforgeeks.org/find-a-pair-with-given-sum-in-bst/

 * */
package leetcode;

import java.util.Stack;

public class TwoSumBST2 {
	public boolean findTarget(TreeNode root, int k) {
		//stack1, val1, done1, root1 are used for normal inorder traversal and other variables are used for reverse inorder traversal
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		int val1=0,val2=2;
		boolean done1=false, done2=false;
		TreeNode root1 = root, root2 = root;
		
		while(true) {
			//normal inorder
			while(done1 == false) {
				if(root1 != null) {
					stack1.add(root1);
					root1 = root1.left;
				}else {
					done1 = true;
					if(!stack1.isEmpty()) {
						done1 = true;
						TreeNode curr1 = stack1.pop();
						val1 = curr1.val;
						root1 = curr1.right;
					}
					
				}
				
			}
			
			//reverse inorder
			while(done2 == false) {
				if(root2 != null) {
					stack2.add(root2);
					root2 = root2.right;
				}else {
					done2 = true;
					if(!stack2.isEmpty()) {
						done2 = true;
						TreeNode curr2 = stack2.pop();
						val2 = curr2.val;
						root2 = curr2.left;
					}
				}
			}
			if(val1 >= val2) {
				return false;
			}
			if(val1+val2 == k) {			//val1 != val2
				return true;
			}
			if(val1 + val2 < k) {			//sum is less hence move 'forward' (normal inorder)
				done1 =false;
			}
			else if(val1 + val2 > k) {		//sum is more hence move 'backward' (reverse inorder)
				done2 = false;
			}
		}
		
	}
	
	/********************************Cleaner version of above*******************************************************************/
	public boolean findTarget_Clean(TreeNode root, int k) {
		Stack<TreeNode> leftStack = new Stack<>();
		Stack<TreeNode> rightStack = new Stack<>();
		
		TreeNode left = root;
		while(left != null) {
			leftStack.add(left);
			left = left.left;
		}
		
		TreeNode right  = root;
		while(right != null) {
			rightStack.add(right);
			right = right.right;
		}
		
		while(!leftStack.isEmpty() && !rightStack.isEmpty()) {
			left = leftStack.peek();								//do not pop it here!
			right = rightStack.peek();								//do not pop it here!
			
			if(left != right) {										//not same value
				if(left.val + right.val == k) {
					return true;
				}else if(left.val + right.val < k) {
					leftStack.pop();
					left = left.right;
					while(left != null) {
						leftStack.add(left);
						left = left.left;
					}
				}else {
					rightStack.pop();
					right = right.left;
					while(right != null) {
						rightStack.add(right);
						right = right.right;
					}
				}
				
			}else {
				return false;
			}
		}
		return false;
	}

}
