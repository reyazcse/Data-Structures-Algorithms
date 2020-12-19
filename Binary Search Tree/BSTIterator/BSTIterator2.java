//https://leetcode.com/problems/binary-search-tree-iterator/

/*
Solution: Using iterative inorder with controlled addition of nodes.
		  Put the leftmost nodes on the stack.
		  Then if next() is called, return the value of the top node and also pop it.
		  Get the right node of the popped element and again push all nodes of this node to the stack.
		  
		  hasNext() returns true if stack is not empty.
		  		  
Complexity: Each node gets pushed and popped exactly once in next() when iterating over all N nodes.
			That comes out to 2N * O(1) over N calls to next(), making it O(1) on average, or O(1) amortized. 
			
			O(h) space
References: leetcode solution2
		  
 * */
package leetcode;

import java.util.Stack;

public class BSTIterator2 {
	Stack<TreeNode> stack;  	
	public BSTIterator2(TreeNode root) {
		stack = new Stack<>();
		left_inorder(root);
	}

	public int next() {				//it is given that next calls will always be valid, so don't check for boundary conditions
		TreeNode top = stack.pop();
		
		if(top.right != null) {
			left_inorder(top.right);
		}
		
		return top.val;
	}

	public boolean hasNext() {
		return stack.size() > 0;
	}
	
	//puts leftmost nodes in the stack
	private void left_inorder(TreeNode root) {
		while(root != null) {
			stack.add(root);
			root = root.left;
		}
	}

}
