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
Solution: Using BFS level order traversal.
		  First find the parent node for each of the nodes.
		  
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class NodesAtDistanceK2 {

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		List<Integer> result = new ArrayList<>();
		HashMap<TreeNode, TreeNode> parent = new HashMap<>();
		Stack<TreeNode> stack = new Stack<>();						//used to find parent node
		Queue<TreeNode> queue = new LinkedList<>();					
		HashSet<TreeNode> seen = new HashSet<>();					//stores nodes which are already seen
		int curr_level = 0;

		//base cases:
		if(target == null) {
			return result;
		}
		if(K == 0) {
			result.add(target.val);
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

		//do BFS
		queue.add(target);
		seen.add(target);
		while(!queue.isEmpty()) {
			int size = queue.size();

			for(int i=0; i<size; i++) {							//process all nodes in the current level
				if(curr_level == K) {							//we have reached nodes at distance K
					for(TreeNode node : queue) {
						result.add(node.val);
					}
					return result;
				}

				TreeNode curr = queue.poll();
				//left node
				if(curr.left != null && !seen.contains(curr.left)) {
					queue.add(curr.left);
					seen.add(curr.left);
				}

				//right node
				if(curr.right != null && !seen.contains(curr.right)) {
					queue.add(curr.right);
					seen.add(curr.right);
				}
				
				//parent node
				TreeNode parent_node = parent.get(curr);
				if(parent_node != null && !seen.contains(parent_node)) {
					queue.add(parent_node);
					seen.add(parent_node);
				}
			}
			curr_level++;
		}

		return result;
	}

}
