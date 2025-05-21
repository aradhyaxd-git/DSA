/*You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 
0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. 
The difficulty of a day is the maximum difficulty of a job done on that day.

You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1. */



class Solution1 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n= jobDifficulty.length;

        if(n<d) return -1; //if we got more days tham jobs then, we will still have a free day

        return solve(0,d,n,jobDifficulty);
        
    }

    public int solve(int idx, int d, int n,int[] jobDifficulty ){
        if(d==1){ //means ek hi din bacha hai.. so... do all jobs
        //in that one day and find the maximum among those... hum logo ko wahi return karwana padega 
            int maxD= jobDifficulty[idx];
            for(int i=idx;i<n;i++){
                maxD= Math.max(maxD, jobDifficulty[i]);
            }
            return maxD;
        }

        int maxD= jobDifficulty[idx];
        int finalResult= Integer.MAX_VALUE;

        /*Try with all possibilities now

        Take idx wala job in first day
        take {idx,idx+1} wala job in first day
        take {idx,idx+2} wala job in first day
        .... and so on 

        Then find the optimal one, among all the results */

        for(int i=idx;i<=n-d;i++){
            maxD= Math.max(maxD, jobDifficulty[i]);
            int result= maxD + solve(i+1,d-1,n,jobDifficulty);
            finalResult= Math.min(result,finalResult);
        }
        return finalResult;

    }
}
class Solution {
    int dp[][]= new int[301][11];
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n= jobDifficulty.length;
        for(int row[]:dp) Arrays.fill(row,-1);


        if(n<d) return -1;

        return solve(0,d,n,jobDifficulty);
        
    }

    public int solve(int idx, int d, int n,int[] jobDifficulty ){
        if(dp[idx][d]!=-1) return dp[idx][d];

        if(d==1){ //means ek hi din bacha hai.. so... do all jobs
        //in that one day and find the maximum among those... hum logo ko wahi return karwana padega 
            int maxD= jobDifficulty[idx];
            for(int i=idx;i<n;i++){
                maxD= Math.max(maxD, jobDifficulty[i]);
            }
            return dp[idx][d]=maxD;
        }

        int maxD= jobDifficulty[idx];
        int finalResult= Integer.MAX_VALUE; //this will store minimum difficult of a job schedule

        /*Try with all possibilities now

        Take idx wala job in first day
        take {idx,idx+1} wala job in first day
        take {idx,idx+2} wala job in first day
        .... and so on 

        Then find the optimal one, among all the results */

        for(int i=idx;i<=n-d;i++){
            // <=n-d tak kyu chala hai??
                //--> aakhri jab din bachega to us din saari jobs consecutively karni hogi
            maxD= Math.max(maxD, jobDifficulty[i]);
            int result= maxD + solve(i+1,d-1,n,jobDifficulty);
            finalResult= Math.min(result,finalResult);
        }
        return dp[idx][d]=finalResult;

    }
}
        
    