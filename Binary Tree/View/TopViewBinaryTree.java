
/*
Solution:
	In case of top view of a tree, for nodes with same hd, 
	the node with less height is considered. If height is same the node which comes first in level order traversal
	or less height in pre order traversal is taken.
	So there are two approaches to solve this: using level order traversal and using preoder traversal.

	In this we solve it using level order traversal which uses queue.
	We also use a map to store the node with its hd.
	We use tree map since we need to print in sorted order.

Complexity: O(n log n) time and O(n) space

 * */

package misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;



public class TopViewBinaryTree {
	public ArrayList<Integer> topView(Node root) {
		ArrayList<Integer> result = new ArrayList<>();
		Queue<Node> q = new LinkedList<>();
		TreeMap<Integer, Integer> tmap = new TreeMap<>();
		root.hd = 0;
		q.add(root);
		while(!q.isEmpty()) {
			Node curr = q.poll();
			int hd = curr.hd;
			if(!tmap.containsKey(hd)) {			//put the node value if no node is present for this hd
				tmap.put(hd, curr.data);
			}

			//put children in queue
			if(curr.left != null) {
				curr.left.hd = hd-1;
				q.add(curr.left);
			}
			if(curr.right != null) {
				curr.right.hd = hd+1;
				q.add(curr.right);
			}

		}

		for(Map.Entry<Integer, Integer> entry : tmap.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
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
		TopViewBinaryTree ob = new TopViewBinaryTree();
		List<Integer> result = ob.topView(root);
		System.out.println(result);
	}

	private static class Node{
		int data;
		int hd;
		Node left, right;

		Node(int item)
		{
			hd = Integer.MAX_VALUE;
			data = item;
			left = right = null;
		}
	}

}
