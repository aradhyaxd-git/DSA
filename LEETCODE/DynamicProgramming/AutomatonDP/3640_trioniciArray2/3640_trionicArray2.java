/*3640. Trionic Array II

You are given an integer array nums of length n.

A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist indices l < p < q < r such that:

nums[l...p] is strictly increasing,
nums[p...q] is strictly decreasing,
nums[q...r] is strictly increasing.
Return the maximum sum of any trionic subarray in nums. */

class Solution {
    //define 4 states:
    /* 0 -> about to start incre
       1 -> 1st increasing
       2 -> decreasing
       3 -> 2nd increasing */
    Long dp[][];
    int nums[];
    int n;
    long INF=Long.MIN_VALUE/2;
    public long maxSumTrionic(int[] nums) {
        n= nums.length;
        this.nums=nums;
        long ans= Long.MIN_VALUE;
        dp= new Long[n+1][4];

        for(int i=0;i<n;i++){
            ans=Math.max(ans,dfs(i,0));
        }

        return ans;    
    }

    private long dfs(int i, int state){
        if(i==n) {
            if(state==3) return 0;
            return INF;
        }
        if(dp[i][state]!=null) return dp[i][state];
        long res= INF;

        if(state==0){
             if(i+1<n && nums[i]<nums[i+1])
                res= Math.max(res,nums[i]+dfs(i+1,1));
            
            else 
                return INF;

        }
           
        
        if(state==1){
            if(i+1<n && nums[i]<nums[i+1])
                res= Math.max(res,nums[i]+dfs(i+1,1));

            if(i+1<n && nums[i]>nums[i+1])
                res= Math.max(res,nums[i]+dfs(i+1,2));
        }

        if(state==2){
            if(i+1<n && nums[i]>nums[i+1])
                res= Math.max(res,nums[i]+dfs(i+1,2));
            
             if(i+1<n && nums[i]<nums[i+1])
                res= Math.max(res,nums[i]+dfs(i+1,3));
        }


         if(state==3){

            res= nums[i];

            if(i+1<n && nums[i]<nums[i+1])
                res= Math.max(res,nums[i]+dfs(i+1,3));

        }

        return dp[i][state]=res;
    }

}


class Solution2 {
    public long maxSumTrionic(int[] nums) {
        int n= nums.length;
        long INF= Long.MIN_VALUE/2;

        long dp[][]= new long[n+1][4];

        //define the base condition transition 
        /* if(i==n) {
            if(state==3) return 0;
            return INF;
        }*/
        for(int state=0;state<4;state++){
            dp[n][state]= (state==3)?0:INF;
        }

        //earlier we moved from 0 to n, but now in tabulation ekdm ulta 
        for(int i=n-1;i>=0;i--){
            dp[i][0]=INF;
            if(i+1<n && nums[i]<nums[i+1])
                dp[i][0]= nums[i]+dp[i+1][1];

            dp[i][1]=INF;
            if(i+1<n && nums[i]<nums[i+1])
                dp[i][1]= Math.max(dp[i][1],nums[i]+dp[i+1][1]);
            if(i+1<n && nums[i]>nums[i+1])
                dp[i][1]= Math.max(dp[i][1],nums[i]+dp[i+1][2]);

            
            dp[i][2]=INF;
            if(i+1<n && nums[i]>nums[i+1])
                dp[i][2]= Math.max(dp[i][2],nums[i]+dp[i+1][2]);
            
            if(i+1<n && nums[i]<nums[i+1])
                dp[i][2]= Math.max(dp[i][2],nums[i]+dp[i+1][3]);

            dp[i][3]= nums[i];
            if(i+1<n && nums[i]<nums[i+1])
                dp[i][3]= Math.max(dp[i][3],nums[i]+dp[i+1][3]);

        }
        long ans= INF;

        for(int i=0;i<n;i++)
            ans= Math.max(ans,dp[i][0]);

        return ans;

    }
}