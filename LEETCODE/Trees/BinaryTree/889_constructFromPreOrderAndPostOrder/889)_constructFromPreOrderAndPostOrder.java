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
 //TC: O(n*n) as while loop mei dhundhne mei O(n) ja rha hai
 //SC: O(n)
 class Solution1 {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n= preorder.length;
        return solve(0,n-1,0,preorder,postorder);
    }

    public TreeNode solve(int preStart, int preEnd, int postStart,int[] preorder , int[] postorder){
        if(preStart>preEnd) return null;

        TreeNode root= new TreeNode(preorder[preStart]);
        
        if(preStart==preEnd) //means ek hi node bacha hai
            return root;
        int nextNode= preorder[preStart+1]; //left subtree ka root
        //now find it in postorder traversal
        int j= postStart;
        while(postorder[j]!=nextNode){
            j++;
        }
        int numberOfNodes = j-postStart+1;

        root.left = solve(preStart+1,preStart+numberOfNodes, postStart, preorder,postorder);

        root.right = solve(preStart+numberOfNodes+1, preEnd , j+1 , preorder , postorder);

        return root;
    }
}

/*OPTIMISATION : PHELE HI INDEX MAP MEI STORE KARLO  */
 
 class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        HashMap<Integer,Integer> map= new HashMap<>();
        int n= preorder.length;
        for (int i = 0; i < n; i++) {
            map.put(postorder[i], i);
        }
        return solve(0,n-1,0,preorder,postorder , map);
    }

    public TreeNode solve(int preStart, int preEnd, int postStart,int[] preorder , int[] postorder , HashMap<Integer,Integer> map){
        if(preStart>preEnd) return null;

        TreeNode root= new TreeNode(preorder[preStart]);
        
        if(preStart==preEnd) //means ek hi node bacha hai
            return root;

        int nextNode= preorder[preStart+1]; //left subtree ka root
        int j= map.get(nextNode);
    
        int numberOfNodes = j-postStart+1;

        root.left = solve(preStart+1,preStart+numberOfNodes, postStart, preorder,postorder , map);

        root.right = solve(preStart+numberOfNodes+1, preEnd , j+1 , preorder , postorder , map);

        return root;
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
 //TC: O(n*n) as while loop mei dhundhne mei O(n) ja rha hai
 //SC: O(n)
 class Solution1 {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n= preorder.length;
        return solve(0,n-1,0,preorder,postorder);
    }

    public TreeNode solve(int preStart, int preEnd, int postStart,int[] preorder , int[] postorder){
        if(preStart>preEnd) return null;

        TreeNode root= new TreeNode(preorder[preStart]);
        
        if(preStart==preEnd) //means ek hi node bacha hai
            return root;
        int nextNode= preorder[preStart+1]; //left subtree ka root
        //now find it in postorder traversal
        int j= postStart;
        while(postorder[j]!=nextNode){
            j++;
        }
        int numberOfNodes = j-postStart+1;

        root.left = solve(preStart+1,preStart+numberOfNodes, postStart, preorder,postorder);

        root.right = solve(preStart+numberOfNodes+1, preEnd , j+1 , preorder , postorder);

        return root;
    }
}

/*OPTIMISATION : PHELE HI INDEX MAP MEI STORE KARLO  */
 
 class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        HashMap<Integer,Integer> map= new HashMap<>();
        int n= preorder.length;
        for (int i = 0; i < n; i++) {
            map.put(postorder[i], i);
        }
        return solve(0,n-1,0,preorder,postorder , map);
    }

    public TreeNode solve(int preStart, int preEnd, int postStart,int[] preorder , int[] postorder , HashMap<Integer,Integer> map){
        if(preStart>preEnd) return null;

        TreeNode root= new TreeNode(preorder[preStart]);
        
        if(preStart==preEnd) //means ek hi node bacha hai
            return root;

        int nextNode= preorder[preStart+1]; //left subtree ka root
        int j= map.get(nextNode);
    
        int numberOfNodes = j-postStart+1;

        root.left = solve(preStart+1,preStart+numberOfNodes, postStart, preorder,postorder , map);

        root.right = solve(preStart+numberOfNodes+1, preEnd , j+1 , preorder , postorder , map);

        return root;
    }
}
