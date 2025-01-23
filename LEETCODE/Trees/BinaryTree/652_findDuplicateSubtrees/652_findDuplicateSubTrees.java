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
    public String find(TreeNode root, HashMap<String,Integer> map, List<TreeNode> result){
        if(root==null)
            return "N";
        String subtree = root.val +","+find(root.left,map,result)+","+find(root.right,map,result);
        map.put(subtree,map.getOrDefault(subtree,0)+1);
        if(map.get(subtree)==2){
            result.add(root);
        }
        return subtree;
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result= new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        find(root,map,result);
        return result;
    }
}