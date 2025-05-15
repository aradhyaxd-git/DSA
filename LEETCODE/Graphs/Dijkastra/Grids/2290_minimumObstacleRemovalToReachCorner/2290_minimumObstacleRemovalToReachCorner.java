/*You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:

0 represents an empty cell,
1 represents an obstacle that may be removed.
You can move up, down, left, or right from and to an empty cell.

Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).

  */


//Appraoch 1: Using classic dijkastra: {Time Complexity : O(V Log E)}
class Solution1 {
    public int directions[][]={{0,1},{0,-1},{1,0},{-1,0}}; //for moving
    public boolean isValid(int x,int y,int m,int n){
        return x<m && y<n && x>=0 && y>=0;
    }
    public int minimumObstacles(int[][] grid) {
        int m= grid.length;
        int n= grid[0].length;
        PriorityQueue<int[]>pq= new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        //step 1: initialise the dist[][] matrix..
        //this will be used to calculate the cost
        int dist[][]= new int[m][n];
        /*Core Idea: Here , We use the concept of dijkastra Here, inorder to implemenet the above question
                     without changing the original graph, we are 
                     Finding the minimum weight using priority Queue, 
                        --> If there is no obstacles, then the new cost will always be 0
                        --> If there is an obstacle, then also, we will write cost+1... 
                        SO, the dijkastra handles the problems cleaner */
        for(int row[]:dist)
            Arrays.fill(row,Integer.MAX_VALUE);
        //cost for 0,0 is 0
        dist[0][0]=0;
        pq.offer(new int[]{0,0,0}); //u v cost

        while(!pq.isEmpty()){
            int curr[]= pq.poll();
            int x= curr[0];
            int y= curr[1];
            int cost=curr[2];
            if(x==m-1 && y==n-1) return cost; //jaise hi depth mei phocho, return cost
            if(cost>dist[x][y]) continue;
            for(int dir[]:directions){
                int x_= x+dir[0];
                int y_= y+dir[1];
                if(isValid(x_,y_,m,n)){
                   int actualCost=cost+grid[x_][y_];
                   if(dist[x_][y_]>actualCost){
                    dist[x_][y_]=actualCost;
                    pq.offer(new int[]{x_,y_,actualCost});
                   }
                }
            }
        }

        return -1;
        
    }
}

//Appraoch 2: 0-1 BFS

/*Concept: It is a special type of bfs used when your graph has only 0 and 1 as weights , you process cost 0 path first
           and cost1 path later 
        
        You want to minimise cost path from a source to all other nodes
        */
class Solution {
    public int directions[][]={{0,1},{0,-1},{1,0},{-1,0}}; //for moving
    public boolean isValid(int x,int y,int m,int n){
        return x<m && y<n && x>=0 && y>=0;
    }
    public int minimumObstacles(int[][] grid) {
        int m= grid.length;
        int n= grid[0].length;
        int dist[][]= new int[m][n];
        for(int row[]:dist) Arrays.fill(row,Integer.MAX_VALUE);

        dist[0][0]=0;

        Deque<int[]> dq= new ArrayDeque<>();
        dq.offerFirst(new int[]{0,0});

        while(!dq.isEmpty()){
            int curr[]= dq.pollFirst();
            int x= curr[0],y=curr[1];
            for(int dir[]:directions){
                int x_ = x+dir[0];
                int y_ = y+dir[1];
                if(isValid(x_,y_,m,n)){
                    int weight= grid[x_][y_];
                    if(dist[x_][y_]> dist[x][y]+weight){ //just like dijkastra logic
                        dist[x_][y_]=dist[x][y]+weight;
                        if(weight==0) //weight 0 hai to phele process hoga queue mei, hence dq.offerFirst
                            dq.offerFirst(new int[]{x_,y_});
                        else //1 weight wala baad mei process hoga, hence dq.offerLast
                            dq.offerLast(new int[]{x_,y_}); 
                        
                    }
                }
            }
        }
        return dist[m-1][n-1]==Integer.MAX_VALUE?-1:dist[m-1][n-1];
        
    }
}