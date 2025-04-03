/*2367. Number of Arithmetic Triplets

You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff. 
A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:

i < j < k,
nums[j] - nums[i] == diff, and
nums[k] - nums[j] == diff.
Return the number of unique arithmetic triplets.

 

Example 1:

Input: nums = [0,1,4,6,7,10], diff = 3
Output: 2
Explanation:
(1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
(2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.  */

//appraoch 1: jo bhi dia hai wahi nikaal do
class Solution1 {
    public int arithmeticTriplets(int[] nums, int diff) {
        /*given we have nums[j]-nums[i]=diff 
                        nums[k]-nums[j]=diff
        */
        int count=0; int n=nums.length;
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    if(nums[j] - nums[i] == diff && nums[k] - nums[j] == diff)
                        count++;
                }
            }
        }
        return count;    
    }
}
//approach 2:
class Solution2 { //O(2N)
    public int arithmeticTriplets(int[] nums, int diff) {
        int count=0; int n= nums.length; 
        HashSet<Integer> set= new HashSet<>();
        /*nums[j]-nums[i]=diff
          nums[k]-nums[j]=diff
    ----> nums[k]-nums[i]=2diff

          nums[k]=2diff+nums[i] --->1
          lly, 
          nums[j]-nums[i]= diff
          nums[j]= diff+nums[i] ---->2
          */
        for(int num:nums) set.add(num);
        for(int num:nums){
            if(set.contains(num+diff) && set.contains(num+2*diff)) 
                count++;
        }
        return count;
    }
}

//approach 3: most optimal one: binary search 
class Solution {  //O(N log N)
    public int arithmeticTriplets(int[] nums, int diff) {
        int n= nums.length;
        int count=0;
        for(int i=0;i<n;i++){
            int j= Arrays.binarySearch(nums,nums[i]+diff);
            int k= Arrays.binarySearch(nums,nums[i]+2*diff);
            if(j>0 && k>0) count++;
        }
        return count;
    }
}
