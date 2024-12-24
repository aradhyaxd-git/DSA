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
    public void traverse(TreeNode root,List<Integer> list){
        if(root==null) return;
        traverse(root.left,list);
        list.add(root.val);
        traverse(root.right,list);
    }
    public boolean isSorted(List<Integer> list){
        int nums[]= list.stream().mapToInt(i->i).toArray();
        int n= nums.length;
        for(int i=1;i<n;i++){
            if(nums[i-1]>=nums[i]){
                return false;
            }
        }
        return true;
    }
    public boolean isValidBST(TreeNode root) {
        List<Integer> ans= new ArrayList<>();
        traverse(root,ans);
        return isSorted(ans);
    }
}