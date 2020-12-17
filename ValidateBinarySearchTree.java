//https://leetcode.com/problems/validate-binary-search-tree/

/*
Solution:
	Do inorder traversal of the tree.
	Keep track of the previous node.
	Check if left subtree is valid bst, current node's value is greater than previous node's value and right subtree is also bst.
	Else return false;
	
	Complexity: O(n) time and O(1) space
 * */
package leetcode;

public class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		Previous previous = new Previous();
		return utl(root, previous);
	}
	
	
	//previous is the previous node traversed across the recursive calls
	private boolean utl(TreeNode node, Previous previous) {
		if(node == null) {
			return true;
		}
		
		if (utl(node.left, previous)) {					//if left subtree is bst
			if(previous.prev == null) {
				previous.prev = node;
			}else {
				if(previous.prev.val >= node.val) {		
					return false;
				}
				previous.prev = node;
			}
			
			return utl(node.right, previous);
		}
		
		return false;									//left subtree is not bst
	}
	
	//to store previous node across recursive calls
	private static class Previous{
		public TreeNode prev = null;
	}
}

//Note:  [1,1] was failing on leetcode, so used '>=' while comparing prev and current values