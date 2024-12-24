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
class Solution2 {
    public int countNodes(TreeNode root){
        if(root==null){
            return 0;
        }
        return 1+countNodes(root.left)+countNodes(root.right);
    }
    public boolean dfs(TreeNode root, int i,int totalNodes){
        if(root==null){
            return true;
        }
        if(i>totalNodes){
            return false;
        }
        return dfs(root.left,2*i,totalNodes) && dfs(root.right,2*i+1,totalNodes);
    }

    public boolean isCompleteTree(TreeNode root) {
        int totalNodes= countNodes(root);
        int i=1;
        return dfs(root,i,totalNodes);
    }
}
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
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue= new LinkedList<>();
        queue.offer(root);
        boolean past= false;
        while(!queue.isEmpty()){
            TreeNode temp= queue.poll();
            if(temp==null){
                past=true;
            }
            else{
                if(past==true){
                    return false;
                }
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }
        return true;       
    }
}