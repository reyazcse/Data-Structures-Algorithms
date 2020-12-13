//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

/*
Solution:
	Using level order traversal.

References: https://www.youtube.com/watch?v=suj1ro8TIVY&t=119s
 * */
package misc;

import java.util.LinkedList;
import java.util.Queue;




public class SerializeDeserializeBinaryTree2 {
	public String serialize(TreeNode root) {
		StringBuilder serialized = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			if(curr != null) {
				serialized.append(curr.val);
			}else {
				serialized.append("#");
			}
			serialized.append(",");
			
			if(curr != null) {
				queue.add(curr.left);
				queue.add(curr.right);
			}
			
		}
		return serialized.toString();
		
	}


	public TreeNode deserialize(String data) {
		if(data.charAt(0) == '#') {					//for null tree, its serialized string is "#,"
			return null;
		}
		
		String[] nodes = data.split(",");
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
		queue.add(root);
		int index = 1;
		
		while(!queue.isEmpty()) {
			if(index == nodes.length) {											//if all nodes have been processed
				break;
			}
			TreeNode curr = queue.poll();
			
			//left child
			if(!nodes[index].equals("#")) {										//if a node is not null
				TreeNode left = new TreeNode(Integer.valueOf(nodes[index]));
				curr.left = left;
				queue.add(left);

			}
			
			index++;
			
			//right child
			if(!nodes[index].equals("#")) {										//if a node is not null
				TreeNode right = new TreeNode(Integer.valueOf(nodes[index]));
				curr.right = right;
				queue.add(right);
			}
			
			index++;
		}
		return root;
		
	}
	
	
	
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

}
