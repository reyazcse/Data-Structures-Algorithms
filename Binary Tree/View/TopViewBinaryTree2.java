//https://www.geeksforgeeks.org/print-nodes-in-top-view-of-binary-tree-set-2/

/*
Solution: Using preorder and map

Complexity: O(n log n) time and O(n) space for recursion
 * */
package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class TopViewBinaryTree2 {
	public ArrayList<Integer> topView(Node root) {
		ArrayList<Integer> result = new ArrayList<>();
		TreeMap<Integer, int[]> tmap = new TreeMap<>();			//value is a pair of (height, node.data)
		
		preorder(root, tmap, 0, 0);								//for root, hd and height both are 0
		for(Map.Entry<Integer, int[]> entry : tmap.entrySet()) {
			result.add(entry.getValue()[1]);
		}
		return result;
	}
	
	private void preorder(Node root, TreeMap<Integer, int[]> tmap, int hd, int height) {
		if(root == null) {
			return;
		}
		//if no value for current hd present OR height of current node is less than or equal to the node present,
		//then insert current node data
		if(!tmap.containsKey(hd) || (height <= tmap.get(hd)[0])) {
			tmap.put(hd, new int[] {height, root.data});
		}
		
		preorder(root.left, tmap, hd-1, height+1);
		preorder(root.right, tmap, hd+1, height+1);
	}

	public static void main(String[] args) {

		/*
		4
	  /	  \
	5		2
	 \	 /	  \
	 9	3		1
	  /  \
	 6    7 


		 */

		Node root = new Node(4);
		root.left = new Node(5);
		root.left.right = new Node(9);
		root.right = new Node(2);
		root.right.left = new Node(3);
		root.right.right = new Node(1);
		root.right.left.left = new Node(6);
		root.right.left.right = new Node(7);
		TopViewBinaryTree2 ob = new TopViewBinaryTree2();
		List<Integer> result = ob.topView(root);
		System.out.println(result);
	
	}
	
	private static class Node{
		int data;
		Node left, right;

		Node(int item)
		{
			data = item;
			left = right = null;
		}
	}

}
