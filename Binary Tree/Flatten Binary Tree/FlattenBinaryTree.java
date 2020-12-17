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

//Solution: In the flattened tree, right pointers are used. Use preorder
	

package misc;


public class FlattenBinaryTree {
	
	public void flatten(TreeNode root) {
		flattenUtl(root);
	}
	
	private TreeNode flattenUtl(TreeNode node) {
		if(node == null) {
			return null;
		}
		TreeNode curr = node;
		TreeNode left = flattenUtl(node.left);
		TreeNode right = flattenUtl(node.right);
		
		node.left = null;								//no left subtree in flattened tree
		
		curr.right = left;								//join with right subtree of current node
		while(curr.right != null) {
			curr = curr.right;
		}
		curr.right = right;
		
		return node;
	}
	
	
	//SIMPLER VERSION OF ABOVE METHOD
	
	public void flatten_simple(TreeNode root) {
		if(root == null) {
			return;
		}
		TreeNode left = root.left;
		TreeNode right = root.right;
		TreeNode tmp = root;
		flatten_simple(root.left);
		flatten_simple(root.right);
		root.left = null;
		root.right = left;
		while(tmp.right != null) {
			tmp = tmp.right;
		}
		tmp.right = right;
		
		
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
