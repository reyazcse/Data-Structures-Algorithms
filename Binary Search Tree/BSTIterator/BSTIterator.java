//https://leetcode.com/problems/binary-search-tree-iterator/
/*
Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 * */

/*
Solution: Store the nodes of the tree in a list.
		  The list has a dummy node at the start.
		  The 'right' pointers of the tree are used as the 'next' pointers of the list.
		  
Complexity:
		O(n) time and O(n) space at the time of initialisation.
		(After that calls to next() and hasNext() takes O(1) time.
		
Note: This solution is similar to the solution 1 given on leetcode. There an arraylist of integer has been used where we store the node values.
 * */
package leetcode;

public class BSTIterator {
	TreeNode head = null, dummy = null;
	public BSTIterator(TreeNode root) {
		dummy = new TreeNode();
		head = dummy;
		inorder(root);
	}

	public int next() {				//it is given that next calls will always be valid, so don't check for boundary conditions
		head = head.right;
		return head.val;
	}

	public boolean hasNext() {
		if(head.right == null) {
			return false;
		}
		return true;
	}

	private void inorder(TreeNode root) {
		if(root == null) {
			return;
		}
		inorder(root.left);
		dummy.right = new TreeNode(root.val);
		dummy = dummy.right;
		inorder(root.right);
	}

}
