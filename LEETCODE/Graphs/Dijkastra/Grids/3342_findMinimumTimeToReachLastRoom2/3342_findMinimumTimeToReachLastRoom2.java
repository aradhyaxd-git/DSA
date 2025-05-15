/*Medium
Topics
Companies
Hint
There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you 
can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between
 adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.

 

Example 1:

Input: moveTime = [[0,4],[4,4]]

Output: 7

Explanation:

The minimum time required is 7 seconds.

At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.
Example 2:

Input: moveTime = [[0,0,0,0],[0,0,0,0]]

Output: 6

Explanation:

The minimum time required is 6 seconds.

At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
At time t == 3, move from room (1, 1) to room (1, 2) in one second.
At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.
Example 3:

Input: moveTime = [[0,1],[1,2]]

Output: 4 */

//is question mei, phele +1 hoga fir +2, like alternate waitingTime lagega
class Solution {
    public int directions[][]={{1,0},{-1,0},{0,1},{0,-1}};
    public boolean isValid(int x,int y, int n,int m){
        return x<n && y<m && x>=0 && y>=0;
    }
    public int minTimeToReach(int[][] moveTime) {
        int n= moveTime.length;
        int m= moveTime[0].length;
        int dist[][][]= new int[n][m][2]; //ek extra state lia hai, to store ki kya add hoga alternate moves mei 1 ya 2
        //step1: fill everything with infinity
        for(int dista[][]:dist){
            for(int rows[]:dista){
                Arrays.fill(rows,Integer.MAX_VALUE);
            }
        }
        //step 2: start from the 0,0 of the matrix
       dist[0][0][0] = 0;
        PriorityQueue<int[]>pq= new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        //next move 1 hoga .. ni to 2 hoga, but shuru mei to 1 hi hoga
        pq.offer(new int[]{0,0,0,1}); //next move should be 1
        while(!pq.isEmpty()){
            int curr[]= pq.poll();
            int x= curr[0];
            int y= curr[1];
            int time= curr[2];
            int nextMoveTime= curr[3];
            if(x==n-1 && y==m-1) return time;
            if(time>dist[x][y][nextMoveTime==1?0:1]) continue;
            for(int dir[]:directions){
                int x_= x+dir[0];
                int y_= y+dir[1];
                if(isValid(x_,y_,n,m)){
                    int wait=Math.max(moveTime[x_][y_]-time,0);
                    int arrivalTime= time+wait+nextMoveTime;
                    int nextNewMoveTime= nextMoveTime==1?2:1;
                    if(nextMoveTime==1){
                        if(arrivalTime<dist[x_][y_][0]){
                        dist[x_][y_][0]=arrivalTime;
                        pq.offer(new int[]{x_,y_,arrivalTime,nextNewMoveTime});
                        }
                    }

                    else{
                        if(arrivalTime<dist[x_][y_][1]){
                         dist[x_][y_][1]=arrivalTime;
                            pq.offer(new int[]{x_,y_,arrivalTime,nextNewMoveTime});
                        }
                    }
                }
            }
        }
        return -1; 
    }
}