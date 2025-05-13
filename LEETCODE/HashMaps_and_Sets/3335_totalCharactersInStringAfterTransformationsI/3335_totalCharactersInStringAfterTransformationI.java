/* Total Characters in String After Transformations I

You are given a string s and an integer t, representing the number of transformations to perform. 
In one transformation, every character in s is replaced according to the following rules:

If the character is 'z', replace it with the string "ab".
Otherwise, replace it with the next character in the alphabet. For example, 'a' is replaced with 'b', 'b' is replaced with 'c', and so on.
Return the length of the resulting string after exactly t transformations.

Since the answer may be very large, return it modulo 109 + 7. */


//basic idea ye hai ki bas frequency store karke transform kar rhe 
class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int mod= 1_000_000_007;
        char x[]= s.toCharArray();
        int freq[]= new int[26];
        //step 1: har character ki frequency store krdo
        for(char ch: s.toCharArray()){
            freq[ch-'a']++;
        }
        //step 2: utni baar operation karna hai jitni baar t rhega
        for(int count=1;count<=t;count++){
            //step 3: create a temporary array: 
                    //--> har baar isko update krengay

           int tempF[]= new int[26];

           for(int i=0;i<26;i++){
                char ch= (char)(i+'a');
                if(ch!='z'){ //step 4: agar character z nahi hai to bas frequency badhao next letter ki 
                    tempF[i+1]=(tempF[i+1]+freq[i])%mod;
                } 
                else{ //step 5: agar letter z hai to, a and b ki frequency badha do
                    tempF[0]=(tempF[0]+freq[i])%mod;
                    tempF[1]=(tempF[1]+freq[i])%mod;
                }
            }
            freq=tempF;
        }

        //step 6: collect all frequencies in one
        int ans=0;
        for(int i=0;i<26;i++){
            ans= (freq[i]+ans)%mod;
        }
        return ans%mod;
        
    }
}