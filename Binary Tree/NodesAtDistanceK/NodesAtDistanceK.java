//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
 * */

/*
Solution:
	Since it is assumed target node is present in the tree, we needn't search it. 
	Store parent of each node.
	Then start from the target node and k=given value initially.
	Recurse to the left, right subtrees and to the parent of current node with k-1.
	When k==0, check if it is not the target node. Add to result if not the target node.
	Also we need to keep a set to check if this node is already visited or not otherwise we will have wrong nodes visited with k=0.
	See implementation below.
	
	Complexity: O(n) time and O(n) space
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class NodesAtDistanceK {

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		List<Integer> result = new ArrayList<>();
		HashMap<TreeNode, TreeNode> parent = new HashMap<>();
		Stack<TreeNode> stack = new Stack<>();
		//null target
		if(target == null) {
			return result;
		}

		//collect parent of each node
		stack.add(root);
		parent.put(root, null);
		
		while(!stack.isEmpty()) {
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
		
		getNodesDistanceK(target, target, K, parent, result, new HashSet<TreeNode>());
		return result;
	}
	
	private void getNodesDistanceK(TreeNode target, TreeNode curr, int k, HashMap<TreeNode, TreeNode> parent, List<Integer> result, HashSet<TreeNode> visited) {
		if(curr == null) {
			return;
		}
		if(visited.contains(curr)) {
			return;
		}
		visited.add(curr);						//visit current node
		
		if(k == 0) {
			result.add(curr.val);
			return;
		}
		
		//check in left, right and parent node of current node with k-1 value
		getNodesDistanceK(target, curr.left, k-1, parent, result, visited);
		getNodesDistanceK(target, curr.right, k-1, parent, result, visited);
		getNodesDistanceK(target, parent.get(curr), k-1, parent, result, visited);
	}

}
