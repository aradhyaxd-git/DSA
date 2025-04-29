/*713. Subarray Product Less Than K

Given an array of integers nums and an integer k, return the number of contiguous subarrays 
where the product of all the elements in the subarray is strictly less than k.

 

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k. */

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n=nums.length;
          if (k <= 1) return 0;
        int right=0,left=0,product=1,c=0;
        while(right<n){
            product*=nums[right];
            while(product>=k && left<=right){
                product/=nums[left];
                left++;
            }
            c+=right-left+1;
            right++;
        }
        return c;
    }
}