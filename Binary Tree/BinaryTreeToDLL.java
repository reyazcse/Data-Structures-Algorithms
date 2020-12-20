//https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1

//Solution: Do inorder. Keep track of global head and prev node. Fix left and node pointer while visiting a node
package misc;

class BinaryTreeToDLL
{
    Node head = null;
    Node prev = null;
    Node bToDLL(Node root)
    {
        
	    utl(root);
	    return head;
    }
    
    private void utl(Node node) {
        if(node == null) {
            return;
        }
        utl(node.left);
        
        if(prev == null) {
            head = node;
        }else {
            prev.right = node;
            node.left = prev;
            
        }
        prev = node;
        
        utl(node.right);
    }
    
    
    
    //Node class
    private static class Node
    {
    	Node left, right;
    	int data;
    	
    	Node(int d)
    	{
    		data = d;
    		left = right = null;
    	}
    	
    }
}
