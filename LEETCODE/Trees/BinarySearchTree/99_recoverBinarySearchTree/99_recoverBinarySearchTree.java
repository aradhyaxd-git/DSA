/**
 * PROBLEM STATEMENT:
 *You are given the root of a binary search tree (BST), where the values of 
 exactly two nodes of thetree were swapped by mistake. 
 Recover the tree without changing its structure.
 *    
 * 
 */
class Solution {
    private TreeNode prev;
    private TreeNode first;
    private TreeNode middle;
    private TreeNode last;
    private void inorder(TreeNode root){
        inorder(root.left);
        if(prev!=null && (prev.val>root.val)){
            if(first==null){
                first=prev;
                middle=root;
            }
            else
                last=root;
        }
        prev=root;
        inorder(root.right);
    }
    public void recoverTree(TreeNode root) {
        first=middle=last=null;
        prev= new TreeNode(Integer.MIN_VALUE);
        if(first!=null && last!=null){
            int temp= first.val;
            first.val= last.val;
            last.val= temp;
        }
        else if(first!=null && middle!=null){
            int temp= first.val;
            first.val= middle.val;
            middle.val= temp;
        }   
    }
}