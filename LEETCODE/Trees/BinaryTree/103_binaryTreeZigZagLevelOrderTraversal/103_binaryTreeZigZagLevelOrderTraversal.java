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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result= new ArrayList<>();
        if(root==null) return result;
        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(root);
        boolean flag=true;
        while(!queue.isEmpty()){
            int levelSize= queue.size();
            List<Integer> currentList= new ArrayList<>();
            for(int i=0;i<levelSize;i++){
                TreeNode currentNode= queue.poll();
                currentList.add(currentNode.val);
                if(currentNode.left!=null){
                    queue.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    queue.add(currentNode.right);
                }
            }
            if(!flag) Collections.reverse(currentList);
            result.add(currentList);
            flag= !flag;
        }
        return result;
    }
}