//https://www.geeksforgeeks.org/bottom-view-binary-tree/

/*
Solution: 
	So there are two approaches to solve this problem: using level order traversal and using preorder traversal
	In this solution we do preorder traversal solution:
	
	
	We use tree map since we want to print the numbers with lowest horizontal distance first.
	The key of map is the horizontal distance and value is a pair(a,b) where a is height and b is the node value
	For nodes with same horizontal distance(hd), we keep the one which is visited later in preorder traversal. Node which is visited later has height greater
	than or equal to height of existing node.
	Traverse the tree in pre order.
	
	Complexity: O(n log n) time since tree map is used
	
	
 * */

package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;





public class BottomViewBinaryTree2 {
	public ArrayList <Integer> bottomView(Node root)
	{
		ArrayList<Integer> result = new ArrayList<>();
		if(root == null) {
			return result;
		}
		
		TreeMap<Integer, int[]> map = new TreeMap<>();
		preorder(root, map, 0, 0);
		
		for(Map.Entry<Integer, int[]> entry :map.entrySet()) {
			result.add(entry.getValue()[1]);
		}
		
		return result;
	}
	
	private void preorder(Node root, TreeMap<Integer, int[]> tMap, int hd, int height) {
		if(root == null) {
			return;
		}
		
		//if map does not contain key 'hd' OR height of current node is greater than or equal to the height of the node already
		//contained in the map with key as 'hd', then insert current node
		if(!tMap.containsKey(hd) ||  (height >= tMap.get(hd)[0])) {
			tMap.put(hd, new int[] {height, root.data});
		}
		
		preorder(root.left, tMap, hd-1, height+1);
		preorder(root.right, tMap, hd+1, height+1);
	} 
	
	
	public static void main(String[] args) {
		Node root = new Node(4);
		root.left = new Node(5);
		root.right = new Node(2);
		root.right.left = new Node(3);
		root.right.right = new Node(1);
		root.right.left.left = new Node(6);
		root.right.left.right = new Node(7);
		BottomViewBinaryTree2 ob = new BottomViewBinaryTree2();
		List<Integer> result = ob.bottomView(root);
		System.out.println(result);
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
