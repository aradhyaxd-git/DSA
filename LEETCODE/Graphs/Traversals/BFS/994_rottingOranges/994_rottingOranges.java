class Solution {
    public int orangesRotting(int[][] grid) {
        int m= grid.length;
        int n= grid[0].length;
        int freshCount=0;
        Queue<int[]> queue= new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2)
                    queue.offer(new int[]{i,j});
                else if(grid[i][j]==1)
                    freshCount++;
            }
        }
        if(freshCount==0) return 0;
        int directions[][]= {{1,0},{-1,0},{0,1},{0,-1}};
        int minutes=0;
        while(!queue.isEmpty()){
            int size= queue.size();
            while(size-- !=0){
                int curr[]= queue.poll();
                int i= curr[0];
                int j= curr[1];
                for(int dir[]:directions){
                    int i_ = i + dir[0];
                    int j_ = j + dir[1];
                    if(i_ >=0 && j_ >=0 && i_ <m && j_<n && grid[i_][j_]==1){
                        freshCount--;
                        grid[i_][j_]=2;
                        queue.offer(new int[]{i_,j_});
                    }
                }
            }
            minutes++;
        }
        return freshCount==0?(minutes-1):-1;
    }
}