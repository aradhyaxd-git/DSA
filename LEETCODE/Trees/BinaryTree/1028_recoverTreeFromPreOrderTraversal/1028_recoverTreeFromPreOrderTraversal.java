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

 /* 1028. Recover a Tree From Preorder Traversal

We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), 
then we output the value of this node.  If the depth of a node is D, the depth of its 
immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root
*/
class Solution {
    private int n; //for length of string

    public TreeNode recoverFromPreorder(String traversal) {
        char x[]= traversal.toCharArray();
        n= x.length;
        int index[]={0}; //i lete lekin Java mei call by reference only works on non primitive
        return solve(x,index,0); // fun(string , index , depth)
    }

    private  TreeNode solve(char x[], int index[], int depth){
        if(index[0]>=n) return null; //index bahar chala jaye length se to return null 

        int j= index[0]; // j is used to count the number of dashes ,, jitne dash hongay utni depth hogi
        while(j<n && x[j]=='-'){
            j++;
        }
        int dash= j-index[0]; // j-i will count the number of dashes

        if(depth!=dash)  //agar dashes jyada ajaye depth se.. mtlab aage badh gaye, so return null
            return null;
        
        index[0]=j;  //dry run karo to , i+= dash means, ab i jaha par dashes khatam huye waha par chalu hoga
        //it can also be, i=j;
        int number=0; //ab number create karo jo dalna hai
        while(index[0]<n && Character.isDigit(x[index[0]])){
            number= number*10 + (x[index[0]]-'0'); //number banaya
            index[0]++;
        }

        TreeNode root= new TreeNode(number);
        root.left= solve(x,index,depth+1); //recursion leap of faith
        root.right= solve(x,index,depth+1);

        return root;
    }
}

