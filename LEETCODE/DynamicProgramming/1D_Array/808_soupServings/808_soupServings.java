//Approach 1: Recursion
class Solution1 {
    int operations[][]={{100,0},{75,25},{50,50},{25,75}};
    public double soupServings(int n) {

        return solve(n,n);
        
    }
    public double solve(int a, int b){

        if(a<=0 && b<=0) return 0.5;

        if(a<=0) return 1.0;

        if(b<=0) return 0.0;

        double prob=0.0;

        for(int op[]:operations){
            int a_taken= op[0];
            int b_taken= op[1];

            prob+= solve(a-a_taken, b-b_taken);
            
        }

        return prob*0.25;
    }
}

//APproach 2: Memoisation --> it will give memory limit exceeed
class Solution2 {
    double dp[][];
    int operations[][]={{100,0},{75,25},{50,50},{25,75}};
    public double soupServings(int n) {
        dp= new double[n+1][n+1];

        for(double row[]:dp)
            Arrays.fill(row,-1);

        return solve(n,n);
        
    }
    public double solve(int a, int b){

        if(a<=0 && b<=0) return 0.5;

        if(a<=0) return 1.0;

        if(b<=0) return 0.0;

        if(dp[a][b]!=-1) return dp[a][b];

        double prob=0.0;

        for(int op[]:operations){
            int a_taken= op[0];
            int b_taken= op[1];

            prob+= solve(a-a_taken, b-b_taken);
            
        }

        return dp[a][b]=prob*0.25;
    }
}

//Appraoch 3: for large N--> probability will be 1
class Solution {
    double dp[][];
    int operations[][]={{100,0},{75,25},{50,50},{25,75}};
    public double soupServings(int n) {

        if(n>=5000) return 1;

        dp= new double[n+1][n+1];

        for(double row[]:dp)
            Arrays.fill(row,-1);

        

        return solve(n,n);
        
    }
    public double solve(int a, int b){

        if(a<=0 && b<=0) return 0.5;

        if(a<=0) return 1.0;

        if(b<=0) return 0.0;

        if(dp[a][b]!=-1) return dp[a][b];

        double prob=0.0;

        for(int op[]:operations){
            int a_taken= op[0];
            int b_taken= op[1];

            prob+= solve(a-a_taken, b-b_taken);
            
        }

        return dp[a][b]=prob*0.25;
    }
}