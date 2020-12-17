//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

package leetcode;

public class LCABinarySearchTree {
	//iterative
    public TreeNode lowestCommonAncestor_Iterative(TreeNode root, TreeNode p, TreeNode q) {

        while(root != null) {
            if(root.val < p.val && root.val < q.val) {
                root = root.right;
            }else if(root.val > p.val && root.val > q.val) {
                root = root.left;
            }else {
                return root;         //LCA found
            }
            
        }
        return null;
        
    }
    
    //recursive
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        
        
        if(p.val < root.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        
        if(p.val > root.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        return root;											//LCA found
    }
    

}
