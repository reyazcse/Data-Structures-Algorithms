//https://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/

//Solutin: Using inorder traversal. O(n) time  and O(1) space
package misc;

public class PredecessorAndSuccessor {
	
	
	public static void findPreSuc(Node root, Res p, Res s, int key)
    {
       if(root == null) {
    	   return;
       }
       
       findPreSuc(root.left, p, s, key);									//left subtree	
       
       if(root.data < key) {												
    	   p.pred = root;		//update predecessor
       }else if (root.data > key && s.succ == null) {						
    	   s.succ = root;		//set successor. It is set only once since subsequent nodes will be greater than key
       }
       
       
       findPreSuc(root.right, p, s, key);									//right subtree
    }
	
	
	
	
	private class Node {
		int data;
		Node left, right;
		
		Node (int item) {
			data = item;
			left = right = null;
		}
	}
	
	//class to store successor and predecessor
	private class Res{
		Node succ = null;
		Node pred = null;
	}
	
	
}
