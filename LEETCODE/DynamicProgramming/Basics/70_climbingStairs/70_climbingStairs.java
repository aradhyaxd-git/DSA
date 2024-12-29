//APPROACH 1 RECURSION+MEMOIZATION
class Solution2 {
    int dp[]= new int[46];
    public Solution(){
        Arrays.fill(dp,-1);
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;
    }
    public int climbStairs(int n) {
        if(dp[n]!=-1) return dp[n];
        dp[n]= climbStairs(n-1)+climbStairs(n-2);
        return dp[n];
    }
}

//APPRAOCH 2  TABULATION(BOTTOM UP)
class Solution1 {
    int dp[]= new int[46];
    public int climbStairs(int n) {
        dp[1]=1; dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
        
    }
}

//APPROACH 3

class Solution {
    public int climbStairs(int n) {
       if(n<=3)
       return n;
       else 
       {
        int a=3,b=2;
        for(int i=0;i<n-3;i++)
        {
            a=a+b;
            b=a-b;
        }
        return a;
       }
    }
}