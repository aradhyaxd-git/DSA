class Solution1 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n= nums.length;
        int count=0;
        for(int i=0;i<n;i++){
            int maxi=Integer.MIN_VALUE;
            for(int j=i;j<n;j++){
                maxi= Math.max(maxi,nums[j]);
                if(maxi>=left && maxi<=right) count++;
            }
           
        }
        return count;
        
    }
}


class Solution {
    public boolean isValid(int number,int left,int right){
        return number>=left&&number<=right;
    }
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n= nums.length;
        int ans=0;
        int culpritIdx=-1,validIdx=-1;
        for(int i=0;i<n;i++){
            if(isValid(nums[i],left,right))
                validIdx=i;
             if(nums[i]>right)
                culpritIdx=i;
            int temp= validIdx-culpritIdx;
            if(temp>0){
                ans+=temp;
            }
        }
        return ans;

        
    }
}