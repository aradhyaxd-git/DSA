/*72. Edit Distance

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character */

class Solution1 {
    public int minDistance(String word1, String word2) {
        int n= word1.length();
        int m= word2.length();
        char s1[]=word1.toCharArray();
        char s2[]= word2.toCharArray();

        //3 operations kar sakte hai, insert , delete , replace
        int dp[][]= new int[n][m];
        for(int row[]:dp) Arrays.fill(row,-1);
        return solve(s1,s2,n-1,m-1,dp);
    }

    public int solve(char s1[], char s2[], int i,int j, int dp[][]){

        //base conditions if s1 gets exhausted first eg f(1,-1)
        // --> min 2 insertions karne hoyengay : j+1
        if(i<0) return j+1;
        if(j<0) return i+1;

        if(dp[i][j]!=-1) return dp[i][j];

        //case  of match, match hua to kuch bhi nahi krengay hum log
        if(s1[i]==s2[j])
            return dp[i][j]= 0+solve(s1,s2,i-1,j-1,dp);

        int insert= solve(s1,s2,i,j-1,dp);
        int delete= solve(s1,s2,i-1,j,dp);
        int replace= solve(s1,s2,i-1,j-1,dp);

        return dp[i][j]=1+Math.min(insert,Math.min(delete,replace));        
    }
}

//appraoch 2: tabulation mei convert karne ke liye negative indexes na aaye to shift kardiya sab

class Solution2 {
    public int minDistance(String word1, String word2) {
        int n= word1.length();
        int m= word2.length();
        char s1[]=word1.toCharArray();
        char s2[]= word2.toCharArray();

        //3 operations kar sakte hai, insert , delete , replace
        int dp[][]= new int[n+1][m+1];
        for(int row[]:dp) Arrays.fill(row,-1);
        return solve(s1,s2,n,m,dp);
    }

    public int solve(char s1[], char s2[], int i,int j, int dp[][]){

        //base conditions if s1 gets exhausted first eg f(1,-1)
        // --> min 2 insertions karne hoyengay : j+1
        if(i==0) return j;
        if(j==0) return i;

        if(dp[i][j]!=-1) return dp[i][j];

        //case  of match, match hua to kuch bhi nahi krengay hum log
        if(s1[i-1]==s2[j-1])
            return dp[i][j]= 0+solve(s1,s2,i-1,j-1,dp);

        int insert= solve(s1,s2,i,j-1,dp);
        int delete= solve(s1,s2,i-1,j,dp);
        int replace= solve(s1,s2,i-1,j-1,dp);

        return dp[i][j]=1+Math.min(insert,Math.min(delete,replace));        
    }
}


//apraoch3 : tabulation 
class Solution4 {
    public int minDistance(String word1, String word2) {
        int n= word1.length();
        int m= word2.length();
        char s1[]=word1.toCharArray();
        char s2[]= word2.toCharArray();
        int dp[][]= new int[n+1][m+1];

        //base case likho
        for(int j=0;j<m+1;j++) dp[0][j]=j;
        for(int i=0;i<n+1;i++) dp[i][0]=i;

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                 if(s1[i-1]==s2[j-1])
                    dp[i][j]=dp[i-1][j-1];
                 else 
                    dp[i][j]=1+Math.min(dp[i][j-1],Math.min(dp[i-1][j],dp[i-1][j-1]));
            }
        }
        return dp[n][m];
    }
}


class Solution {
    public int minDistance(String word1, String word2) {
        int n= word1.length();
        int m= word2.length();
        char s1[]=word1.toCharArray();
        char s2[]= word2.toCharArray();
        // int dp[][]= new int[n+1][m+1];
        int prev[]= new int[m+1];
        int curr[]= new int[m+1];
        //base case likho
        // for(int j=0;j<m+1;j++) dp[0][j]=j;
        for(int j=0;j<m+1;j++) prev[j]=j;

         for(int i=1;i<n+1;i++){
             // for(int i=0;i<n+1;i++) dp[i][0]=i;
            curr[0]=i; //make sure everytime you are using curr[0], initialise it to i
            for(int j=1;j<m+1;j++){
                 if(s1[i-1]==s2[j-1])
                    curr[j]=prev[j-1];
                 else 
                    curr[j]=1+Math.min(curr[j-1],Math.min(prev[j],prev[j-1]));
            }
            prev= curr.clone();
        }
        return prev[m];
    }
}