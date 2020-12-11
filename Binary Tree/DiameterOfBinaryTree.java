//https://leetcode.com/problems/diameter-of-binary-tree/
/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 * */

/*
Solution:
	Diameter of a tree at current node = max of (diameter of left subtree, diameter of right subtree, left height + right height)
	Complexity: O(n) time 
 * */
package misc;

public class DiameterOfBinaryTree {
	int ans;
	public int diameterOfBinaryTree(TreeNode root) {
		utl(root);
		return ans;
	}
	
	private int utl(TreeNode node) {
		if(node == null) {
			return 0;
		}
		
		int lheight = utl(node.left);
		int rheight = utl(node.right);
		ans = Math.max(ans, lheight + rheight);		//lheight + rheight is the case when current node is part of the longest diameter so far
		return  1 + Math.max(lheight, rheight);		//return height of current node
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
