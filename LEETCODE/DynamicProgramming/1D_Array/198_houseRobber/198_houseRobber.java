//APPROACH 1
class Solution2 {
    int dp[];
    public int solve(int nums[],int i){
        int n=nums.length;
        if(i>=n) return 0;
        if(dp[i]!=-1) return dp[i];
        int steal= nums[i]+solve(nums,i+2);
        int skip = solve(nums,i+1);
        dp[i]= Math.max(steal,skip);
        return dp[i];
    }
    public int rob(int[] nums) {
        int n=nums.length;
        dp= new int[n+1];
        Arrays.fill(dp,-1);
        return solve(nums,0);      
    }
}
//APPRAOCH 2
class Solution1 {
    public int rob(int[] nums) {
        int n= nums.length;
        int dp[]= new int[n+1];
        dp[0]=0;
        dp[1]=nums[0];
        for(int i=2;i<=n;i++){
            int steal= nums[i-1]+dp[i-2]; // option 1: steal current house and add profit from two house before
            int skip= dp[i-1]; //option 2: skip the current house and take profit from previous ones
            dp[i]= Math.max(steal,skip);
        }
        return dp[n];
    }
}
//APPROACH 3 
class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        int prevPrev=0;
        int prev= nums[0];
        for(int i=2;i<=n;i++){
            int steal= nums[i-1]+prevPrev;
            int skip= prev;
            int temp = Math.max(steal,skip);
            prevPrev= prev;
            prev=temp;
        }
        return prev;
    }
}