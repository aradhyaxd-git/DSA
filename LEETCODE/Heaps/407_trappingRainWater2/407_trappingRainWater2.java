class Solution {
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->a[0]-b[0]);
        int m= heightMap.length;
        int n= heightMap[0].length;
        boolean visited[][]= new boolean[m][n];

        for(int row=0;row<m;row++){
            visited[row][0]=true;
            pq.offer(new int[]{heightMap[row][0],row,0});
            visited[row][n-1]=true;
            pq.offer(new int[]{heightMap[row][n-1],row,n-1});
        }
         for(int col=0;col<n;col++){
            visited[0][col]=true;
            pq.offer(new int[]{heightMap[0][col],0,col});
            visited[m-1][col]=true;
            pq.offer(new int[]{heightMap[m-1][col],m-1,col});
        }

        int[][] directions= {{0,1},{0,-1},{1,0},{-1,0}};
        int water=0;
        
        while(!pq.isEmpty()){
            int cell[]= pq.poll();
            int currentHeight= cell[0];
            int i= cell[1];
            int j= cell[2];
            for(int dir[]:directions){
                int i_ = i+ dir[0];
                int j_ = j+ dir[1];
                if(i_ >=0 && j_ >=0 && i_<m && j_ <n && !visited[i_][j_]){
                    water+= Math.max(0, currentHeight- heightMap[i_][j_]);
                    pq.offer(new int[]{Math.max(currentHeight,heightMap[i_][j_]),i_,j_});
                    visited[i_][j_]=true;
                }
            }
        }
        return water; 
    }
}