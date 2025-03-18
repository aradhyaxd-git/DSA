/*2401. Longest Nice Subarray

You are given an array nums consisting of positive integers.

We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different
positions in the subarray is equal to 0.

Return the length of the longest nice subarray.

A subarray is a contiguous part of an array.


Note that subarrays of length 1 are always considered nice.  
-------------------------------------------------------------------------------------------------------------------------------------------*/
//APPROACH 1: USING MASKING CONCEPT
    /* { 1  , 3 , 48  ,10}
    FOR EXAMPLE:
    3-> 0 0 0 0 1 1
    8-> 0 0 1 0 0 0
    _________________ TAKE &
        0 0 1 0 1 1 
        IT IS NOT EQUAL TO ZERO

    WHAT WE LEARN HERE IS 
      ->  mask & nums[j] checks if nums[j] shares any set bits with the mask.
      ->  mask | nums[j] updates the mask by adding nums[j] to it.

      HOW TO CHECK IF ALL POSSIBLE PAIRS & GIVES 0  -> MASK WITH NUMS[I]
                                                UPDATE  MASK = MASK | NUMS[I]  */

class Solution1 {
    public int longestNiceSubarray(int[] nums) {
      int n= nums.length;
      int maxLen=0;
      for(int i=0;i<n;i++){
        int mask=0;
        for(int j=i;j<n;j++){
          if((mask&nums[j])!=0)
            break;
          else{
            mask= mask|nums[j];
            maxLen= Math.max(maxLen,j-i+1);
          }
        }
      }
      return maxLen;
    }
}


/*OPTIMAL SOLUTION : SLIDING WINDOW + BITMASKING

SINCE , WE CAN CLEARLY SEE THAT , IT IS A LONGEST SUBARRAY KINDA QUESTIONS--> SLIDING WINDOW TO OPTIMISE

Now main cheez ye phasti yaha par ki window shrink kaise krengay??? --> use the property of XOR i.e XOR with 1

*/

class Solution {
    public int longestNiceSubarray(int[] nums) {
      int n= nums.length;
      int maxLen=0;
      int mask=0;
      int right=0,left=0;
      while(right<n){
        while((mask&nums[right])!=0){
          mask= (mask^nums[left]);
          left++;
        }
        maxLen= Math.max(maxLen,right-left+1);
        mask= mask|nums[right];
        right++;
      }
      return maxLen;
    }
}