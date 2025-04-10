/*583. Delete Operation for Two Strings

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea". */

class Solution {
    public int minDistance(String word1, String word2) {
        char x1[]=word1.toCharArray(),x2[]=word2.toCharArray();
        int n=x1.length,m=x2.length;
        return n+m-2*(LCS(x1,x2));
    }
    public int LCS(char s1[],char s2[]){
        int n= s1.length,m=s2.length;
        int dp[][]= new int[n+1][m+1];
        for(int i=0;i<n;i++) dp[i][0]=0;
        for(int j=0;j<m;j++) dp[0][j]=0;

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1[i-1]==s2[j-1]){
                    dp[i][j]= 1+ dp[i-1][j-1];
                }
                else 
                    dp[i][j]= Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
}