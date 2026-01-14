/*Approach 1: Recursion
TC: O(2^n) , cant be memoised easily */
class Solution1 {
    int maxi=0;
    public void dfs(char x[], int i, int k, long val, int len){
        if(i==x.length){
            maxi= Math.max(maxi,len);
            return;
        }
        if(val>k){
            maxi=Math.max(maxi,len);
            return;
        }
        dfs(x,i+1,k,val,len);
        long newVal= val*2+(x[i]-'0');
        if(newVal<=k)
        dfs(x,i+1,k,newVal,len+1);
    }
    public int longestSubsequence(String s, int k) {
        int n= s.length();
        char x[]= s.toCharArray();
        dfs(x,0,k,0,0);
        return maxi;
    }
}


/* Approach 2: Greedy

--> Greedily start from the LSB , take as many zeroes as we can have because leading zeroes sse fark ni padta hai

Intuition: Since whenver we use to calculate the value, we start from LSB 

eg: 1001010     and k=5

start 1 0 0 1 0 1 0
                  i here,  value= 0
                  powerValue= 2^0 =1 
                  since it is a zero, we take it    --> len++;

                i comes here, value=1
                powerValue= 2^1=2 
                since it is a 1 and powerValue i.e 2<=5, yes
                then k=k-powerValue i.e k=5-2 ... 3
                len++

             i here, 0 doesnt affect anything , we will do len++;
            
            we will skip this one
        0 , at this index, just do len++
      0 again--> do len++; 
      */


class Solution {
    public int longestSubsequence(String s, int k) {
        
        int n= s.length();
        int powerValue=1; //this represents the current power of 2(LSB First) : 1,2,4,8,16...

        int len=0; //this stores the length of longest valid subsequence from the back 


//we go from LSB to MSB, least significant is on right 
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i)=='0'){
                //always include 0 , because it doesnt increase the numeric value of the number,-=> we can have as many laeding zeroes as we want

                len++; //blindly len ++ kardo kyuku fark ni padta hai
            }
            //now comes 1, rightmost 1 can add to 2, 4 , 8 etc

            //we only include 1's actual value if total value<=k


            else if(powerValue<=k){
                //instead of maintaining an answer --> we greedily deduct powerValue from k
                k=k-powerValue;
                len++;
            }

            //after every bit position, we move to the next higher power of 2 
            //but we only do this if the nexxt power is still <=k
            if(powerValue<=k){
                powerValue=powerValue<<1;
            }
        }
        return len;
    }
}