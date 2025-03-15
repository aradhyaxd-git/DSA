/*2379. Minimum Recolors to Get K Consecutive Black Blocks

You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', 
representing the color of the ith block. The characters 'W' and 'B' denote the colors white
and black, respectively.

You are also given an integer k, which is the desired number of consecutive black blocks.

In one operation, you can recolor a white block such that it becomes a black block.

Return the minimum number of operations needed such that there is at least one occurrence of k 
consecutive black blocks.

 

Example 1:

Input: blocks = "WBBWWBBWBW", k = 7
Output: 3
Explanation:
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = "BBBBBBBWBW". 
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3. */

class Solution {
    public int minimumRecolors(String blocks, int k) {
        //fixed width sliding window ka problem hai ye
        int n= blocks.length();
        int right=0,left=0;
        int operations=k,totalWhites=0;
        while(right<n){
          if(blocks.charAt(right)=='W'){
            totalWhites++;
          }
          if(right-left+1 == k){
            //check karo minimum size kya hai
            operations= Math.min(operations,totalWhites);
            if(blocks.charAt(left)=='W'){
              totalWhites--;
            }
            left++;
          }
          right++;
        }
        return operations;
    }
}

