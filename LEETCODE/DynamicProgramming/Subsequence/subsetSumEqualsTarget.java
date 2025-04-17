// static Boolean isSubsetSum(int arr[], int sum) {
    //     // code here
    //     int n= arr.length;
    //     Boolean dp[][]=new Boolean [n+1][sum+1];
    //     return solve(n-1,arr,sum,dp);
    // }
    
    // static boolean solve(int i,int arr[],int target,Boolean dp[][]){
    //     if(target==0) return true;
    //     if(i==0) return arr[0]==target;
    //     if(dp[i][target]!=null) return dp[i][target];
    //     boolean not_take= solve(i-1,arr,target,dp);
    //     boolean take=false;
    //     if(target>=arr[i])
    //         take=solve(i-1,arr,target-arr[i],dp);
    //     return dp[i][target]= take || not_take;
    // }
    
    
// class Solution {
//     static Boolean isSubsetSum(int arr[], int sum) {
//         int n = arr.length;
//         boolean[][] dp = new boolean[n][sum + 1];

//         // base case: sum = 0 => true
//         for (int i = 0; i < n; i++) dp[i][0] = true;

//         // base case for first element
//         if (arr[0] <= sum) dp[0][arr[0]] = true;

//         for (int i = 1; i < n; i++) {
//             for (int j = 1; j <= sum; j++) {
//                 boolean not_take = dp[i - 1][j];
//                 boolean take = false;
//                 if (j >= arr[i])
//                     take = dp[i - 1][j - arr[i]];
//                 dp[i][j] = take || not_take;
//             }
//         }

//         return dp[n - 1][sum];
//     }
// }


class Solution {

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int n= arr.length;
        Boolean dp[][]= new Boolean[n+1][sum+1];
        
        //base case1: jab size0 hai to sum=0 is always possible
        for(int i=0;i<=n;i++){
            for(int j=0;j<=sum;j++){
                if(i==0) dp[i][j]=false;
                if(j==0) dp[i][j]=true; //jab size 
            }
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sum;j++){
                if(arr[i-1]<=j){
                    dp[i][j]= dp[i-1][j-arr[i-1]]||dp[i-1][j];
                }
                else 
                    dp[i][j]= dp[i-1][j];
            }
        }
        
        return dp[n][sum];
    }
}