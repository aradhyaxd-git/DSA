/* PROBLEM STATEMENT:
 * Given two positive integers num1 and num2, find the positive integer x such that:

x has the same number of set bits as num2, and
The value x XOR num1 is minimal.
Note that XOR is the bitwise XOR operation.

Return the integer x. The test cases are generated such that x is uniquely determined.
 */


/*APPROACH-1
Our goal is to minimise the XOR value b/w num1 and num2
while ensuring the result(x) has same set bits as num2

  Calculate set bits in both the num1,num2
Now:  1) If currSetBits(of num1) < requiredSetBits( of  num2)
            -> take x= num1, here, we only adddress x
            -> add set bits 
                -> identify the unset bits and set them to 1
                -> Do it from the LSB, and in each step, increase ith bit by 1
      2) If currSetBits > requiredSetBits 
            -> We need to lessen the set bits
            -> No again., from the LSB, 
                -> if the ith bit is set, unset it

        TC: O(log(n)) SC:O(1)
        */
        class Solution1 {
            public boolean isSet(int x, int bit){
                return (x&(1<<bit))!=0;
            }
            
            public int setBit(int x,int bit){
                return x|(1<<bit);
            }
        
            public int unSetBit(int x,int bit){
                return x& ~(1<<bit);
            }
        
            public int minimizeXor(int num1, int num2) {
                int x= num1;
                int requiredSetBit= Integer.bitCount(num2);
                int currentSetBit= Integer.bitCount(num1);
                int bit=0;
                if(currentSetBit<requiredSetBit){
                    while(currentSetBit<requiredSetBit){
                        if(!isSet(x,bit)){
                            x = setBit(x,bit);
                            currentSetBit++;
                        }
                        bit++;
                    }
                }
        
                else if(currentSetBit>requiredSetBit){
                    while(currentSetBit>requiredSetBit)
                    {
                        if(isSet(x,bit)){
                            x= unSetBit(x,bit);
                            currentSetBit--;
                        }
                        bit++; 
                    }
        
                }
                return x;
            }
        }
        
        
        /*Approach-2: To do the following things, just take x=0
         WE know x has total of 32 bits
          x ki set bits kaha dalengay
                -> aisi jagah such that num1 ka MS set bit gets cancelled out 
                    eg: num1= 0 1 1 0 and num2= 0 1 1 1
                        x=    0 0 0 0 
                        so, Here, set the bits from MSB to 1, inorder to reduce the number value
                        x=    0 1 1 0 , 
                        Now , We have still requiredSetBit as 1 left 
                -> Now, num1 ke LSB se unSetBit dhundho and Set them
                        x=    0 1 1 1
        
                        so, 0 1 1 0 
                        ^   0 1 1 1
                        =   0 0 0 1
                        which is the minimum  */
        
        class Solution {
            public boolean isSet(int x, int bit){
                return (x&(1<<bit))!=0;
            }
            
            public int setBit(int x,int bit){
                return x|(1<<bit);
            }
        
            public int unSetBit(int x,int bit){
                return x& ~(1<<bit);
            }
        
            public boolean isUnset(int x, int bit){
                return (x&(1<<bit))==0;
            }
        
            public int minimizeXor(int num1, int num2) {
                int requiredBit= Integer.bitCount(num2);
                int x=0;
                for(int bit=31;bit>=0 && requiredBit>0 ;bit--){
                    if(isSet(num1,bit)){
                       x= setBit(x,bit);
                       requiredBit--;
                    }
                }
        
                for(int bit=0;bit<32 && requiredBit>0 ; bit++){
                    if(isUnset(num1,bit)){
                        x= setBit(x,bit);
                        requiredBit--;
                    }
                }
                return x;
            }
        }
        