//https://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/


package misc;


public class PredecessorAndSuccessor2 {
	/*
	 	Recursive version:
	 	
		If root.data == key, 
		then predecessor is rightmost node in left subtree and successor is leftmost node in right subtree
	
		else if key < root.data
			set root as successor and recurse to left subtree
		else
			set root as predecessor and recurse to right subtree
		

		Note: SOLUTION NOT ACCEPTE ON GFG, though it is similar to the one given there
	 * */
	public static void findPreSuc(Node root, Res p, Res s, int key)
    {
		if(root == null) {
			return;
		}
		
		if(root.data == key) {
			//find predecessor
			if(root.left != null) {
				Node tmp = root.left;
				while(tmp.right != null) {
					tmp = tmp.right;
				}
				p.pred = tmp;
			}
			
			//find successor
			if(root.right != null) {
				Node tmp = root.right;
				while(tmp.left != null) {
					tmp = tmp.left;
				}
				
				s.succ = tmp;
			}
			
			return;
		}
		
		if(key < root.data) {
			s.succ = root;											//set successor and move to left subtree
			findPreSuc(root.left, p, s, key);
		}
		//else key > root.data											
		p.pred = root;												//set predecessor and move to right subtree
		findPreSuc(root.right, p, s, key);
		
		
    }
	
	
	//Iterative version of above. ACCEPTED
	public static void findPreSuc_Iterative(Node root, Res p, Res s, int key)
    {
		while(root != null) {
			if(root.data == key) {
				if(root.left != null) {
					Node tmp = root.left;
					while(tmp.right != null) {
						tmp = tmp.right;
					}
					p.pred = tmp;
				}
				
				if(root.right != null) {
					Node tmp = root.right;
					while(tmp.left != null) {
						tmp = tmp.left;
					}
					s.succ = tmp;
				}
				
				return;
			}else if (key < root.data) {
				s.succ = root;
				root = root.left;
			}else {
				p.pred = root;
				root = root.right;
			}
			
		}
    }
	
	
	
	
	private static class Node {
		int data;
		Node left, right;
		
		Node (int item) {
			data = item;
			left = right = null;
		}
	}
	
	//class to store successor and predecessor
	private static class Res{
		Node succ = null;
		Node pred = null;
	}

}
