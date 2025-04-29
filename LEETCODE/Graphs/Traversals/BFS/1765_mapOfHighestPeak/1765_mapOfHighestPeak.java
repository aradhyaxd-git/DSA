class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m= isWater.length;
        int n= isWater[0].length;
        Queue<int[]> queue= new LinkedList<>();
        int height[][]= new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(isWater[i][j]==1){
                    queue.offer(new int[]{i,j});
                    height[i][j]=0;
                }
                else{
                    height[i][j]=-1;
                }
            }
        }
        int directions[][]={{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()){
            int size= queue.size();
            while(size-- !=0){
                int curr[]= queue.poll();
                int i= curr[0];
                int j= curr[1];
                for(int dir[]: directions){
                    int i_ = i+ dir[0];
                    int j_ = j+ dir[1];
                    if(i_>=0 && j_>=0 && i_ < m && j_ <n && height[i_][j_]==-1){
                        height[i_][j_]= 1 + height[i][j];
                        queue.offer(new int[]{i_,j_});
                    }
                }
            }
        }
        return height;  
    }
}