/*PROBLEM STATEMENT
 * You are given the customer visit log of a shop represented by a 0-indexed string customers 
 * consisting only of characters 'N' and 'Y':

1) if the ith character is 'Y', it means that customers come at the ith hour
2) whereas 'N' indicates that no customers come at the ith hour.

If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:

1) For every hour when the shop is open and no customers come, the penalty increases by 1.
2) For every hour when the shop is closed and customers come, the penalty increases by 1.
Return the earliest hour at which the shop must be closed to incur a minimum penalty.

Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
*/
 /********************************************************************************************** */

 //APPROACH 1: BRUTEFORCE
class Solution2 {
    public int bestClosingTime(String customers) {
        int n= customers.length();
        char x[]= customers.toCharArray();
        int minPenalty= Integer.MAX_VALUE;
        int minHour= Integer.MAX_VALUE;
        int countN=0;
        /*STEPS : for (i=0 --> n){
                        --> j = i-1 to 0 open wale ki calculate karo
                        --> j = i to n-1 closed wale ki calculate karo
                Basically here, we Are just stimulating the process */
        
        for(int i=0;i<n;i++){
            if(x[i]=='N') countN++;
            int penalty=0;
            int j=i-1;
            //Calculating for open ones; 
            while(j>=0){
                if(x[j]=='N')
                    penalty++;
                j--;
            }
            //now calculte for closed one
             j=i;
            while(j<n){
                if(x[j]=='Y')
                    penalty++;
                j++;
            }
            if(penalty<minPenalty){
                minPenalty=penalty;
                minHour=i;       
            }
        }

        if(countN<minPenalty){
            minHour=n;
        }
        return minHour;
    }
}


//Approach2:  Better Approach:
/* COST = Y Y Y N Y N Y
prefixN=  0 0 0 0 1 1 2 2  ->
suffixN=  5 4 3 2 2 1 1 0  <- 
add+      _________________
          5 4 3 2 2 1 1 0  
*/
class Solution1 {
    public int bestClosingTime(String customers) {
        int n= customers.length();
        int prefixN[]= new int[n+1];
        int suffixY[]= new int[n+1];
        for(int i=1;i<=n;i++){
            prefixN[i]= prefixN[i-1] + (customers.charAt(i-1)=='N'?1:0);
        }
        for(int i=n-1;i>=0;i--){
            suffixY[i]= suffixY[i+1] + (customers.charAt(i)=='Y'?1:0);
        }
        int minP= Integer.MAX_VALUE;
        int minHour= Integer.MAX_VALUE;
        for(int i=0;i<=n;i++){
            int totalPenalty= prefixN[i]+suffixY[i];
            if(totalPenalty<minP){
                minP= totalPenalty;
                minHour=i;
            }
        }
        return minHour;
    }
}
//Approach:3
class Solution {
    public int bestClosingTime(String customers) {
        /*In this approach, WE assume that at the 0th hour, our shop is closed */
        int n= customers.length();
        int totalY=0;
        /*STEP 1: Calculate totalY -> this represents the initial penalty 
                                      if the shop closes at hour 0 */
        for(char ch: customers.toCharArray()){
            if(ch=='Y'){
                totalY++;
            }
        }
        int minHour=0;
        int penalty=totalY;
        int minPenalty=penalty;
        
        for(int i=0;i<n;i++){
            //Now assume we open the shop
            /*STEP 2: If we are at current hour = Y 
                            -> it represents customer is no longer penalised, therfore
                                decrement penalty by 1 */
            if(customers.charAt(i)=='Y'){
                penalty--;
            }
            else {
                penalty++; /*STEP 3: Since our shop is open, when we encounter N , we incremen the penalty count */
            }
            if(minPenalty>penalty){
                minPenalty=penalty;
                minHour=i+1; /*i+1 as , at this index, our shop will get closed */
            } 
        }
        return minHour;
        
    }
}