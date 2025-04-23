class Solution {

    public int findMax(int nums[]){
        int max=0;
        for(int i=0;i<nums.length;i++){
            max= Math.max(max,nums[i]);
        }
        return max;
    }

    public boolean isPossible(int nums[],int maxOperations, int mid){
        int totalOperations=0;
        for(int num:nums){
            int ops= num/mid;
            if(num%mid==0){
                ops-=1;
            }
            totalOperations+=ops;
        }
        return totalOperations<=maxOperations;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int low=1,high= findMax(nums);
        int result= high;
        while(low<=high){
            int mid= low+(high-low)/2;
            if(isPossible(nums,maxOperations,mid)==true){
                result=mid;
                high= mid-1;
            }
            else {
                low=mid+1;
            }
        }
        return result;
    }
}