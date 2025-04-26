class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n= nums.length;

        long ans=0;

        int minKIdx=-1;
        int maxKIdx=-1;
        int culpritIdx=-1;

        for(int i=0;i<n;i++){

            if(nums[i]>maxK || nums[i]<minK)
                culpritIdx=i;
            
            if(nums[i]==maxK)
                maxKIdx=i;
            
            if(nums[i]==minK)
                minKIdx=i;

            int temp= Math.min(maxKIdx,minKIdx)-culpritIdx;

            if(temp>0) 
                ans+=temp;
        }

        return ans;
    }
}