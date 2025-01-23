/*APPROACH 1: USING DFS */
class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        /* CASE 1: if there is only one element present, so
                    -> We add new root.
                    -> We assign previous root to the newRoot's left */
        if(depth==1){ 
            TreeNode newNode= new TreeNode(val);
            newNode.left= root;
            return newNode;
        }
        int current=1;
        return add(root,val,depth,current);
    }
    public TreeNode add(TreeNode root,int val, int depth, int current){
        if(root==null) return null;
        /*We will add new row, only when currentDepth = depth -1 */
        if(current==depth-1){
            /* Steps:
                -> We store root's left and right temporarily.
                -> We assign the value to our root's left and right
                -> Now, we assign roots'left'left and same with right  */
            TreeNode tempLeft= root.left;
            TreeNode tempRight= root.right;

            root.left= new TreeNode(val);
            root.right= new TreeNode(val);

            root.left.left= tempLeft;
            root.right.right= tempRight;
            return root;
        }
        /*Now recursion will travel depth wise 
            -> To left and to right */
        root.left= add(root.left,val,depth,current+1);
        root.right= add(root.right,val,depth,current+1);


        return root;
    }
}

/*Approach 2: Using BFS */
class Solution2 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode newNode= new TreeNode(val);
            newNode.left= root;
            return newNode;
        }
        Queue<TreeNode> queue= new LinkedList<>();
        queue.offer(root);
        int level=0;
        while(!queue.isEmpty()){
            int size= queue.size();
            level++;
            boolean check=false;
            while(size--!=0){
                TreeNode currentNode= queue.poll();
                if(level==depth-1){
                    TreeNode tempLeft= currentNode.left;
                    TreeNode tempRight= currentNode.right;
                    currentNode.left= new TreeNode(val);
                    currentNode.right= new TreeNode(val);
                    currentNode.left.left= tempLeft;
                    currentNode.right.right= tempRight;
                    check= true;
                }
                if(currentNode.left!=null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right!=null){
                    queue.offer(currentNode.right);
                }
            }
            if(check==true) break;
        }
        return root;
    }
}