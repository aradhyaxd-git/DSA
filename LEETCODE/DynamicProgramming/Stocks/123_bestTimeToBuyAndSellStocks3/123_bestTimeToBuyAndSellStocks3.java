/*123. Best Time to Buy and Sell Stock III

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
 before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple 
transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
  */


//approach 1: reursion+memoization
class Solution1 {
    public int fun(int i, int buy, int cap, int n, int[] prices, int [][][] dp){
        if(i==n || cap==0 ) return 0; //cap==0 means apna chance khatam hogya and there is no more chance left
        if(dp[i][buy][cap]!=-1)
            return dp[i][buy][cap];
        int profit=0;
        if(buy==1)
            profit= Math.max(-prices[i]+ fun(i+1,0,cap,n,prices,dp), 0+fun(i+1,1,cap,n,prices,dp));
        else
            profit= Math.max(prices[i]+fun(i+1,1,cap-1,n,prices,dp),0+fun(i+1,0,cap,n,prices,dp));
        return dp[i][buy][cap]= profit;
    }
    public int maxProfit(int[] prices) {
        int n= prices.length;
        int dp[][][]= new int[n][2][3];
        for(int row[][]:dp){
            for(int filll[]:row){
                Arrays.fill(filll,-1);
            }
        }
        return fun(0,1,2,n,prices,dp);      
    }
}

//approach 2: tabulation 
public class Solution{
     public int maxProfit(int[] prices) {
        int n= prices.length;
        int dp[][][]= new int[n+1][2][3];
        /*Base case 1: when cap is 0, i and buy can be anything */
        for(int i=0;i<n;i++){
            for(int buy=0;buy<=1;buy++){
                dp[i][buy][0]=0;
            }
        }
        /*BASE CASE 2: WHEN i==n */
        for(int buy=0;buy<=1;buy++){
            for(int cap=0;cap<=2;cap++){
                dp[n][buy][cap]=0;
            }
        }
        
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++){
                // for(int cap=0;cap<=2;cap++){ //for every cap=0, the value is zero
                    for(int cap=1;cap<=2;cap++){
                    if(buy==1)
                        dp[i][buy][cap]= Math.max(-prices[i]+dp[i+1][0][cap],dp[i+1][1][cap]);

                    else 
                        dp[i][buy][cap]= Math.max(prices[i]+dp[i+1][1][cap-1],dp[i+1][0][cap]);
                }
            }
        }
        return dp[0][1][2];
     }
        
}



//approach 2: tabulation 
public class Solution3{
     public int maxProfit(int[] prices) {
        int n= prices.length;
        int dp[][][]= new int[n+1][2][3];
        /*Base case 1: when cap is 0, i and buy can be anything */
        // for(int i=0;i<n;i++){
        //     for(int buy=0;buy<=1;buy++){  these things can be omitted as well because Java mei automatically 0 ho jati hai
        //         dp[i][buy][0]=0;
        //     }
        // }
        // /*BASE CASE 2: WHEN i==n */
        // for(int buy=0;buy<=1;buy++){
        //     for(int cap=0;cap<=2;cap++){
        //         dp[n][buy][cap]=0;
        //     }
        // }
        
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++){
                // for(int cap=0;cap<=2;cap++){ //for every cap=0, the value is zero
                    for(int cap=1;cap<=2;cap++){
                    if(buy==1)
                        dp[i][buy][cap]= Math.max(-prices[i]+dp[i+1][0][cap],dp[i+1][1][cap]);

                    else 
                        dp[i][buy][cap]= Math.max(prices[i]+dp[i+1][1][cap-1],dp[i+1][0][cap]);
                }
            }
        }
        return dp[0][1][2];
     }
        
}