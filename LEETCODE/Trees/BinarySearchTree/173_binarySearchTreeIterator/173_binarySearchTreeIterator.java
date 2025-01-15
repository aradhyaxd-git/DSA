/*   PROBLEM STATEMENT:

Implement the BSTIterator class that represents an iterator over the in-order 
traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. 
The root of the BST is given as part of the constructor. The pointer should be initialized to a 
non-existent number smaller than any element in the BST.

boolean hasNext() Returns true if there exists a number in the traversal 
to the right of the pointer, otherwise returns false.

int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, 
the first call to next() will return the smallest element in the BST.
You may assume that next() calls will always be valid. That is, there will be at 
least a next number in the in-order traversal when next() is called. */

class BSTIterator {
    public Stack<TreeNode> st= new Stack<>();
    /*approach: 
    1) We took a stack , WE use inorder nature
    ->  Left Root Right
    2) Take a stack 
        -> Go to the extreme left and take up everything
            in stack on the process.
        -> Assume there is a next, 
            -> Check if it has a right 
                -> if Yes, add everthing in stack */

    public BSTIterator(TreeNode root) {
        pushLeftNodes(root);
    }
    
    public int next() {
        TreeNode top= st.pop(); 
        //check if, right exists, if yes Then push everything in stack
        if(top.right!=null){
            pushLeftNodes(top.right);
        }
        return top.val;
    }
    
    public boolean hasNext() {
        return !st.isEmpty();
    }

    private void pushLeftNodes(TreeNode node){
        while(node!=null){
            st.push(node);
            node= node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */