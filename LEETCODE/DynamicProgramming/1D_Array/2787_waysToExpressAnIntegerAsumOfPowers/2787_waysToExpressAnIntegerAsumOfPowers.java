class Solution {
    int mod= 1_000_000_007;
    Integer dp[][];
    public int numberOfWays(int n, int x) {

        dp= new Integer[301][301];
        
        return solve(1,x,0,n);
        
    }

    public int solve(int i, int x, int sum, int target){

       
        if(target==sum){
            return 1;
        }
        if(target<sum || (int)Math.pow(i,x)>target) return 0;

        if(dp[i][sum]!=null) return dp[i][sum];


        int val= (int)Math.pow(i,x);

        int take=solve(i+1,x,sum+val,target);
        int notTake= solve(i+1,x,sum,target);

        return dp[i][sum]=(take+notTake)%mod;

    }
}