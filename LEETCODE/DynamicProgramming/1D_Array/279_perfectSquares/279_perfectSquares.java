/*279. Perfect Squares
s
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product
 of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not. */



 /*Approach 1: It will give memory limit exceeded
        --> We are relating this problem with Unbounded Knapsack
        --> If We just create an arrray/list of perfect squares upto n
                    ---> we CAN RELATE THIS WITH COIN CHANGE PROBLEM

                    --> given the dominations, we have to calculate kismei se minimum aayega
                            --> unbounded because we can use same domination multiple number of times */
class Solution1 {
    final int BIG=(int)1e9;
    public int numSquares(int n) {
        List<Integer> list= new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(i*i);
        }
        int amount=n;
        int size=list.size();
        int dp[][]= new int[size][n+1];
        for(int row[]:dp){
            Arrays.fill(row,-1);
        }
        int res= solve(size-1,amount,list,dp);
        return res>=BIG?-1:res;
    }

    public int solve(int i, int amount, List<Integer> coins,int dp[][]){
        if(i==0){
            if(amount%coins.get(i)==0) return amount/coins.get(i);
            return BIG;
        }
        if(dp[i][amount]!=-1) return dp[i][amount];
        int not_take= solve(i-1,amount,coins,dp);
        int take= BIG;
        if(amount>=coins.get(i)){
            take=1+solve(i,amount-coins.get(i),coins,dp);
        }
        return dp[i][amount]=Math.min(take,not_take);
    }
}



//Appraoch 2: Recursion + Memoisation 

class Solution {

    public int numSquares(int n) {
        int dp[]= new int[n+1];
        Arrays.fill(dp,-1);
        return solve(n,dp);
        
    }
    public int solve(int n,int dp[]){
        if(n==0) return 0;
        if(dp[n]!=-1) return dp[n];
        int result= Integer.MAX_VALUE;

        for(int i=1;i*i<=n;i++){
            result= Math.min(result,1+solve(n-i*i,dp));
        }
        return dp[n]=result;
    } 
}