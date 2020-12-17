//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
 * */

/*
Solution:
	Solve it using BFS. 
	O(n) time and O(n) space since we use queue
 * */
package misc;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointers {
	public Node connect(Node root) {
		if(root == null) {
			return root;
		}
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		
		while(!q.isEmpty()) {
			Node prev = null;
			int size = q.size();
			for(int i=0; i<size; i++) {
				Node curr = q.poll();
				if(prev != null) {
					prev.next = curr;
				}
				prev = curr;
				
				if(curr.left != null) {
					q.add(curr.left);
				}
				
				if(curr.right != null) {
					q.add(curr.right);
				}
						
			}
		}
		return root;
	}
	
	
	private class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	}
}
