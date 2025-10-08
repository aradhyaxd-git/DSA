/*417. Pacific Atlantic Water Flow

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches 
the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c]
 represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and 
west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell 
adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell 
(ri, ci) to both the Pacific and Atlantic oceans.

  */

class Solution {
    int directions[][]={{0,1},{1,0},{-1,0},{0,-1}};
    int m,n;
    public boolean isValid(int i, int j){
        return i>=0&& i<m && j>=0 && j<n;
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m= heights.length;
        n= heights[0].length;
        List<List<Integer>> ans= new ArrayList<>();
        boolean visited1[][]= solve(heights,0);  //0 for pacific
        boolean visited2[][]= solve(heights,1); //1 for atlantic


        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited1[i][j] && visited2[i][j]){
                    ans.add(Arrays.asList(i,j));
                }
            }
        }

        return ans;    
    }

    private boolean[][] solve(int heights[][], int type){

        boolean visited[][]= new boolean[m][n];
        Queue<int[]> queue= new LinkedList<>();

        if(type==0){ //pacific
            for(int i=0;i<m;i++){
                visited[i][0]=true;
                queue.offer(new int[]{i,0});   
            }
            for(int j=0;j<n;j++){
                visited[0][j]=true;
                queue.offer(new int[]{0,j});   
            }
        }
        else{
            for(int i=0;i<m;i++){
                visited[i][n-1]=true;
                queue.offer(new int[]{i,n-1});   
            }
            for(int j=0;j<n;j++){
                visited[m-1][j]=true;
                queue.offer(new int[]{m-1,j});   
            }
        }


        while(!queue.isEmpty()){

                int curr[]= queue.poll();
                int i=curr[0]; int j=curr[1];

                for(int dir[]:directions){
                    int i_= i+dir[0];
                    int j_= j+dir[1];

                    if(isValid(i_,j_) && !visited[i_][j_] && heights[i_][j_]>= heights[i][j]){
                        visited[i_][j_]=true;
                        queue.offer(new int[]{i_,j_});
                    }
                }
        }
        return visited;
    }
}