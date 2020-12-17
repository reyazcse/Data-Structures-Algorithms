//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

/*
Solution:
	Using parent of each node.
	
	Complexity: O(n) time and O(n) space
	
	Note: There is a third method provided on leetcode. You can refer that too
 * */
package misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LCABinaryTree2 {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		HashMap<TreeNode, TreeNode> parent = new HashMap<>();
		Stack<TreeNode> stack = new Stack<>();
		parent.put(root, null);
		stack.add(root);
		
		//store the parents of tree nodes till we store the parents of p and q
		while(!parent.containsKey(p) || !parent.containsKey(q)) {
			TreeNode curr = stack.pop();
			
			if(curr.left != null) {
				parent.put(curr.left, curr);
				stack.add(curr.left);
			}
			
			if(curr.right != null) {
				parent.put(curr.right, curr);
				stack.add(curr.right);
			}
		}
		
		//store all ancestors p.
		Set<TreeNode> ancestors_p = new HashSet<>();
		while(p != null) {
			ancestors_p.add(p);
			p = parent.get(p);
		}
		
		//find common ancestor of p and q
		while(!ancestors_p.contains(q)) {
			q = parent.get(q);
		}
		
		return q;
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
