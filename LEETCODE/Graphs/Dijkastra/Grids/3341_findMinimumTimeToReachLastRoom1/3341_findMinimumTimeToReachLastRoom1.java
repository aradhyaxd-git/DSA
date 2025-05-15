/*There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds after 
which the room opens and can be moved to. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. 
Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.

 

Example 1:

Input: moveTime = [[0,4],[4,4]]

Output: 6

Explanation:

The minimum time required is 6 seconds.

At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.
Example 2:

Input: moveTime = [[0,0,0],[0,0,0]]

Output: 3

Explanation:

The minimum time required is 3 seconds.

At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.
Example 3:

Input: moveTime = [[0,1],[1,2]]

Output: 3 */


class Solution {
    public int directions[][]={{1,0},{-1,0},{0,1},{0,-1}};
    public boolean isValid(int x,int y, int n,int m){
        return x<n && y<m && x>=0 && y>=0;
    }
    public int minTimeToReach(int[][] moveTime) {
        int n= moveTime.length;
        int m= moveTime[0].length;
        int dist[][]= new int[n][m];
        for(int rows[]:dist)
            Arrays.fill(rows,Integer.MAX_VALUE);
       dist[0][0] = 0;

        PriorityQueue<int[]>pq= new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        pq.offer(new int[]{0,0,0});
        while(!pq.isEmpty()){
            int curr[]= pq.poll();
            int x= curr[0];
            int y= curr[1];
            int time= curr[2];
            if(x==n-1 && y==m-1) return time;
            if(time>dist[x][y]) continue;
            for(int dir[]:directions){
                int x_= x+dir[0];
                int y_= y+dir[1];
                if(isValid(x_,y_,n,m)){
                    int wait=Math.max(moveTime[x_][y_]-time,0);
                    int arrivalTime= time+wait+1;
                    if(arrivalTime<dist[x_][y_]){
                        dist[x_][y_]=arrivalTime;
                        pq.offer(new int[]{x_,y_,arrivalTime});
                    }
                }
            }
        }
        return -1; 
    }
}