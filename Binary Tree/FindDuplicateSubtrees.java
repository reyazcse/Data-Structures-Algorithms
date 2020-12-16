//https://leetcode.com/problems/find-duplicate-subtrees/
/* 
	Given the root of a binary tree, return all duplicate subtrees.	
	For each kind of duplicate subtrees, you only need to return the root node of any one of them.
	Two trees are duplicate if they have the same structure with the same node values.
 * */

/*
	Solution:
		Serialize the tree using preorder traversal.
		Store the serialized tree in a map. 
		If count is 2, then add current node to the list.
		Return list.
	
	Complexity: O(n^2) time since forming the string serialized can take O(n) time.
				O(n^2) space for the count_subtrees map
				
 * */
package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FindDuplicateSubtrees {
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> ans = new ArrayList<>();
		HashMap<String, Integer> count_subtrees = new HashMap<>();
		utl(root, ans, count_subtrees);
		return ans;
	}
	
	private String utl(TreeNode root, List<TreeNode> ans, HashMap<String, Integer> count_subtrees) {
		if(root == null) {
			return "#";
		}
		String serialized = root.val  + "," + utl(root.left, ans, count_subtrees) + "," + utl(root.right, ans, count_subtrees);	//O(1) or O(n)
		count_subtrees.put(serialized, count_subtrees.getOrDefault(serialized, 0) + 1);
		if(count_subtrees.get(serialized) == 2) {
			ans.add(root);
		}
		return serialized;
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
