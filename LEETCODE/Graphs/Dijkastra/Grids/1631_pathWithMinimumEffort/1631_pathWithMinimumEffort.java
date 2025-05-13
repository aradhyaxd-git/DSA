/*1631. Path With Minimum Effort

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, 
 (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell. */


class Solution {
    private static final int[][] DIRECTIONS = {
          {-1, 0}, {1, 0}, {0, -1}, {0, 1}
      };
      public int minimumEffortPath(int[][] heights) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int m= heights.length;
        int n= heights[0].length;
        int[][]result= new int[m][n];
        for(int rows[]:result){
          Arrays.fill(rows,Integer.MAX_VALUE);
        }
        pq.offer(new int[]{0,0,0}); // absolute diff, (x-co ordinate, y co ordinate)
        result[0][0]=0;
  
  
        while(!pq.isEmpty()){
          int curr[]= pq.poll();
          int difference= curr[0];
          int x=curr[1],y=curr[2];
          if(x==m-1 && y==n-1) return difference;
  
          for(int dir[]:DIRECTIONS){
            int x_ = x+ dir[0];
            int y_ = y+dir[1];
            if(x_>=0 && x_<m && y_>=0 && y_<n){
              int absdiff= Math.abs(heights[x][y] - heights[x_][y_]);
              int maxDiff= Math.max(absdiff,difference);
              if(result[x_][y_]>maxDiff){
                result[x_][y_]=maxDiff;
                pq.offer(new int[]{maxDiff,x_,y_});
              }
            }
          }
        }
        return result[m-1][n-1];
      }
  }