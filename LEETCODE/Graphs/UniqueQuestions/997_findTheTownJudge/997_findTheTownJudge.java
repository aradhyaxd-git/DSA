class Solution {
    public int findJudge(int n, int[][] trust) {
        int outdegree[]= new int[n+1];
        int indegree[]= new int[n+1];
        for(int t[]:trust){
            int banda1= t[0];
            int banda2= t[1];
            outdegree[banda1]++;
            indegree[banda2]++;
        }
        for(int i=1;i<=n;i++){
            if(indegree[i]==n-1 && outdegree[i]==0){
                return i;
            }
        }
        //
        return -1;
    }
}