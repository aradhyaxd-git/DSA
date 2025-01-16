/*PROBLEM STATEMENT:
 * A 0-indexed array derived with length n is derived by computing the bitwise XOR (⊕) 
 * of adjacent values in a binary array original of length n.

Specifically, for each index i in the range [0, n - 1]:

If i = n - 1, then derived[i] = original[i] ⊕ original[0].
Otherwise, derived[i] = original[i] ⊕ original[i + 1].
Given an array derived, your task is to determine whether there exists a valid 
binary array original that could have formed derived.

Return true if such an array exists or false otherwise.

A binary array is an array containing only 0's and 1's
 */
 /****************************************************************************************************************/

//Approach1 
 /* WE KNOW , x ^ y = z
            xorring y both sides , then xorring x both sides, we will eventually get

         x^y=z   z^y=x   z^x=y
    Now,
        -> iF a^b=0 means a,b can either be both 0,0 or 1,1

    In this approach, We take 2 cases
        -> start by taking original[0]as 0 
        -> check the last by original[n-1]^original[0]= derived[n-1]
        */
        class Solution1 {
            public boolean doesValidArrayExist(int[] derived) {
                int n= derived.length;
                int original[]= new int[n];
                //Start with taking 0
                original[0]=0;
                for(int i=0;i<n-1;i++){
                    original[i+1]= original[i]^derived[i];
                }
                if((original[n-1]^original[0])==derived[n-1]) return true;
                
                //Now take original[0]=1
                original[0]=1;
                for(int i=0;i<n-1;i++){
                    original[i+1]= original[i]^derived[i];
                }
               if((original[n-1]^original[0])==derived[n-1]) return true;
        
                return false;
            }
        }
        
        /*Approach:2 Let original= {a,b,c,d,e}
                         derived = {0,1,0,1,1}
                                { a^b , b^c , c^d , d^e , e^a}
                                sabhi ki frequency 2 aa rhi
            SO we arrive at a conclusion that, derived array 
            tbhi possibly ban payegi
            --> If original has xor==0
            */
        class Solution {
            public boolean doesValidArrayExist(int[] derived) {
                int xorr=0;
                for(int num:derived)
                    xorr ^=num;
                return xorr==0; 
            }
        }