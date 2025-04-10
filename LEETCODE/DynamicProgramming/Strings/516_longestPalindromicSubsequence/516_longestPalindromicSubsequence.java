class Solution {
    public String rev(String s){
        return new StringBuilder(s).reverse().toString();
    }
    public int longestPalindromeSubseq(String s) {
        return longestCommonSubsequence(s,rev(s));
    }
     public int longestCommonSubsequence(String text1, String text2) {
        char s1[]= text1.toCharArray();
        char s2[]= text2.toCharArray();
        int n= s1.length,m=s2.length;
        int dp[][]=new int[n+1][m+1];
        //  if(i==0 || j==0) return 0;
        for(int j=0;j<m;j++) dp[0][j]=0;
        for(int i=0;i<n;i++) dp[i][0]=0;
        /*if(s1[i-1]==s2[j-1]) return dp[i][j]=1+solve(i-1,j-1,s1,s2,dp);
        else
            return dp[i][j]=Math.max(solve(i-1,j,s1,s2,dp),solve(i,j-1,s1,s2,dp)); */
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1[i-1]==s2[j-1])
                    dp[i][j]=1 + dp[i-1][j-1];
                else
                    dp[i][j]= Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[n][m];

    }
}