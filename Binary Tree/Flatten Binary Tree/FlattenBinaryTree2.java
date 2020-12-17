//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
/*
For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 * */

/*
Solution:
	Create a dummy root of the new tree.
	Do preorder traversal of the original tree.
	At each current node, create a node with the value of the current node and attach to the dummy root.
	Move the dummy root forward (tmp = tmp.right)
	
	In this solution, the original tree is not modified.
	
	DON'T KNOW WHY THIS SOLUTION IS RETURNING WRONG ANSWER ON LEETCODE!
 * */
package misc;


public class FlattenBinaryTree2 {
	TreeNode pseudoRoot = new TreeNode();	//dummy root. state maintained across all recursive calls.
	TreeNode tmp = pseudoRoot;
	
	
	//WRONG ANSWER ON LEETCODE!!!
	public void flatten(TreeNode root) {
		solve(root);
		root = tmp.right;
		//inorder(root);
	}


	private void solve(TreeNode root) {
		if(root == null) {
			return;
		}
		
		pseudoRoot.right = new TreeNode(root.val);			//create a new node to store
		pseudoRoot = pseudoRoot.right;						//move global pointer
		solve(root.left);
		solve(root.right);
	}

	public static void main(String[] args) {
		FlattenBinaryTree2 ob = new FlattenBinaryTree2();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		
		//ob.inorder(root);
		ob.flatten(root);
		//ob.inorder(root);
		
		
		
		
	}
	
	

	private void inorder(TreeNode root) {
		if(root == null) {
			return;
		}
		inorder(root.left);
		System.out.println(root.val);
		inorder(root.right);
	}
	
	private void preorder(TreeNode root) {
		if(root == null) {
			return;
		}
		System.out.println(root.val);
		preorder(root.left);
		preorder(root.right);
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
