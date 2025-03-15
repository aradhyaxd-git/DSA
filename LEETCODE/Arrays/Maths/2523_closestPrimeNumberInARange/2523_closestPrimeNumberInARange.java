/*2523. Closest Prime Numbers in Range

Given two positive integers left and right, find the two integers num1 and num2 such that:

left <= num1 < num2 <= right .
Both num1 and num2 are prime numbers.
num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.

Return the positive integer array ans = [num1, num2]. If there are multiple pairs satisfying 
these conditions, return the one with the smallest num1 value. 
If no such numbers exist, return [-1, -1]. */

class Solution {
    public boolean [] sieveHelp(int right){
      boolean isPrime[]= new boolean[right+1];
      Arrays.fill(isPrime, true);
      isPrime[0]=false;
      isPrime[1]=false;
      for(int i=2;i*i<=right;i++){
        if(isPrime[i]==true){
          for(int j=i*i;j<=right;j+=i){
            isPrime[j]=false;
          }
        }
      }
      return isPrime;
    }
    public int[] closestPrimes(int left, int right) {
       boolean[] isPrime = sieveHelp(right); 
      List<Integer> primes = new ArrayList<>();
      for (int i = left; i <= right; i++) {
          if (isPrime[i]) primes.add(i);
        }
      int ans[]= new int[2];
      ans[0]=-1;ans[1]=-1;
      if (primes.size()<2) return ans;
      int minDiff= Integer.MAX_VALUE;
      for(int i=1;i<primes.size();i++){
        int diff= primes.get(i)-primes.get(i-1);
        if(diff<minDiff){
          minDiff=diff;
          ans[0]=primes.get(i-1);
          ans[1]= primes.get(i);
        }
      }
      return ans;
    }
}

