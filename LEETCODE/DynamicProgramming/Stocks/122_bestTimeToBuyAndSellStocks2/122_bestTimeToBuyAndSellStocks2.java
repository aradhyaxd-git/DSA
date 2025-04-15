/*122. Best Time to Buy and Sell Stock II

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share 
of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

 */


 /* REOCCURENCE RELATION */
class Solution3 {
    public int fun(int i,int buy, int n,int prices[]){
        /*BASE CASE: if i reached the end, I return 0 */
        if(i==n) return 0;
        int profit=0;
        /*So we have two cases, either we buy or we sell
            We track the buy with a variable */
        if(buy==1)
        /* We are allowed to buy this stock ,  we have two choices 
            1) Buy the stock: deduct the cost of stock i.e -stocks[i] and move to the next day where you can buy
            2) do nothing: i.e move to the next day and stay in buying state */
            profit= Math.max(-prices[i]+fun(i+1,0,n,prices), 0+fun(i+1,1,n,prices));
        else
        /* In this case, we have two cases as well, 
            1) we either sell the stock, and move to the next index with buying state 
            2) we hold the stock, and move to the next index in selling state */
            profit= Math.max(prices[i]+fun(i+1,1,n,prices) , 0+fun(i+1,0,n,prices));

        return profit;
    }
    public int maxProfit(int[] prices) {
        int n= prices.length;
        return fun(0,1,n,prices);   
    }
}



/*APPROACH -1 Recursion+ Memoization */
class Solution2 {
    public int fun(int i,int buy, int n,int prices[],int dp[][]){
        /*BASE CASE: if i reached the end, I return 0 */
        if(i==n) return 0;

        if(dp[i][buy]!=-1) 
            return dp[i][buy];
        int profit=0;
        /*So we have two cases, either we buy or we sell
            We track the buy with a variable */

        if(buy==1)
        /* We are allowed to buy this stock ,  we have two choices 
            1) Buy the stock: deduct the cost of stock i.e -stocks[i] and move to the next day where you can buy
            2) do nothing: i.e move to the next day and stay in buying state */
            profit= Math.max(-prices[i]+fun(i+1,0,n,prices,dp), 0+fun(i+1,1,n,prices,dp));
        else
        /* In this case, we have two cases as well, 
            1) we either sell the stock, and move to the next index with buying state 
            2) we hold the stock, and move to the next index in selling state */
            profit= Math.max(prices[i]+fun(i+1,1,n,prices,dp) , 0+fun(i+1,0,n,prices,dp));

        return dp[i][buy]=profit;
    }
    public int maxProfit(int[] prices) {
        int n= prices.length;
        int dp[][]=new int[n][2];
        for(int row[]:dp)
            Arrays.fill(row,-1);
        return fun(0,1,n,prices,dp);   
    }
}


/*APPROACHC-2 TABULISATION  */
class Solution1 {
    public int maxProfit(int[] prices) {
        int n= prices.length;
        int dp[][]= new int[n+1][2];
        for(int row[]:dp)
            Arrays.fill(row,-1);
        /* Since in rec+memoization, we used from 0 to bottom, 
            Here, we will start from bottom to 0 

        dp[n][0]=dp[n][1] =0 because, n means we have reached
        the end of array so, no transaction , so we have zero, it is the base  case */
        dp[n][0]= dp[n][1]=0;
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++){
                int profit=0;
                if(buy==1)  
                     profit= Math.max(-prices[i]+dp[i+1][0], dp[i+1][1]);
                else
                    profit= Math.max(prices[i]+dp[i+1][1], 0+dp[i+1][0]);
                
                dp[i][buy]=profit;
            }
        }
        return dp[0][1]; //as it represents the max profit , we can achieve
        
    }
}

//FURTHER MORE OPTIMISATION /*APPROACH 3 */
class Solution {
    public int maxProfit(int[] prices) {
        int n= prices.length;
        /*instead of dp[][], we take 4 variables 
            aheadBuy represents dp[i+1][1]
            aheadSell represents dp[i+1][0] */
        int aheadBuy=0,aheadSell=0;
        int currBuy,currSell;
        for(int i=n-1;i>=0;i--){
            currBuy= Math.max(-prices[i]+aheadSell,aheadBuy);
            currSell= Math.max(prices[i]+aheadBuy, aheadSell);
            aheadBuy= currBuy;
            aheadSell= currSell;
        }
        return aheadBuy;   
    }
}