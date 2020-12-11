//https://www.geeksforgeeks.org/print-left-view-binary-tree/
/*
Examples: 

Input : 
                 1
               /   \
              2     3
             / \     \
            4   5     6             
Output : 1 2 4

Input :
        1
      /   \
    2       3
      \   
        4  
          \
            5
             \
               6
Output :1 2 4 5 6
 * */

//Solution: Using queue. O(n) time and O(n) space.  n = number of nodes in the tree
package misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftViewBinaryTree {
	ArrayList<Integer> leftView(Node root)
    {
		ArrayList<Integer> result = new ArrayList<>();
		if(root == null) {								//base case
			return result;
		}
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node curr = q.peek();
			result.add(curr.data);
			int size = q.size();
			for(int i=0; i<size; i++) {
				curr = q.poll();
				if(curr.left != null) {
					q.add(curr.left);
				}
				if(curr.right != null) {
					q.add(curr.right);
				}
			}
		}
		return result;
    }
	
	public static void main(String[] args) {
		/*
								4
							  /	  \
							5		2
							 	 /	  \
								3		1
							  /  \
							 6    7 
		
								
		*/
		
		Node root = new Node(4);
		root.left = new Node(5);
		root.right = new Node(2);
		root.right.left = new Node(3);
		root.right.right = new Node(1);
		root.right.left.left = new Node(6);
		root.right.left.right = new Node(7);
		LeftViewBinaryTree ob = new LeftViewBinaryTree();
		List<Integer> result = ob.leftView(root);
		System.out.println(result);
	}
	
	private static class Node
	{
	    int data;
	    Node left, right;

	    Node(int item)
	    {
	        data = item;
	        left = right = null;
	    }
	}

}
