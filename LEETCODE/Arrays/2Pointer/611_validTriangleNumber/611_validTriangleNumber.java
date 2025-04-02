/*
611. Valid Triangle Number

Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we
take them as side lengths of a triangle.

Example 1:

Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3 */

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        //step 1: sort kardia
        int n= nums.length;
        int count=0;
        /* NOW SEE ARRAY 2 4 6 8 9 
                         l     r  i
                now ,    it is ensured that everything in b/w right to left will be available     */
        for(int i=n-1;i>=0;i--){
            // i ko max side se pakda hai, peeche se aage chalo -> agar sum of 2 smaller sides> third side ----> valid triangle hai
            int left=0;
            int right=i-1;
            while(left<right){
                if(nums[left]+nums[right]>nums[i]){
                    count+= (right-left); // note : agar nums[left]+nums[right]>nums[i] , to right-left pairs banengay kyuki array sorted hai to beech wale bhi ajayengay
                    right--;
                }
                else left++;
            }
        }
        return count;
    }
}