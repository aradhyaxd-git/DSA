/*115. Distinct Subsequences

Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit */

class Solution3 {
    /*STEPS:
        -> express everything in terms of index
        -> explore all posibblities 
        -> return summation of all possibilities */
    public int numDistinct(String s1, String s2) {
        char s[]= s1.toCharArray();
        char t[]= s2.toCharArray();
        int n= s.length; int m= t.length;
        return solve(s,t,n-1,m-1);
    }
    public int solve(char s[], char t[], int i, int j){
        if(j<0) return 1; //ye condition phele aayegi kyuki s2 ki length choti hai to phele exhaust hoyega

        if(i<0) return 0;  // means i bahar chala gaya hai negative index hogya hai

        if(s[i]==t[j]) { //match hogya i.e charactr match hogya
            return solve(s,t,i-1,j-1)+solve(s,t,i-1,j); // 2 cases ayengay, ya to wo character jo match hua hai usko lelo, ya nhi lo --> dono ka summation karna hai
        }
        else
            return solve(s,t,i-1,j);
    }
}

//approach 1: recursion+ memoization
class Solution2 {
    /*STEPS:
        -> express everything in terms of index
        -> explore all posibblities 
        -> return summation of all possibilities */
    public int numDistinct(String s1, String s2) {
        char s[]= s1.toCharArray();
        char t[]= s2.toCharArray();
        int n= s.length; int m= t.length;
        int dp[][]= new int[n+1][m+1];
        for(int row[]:dp) Arrays.fill(row,-1);
        return solve(s,t,n-1,m-1,dp);
    }
    public int solve(char s[], char t[], int i, int j,int dp[][]){
        if(j<0) return 1; //ye condition phele aayegi kyuki s2 ki length choti hai to phele exhaust hoyega

        if(i<0) return 0;  // means i bahar chala gaya hai negative index hogya hai

        if(dp[i][j]!=-1) return dp[i][j];

        if(s[i]==t[j]) { //match hogya i.e charactr match hogya
            return dp[i][j]=solve(s,t,i-1,j-1,dp)+solve(s,t,i-1,j,dp); // 2 cases ayengay, ya to wo character jo match hua hai usko lelo, ya nhi lo --> dono ka summation karna hai
        }
        else
            return dp[i][j]=solve(s,t,i-1,j,dp);
    }
}


class Solution4{
    /*STEPS:
        -> express everything in terms of index
        -> explore all posibblities 
        -> return summation of all possibilities */
    public int numDistinct(String s1, String s2) {
        char s[]= s1.toCharArray();
        char t[]= s2.toCharArray();
        int n= s.length; int m= t.length;
        int dp[][]= new int[n+1][m+1];

        //lets see the base cases, if i>0 we got 0, if j>0 we got 1
        // but we are making itby shifting the indexes the base cases will be i==0 and j==0
        // now instead of n-1,m-1 we will call it at n,m --> index change kardengay 

        for(int i=0;i<n;i++) dp[i][0]=1;  // kisi bhi i ke liye if we have j==0 we return 1
        
        // for(int j=0;j<m+1;j++) dp[0][j]=0;    // kisi bhi j ke liye if i==0 , we return 0;
        for(int j=1;j<m;j++) dp[0][j]=0; //it will be because dp[0][0] should be 1

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s[i-1]==t[j-1]){
                    dp[i][j]= dp[i-1][j-1]+dp[i-1][j];
                }
                else 
                    dp[i][j]= dp[i-1][j];
            }
        }
        return dp[n][m];
    }
}



class Solution1 {
    /*STEPS:
        -> express everything in terms of index
        -> explore all posibblities 
        -> return summation of all possibilities */
    public int numDistinct(String s1, String s2) {
        char s[]= s1.toCharArray();
        char t[]= s2.toCharArray();
        int n= s.length; int m= t.length;
        // int dp[][]= new int[n+1][m+1];
        int curr[]= new int[m+1];
        int prev[]= new int[m+1];


        //lets see the base cases, if i>0 we got 0, if j>0 we got 1
        // but we are making itby shifting the indexes the base cases will be i==0 and j==0
        // now instead of n-1,m-1 we will call it at n,m --> index change kardengay 

        // for(int i=0;i<n;i++) dp[i][0]=1;  // kisi bhi i ke liye if we have j==0 we return 1
        
        // // for(int j=0;j<m+1;j++) dp[0][j]=0;    // kisi bhi j ke liye if i==0 , we return 0;
        // for(int j=1;j<m;j++) dp[0][j]=0; //it will be because dp[0][0] should be 1
        curr[0]=prev[0]=1;

        //prev=dp[i-1], curr=dp[i]
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s[i-1]==t[j-1]){
                    curr[j]= prev[j-1]+prev[j];
                }
                else 
                    curr[j]= prev[j];
            }
            prev=curr.clone();
        }
        return prev[m];
    }
}


class Solution {
    /*STEPS:
        -> express everything in terms of index
        -> explore all posibblities 
        -> return summation of all possibilities */
    public int numDistinct(String s1, String s2) {
        char s[]= s1.toCharArray();
        char t[]= s2.toCharArray();
        int n= s.length; int m= t.length;
        // int dp[][]= new int[n+1][m+1];
        // int curr[]= new int[m+1];
        // int prev[]= new int[m+1];
        //prev=dp[i-1], curr=dp[i]

        //do we even need curr[]--> the real answer will be no as , 
                //if we traverse j from j=m to 1 ... we can store it in prev[j]only 
        int prev[]= new int[m+1];
        prev[0]=1;
        for(int i=1;i<n+1;i++){
            for(int j=m;j>=1;j--){
                if(s[i-1]==t[j-1]){
                    prev[j]= prev[j-1]+prev[j];
            //     }
            //     else 
            //         prev[j]= prev[j]; //not needed here
            // }
            // prev=curr.clone();
                }
            }
        }
        return prev[m];
    }
}