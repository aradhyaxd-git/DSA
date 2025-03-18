class Solution {
    public int maxAbsoluteSum(int[] nums) {
      int n= nums.length;
      int maxiMax=Integer.MIN_VALUE; int sum=0;
      for(int i=0;i<n;i++){
        sum+=nums[i];
        maxiMax=Math.max(maxiMax,sum);
        if(sum<0) sum=0;
      }
      int miniMax=Integer.MAX_VALUE;sum=0;
      for(int i=0;i<n;i++){
        sum+=nums[i];
        miniMax= Math.min(miniMax,sum);
        if(sum>0) sum=0;  
      }
      return Math.max(Math.abs(miniMax),maxiMax);    
    }
}