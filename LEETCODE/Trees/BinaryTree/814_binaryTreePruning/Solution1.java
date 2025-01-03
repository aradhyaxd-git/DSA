/**
* Definition for a binary tree node.
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode() {}
*     TreeNode(int val) { this.val = val; }
*     TreeNode(int val, TreeNode left, TreeNode right) {
*         this.val = val;
*         this.left = left;
*         this.right = right;
*     }
* }
*/

//APPROACH-1 USING A HELPER FUNCTION isOnePresent 
/* Here , we are pruning and removing all 0 by setting root==null
TC:0(n^2) as we are going to each nodes twice */
public class Solution1 {
   public boolean isOnePresent(TreeNode root){
       if(root==null) return false;
       if(root.val==1) return true;
       return isOnePresent(root.left)||isOnePresent(root.right);
   }
   public TreeNode pruneTree(TreeNode root) {
       if(root==null) return null;
       if(!isOnePresent(root.left)) 
           root.left=null;
       if(!isOnePresent(root.right))
           root.right=null;
       pruneTree(root.left);
       pruneTree(root.right);
       if(root.left==null && root.right==null && root.val==0) return null;
       return root;
   }
}
//APPROACH 2 TC:O(N) linear
class Solution {
   public TreeNode pruneTree(TreeNode root) {
       if(root==null) return null;
       root.left= pruneTree(root.left);
       root.right= pruneTree(root.right);
       if(root.left==null && root.right==null && root.val==0) return null;
       return root;
   }
