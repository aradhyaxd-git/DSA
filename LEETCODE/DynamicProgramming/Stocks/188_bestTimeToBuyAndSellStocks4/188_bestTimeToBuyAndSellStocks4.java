/*188. Best Time to Buy and Sell Stock IV

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, 
and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy 
at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock 
before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2. */


//similar to part 3 .. just we have used k instead of 2 in cap.. rest all approaches will work: recursion+memoization, tabulation... dont go for 4 variables one as that one is not intuitive
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n= prices.length;
        int dp[][][]= new int[n][2][k+1]; //k+1 to store all possibilities of k
        for(int row[][]:dp){
            for(int filll[]:row){
                Arrays.fill(filll,-1);
            }
        }

        return solve(0,1,k,n,prices,dp);
    }


    public int solve(int i,int buy, int cap, int n,int prices[],int dp[][][]){
        if(i==n || cap==0) return 0;
        if(dp[i][buy][cap]!=-1) return dp[i][buy][cap];

        int profit=0;
        if(buy==1)
            profit= Math.max(-prices[i]+solve(i+1,0,cap,n,prices,dp), 0+solve(i+1,1,cap,n,prices,dp));
        else
            profit= Math.max(prices[i]+solve(i+1,1,cap-1,n,prices,dp),0+solve(i+1,0,cap,n,prices,dp));

        return dp[i][buy][cap]=profit;
            
    }
}