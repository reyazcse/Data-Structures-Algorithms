//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
/*
Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * */

/*
Solution: Three methods to solve this:
			Method 1 : 
				Traverse each node of the tree. 
				Let node value be val. Then check if k-val exists in the set or not.
				If exists then return true.
				else put val in the set.

		   Method 2: 
		   		Similar to method1 but we do level order traversal instead of dfs

		   Method 3:
		   		Do inorder traversal of the tree and store nodes in a list.
		   		The list will be sorted since BST is there.
		   		Now using two pointer method, check if sum of two elements is k.


		  All three methods take O(n) time and O(n) space.
		  Reference: leetcode solution

Below is implementation of Method 1:
 * */
package leetcode;

import java.util.HashSet;

public class TwoSumBST {
	public boolean findTarget(TreeNode root, int k) {
		HashSet<Integer> set = new HashSet<>();
		return utl(root, k, set);
	}
	
	private boolean utl(TreeNode node, int k, HashSet<Integer> set) {
		if(node == null) {
			return false;
		}
		if(set.contains(k - node.val)) {
			return true;
		}
		
		set.add(node.val);
		return utl(node.left, k, set) || utl(node.right, k, set);
		
	}

}
