class Solution {
    public int[] findErrorNums(int[] nums) {
        int n=nums.length;
        int duplicate=-1,missing=-1;
        for(int i=0;i<n;i++){
            int index= Math.abs(nums[i])-1;
            if(nums[index]<0) { //mtlb already negative kia jaa chuka hai
            duplicate= Math.abs(nums[i]);
            }
            else { 
                nums[index]*=(-1);     
            }
        }
            for(int i=0;i<n;i++){
                if(nums[i]>0){
                    missing= i+1;
                    break;
                }
            }
            return new int[]{duplicate,missing};
    }
}