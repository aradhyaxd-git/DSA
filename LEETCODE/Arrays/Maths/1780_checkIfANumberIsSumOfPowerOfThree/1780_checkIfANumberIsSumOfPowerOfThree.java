/*1780. Check if Number is a Sum of Powers of Three

Given an integer n, return true if it is possible to represent n as the sum of
distinct powers of three. Otherwise, return false.

An integer y is a power of three if there exists an integer x such that y == 3x.

Example 1:

Input: n = 12
Output: true
Explanation: 12 = 31 + 32 */

//Approach 1: using simple maths of class 9
class Solution1 {
    public boolean checkPowersOfThree(int n) {
      int p=0;
      while((int)Math.pow(3,p)<=n){
        p++;
      }
      while(n>0){
        if(n>= (int)Math.pow(3,p)){
          n= n-(int)Math.pow(3,p);
        }
         if(n>= (int)Math.pow(3,p))
            return false;
          p--;
      }
      return true;    
    }
}

//Approach 2 Using ternary operation..  agar ternary mei 2aya mtlb wo power 2 bar ayi
//A)
class Solution {
    public boolean checkPowersOfThree(int n) {
      HashSet<Integer> set= new HashSet<>();
      while(n!=0){
        set.add(n%3);
        n/=3;
      }
      return !set.contains(2);
    }
}

//B)
class Solution2 {
    public boolean checkPowersOfThree(int n) {
      HashSet<Integer> set= new HashSet<>();
      while(n!=0){
        if(n%3==2) return false;
        n/=3;
      }
      return true;
    }
}

//APPROACH 3: USING RECURSION 
//TC: O(2^log3N)    SC= O(log3N)
class Solution4 {
    public boolean solve(int n,int p){
      if(n==0) return true;
      if((int)Math.pow(3,p)>n) return false;
      boolean p_ko_lelo= solve(n-(int)Math.pow(3,p),p+1);
      boolean p_ko_na_lo= solve(n,p+1);
      return p_ko_lelo||p_ko_na_lo;
    }
    public boolean checkPowersOfThree(int n) {
        return solve(n,0);
    }
}