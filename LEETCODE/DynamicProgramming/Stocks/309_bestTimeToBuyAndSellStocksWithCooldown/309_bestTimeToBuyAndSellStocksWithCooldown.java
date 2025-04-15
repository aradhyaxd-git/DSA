/*309. Best Time to Buy and Sell Stock with Cooldown

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell] */


class Solution {
    public int maxProfit(int[] prices) {
        //is question mei.. very similar to buyy and sell stock with cooldown
        int n= prices.length;
        int dp[][]= new int[n][2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return solve(0,1,n,prices,dp);
    }

    public int solve(int i,int buy,int n, int prices[],int dp[][]){
        if(i>=n) return 0; //i==n nhi hoga kyuki i+2 hoga
        if(dp[i][buy]!=-1) return dp[i][buy];
        int profit=0;
        if(buy==1)
            profit= Math.max(-prices[i]+solve(i+1,0,n,prices,dp),0+solve(i+1,1,n,prices,dp));
        else 
            profit= Math.max(prices[i]+solve(i+2,1,n,prices,dp),0+solve(i+1,0,n,prices,dp)); //i+2 isliye  kyuki next state mei cooldown hoga

        return dp[i][buy]=profit;
    }

}