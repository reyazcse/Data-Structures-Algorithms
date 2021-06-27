//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
/*
Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.

 * */
/*
Solution:
	ASSUMPTION: Both the given nodes are present in the tree and nodes values are unique
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
		
		if(left != null && right != null) {			//p and q are found in left and right subtrees, hence root is the LCA
			return root;
		}
		
		if(left != null) {			//LCA found in left subtree
			return left;
		}
		return right;				//LCA found in right subtree
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
