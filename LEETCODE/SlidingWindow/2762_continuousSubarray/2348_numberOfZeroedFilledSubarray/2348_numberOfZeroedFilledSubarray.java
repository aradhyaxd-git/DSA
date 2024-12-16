class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long result=0;
        int right=0,left=0;
        int n=nums.length;
        while(right<n){
            if(nums[right]==0){
                result+= (right-left+1);
            }
            else {
                left=right+1;
            }
            right++;
        }
        return result;
        
    }
}


class Solution2 {
    public long zeroFilledSubarray(int[] nums) {
        long zero=0,result=0;
        for(int num:nums){
            if(num==0){
                zero++;
                result+= zero;
            }
            else zero=0;
        }
        return result;    
    }
}