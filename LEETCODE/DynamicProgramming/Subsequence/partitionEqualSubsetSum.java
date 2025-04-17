
class Solution {
    static boolean equalPartition(int arr[]) {
        int n= arr.length;
        int sum= Arrays.stream(arr).sum();
        if(sum%2!=0) return false;
        
        return isSubsetSum(arr,sum/2);
        // agar sum/2 hai, to 2 parts mei divide kar sakte hai--> ek part ka answer reach hoja rha 
        //partition karke.. to automatically dusre ka bhi ho jayega
        
    }
    
    static Boolean isSubsetSum(int arr[], int sum) {
        int n= arr.length;
        Boolean dp[][]= new Boolean[n+1][sum+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=sum;j++){
                if(i==0) dp[i][j]=false;
                if(j==0) dp[i][j]=true; 
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
