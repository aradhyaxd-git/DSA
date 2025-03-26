/* Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s 
such that every character in t (including duplicates) is included in the window. 
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
//APPROACH_1 : USING HASHMAPS + SLIDING WINDOW */
class Solution1 {
    public String minWindow(String s, String t) {
      int startIndex=-1,right=0,left=0; int n= s.length();
      int formed=0; int mini=Integer.MAX_VALUE;
      HashMap<Character,Integer> tmap= new HashMap<>();
      for(char ch: t.toCharArray()){
        tmap.put(ch,tmap.getOrDefault(ch,0)+1);
      }
      //tmap ki hi frequency chahiye and we dont want the exact order of elements, hence we used map
      int required= tmap.size(); // jitna temp ka size hoga utna hi required hoga
      HashMap<Character,Integer> smap= new HashMap<>();
      while(right<n){
        char rightCh= s.charAt(right);
        smap.put(rightCh,smap.getOrDefault(rightCh,0)+1);
        if(tmap.containsKey(rightCh) && smap.get(rightCh).intValue()==tmap.get(rightCh).intValue()){
          formed++;
        }
        while(formed==required){
          if(right-left+1<mini){
            mini= right-left+1;
            startIndex=left;
          }
          char leftCh= s.charAt(left);
          smap.put(leftCh,smap.get(leftCh)-1);
          /*Why do we check tFreq[leftChar] > 0?
             Why do we check sFreq[leftChar] < tFreq[leftChar]?
                If removing leftChar causes the window to lack a required character, we decrease formed.
                ✔️ This ensures we stop shrinking when the window is no longer valid.*/
          if(tmap.containsKey(leftCh) && smap.get(leftCh)<tmap.get(leftCh)){
            formed--;
          }
          left++;
        }
        right++;
      }
      return mini==Integer.MAX_VALUE?"":s.substring(startIndex,startIndex+mini);      
    }
}

//APPROACH 2: OPTIMAL( USE FREQUENCY ARRAY )
class Solution {
    public String minWindow(String s, String t) {
      int tFreq[]= new int[128];
      int sFreq[]= new int[128];
      for(char ch: t.toCharArray()){
        tFreq[ch]++;
      }
      int min=Integer.MAX_VALUE,startIndex=-1,right=0,left=0;
      int n= s.length();
      int formed=0;
      int required=t.length();
      while(right<n){
        char ch= s.charAt(right);
        sFreq[ch]++;
        if(tFreq[ch]>0 && tFreq[ch]>=sFreq[ch]){
          formed++;
        }
        while(formed==required){
          char leftCh= s.charAt(left);
          if(right-left+1<min){
            min= right-left+1;
            startIndex=left;
          }
          sFreq[leftCh]--;
          if(tFreq[leftCh]>0 && tFreq[leftCh]>sFreq[leftCh]){
            formed--;
          }
          left++;
        }
        right++;
      }
      return min==Integer.MAX_VALUE?"":s.substring(startIndex,startIndex+min);
    }
}