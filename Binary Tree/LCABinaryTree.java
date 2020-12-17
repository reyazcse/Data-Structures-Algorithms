//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

/*
Solution:
	ASSUMPTION: Both the given nodes are present in the tree
	Complexity:
		O(n) time and O(h) space.
		h = n when we have a skewed tree
 * */
package misc;


public class LCABinaryTree {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) {
			return null;
		}
		if(root.val == p.val || root.val == q.val) {
			return root;
		}
		
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		
		if(left == null) {
			return right;
		}
		if(right == null) {
			return left;
		}
		
		return root;
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
