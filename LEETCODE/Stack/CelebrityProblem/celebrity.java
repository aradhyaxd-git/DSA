/*A celebrity is a person who is known to all but does not know anyone at a party.
 A party is being organized by some people. A square matrix mat[][] (n*n) is used to represent 
 people at the party such that if an element of row i and column j is set to 1 it means ith person knows
  jth person. You need to return the index of the celebrity in the party, if the celebrity does not exist, return -1.

Note: Follow 0-based indexing.

Examples:

Input: mat[][] = [[1, 1, 0], [0, 1, 0], [0, 1, 1]]
Output: 1
Explanation: 0th and 2nd person both know 1st person. Therefore, 1 is the celebrity person. 

Input: mat[][] = [[1, 1], [1, 1]]
Output: -1
Explanation: Since both the people at the party know each other. Hence none of them is a celebrity person. */



class Solution {
    public int celebrity(int mat[][]) {
        int n= mat.length;
        int top=0,down=n-1;
        while(top<down){
            if(mat[top][down]==1){
                top++;
            }else if(mat[down][top]==1){
                down--;
            }
            else{ 
                top++; down--;
            }
        }
        if(top>down) return -1;
        //now we have top==down 
        
    
        for(int i=0;i<n;i++){
            if(i==top) continue; //ye diagonal elements ke liye hogya hai
            if(mat[top][i]==0 && mat[i][top]==1) continue;
            else return -1;
        }
        return top;
    }
    
}