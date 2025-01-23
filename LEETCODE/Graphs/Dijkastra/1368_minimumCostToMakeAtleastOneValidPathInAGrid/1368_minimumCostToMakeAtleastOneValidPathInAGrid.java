class Solution {
    private int [][] directions= {{0,1},{0,-1},{1,0},{-1,0}};
    private int m,n;
    public int minCost(int[][] grid) {
        m= grid.length;
        n= grid[0].length;
        boolean visited[][]= new boolean[m][n];
        return dfs(0,0,grid,visited,0);     
    }
    public int dfs(int i,int j, int[][]grid, boolean [][] visited, int cost){
        if(i==m-1 && j==n-1)
            return cost;
        visited[i][j]=true;
        int minCost= Integer.MAX_VALUE;
        for(int dir=0;dir<=3;dir++){
            int new_i = i + directions[dir][0];
            int new_j = j + directions[dir][1];
            if(new_i>=0 && new_i < m && new_j>=0 && new_j < n && !visited[new_i][new_j]){
                int directionCost=0;
                if((grid[i][j]==1 && dir!=0)|| (grid[i][j]==2 && dir!=1)|| (grid[i][j]==3 && dir!=2)|| (grid[i][j]==4 && dir!=3)){
                    directionCost=1;
                }
                int newCost= cost + directionCost;
                minCost= Math.min(minCost, dfs(new_i, new_j , grid , visited , newCost));
            }
        }
        visited[i][j]=false;
        return minCost;
    }
}