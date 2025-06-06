//write the recurzion solution without memoization
class Solution4 {
    public int longestCommonSubsequence(String text1, String text2) {
        char s1[]= text1.toCharArray();
        char s2[]= text2.toCharArray();
        int n= s1.length,m=s2.length;
        return solve(n-1,m-1,s1,s2);
    }
    public int solve(int i,int j,char s1[], char s2[]){
        if(i<0 || j<0) return 0;
        if(s1[i]==s2[j]) return 1+solve(i-1,j-1,s1,s2);
        else
            return Math.max(solve(i-1,j,s1,s2),solve(i,j-1,s1,s2));
    }
}



//approach 1: recursion+ memoization

class Solution3 {
    public int longestCommonSubsequence(String text1, String text2) {
        char s1[]= text1.toCharArray();
        char s2[]= text2.toCharArray();
        int n= s1.length,m=s2.length;
        int dp[][]=new int[n][m];
        for(int row[]:dp) Arrays.fill(row,-1);
        return solve(n-1,m-1,s1,s2,dp);
    }
    public int solve(int i,int j,char s1[], char s2[],int dp[][]){
        if(i<0 || j<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        if(s1[i]==s2[j]) return dp[i][j]=1+solve(i-1,j-1,s1,s2,dp);
        else
            return dp[i][j]=Math.max(solve(i-1,j,s1,s2,dp),solve(i,j-1,s1,s2,dp));
    }
}


//appraoch 2: we convert the recursion+ memoization int tabulation format
        /*-> step 1; copy base case
          -> step 2: i = 
                        j= 

           -> step 3: shifting of index
            -1 0 1 2 3 ... n-1
            instead of f(n-1,m-1) call f(n,m) 
                        f(0) means -1
                        */
class Solution1 {
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

//space optimised code:
/* we look carefully , we can get dp[i-1][j]= previous row, same column
                                  dp[i][j-1]=current row, previous column
                                  dp[i-1[j-1]= prev row, prev column

                    so dont take whole matrix: instead: we keep --> prev[] for dp[i-1]
                                                                --> curr[] for dp[i] */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char s1[]= text1.toCharArray();
        char s2[]= text2.toCharArray();
        int n= s1.length,m=s2.length;
        int curr[]= new int[m+1];
        int prev[]= new int[m+1];

        /* for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1[i-1]==s2[j-1])
                    dp[i][j]=1 + dp[i-1][j-1];
                else
                    dp[i][j]= Math.max(dp[i-1][j],dp[i][j-1]);
            }
        } */

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1[i-1]==s2[j-1])
                    curr[j]= 1+ prev[j-1];
                else 
                    curr[j]=Math.max(curr[j-1],prev[j]);
                }
            prev=curr.clone();
        }
        return curr[m];
    }
}



//*INORDER TO PRINT ALL OF THE LCS */
class Solution {
    public List<String> all_longest_common_subsequences(String s, String t) {
        // Code here
        char s1[]= s.toCharArray();
        char s2[]= t.toCharArray();
        int n= s1.length,m=s2.length;
        
        int dp[][]= new int[n+1][m+1];
        
        for(int i=0;i<n;i++) dp[i][0]=0;
        for(int j=0;j<m;j++) dp[0][j]=0;
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1[i-1]==s2[j-1])
                    dp[i][j]= 1+ dp[i-1][j-1];
                else 
                    dp[i][j]= Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
    
        Set<String> result= new HashSet<>();
        backtrack(s1, s2, dp, n, m, new StringBuilder(), result);
        List<String> resultList = new ArrayList<>(result);
        Collections.sort(resultList);
        return resultList;
    }
    
     private void backtrack(char[] s1, char[] s2, int[][] dp, int i, int j, StringBuilder current, Set<String> result) {
        // Base case: reached the start of either string
        if (i == 0 || j == 0) {
            result.add(current.reverse().toString());
            current.reverse(); // Restore original order for further backtracking
            return;
        }
        
        // Case 1: Characters match → part of LCS
        if (s1[i - 1] == s2[j - 1]) {
            current.append(s1[i - 1]);
            backtrack(s1, s2, dp, i - 1, j - 1, current, result);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
        // Case 2: Characters don't match → explore both paths if they have equal LCS lengths
        else if (dp[i - 1][j] == dp[i][j - 1]) {
            backtrack(s1, s2, dp, i - 1, j, current, result);
            backtrack(s1, s2, dp, i, j - 1, current, result);
        }
        // Case 3: Only one path leads to LCS
        else if (dp[i - 1][j] > dp[i][j - 1]) {
            backtrack(s1, s2, dp, i - 1, j, current, result);
        } else {
            backtrack(s1, s2, dp, i, j - 1, current, result);
        }
    }
}


//for longest common substring

class Solution {
    public int longestCommonSubstr(String s1, String s2) {
        // code here
        int n= s1.length(),m=s2.length();
        int dp[][]=new int[n+1][m+1];
        //  if(i==0 || j==0) return 0;
        for(int j=0;j<m;j++) dp[0][j]=0;
        for(int i=0;i<n;i++) dp[i][0]=0;
        /*if(s1[i-1]==s2[j-1]) return dp[i][j]=1+solve(i-1,j-1,s1,s2,dp);
        else
            return dp[i][j]=Math.max(solve(i-1,j,s1,s2,dp),solve(i,j-1,s1,s2,dp)); */
            
        int maxi=Integer.MIN_VALUE;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=1 + dp[i-1][j-1];
                    maxi= Math.max(maxi,dp[i][j]);
                }
                else{
                    
                    // dp[i][j]= Math.max(dp[i-1][j],dp[i][j-1]);
                    dp[i][j]=0;
                }
            }
        }
        return Math.max(maxi,0);
    }
}