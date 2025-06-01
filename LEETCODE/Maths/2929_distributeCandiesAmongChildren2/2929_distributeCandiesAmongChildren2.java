/*2929. Distribute Candies Among Children II

You are given two positive integers n and limit.

Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit candies.

 

Example 1:

Input: n = 5, limit = 2
Output: 3
Explanation: There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
Example 2:

Input: n = 3, limit = 3
Output: 10
Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).
 

Constraints:

1 <= n <= 106
1 <= limit <= 106 */


//Approach 1: Recursion
class Solution1 {
    public long distributeCandies(int n, int limit) {
        return solve(0,n,limit);
    }
    public long solve(int countChild, int n, int limit){
        if(countChild==3){ //jaise hi countOf children 3 ke barabar hogya wese hi check karo
                //ki kya, jitni candies assign kari hai wo exhaust hogyi
            if(n==0){
                return 1;
            }
            return 0;
        }

        long ways=0;
        /* we take Math.min(n,limit) because 
        if we take a case of n=5, limit=10 ---> 5 hi de sakte hai at max kyuki 5 hi candies hai */
        for(int assign=0;assign<=Math.min(n,limit);assign++){
            
            ways+= solve(countChild+1,n-assign,limit);
        }
        return ways;
    }

}

//approach 2: gave tle at 500 cases
//TC: O(n^3)
class Solution2 {
    public long distributeCandies(int n, int limit) {
        long ways=0;
        for(int ch1=0;ch1<=Math.min(n,limit);ch1++){
            for(int ch2=0;ch2<=Math.min(n-ch1,limit);ch2++){
                for(int ch3=0;ch3<=Math.min(n-ch1-ch2,limit);ch3++){
                    if(ch1+ch2+ch3==n){
                        ways++;
                    }
                }
            }
        }
        return ways;
        
    }
}


//approach 3: O(n^2)
class Solution3 {
    public long distributeCandies(int n, int limit) {
        long ways=0;
        for(int ch1=0;ch1<=Math.min(n,limit);ch1++){
            for(int ch2=0;ch2<=Math.min(n-ch1,limit);ch2++){
                int ch3= n-ch1-ch2;
                if(ch1+ch2+ch3==n && ch3<=limit){
                     ways++;
                }
            }
        }
        return ways;
    }
}

class Solution {
    /*The idea of the approach comes from , first taking and thinking question as, what if i had only one child
    --> eg: n=5, limit=3
        min the child can get=0;
        max the child can get=limit

        total elements=[a,b]= b-a+1;

    
    WHAT IF I HAD 2 CHILD 
         ch1 ka min candies= n-limit
         ch1 ka max candies= limits

    NOW COMES 3 CHILDREN ---> ISKO 2 CHILD KI TRAH TREAT KARO 

    CH1                     CH2             CH3
    give x to ch1       (for both ch2,ch3) --> we will have N= n-x

    min= n-2*limit
    max= limit */


    public long distributeCandies(int n, int limit) {
        long ways=0;

        int minChild1= Math.max(0,n-2*limit);
        int maxChild1= Math.min(n,limit);

        for(int i=minChild1;i<=maxChild1;i++){ //fixing child 1 assignmet of candies
            int N= n-i;

            int minChild2= Math.max(0,N-limit);
            int maxChild2= Math.min(N,limit);

            ways+= (maxChild2-minChild2+1);

        }
        return ways;
    }
}