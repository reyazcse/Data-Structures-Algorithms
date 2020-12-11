//https://www.geeksforgeeks.org/bottom-view-binary-tree/
/*
Example:

   					  20
                    /    \
                  8       22
                /   \    /   \
              5      3 4     25
                    / \      
                  10    14
For the above tree the output should be 5, 10, 4, 14, 25.
If there are multiple bottom-most nodes for a horizontal distance from root, then print the later one in level traversal


 * */

/*
Solution:
	In case of bottom view of a tree, for nodes with same hd, 
	the node with  height greater  is considered. If height is same, then consider the node is found later in level order
	or pre order traversal
	
	So there are two approaches to solve this problem: using level order traversal and using preorder traversal
	In this solution we present level order solution using queue:
		
	For root, its hd is 0.
	Let hd = hd of current node
	We put this current node in the map. So it will replace  if already some value is there in map for same hd.
	This means we put the node which is at a higher level for same hd or node which is traversed later in the level order traversal.
	While pushing left child of current node, we set hd-1.
	While pushing right child of current node, we set hd+1.
	
	Complexity: Since we use tree map, O(n log n) time 
	 			O(n) space for queue
 * */
package misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomViewBinaryTree {

	public ArrayList <Integer> bottomView(Node root)
	{
		ArrayList<Integer> result = new ArrayList<>();
		if(root == null) {
			return result;
		}
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		Queue<Node> q = new LinkedList<>();
		root.hd = 0;								//set hd of root to be 0
		q.add(root);
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			int hd = curr.hd;
			map.put(hd, curr.data);					//add current node in map with key = hd. So this current might replace other node with same hd but lower level 
			
			if(curr.left != null) {					//put left child with hd-1
				curr.left.hd = hd-1;
				q.add(curr.left);
			}
			if(curr.right != null) {				//put right child with hd+1
				curr.right.hd = hd+1;
				q.add(curr.right);
			}
		}
		
		for(Map.Entry<Integer, Integer> entry :map.entrySet()) {
			result.add(entry.getValue());
		}
		
		return result;
		
	}

	private static class Node
	{
		int data;
		Node left, right;
		int hd;

		Node(int key)
		{
			data = key;
			hd = Integer.MAX_VALUE;
			left = right = null;
		}
	}
}
