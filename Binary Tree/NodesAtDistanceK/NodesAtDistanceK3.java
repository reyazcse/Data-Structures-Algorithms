//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree

/*
We are given a binary tree (with root node root), a target node, and an integer value k.

Return a list of the values of all nodes that have a distance k from the target node.  
The answer can be returned in any order.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2

Output: [7,4,1]

 * */

/*
Solution:
	In this solution, we do not use parent nodes.
	
	Instead at each node, we first check if it is target node or not.
	If it is target node, then find the nodes below at level k from this node.
	And also return 1. 1 means target node is at distance 1 from its ancestor  node
	
	If current node is not target node then find the distance of target node in the left and right subtree.
	If target node is not found in any of those subtrees, return 0
	If target node is found at distance LD in left subtree, then
		first check if LD == k. 
			if yes then current node is at distance k. 
			return 1 + LD
			else find nodes at level = k - LD - 1 in the right subtree.
	
	Similarly if target node is found at distance RD in the right subtree, do similarly.
	
             1    
           /    \      
         2       3
       /   \
      4*     5
    /   \   / \
   6    7  8   9
      /  \
     10   11

 here 4 is target node and k = 2
 When we are at 2, we get LD = 1. Then we find nodes at level k-LD-1 from node 5
 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NodesAtDistanceK3 {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		List<Integer> result = new ArrayList<>();
		
		if(target == null) {
			return result;
		}
		
		getNodesAtDistanceK(root, target, k, result);
		
		return result;

	}
	
	/*
	 * returns 0 if target node is not found in the tree rooted at 'root' node
	 * return distance if target node is found
	 * 
	 * */
	private int getNodesAtDistanceK(TreeNode root, TreeNode target, int k, List<Integer> result) {
		if(root == null) {
			return 0;
		}
		
		if(root == target) {						//current is target itself
			findNodesAtLevelK(root, k, result);		//find nodes below at level k
			return 1;
		}
		
		int LD = getNodesAtDistanceK(root.left, target, k, result);
		
		if(LD != 0) {							//target node found in left subtree
			if(LD == k) {						//current node at distance k
				result.add(root.val);
			}
			else {
				findNodesAtLevelK(root.right, k-LD-1, result);
			}
			return 1 + LD;								//distance of target node
		}
		
		int RD = getNodesAtDistanceK(root.right, target, k, result);
		
		if(RD != 0) {							//target node found in right subtree
			if(RD == k) {						//current node at distance k
				result.add(root.val);
			}
			else {
				findNodesAtLevelK(root.left, k-RD-1, result);
			}
			return 1 + RD;									//distance of target node
		}
		
		return 0;				//target node is not found 
	}
	
	/*
	 * finds nodes at level k from 'root' node
	 * */
	private void findNodesAtLevelK(TreeNode root, int lvl, List<Integer> result) {
		if(root == null) {
			return;
		}
		
		if(lvl == 0) {
			result.add(root.val);
			return;
		}
		
		findNodesAtLevelK(root.left, lvl-1, result);
		findNodesAtLevelK(root.right, lvl-1, result);
	}
}
