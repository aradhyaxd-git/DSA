class Solution {
    public int minStartValue(int[] nums) {
        int n=nums.length;
        int prefixSum=0; int mostNegative=0;
        for(int i=0;i<n;i++){
            prefixSum+=nums[i];
            mostNegative=Math.min(mostNegative,prefixSum);
        }
          /*We need to find the minimum startValue 
        such that the array elements are progressively 
        added, so that the cumulativeSum never goes below 1

        -> TO ensure this condition, We need to add enough sum
        to offset the negative value, which is done by 
        -mostNegative + 1 */
        return -(mostNegative)+1;    
    }
}