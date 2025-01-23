/**
 Given the root of a binary tree and an integer targetSum, 
 return all root-to-leaf paths where the sum of the node values 
in the path equals targetSum. Each path should be returned as a
 list of the node values, not node references.

A root-to-leaf path is a path starting from the root and
ending at any leaf node. A leaf is a node with no children.

 */
class Solution {
    public List<List<Integer>> ans= new ArrayList<>();
    public void fun(TreeNode root, int targetSum, int sum, List<Integer> list){
        if(root==null) return;
        sum+= root.val;
        list.add(root.val);
        if(root.left==null && root.right==null){
            if(sum==targetSum){
                ans.add(new ArrayList<>(list));
            }
        }
        else{
            fun(root.left,targetSum,sum,list);
            fun(root.right,targetSum,sum,list);
        }
        list.remove(list.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> list= new ArrayList<>();
        fun(root,targetSum,0,list);
        return ans;
        
    }
}