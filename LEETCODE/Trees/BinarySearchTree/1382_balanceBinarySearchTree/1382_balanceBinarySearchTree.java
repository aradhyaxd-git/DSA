/** PROBLEM STATEMENT:
 Given the root of a binary search tree, return a balanced binary 
 search tree with the same node values. If there is more than one answer, 
 return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1
 */

 

 /*APPROACH: 
 1) fIRST fIND the inorder traversal and store it in an arrayList
 2) Now we have the actual sorted order data form in a list like [1,2,3,4]
 3) Inorder to balance this, start from the middle index and split the tree
    Then split left child
    Then split right child
    */
    class Solution {
        public void inorder(TreeNode root,List<Integer> list){
            if(root==null)
                return;
            inorder(root.left,list);
            list.add(root.val);
            inorder(root.right,list);
        }
        public TreeNode solve(int l,int r, List<Integer> list){
            if(l>r)
                return null;
            int mid= l+ (r-l)/2;
            TreeNode root= new TreeNode(list.get(mid));
            root.left=solve(l,mid-1,list);
            root.right=solve(mid+1,r,list);
            return root;
        }
        public TreeNode balanceBST(TreeNode root) {
            List<Integer> list= new ArrayList<>();
            inorder(root,list);
            return solve(0,list.size()-1,list);  
        }
    }