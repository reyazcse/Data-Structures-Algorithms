/*
 * Find the shortest distance between two nodes in a binary tree.
 * Assume nodes are present in the tree and all nodes are unique
 * */

/*
Solution:
	Brute force:
	find path length of node a from root : l1
	find path length of node b from root : l2
	find common node and then find its path length from root = l3
	result = l1 + l2 - 2 * l3
	
	Efficient:
	Find lca node.
	Find level of a and leve of b from lca : l1 an l2 respectively
	return l1 + l2;
	
		
             1    
           /    \      
         2       3
       /   \
      4     5
    /   \   / \
   6    7  8   9
      /  \
     10   11
	
 * */
package misc;


public class ShortestDistanceTwoNodes {
	
	public int findShortestDistance(TreeNode root, TreeNode a, TreeNode b) {
		TreeNode lcaNode = getLCA(root, a, b);
		
		int l1 = search(lcaNode, a, 0);
		int l2 = search(lcaNode, b, 0);
		
		return l1 + l2;
	}

	/*
	 * finds lca node of a and b.
	 * assumption : a and b are present and unique
	 * 
	 * */
	private TreeNode getLCA(TreeNode root, TreeNode a, TreeNode b) {
		if(root == null) {
			return null;
		}
		
		if(root == a || root == b) {
			return root;
		}
		
		TreeNode left = getLCA(root.left, a, b);
		TreeNode right = getLCA(root.right, a, b);
		
		if(left != null && right != null) {
			return root;
		}
		
		if(left != null) {
			return left;
		}
		return right;
	}
	
	//returns level of target node from root node
	private int search(TreeNode root, TreeNode target, int level) {
		if(root == null) {
			return -1;
		}
		
		if(root == target) {
			return level;
		}
		
		int left = search(root.left, target, level+1);
		
		if(left != -1) {									//target node found in the left subtree
			return left;
		}
		return search(root.right, target, level+1);			//find in right subtree
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.left.left.left = new TreeNode(6);
		root.left.left.right = new TreeNode(7);
		root.left.right.left = new TreeNode(8);
		root.left.right.right = new TreeNode(9);
		root.left.left.right.left = new TreeNode(10);
		root.left.left.right.right = new TreeNode(11);
		
		ShortestDistanceTwoNodes ob = new ShortestDistanceTwoNodes();
		System.out.println(ob.findShortestDistance(root, root.left.left.left, root.left.right.right));
	}
	
	
	
	
	
	private static class TreeNode {
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
