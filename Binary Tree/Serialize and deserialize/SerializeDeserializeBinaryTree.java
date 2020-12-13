//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

/*
Solution: Using preorder traversal

References: https://www.youtube.com/watch?v=suj1ro8TIVY&t=119s
 * */
package misc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {
	public String serialize(TreeNode root) {
		if(root == null) {
			return "#,";
		}
		String leftSerialized = serialize(root.left);
		String rightSerialized = serialize(root.right);
		return root.val + "," + leftSerialized + rightSerialized;
		
	}

	
	public TreeNode deserialize(String data) {
		Queue<String> queue = new LinkedList<>();
		queue.addAll(Arrays.asList(data.split(",")));
		return deserializeUtl(queue);
		
	}
	
	private TreeNode deserializeUtl(Queue<String> queue) {
		String nodeStr = queue.poll();
		if(nodeStr.equals("#")) {
			return null;
		}
		TreeNode node = new TreeNode(Integer.valueOf(nodeStr));
		node.left = deserializeUtl(queue);
		node.right = deserializeUtl(queue);
		return node;
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
