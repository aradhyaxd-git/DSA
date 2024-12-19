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
//APPROACH 1 : USING DEPTH FIRST SEARCH
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> ans= new ArrayList<>();
        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode rightNode= null;
            int n=queue.size();
            for(int i=0;i<n;i++){
                rightNode= queue.poll();
                if(rightNode.left!=null){
                    queue.add(rightNode.left);
                }
                if(rightNode.right!=null){
                    queue.add(rightNode.right);
                }
            }
            ans.add(rightNode.val); 
        }
        return ans;     
    }
}


//APPROACH - 2 USING DEPTH FIRST SEARCH
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
class Solution {
    void preOrderDFS(TreeNode root,int level,List<Integer> result){
        if(root==null){
            return;
        }
        if(result.size()<level){
            result.add(root.val);
        }
        preOrderDFS(root.right,level+1,result);
        preOrderDFS(root.left,level+1,result);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans= new ArrayList<>();
        preOrderDFS(root,1,ans);
        return ans;  
    }
}