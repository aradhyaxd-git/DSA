/*2537. Count the Number of Good Subarrays

Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if there are at least k pairs of indices (i, j) such that i < j and 
arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i], k <= 109
 */

 class Solution {
    public long countGood(int[] nums, int k) {
        int n= nums.length;
        long pairs=0; //pairs store kr rhega...ki kitne valid pairs ban rahe hai
        /* for example agar 2 1 2 is a valid pair
                        --> then 2 1 2 3 is also a valid pair 
                        */
        long result=0;
        int right=0,left=0;

        Map<Integer,Integer> map= new HashMap<>();
        //maps are used to store the past: here we need the frequency of every past value

        while(right<n){
            int val=nums[right];
            int freq= map.getOrDefault(val,0); // frequency nikalo, agar map mei nahi hai to 0 krdo

            map.put(val,freq+1); // map mei frequency badha di hai.

            pairs+= freq;

            while(pairs>=k){
                //jaise hi valid condition hit kari.. we will sshrink the window
                result+= (n-right); //right is point where good subArrays milengi

                int leftVal= nums[left];
                int leftfreq= map.get(leftVal);
                leftfreq= leftfreq-1;
                map.put(leftVal,leftfreq);
                pairs-=(leftfreq);

                left++;
            }

            right++;
        }

        return result;
        
    }
}