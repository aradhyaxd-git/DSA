/*2141. Maximum Running Time of N Computers

You have n computers. You are given the integer n and a 0-indexed integer array batteries where 
the ith battery can run a computer for batteries[i] minutes. You are interested in running all n 
computers simultaneously using the given batteries.

Initially, you can insert at most one battery into each computer. After that and at any integer 
time moment, you can remove a battery from a computer and insert another battery any number of times. 
The inserted battery can be a totally new battery or a battery from another computer. You may assume that the removing and inserting processes take no time.

Note that the batteries cannot be recharged.

Return the maximum number of minutes you can run all the n computers simultaneously.
 

Example 1:


Input: n = 2, batteries = [3,3,3]
Output: 4
Explanation: 
Initially, insert battery 0 into the first computer and battery 1 into the second computer.
After two minutes, remove battery 1 from the second computer and insert battery 2 instead. Note that battery 1 can still run for one minute.
At the end of the third minute, battery 0 is drained, and you need to remove it from the first computer and insert battery 1 instead.
By the end of the fourth minute, battery 1 is also drained, and the first computer is no longer running.
We can run the two computers simultaneously for at most 4 minutes, so we return 4.
*/


class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long low=0;
        long sum=0;

        for(int b:batteries) sum+=b;

        long high= sum/n;

        long result=0;

        while(low<=high){
            long mid_minutes= low+ (high-low)/2;
            if(isPossible(batteries,n,mid_minutes)){
                result=mid_minutes;
                low=mid_minutes+1;
            }
            else
                high=mid_minutes-1;

        }
        return result;
    }

    public boolean isPossible(int batteries[],int n,long mid_minutes){
        long target_minutes= n*mid_minutes;
        long sum=0;
        for(int i=0;i<batteries.length;i++){

            sum+= (Math.min(batteries[i],mid_minutes));
            if(sum>=target_minutes) return true;
        }
        
        return false;

    }
}

