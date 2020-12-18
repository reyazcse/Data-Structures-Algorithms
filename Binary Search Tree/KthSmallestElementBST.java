//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Constraints:

	The number of elements of the BST is between 1 to 10^4.
	You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * */

/*
Solution: Both recursive and iterative approach follow below idea:

		  Do inorder traversal and keep track of number of nodes visited.
		  When k nodes have been visited, return that node.


Note: 
1. For follow up question, refer the leetcode solution. The idea is to keep the tree and also a doubly linked list.
2. To find the Kth largest, do reverse inorder traversal.

		  
 * */
package leetcode;

import java.util.Stack;

public class KthSmallestElementBST {
	//Complexity: O(H + k) time and O(H) space for recursive stack. H = height. In worst case H = N
	int count = 0;
	public int kthSmallest(TreeNode root, int k) {
		TreeNode kthNode = utl(root, k);
		return kthNode.val;							//since it is given that kth element exists, no null is returned
	}
	
	private TreeNode utl(TreeNode node, int k) {
		if(node == null) {
			return null;
		}
		TreeNode left = utl(node.left, k); 
		if(left != null) {						//kth node found in left subtree
			return left; 
		}
		
		count++;
		if(count  == k) {
			return node;
		}
		
		return utl(node.right, k);
	}
	
	
	/************************************Iterative version*****************************************************************************/
	//Assumption is k >= 1 
	//Complexity: O(H + k) time and O(H) space for stack.  H = height. In worst case H = N
	public int kthSmallest_Iterative(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		while(true) {
			while(root != null) {
				stack.add(root);
				root = root.left;
			}
			root = stack.pop();
			k--;
			if(k == 0) {
				return root.val;
			}
			root = root.right;
		}
	}
	

}
