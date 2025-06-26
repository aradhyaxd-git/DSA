/*
2294. Partition Array Such That Maximum Difference Is K

You are given an integer array nums and an integer k. You may partition nums into one or more subsequences such that each element 
in nums appears in exactly one of the subsequences.

Return the minimum number of subsequences needed such that the difference between the maximum and minimum values in each subsequence 
is at most k.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the
 remaining elements.

 

Example 1:

Input: nums = [3,6,1,2,5], k = 2
Output: 2
Explanation:
We can partition nums into the two subsequences [3,1,2] and [6,5].
The difference between the maximum and minimum value in the first subsequence is 3 - 1 = 2.
The difference between the maximum and minimum value in the second subsequence is 6 - 5 = 1.
Since two subsequences were created, we return 2. It can be shown that 2 is the minimum number of subsequences needed. */

class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n= nums.length;
        int count=0,i=0;
        while(i<n){
            int small=nums[i];
            i++;
            while(i<n && nums[i]-small <=k){
                i++;
            }
            count++;

        }
        return count;
        
    }
}