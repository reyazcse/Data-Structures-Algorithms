//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

/*
Solution:
	At each level modify the pointers.
	'head' is the first node in each level at the beginning.
	
	Complexity: O(n) time and O(1) space
	
	For video explanation: https://www.youtube.com/watch?v=30Bqbk-Vk3Q
		
 * */
package misc;


public class PopulatingNextRightPointers2 {
	public Node connect(Node root) {
		if(root == null) {
			return root;
		}
		
		Node leftNode = root;
		while(leftNode.left != null) {
			Node head = leftNode;
			while(head != null) {
				head.left.next = head.right;
				
				if(head.next != null) {
					head.right.next = head.next.left;
				}
				head = head.next;
			}
			
			leftNode = leftNode.left;
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
